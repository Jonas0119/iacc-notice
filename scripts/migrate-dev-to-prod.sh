#!/bin/bash

echo "=== 开发环境到生产环境数据迁移 ==="

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
DEV_DB_FILE="notice-provider/data/iacc-notice.db"
TEMP_DIR="/tmp/iacc-notice-migration"
BACKUP_DIR="backups/$(date +%Y%m%d_%H%M%S)"

# 检查环境
check_environment() {
    print_step "检查迁移环境..."
    
    # 检查开发环境数据库
    if [ ! -f "$DEV_DB_FILE" ]; then
        print_error "未找到开发环境数据库文件: $DEV_DB_FILE"
        exit 1
    fi
    
    # 检查SQLite
    if ! command -v sqlite3 &> /dev/null; then
        print_error "未找到sqlite3命令，请先安装SQLite"
        exit 1
    fi
    
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

# 创建备份目录
create_backup_dir() {
    print_step "创建备份目录..."
    mkdir -p "$BACKUP_DIR"
    mkdir -p "$TEMP_DIR"
    print_info "✅ 备份目录: $BACKUP_DIR"
}

# 备份生产环境数据
backup_production() {
    print_step "备份生产环境数据..."
    
    local backup_file="$BACKUP_DIR/prod_backup_$(date +%Y%m%d_%H%M%S).sql"
    
    print_info "创建生产环境数据备份..."
    mysqldump -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" \
        --single-transaction --routines --triggers "$MYSQL_DATABASE" > "$backup_file"
    
    if [ $? -eq 0 ]; then
        print_info "✅ 生产环境备份完成: $backup_file"
        print_info "   备份文件大小: $(du -h "$backup_file" | cut -f1)"
    else
        print_error "❌ 生产环境备份失败"
        exit 1
    fi
}

# 导出开发环境数据
export_dev_data() {
    print_step "导出开发环境数据..."
    
    # 获取所有表名
    local tables=$(sqlite3 "$DEV_DB_FILE" ".tables" | tr ' ' '\n' | grep -v '^$')
    
    print_info "开发环境表列表:"
    echo "$tables" | sed 's/^/  - /'
    
    # 为每个表导出数据
    for table in $tables; do
        print_info "导出表: $table"
        
        # 导出表结构和数据为INSERT语句
        sqlite3 "$DEV_DB_FILE" <<EOF > "$TEMP_DIR/${table}_data.sql"
.mode insert $table
SELECT * FROM $table;
EOF
        
        if [ -s "$TEMP_DIR/${table}_data.sql" ]; then
            local count=$(wc -l < "$TEMP_DIR/${table}_data.sql")
            print_info "  ✅ 导出 $count 条记录"
        else
            print_info "  ℹ️  表为空，跳过"
            rm -f "$TEMP_DIR/${table}_data.sql"
        fi
    done
    
    print_info "✅ 开发环境数据导出完成"
}

# 转换数据格式
convert_data_format() {
    print_step "转换数据格式 (SQLite -> MySQL)..."
    
    for sql_file in "$TEMP_DIR"/*_data.sql; do
        if [ -f "$sql_file" ]; then
            local table_name=$(basename "$sql_file" _data.sql)
            local mysql_file="$TEMP_DIR/${table_name}_mysql.sql"
            
            print_info "转换表: $table_name"
            
            # 转换SQLite INSERT语句为MySQL兼容格式
            sed -e "s/INSERT INTO $table_name VALUES/INSERT INTO \`$table_name\` VALUES/g" \
                -e "s/''/NULL/g" \
                -e "s/INSERT INTO \`$table_name\` VALUES();/-- Empty table/g" \
                "$sql_file" > "$mysql_file"
            
            # 添加MySQL特定设置
            {
                echo "-- Data for table \`$table_name\`"
                echo "SET FOREIGN_KEY_CHECKS = 0;"
                echo "TRUNCATE TABLE \`$table_name\`;"
                cat "$mysql_file"
                echo "SET FOREIGN_KEY_CHECKS = 1;"
                echo ""
            } > "$mysql_file.tmp" && mv "$mysql_file.tmp" "$mysql_file"
            
            rm -f "$sql_file"
        fi
    done
    
    print_info "✅ 数据格式转换完成"
}

# 导入数据到生产环境
import_to_production() {
    print_step "导入数据到生产环境..."
    
    print_warn "⚠️  即将清空生产环境数据并导入开发环境数据"
    read -p "确认继续? (yes/no): " confirm
    
    if [ "$confirm" != "yes" ]; then
        print_info "操作已取消"
        exit 0
    fi
    
    # 导入每个表的数据
    for mysql_file in "$TEMP_DIR"/*_mysql.sql; do
        if [ -f "$mysql_file" ]; then
            local table_name=$(basename "$mysql_file" _mysql.sql)
            
            print_info "导入表: $table_name"
            
            if mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" < "$mysql_file"; then
                # 统计导入的记录数
                local count=$(mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -se "SELECT COUNT(*) FROM \`$table_name\`")
                print_info "  ✅ 导入 $count 条记录"
            else
                print_error "  ❌ 导入失败: $table_name"
                exit 1
            fi
        fi
    done
    
    print_info "✅ 数据导入完成"
}

# 验证迁移结果
verify_migration() {
    print_step "验证迁移结果..."
    
    print_info "生产环境数据统计:"
    
    # 获取生产环境所有表
    local prod_tables=$(mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -se "SHOW TABLES")
    
    for table in $prod_tables; do
        local count=$(mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -se "SELECT COUNT(*) FROM \`$table\`")
        print_info "  - $table: $count 条记录"
    done
    
    print_info "✅ 迁移验证完成"
}

# 清理临时文件
cleanup() {
    print_step "清理临时文件..."
    
    if [ -d "$TEMP_DIR" ]; then
        rm -rf "$TEMP_DIR"
        print_info "✅ 清理完成"
    fi
}

# 主函数
main() {
    print_info "开始数据迁移: 开发环境 -> 生产环境"
    print_info "源数据库: SQLite ($DEV_DB_FILE)"
    print_info "目标数据库: MySQL ($MYSQL_HOST:$MYSQL_PORT/$MYSQL_DATABASE)"
    print_info ""
    
    check_environment
    create_backup_dir
    backup_production
    export_dev_data
    convert_data_format
    import_to_production
    verify_migration
    cleanup
    
    print_info ""
    print_info "🎉 数据迁移完成!"
    print_info ""
    print_info "📂 备份文件位置: $BACKUP_DIR"
    print_info ""
    print_info "📖 下一步操作:"
    print_info "   1. 验证生产环境数据: mysql -h$MYSQL_HOST -u$MYSQL_USER -p$MYSQL_PASSWORD $MYSQL_DATABASE"
    print_info "   2. 启动生产服务: sudo systemctl start iacc-notice"
    print_info "   3. 检查应用健康: curl http://localhost:8019/actuator/health"
    print_info ""
    print_info "⚠️  如需回滚，请使用备份文件恢复数据"
}

# 处理中断信号
trap 'print_warn "\n⚠️  迁移被中断，正在清理..."; cleanup; exit 1' INT TERM

# 运行主函数
main "$@" 