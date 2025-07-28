#!/bin/bash

echo "=== 生产环境数据备份 ==="

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

# 配置参数
BACKUP_DIR="backups"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
BACKUP_NAME="prod_backup_${TIMESTAMP}"

# 检查环境
check_environment() {
    print_step "检查备份环境..."
    
    # 检查MySQL客户端
    if ! command -v mysqldump &> /dev/null; then
        print_error "未找到mysqldump命令，请先安装MySQL客户端"
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

# 创建备份目录
create_backup_dir() {
    print_step "创建备份目录..."
    
    mkdir -p "$BACKUP_DIR"
    print_info "✅ 备份目录: $BACKUP_DIR"
}

# 备份数据库
backup_database() {
    print_step "备份数据库..."
    
    local backup_file="$BACKUP_DIR/${BACKUP_NAME}.sql"
    
    print_info "开始数据库备份..."
    print_info "  源数据库: $MYSQL_HOST:$MYSQL_PORT/$MYSQL_DATABASE"
    print_info "  备份文件: $backup_file"
    
    # 备份数据库
    mysqldump -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" \
        --single-transaction \
        --routines \
        --triggers \
        --complete-insert \
        --extended-insert \
        --lock-tables=false \
        --add-drop-database \
        --add-drop-table \
        "$MYSQL_DATABASE" > "$backup_file"
    
    if [ $? -eq 0 ]; then
        print_info "✅ 数据库备份完成"
        print_info "   备份文件大小: $(du -h "$backup_file" | cut -f1)"
        
        # 统计备份的表数量
        local table_count=$(grep -c "CREATE TABLE" "$backup_file" || echo "0")
        print_info "   备份表数量: $table_count"
        
        # 压缩备份文件
        print_info "压缩备份文件..."
        gzip "$backup_file"
        local compressed_file="${backup_file}.gz"
        
        if [ -f "$compressed_file" ]; then
            print_info "✅ 备份文件已压缩: $compressed_file"
            print_info "   压缩后大小: $(du -h "$compressed_file" | cut -f1)"
        fi
    else
        print_error "❌ 数据库备份失败"
        exit 1
    fi
}

# 备份配置文件
backup_configs() {
    print_step "备份配置文件..."
    
    local config_backup_dir="$BACKUP_DIR/configs_${TIMESTAMP}"
    mkdir -p "$config_backup_dir"
    
    # 备份生产配置
    if [ -f "iacc-notice-pro.yaml" ]; then
        cp "iacc-notice-pro.yaml" "$config_backup_dir/"
        print_info "✅ 备份生产配置: iacc-notice-pro.yaml"
    fi
    
    # 备份部署配置
    if [ -f "/opt/iacc-notice/config/application.yaml" ]; then
        sudo cp "/opt/iacc-notice/config/application.yaml" "$config_backup_dir/deployed-application.yaml" 2>/dev/null || print_warn "无法备份部署配置文件"
    fi
    
    # 备份数据库配置
    if [ -f "scripts/.prod-db-config" ]; then
        cp "scripts/.prod-db-config" "$config_backup_dir/"
        print_info "✅ 备份数据库配置"
    fi
    
    # 压缩配置文件
    if [ -d "$config_backup_dir" ]; then
        tar -czf "$config_backup_dir.tar.gz" -C "$BACKUP_DIR" "configs_${TIMESTAMP}"
        rm -rf "$config_backup_dir"
        print_info "✅ 配置文件已备份并压缩: configs_${TIMESTAMP}.tar.gz"
    fi
}

# 备份日志文件
backup_logs() {
    print_step "备份日志文件..."
    
    local log_backup_dir="$BACKUP_DIR/logs_${TIMESTAMP}"
    
    # 检查生产环境日志
    if [ -d "/var/log/iacc-notice" ]; then
        mkdir -p "$log_backup_dir"
        
        # 备份最近的日志文件 (最近7天)
        find /var/log/iacc-notice -name "*.log*" -mtime -7 -exec sudo cp {} "$log_backup_dir/" \; 2>/dev/null || print_warn "无法备份日志文件"
        
        if [ "$(ls -A "$log_backup_dir" 2>/dev/null)" ]; then
            # 压缩日志文件
            tar -czf "$log_backup_dir.tar.gz" -C "$BACKUP_DIR" "logs_${TIMESTAMP}"
            rm -rf "$log_backup_dir"
            print_info "✅ 日志文件已备份并压缩: logs_${TIMESTAMP}.tar.gz"
        else
            rm -rf "$log_backup_dir"
            print_warn "⚠️  没有找到可备份的日志文件"
        fi
    else
        print_warn "⚠️  生产环境日志目录不存在"
    fi
}

# 创建备份清单
create_backup_manifest() {
    print_step "创建备份清单..."
    
    local manifest_file="$BACKUP_DIR/backup_manifest_${TIMESTAMP}.txt"
    
    cat > "$manifest_file" <<EOF
# IACC Notice 生产环境备份清单
# 备份时间: $(date '+%Y-%m-%d %H:%M:%S')
# 备份版本: $BACKUP_NAME

## 数据库信息
数据库主机: $MYSQL_HOST:$MYSQL_PORT
数据库名称: $MYSQL_DATABASE
数据库用户: $MYSQL_USER
备份时间: $(date '+%Y-%m-%d %H:%M:%S')

## 备份文件
EOF
    
    # 列出所有备份文件
    echo "## 备份文件列表" >> "$manifest_file"
    ls -lh "$BACKUP_DIR"/*"$TIMESTAMP"* >> "$manifest_file" 2>/dev/null || true
    
    # 数据库表统计
    echo "" >> "$manifest_file"
    echo "## 数据库表统计" >> "$manifest_file"
    mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -e "
        SELECT 
            table_name AS '表名',
            table_rows AS '记录数',
            ROUND(((data_length + index_length) / 1024 / 1024), 2) AS '大小(MB)'
        FROM information_schema.tables 
        WHERE table_schema = '$MYSQL_DATABASE'
        ORDER BY table_rows DESC;
    " >> "$manifest_file" 2>/dev/null || true
    
    print_info "✅ 备份清单已创建: $manifest_file"
}

# 清理旧备份
cleanup_old_backups() {
    print_step "清理旧备份..."
    
    local keep_days=30
    print_info "保留最近 $keep_days 天的备份文件"
    
    # 清理旧的备份文件
    find "$BACKUP_DIR" -name "prod_backup_*.sql.gz" -mtime +$keep_days -delete 2>/dev/null || true
    find "$BACKUP_DIR" -name "configs_*.tar.gz" -mtime +$keep_days -delete 2>/dev/null || true
    find "$BACKUP_DIR" -name "logs_*.tar.gz" -mtime +$keep_days -delete 2>/dev/null || true
    find "$BACKUP_DIR" -name "backup_manifest_*.txt" -mtime +$keep_days -delete 2>/dev/null || true
    
    print_info "✅ 旧备份清理完成"
}

# 验证备份
verify_backup() {
    print_step "验证备份..."
    
    local backup_file="$BACKUP_DIR/${BACKUP_NAME}.sql.gz"
    
    if [ -f "$backup_file" ]; then
        # 检查压缩文件完整性
        if gzip -t "$backup_file" 2>/dev/null; then
            print_info "✅ 备份文件完整性验证通过"
        else
            print_error "❌ 备份文件损坏!"
            exit 1
        fi
        
        # 检查SQL文件内容
        if zcat "$backup_file" | head -20 | grep -q "MySQL dump"; then
            print_info "✅ 备份文件格式验证通过"
        else
            print_error "❌ 备份文件格式错误!"
            exit 1
        fi
        
        # 统计备份文件信息
        local file_size=$(du -h "$backup_file" | cut -f1)
        local line_count=$(zcat "$backup_file" | wc -l)
        print_info "✅ 备份文件统计:"
        print_info "   文件大小: $file_size"
        print_info "   SQL行数: $line_count"
    else
        print_error "❌ 备份文件不存在: $backup_file"
        exit 1
    fi
}

# 主函数
main() {
    print_info "开始生产环境数据备份..."
    print_info "备份名称: $BACKUP_NAME"
    print_info ""
    
    check_environment
    create_backup_dir
    backup_database
    backup_configs
    backup_logs
    create_backup_manifest
    verify_backup
    cleanup_old_backups
    
    print_info ""
    print_info "🎉 备份完成!"
    print_info ""
    print_info "📂 备份文件位置:"
    print_info "   备份目录: $BACKUP_DIR"
    print_info "   数据库备份: $BACKUP_DIR/${BACKUP_NAME}.sql.gz"
    print_info "   配置备份: $BACKUP_DIR/configs_${TIMESTAMP}.tar.gz"
    print_info "   日志备份: $BACKUP_DIR/logs_${TIMESTAMP}.tar.gz (如果存在)"
    print_info "   备份清单: $BACKUP_DIR/backup_manifest_${TIMESTAMP}.txt"
    print_info ""
    print_info "📖 恢复命令:"
    print_info "   ./scripts/restore-prod-data.sh $BACKUP_DIR/${BACKUP_NAME}.sql.gz"
    print_info ""
    print_info "⚠️  请妥善保管备份文件，建议定期异地备份"
}

# 处理中断信号
trap 'print_warn "\n⚠️  备份被中断"; exit 1' INT TERM

# 运行主函数
main "$@" 