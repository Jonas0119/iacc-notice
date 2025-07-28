#!/bin/bash

echo "=== IACC Notice 系统健康检查 ==="

# 颜色定义
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m'

print_ok() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_warn() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

print_info() {
    echo -e "${BLUE}ℹ️  $1${NC}"
}

# 检查应用进程
check_application_process() {
    echo "==================== 应用进程检查 ===================="
    
    # 检查开发环境进程
    if pgrep -f "spring-boot:run.*dev" > /dev/null; then
        local dev_pid=$(pgrep -f "spring-boot:run.*dev")
        print_ok "开发环境进程运行中 (PID: $dev_pid)"
    else
        print_warn "开发环境进程未运行"
    fi
    
    # 检查生产环境进程
    if [ -f "/var/run/iacc-notice.pid" ]; then
        local prod_pid=$(cat /var/run/iacc-notice.pid)
        if kill -0 $prod_pid 2>/dev/null; then
            print_ok "生产环境进程运行中 (PID: $prod_pid)"
        else
            print_error "生产环境PID文件存在但进程未运行"
        fi
    else
        print_warn "生产环境进程未运行或PID文件不存在"
    fi
    
    echo ""
}

# 检查端口监听
check_ports() {
    echo "==================== 端口监听检查 ===================="
    
    # 检查开发环境端口 8090
    if lsof -i :8090 >/dev/null 2>&1; then
        local dev_process=$(lsof -i :8090 | tail -1 | awk '{print $1, $2}')
        print_ok "开发环境端口 8090 监听中 ($dev_process)"
    else
        print_warn "开发环境端口 8090 未监听"
    fi
    
    # 检查生产环境端口 8019
    if lsof -i :8019 >/dev/null 2>&1; then
        local prod_process=$(lsof -i :8019 | tail -1 | awk '{print $1, $2}')
        print_ok "生产环境端口 8019 监听中 ($prod_process)"
    else
        print_warn "生产环境端口 8019 未监听"
    fi
    
    echo ""
}

