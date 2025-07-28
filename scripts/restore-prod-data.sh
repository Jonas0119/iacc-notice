#!/bin/bash

echo "=== 生产环境数据恢复 ==="

# 设置错误时退出
set -e

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

# 颜色定义
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m'

print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_step() {
    echo -e "${BLUE}[STEP]${NC} $1"
}

# 使用说明
usage() {
    echo "用法: $0 <backup_file>"
    echo ""
    echo "参数:"
    echo "  backup_file  备份文件路径 (.sql 或 .sql.gz 格式)"
    echo ""
    echo "示例:"
    echo "  $0 backups/prod_backup_20240724_141530.sql.gz"
    echo "  $0 backups/prod_backup_20240724_141530.sql"
    echo ""
    echo "注意: 此操作将完全替换现有数据，请确保已做好备份！"
}

# 检查参数
if [ $# -eq 0 ]; then
    print_error "缺少备份文件参数"
    usage
    exit 1
fi

BACKUP_FILE="$1"

# 检查环境
check_environment() {
    print_step "检查恢复环境..."
    
    # 检查备份文件
    if [ ! -f "$BACKUP_FILE" ]; then
        print_error "备份文件不存在: $BACKUP_FILE"
        exit 1
    fi
    
    # 检查文件格式
    case "$BACKUP_FILE" in
        *.sql.gz)
            BACKUP_TYPE="compressed"
            print_info "✅ 检测到压缩备份文件"
            
            # 检查gzip完整性
            if ! gzip -t "$BACKUP_FILE" 2>/dev/null; then
                print_error "❌ 备份文件损坏或格式错误"
                exit 1
            fi
            ;;
        *.sql)
            BACKUP_TYPE="uncompressed"
            print_info "✅ 检测到SQL备份文件"
            ;;
        *)
            print_error "不支持的备份文件格式: $BACKUP_FILE"
            print_error "支持的格式: .sql, .sql.gz"
            exit 1
            ;;
    esac
    
    # 检查MySQL客户端
    if ! command -v mysql &> /dev/null; then
        print_error "未找到mysql客户端，请先安装MySQL客户端"
        exit 1
    fi
    
    # 加载生产环境数据库配置
    if [ -f "scripts/.prod-db-config" ]; then
        source scripts/.prod-db-config
        print_info "✅ 加载生产环境配置: $MYSQL_HOST:$MYSQL_PORT/$MYSQL_DATABASE"
    else
        print_error "❌ 未找到生产环境数据库配置，请先运行 init-prod-database.sh"
        exit 1
    fi
    
    # 检查MySQL连接
    if [ -z "$MYSQL_PASSWORD" ]; then
        echo "请输入生产环境MySQL密码:"
        read -s MYSQL_PASSWORD
        echo ""
    fi
    
    if ! mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "SELECT 1;" >/dev/null 2>&1; then
        print_error "❌ 无法连接到生产环境数据库"
        exit 1
    fi
    
    print_info "✅ 环境检查通过"
}

# 分析备份文件
analyze_backup() {
    print_step "分析备份文件..."
    
    local file_size=$(du -h "$BACKUP_FILE" | cut -f1)
    print_info "备份文件大小: $file_size"
    
    # 获取备份文件内容预览
    case "$BACKUP_TYPE" in
        "compressed")
            local header=$(zcat "$BACKUP_FILE" | head -20)
            ;;
        "uncompressed")
            local header=$(head -20 "$BACKUP_FILE")
            ;;
    esac
    
    # 检查是否是MySQL dump文件
    if echo "$header" | grep -q "MySQL dump"; then
        print_info "✅ 确认为MySQL备份文件"
        
        # 提取备份信息
        local mysql_version=$(echo "$header" | grep "MySQL dump" | head -1)
        print_info "   $mysql_version"
        
        # 提取备份的数据库名
        local db_name=$(echo "$header" | grep "Current Database:" | sed 's/.*Current Database: `\([^`]*\)`.*/\1/' | head -1)
        if [ -n "$db_name" ]; then
            print_info "   源数据库: $db_name"
            
            if [ "$db_name" != "$MYSQL_DATABASE" ]; then
                print_warn "⚠️  源数据库名称与目标不一致"
                print_warn "   源: $db_name, 目标: $MYSQL_DATABASE"
            fi
        fi
    else
        print_error "❌ 不是有效的MySQL备份文件"
        exit 1
    fi
}

# 备份当前数据
backup_current_data() {
    print_step "备份当前数据..."
    
    local current_backup_dir="restore_backups"
    local timestamp=$(date +%Y%m%d_%H%M%S)
    local current_backup_file="$current_backup_dir/before_restore_${timestamp}.sql"
    
    mkdir -p "$current_backup_dir"
    
    print_info "创建恢复前备份: $current_backup_file"
    
    mysqldump -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" \
        --single-transaction --routines --triggers "$MYSQL_DATABASE" > "$current_backup_file"
    
    if [ $? -eq 0 ]; then
        # 压缩备份
        gzip "$current_backup_file"
        print_info "✅ 当前数据已备份: ${current_backup_file}.gz"
        print_info "   如需回滚: ./scripts/restore-prod-data.sh ${current_backup_file}.gz"
    else
        print_error "❌ 当前数据备份失败"
        exit 1
    fi
}

# 停止应用服务
stop_application() {
    print_step "停止应用服务..."
    
    # 检查服务是否运行
    if command -v systemctl &> /dev/null && systemctl is-active iacc-notice >/dev/null 2>&1; then
        print_info "停止systemd服务..."
        if sudo systemctl stop iacc-notice; then
            print_info "✅ 应用服务已停止"
        else
            print_warn "⚠️  停止服务失败，继续执行恢复"
        fi
    else
        print_info "应用服务未运行或不是systemd管理"
    fi
}

# 恢复数据
restore_data() {
    print_step "恢复数据..."
    
    print_warn "⚠️  即将完全替换数据库内容!"
    print_warn "   目标数据库: $MYSQL_HOST:$MYSQL_PORT/$MYSQL_DATABASE"
    print_warn "   备份文件: $BACKUP_FILE"
    print_warn ""
    read -p "确认继续恢复? 输入 'YES' 确认: " confirm
    
    if [ "$confirm" != "YES" ]; then
        print_info "恢复操作已取消"
        exit 0
    fi
    
    print_info "开始恢复数据..."
    
    # 根据备份类型选择恢复方式
    case "$BACKUP_TYPE" in
        "compressed")
            print_info "从压缩文件恢复..."
            zcat "$BACKUP_FILE" | mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE"
            ;;
        "uncompressed")
            print_info "从SQL文件恢复..."
            mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" < "$BACKUP_FILE"
            ;;
    esac
    
    if [ $? -eq 0 ]; then
        print_info "✅ 数据恢复完成"
    else
        print_error "❌ 数据恢复失败"
        exit 1
    fi
}

# 验证恢复结果
verify_restore() {
    print_step "验证恢复结果..."
    
    # 检查数据库连接
    if mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "SELECT 1;" >/dev/null 2>&1; then
        print_info "✅ 数据库连接正常"
    else
        print_error "❌ 数据库连接失败"
        exit 1
    fi
    
    # 统计表和记录数
    print_info "数据库统计:"
    mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -e "
        SELECT 
            COUNT(*) AS '总表数'
        FROM information_schema.tables 
        WHERE table_schema = '$MYSQL_DATABASE';
    " 2>/dev/null | tail -1 | while read table_count; do
        print_info "  总表数: $table_count"
    done
    
    # 显示各表记录数
    print_info "各表记录统计:"
    mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -e "
        SELECT 
            table_name AS '表名',
            table_rows AS '记录数'
        FROM information_schema.tables 
        WHERE table_schema = '$MYSQL_DATABASE'
        ORDER BY table_rows DESC;
    " 2>/dev/null | tail -n +2 | while IFS=$'\t' read table_name row_count; do
        print_info "  $table_name: $row_count 条记录"
    done
}

# 启动应用服务
start_application() {
    print_step "启动应用服务..."
    
    if command -v systemctl &> /dev/null; then
        print_info "启动systemd服务..."
        if sudo systemctl start iacc-notice; then
            print_info "✅ 应用服务启动命令已执行"
            
            # 等待服务启动
            print_info "等待服务启动..."
            local count=0
            while [ $count -lt 60 ]; do
                if systemctl is-active iacc-notice >/dev/null 2>&1; then
                    if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
                        print_info "✅ 应用服务启动成功"
                        return 0
                    fi
                fi
                sleep 2
                count=$((count + 2))
                if [ $((count % 10)) -eq 0 ]; then
                    print_info "  等待中... (${count}s)"
                fi
            done
            
            print_warn "⚠️  应用服务启动超时，请手动检查"
        else
            print_warn "⚠️  应用服务启动失败，请手动启动"
        fi
    else
        print_warn "⚠️  请手动启动应用服务"
    fi
}

# 主函数
main() {
    print_info "开始生产环境数据恢复..."
    print_info "备份文件: $BACKUP_FILE"
    print_info ""
    
    check_environment
    analyze_backup
    backup_current_data
    stop_application
    restore_data
    verify_restore
    start_application
    
    print_info ""
    print_info "🎉 数据恢复完成!"
    print_info ""
    print_info "📖 验证命令:"
    print_info "   数据库连接: mysql -h$MYSQL_HOST -u$MYSQL_USER -p$MYSQL_PASSWORD $MYSQL_DATABASE"
    print_info "   应用健康: curl http://localhost:8019/actuator/health"
    print_info "   服务状态: ./scripts/status.sh prod"
    print_info ""
    print_info "⚠️  如有问题，可使用恢复前备份回滚"
}

# 处理中断信号
trap 'print_warn "\n⚠️  恢复被中断"; exit 1' INT TERM

# 检查参数
if [ "$1" = "-h" ] || [ "$1" = "--help" ]; then
    usage
    exit 0
fi

# 运行主函数
main "$@" 