# 检查HTTP健康状态
check_http_health() {
    echo "==================== HTTP健康检查 ===================="
    
    # 检查开发环境
    if curl -s http://localhost:8090/actuator/health >/dev/null 2>&1; then
        local health_status=$(curl -s http://localhost:8090/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
        if [ "$health_status" = "UP" ]; then
            print_ok "开发环境健康状态: $health_status"
        else
            print_warn "开发环境健康状态: $health_status"
        fi
        
        # 详细组件状态
        echo "  开发环境组件状态:"
        curl -s http://localhost:8090/actuator/health | jq -r '.components | to_entries[] | "    " + .key + ": " + .value.status' 2>/dev/null || print_info "    无法解析详细状态 (需要jq工具)"
    else
        print_error "开发环境HTTP健康检查失败"
    fi
    
    # 检查生产环境
    if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
        local health_status=$(curl -s http://localhost:8019/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
        if [ "$health_status" = "UP" ]; then
            print_ok "生产环境健康状态: $health_status"
        else
            print_warn "生产环境健康状态: $health_status"
        fi
        
        # 详细组件状态
        echo "  生产环境组件状态:"
        curl -s http://localhost:8019/actuator/health | jq -r '.components | to_entries[] | "    " + .key + ": " + .value.status' 2>/dev/null || print_info "    无法解析详细状态 (需要jq工具)"
    else
        print_error "生产环境HTTP健康检查失败"
    fi
    
    echo ""
}

# 检查数据库连接
check_database() {
    echo "==================== 数据库连接检查 ===================="
    
    # 检查开发环境SQLite
    local dev_db="notice-provider/data/iacc-notice.db"
    if [ -f "$dev_db" ]; then
        if command -v sqlite3 &> /dev/null; then
            if sqlite3 "$dev_db" "SELECT 1;" >/dev/null 2>&1; then
                local table_count=$(sqlite3 "$dev_db" ".tables" | wc -w)
                print_ok "开发环境SQLite数据库连接正常 ($table_count 个表)"
            else
                print_error "开发环境SQLite数据库连接失败"
            fi
        else
            print_warn "未安装sqlite3，跳过SQLite检查"
        fi
    else
        print_warn "开发环境数据库文件不存在: $dev_db"
    fi
    
    # 检查生产环境MySQL
    if [ -f "scripts/.prod-db-config" ]; then
        source scripts/.prod-db-config
        
        if command -v mysql &> /dev/null; then
            if [ -n "$MYSQL_PASSWORD" ]; then
                if mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "SELECT 1;" >/dev/null 2>&1; then
                    local table_count=$(mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -se "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='$MYSQL_DATABASE'")
                    print_ok "生产环境MySQL数据库连接正常 ($table_count 个表)"
                else
                    print_error "生产环境MySQL数据库连接失败"
                fi
            else
                print_warn "未设置MYSQL_PASSWORD环境变量，跳过MySQL检查"
            fi
        else
            print_warn "未安装mysql客户端，跳过MySQL检查"
        fi
    else
        print_warn "未找到生产环境数据库配置"
    fi
    
    echo ""
}

# 检查磁盘空间
check_disk_space() {
    echo "==================== 磁盘空间检查 ===================="
    
    # 检查根分区
    local root_usage=$(df / | tail -1 | awk '{print $5}' | sed 's/%//')
    if [ "$root_usage" -lt 80 ]; then
        print_ok "根分区磁盘使用率: ${root_usage}%"
    elif [ "$root_usage" -lt 90 ]; then
        print_warn "根分区磁盘使用率: ${root_usage}% (警告)"
    else
        print_error "根分区磁盘使用率: ${root_usage}% (危险)"
    fi
    
    # 检查日志目录
    if [ -d "/var/log/iacc-notice" ]; then
        local log_size=$(du -sh /var/log/iacc-notice 2>/dev/null | cut -f1)
        print_info "生产环境日志目录大小: $log_size"
    fi
    
    # 检查应用目录
    if [ -d "/opt/iacc-notice" ]; then
        local app_size=$(du -sh /opt/iacc-notice 2>/dev/null | cut -f1)
        print_info "生产环境应用目录大小: $app_size"
    fi
    
    echo ""
}

# 检查内存使用
check_memory() {
    echo "==================== 内存使用检查 ===================="
    
    # 系统内存
    local mem_info=$(free -m | grep "Mem:")
    local total_mem=$(echo $mem_info | awk '{print $2}')
    local used_mem=$(echo $mem_info | awk '{print $3}')
    local mem_percent=$((used_mem * 100 / total_mem))
    
    if [ "$mem_percent" -lt 80 ]; then
        print_ok "系统内存使用率: ${mem_percent}% (${used_mem}MB/${total_mem}MB)"
    elif [ "$mem_percent" -lt 90 ]; then
        print_warn "系统内存使用率: ${mem_percent}% (${used_mem}MB/${total_mem}MB)"
    else
        print_error "系统内存使用率: ${mem_percent}% (${used_mem}MB/${total_mem}MB)"
    fi
    
    # Java进程内存
    if command -v ps &> /dev/null; then
        local java_processes=$(ps aux | grep java | grep -v grep)
        if [ -n "$java_processes" ]; then
            echo "  Java进程内存使用:"
            echo "$java_processes" | while IFS= read -r line; do
                local mem_usage=$(echo "$line" | awk '{print $4}')
                local command=$(echo "$line" | awk '{print $11}' | cut -d'/' -f5)
                print_info "    $command: ${mem_usage}%"
            done
        fi
    fi
    
    echo ""
}

# 检查日志文件
check_logs() {
    echo "==================== 日志文件检查 ===================="
    
    # 检查开发环境日志
    if [ -f "logs/app.log" ]; then
        local dev_log_size=$(du -h logs/app.log | cut -f1)
        local dev_log_lines=$(wc -l < logs/app.log)
        print_info "开发环境日志: $dev_log_size ($dev_log_lines 行)"
        
        # 检查最近的错误
        local recent_errors=$(tail -1000 logs/app.log | grep -c "ERROR" || echo "0")
        if [ "$recent_errors" -eq 0 ]; then
            print_ok "开发环境最近1000行无错误"
        else
            print_warn "开发环境最近1000行有 $recent_errors 个错误"
        fi
    else
        print_warn "开发环境日志文件不存在: logs/app.log"
    fi
    
    # 检查生产环境日志
    if [ -f "/var/log/iacc-notice/app.log" ]; then
        local prod_log_size=$(du -h /var/log/iacc-notice/app.log | cut -f1)
        local prod_log_lines=$(wc -l < /var/log/iacc-notice/app.log)
        print_info "生产环境日志: $prod_log_size ($prod_log_lines 行)"
        
        # 检查最近的错误
        local recent_errors=$(tail -1000 /var/log/iacc-notice/app.log | grep -c "ERROR" || echo "0")
        if [ "$recent_errors" -eq 0 ]; then
            print_ok "生产环境最近1000行无错误"
        else
            print_warn "生产环境最近1000行有 $recent_errors 个错误"
        fi
    else
        print_warn "生产环境日志文件不存在"
    fi
    
    echo ""
}

# 检查外部服务
check_external_services() {
    echo "==================== 外部服务检查 ===================="
    
    # 检查OSS连接
    if curl -I "https://noticefiles.oss-cn-beijing.aliyuncs.com/" >/dev/null 2>&1; then
        print_ok "阿里云OSS连接正常"
    else
        print_error "阿里云OSS连接失败"
    fi
    
    # 检查邮件服务器
    if command -v nc &> /dev/null; then
        if nc -z smtp.mxhichina.com 465 2>/dev/null; then
            print_ok "邮件服务器连接正常 (smtp.mxhichina.com:465)"
        else
            print_error "邮件服务器连接失败"
        fi
    else
        print_warn "未安装nc命令，跳过邮件服务器检查"
    fi
    
    echo ""
}

# 生成健康报告总结
generate_summary() {
    echo "==================== 健康检查总结 ===================="
    
    local timestamp=$(date '+%Y-%m-%d %H:%M:%S')
    echo "检查时间: $timestamp"
    echo ""
    
    # 统计各类状态
    local total_checks=0
    local passed_checks=0
    local warning_checks=0
    local failed_checks=0
    
    # 这里可以添加更详细的统计逻辑
    
    print_info "建议定期运行此脚本进行系统监控"
    print_info "如发现问题，请查看详细日志并联系系统管理员"
    
    echo ""
}

# 主函数
main() {
    echo "开始系统健康检查..."
    echo "检查时间: $(date '+%Y-%m-%d %H:%M:%S')"
    echo ""
    
    check_application_process
    check_ports
    check_http_health
    check_database
    check_disk_space
    check_memory
    check_logs
    check_external_services
    generate_summary
    
    echo "✅ 健康检查完成!"
}

# 运行主函数
main "$@" 