#!/bin/bash

echo "=== IACC Notice 服务状态 ==="

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

print_header() {
    echo -e "${BLUE}==================== $1 ====================${NC}"
}

# 检查开发环境状态
check_dev_status() {
    print_header "开发环境状态"
    
    # 检查进程
    local dev_pids=$(pgrep -f "spring-boot:run.*dev" 2>/dev/null)
    if [ -n "$dev_pids" ]; then
        print_ok "进程状态: 运行中 (PID: $dev_pids)"
        
        # 进程详细信息
        ps aux | grep -E "spring-boot:run.*dev" | grep -v grep | while IFS= read -r line; do
            local mem_usage=$(echo "$line" | awk '{print $4}')
            local cpu_usage=$(echo "$line" | awk '{print $3}')
            local start_time=$(echo "$line" | awk '{print $9}')
            print_info "  CPU: ${cpu_usage}%, 内存: ${mem_usage}%, 启动时间: $start_time"
        done
    else
        print_error "进程状态: 未运行"
    fi
    
    # 检查端口
    if lsof -i :8090 >/dev/null 2>&1; then
        print_ok "端口状态: 8090 监听中"
    else
        print_error "端口状态: 8090 未监听"
    fi
    
    # 检查HTTP健康
    if curl -s http://localhost:8090/actuator/health >/dev/null 2>&1; then
        local health=$(curl -s http://localhost:8090/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
        if [ "$health" = "UP" ]; then
            print_ok "健康状态: $health"
        else
            print_warn "健康状态: $health"
        fi
    else
        print_error "健康状态: HTTP请求失败"
    fi
    
    # 检查数据库
    if [ -f "notice-provider/data/iacc-notice.db" ]; then
        print_ok "数据库: SQLite文件存在"
    else
        print_error "数据库: SQLite文件不存在"
    fi
    
    echo ""
}

# 检查生产环境状态
check_prod_status() {
    print_header "生产环境状态"
    
    # 检查systemd服务
    if command -v systemctl &> /dev/null; then
        if systemctl is-active iacc-notice >/dev/null 2>&1; then
            print_ok "systemd服务: active"
            
            # 服务详细状态
            local service_info=$(systemctl show iacc-notice --property=ExecMainPID,ActiveState,SubState,LoadState)
            echo "$service_info" | while IFS= read -r line; do
                print_info "  $line"
            done
        else
            local service_state=$(systemctl is-active iacc-notice 2>/dev/null || echo "unknown")
            print_error "systemd服务: $service_state"
        fi
        
        if systemctl is-enabled iacc-notice >/dev/null 2>&1; then
            print_ok "开机自启: 已启用"
        else
            print_warn "开机自启: 未启用"
        fi
    else
        print_warn "systemctl不可用，跳过systemd检查"
    fi
    
    # 检查PID文件
    local pid_file="/var/run/iacc-notice.pid"
    if [ -f "$pid_file" ]; then
        local pid=$(cat "$pid_file")
        if kill -0 $pid 2>/dev/null; then
            print_ok "进程状态: 运行中 (PID: $pid)"
            
            # 进程详细信息
            if ps aux | grep -q "^[^ ]* *$pid "; then
                local process_info=$(ps aux | awk -v pid="$pid" '$2 == pid {print "CPU: " $3 "%, 内存: " $4 "%, 启动: " $9}')
                print_info "  $process_info"
            fi
        else
            print_error "进程状态: PID文件存在但进程未运行"
        fi
    else
        print_error "进程状态: PID文件不存在"
    fi
    
    # 检查端口
    if lsof -i :8019 >/dev/null 2>&1; then
        print_ok "端口状态: 8019 监听中"
    else
        print_error "端口状态: 8019 未监听"
    fi
    
    # 检查HTTP健康
    if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
        local health=$(curl -s http://localhost:8019/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
        if [ "$health" = "UP" ]; then
            print_ok "健康状态: $health"
        else
            print_warn "健康状态: $health"
        fi
    else
        print_error "健康状态: HTTP请求失败"
    fi
    
    # 检查数据库连接
    if [ -f "scripts/.prod-db-config" ]; then
        source scripts/.prod-db-config
        if command -v mysql &> /dev/null && [ -n "$MYSQL_PASSWORD" ]; then
            if mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "SELECT 1;" >/dev/null 2>&1; then
                print_ok "数据库: MySQL连接正常"
            else
                print_error "数据库: MySQL连接失败"
            fi
        else
            print_warn "数据库: 无法测试连接 (缺少mysql客户端或密码)"
        fi
    else
        print_warn "数据库: 无配置文件"
    fi
    
    # 检查日志文件
    if [ -f "/var/log/iacc-notice/app.log" ]; then
        local log_size=$(du -h /var/log/iacc-notice/app.log | cut -f1)
        print_ok "日志文件: 存在 ($log_size)"
        
        # 检查最近错误
        local recent_errors=$(tail -100 /var/log/iacc-notice/app.log | grep -c "ERROR" 2>/dev/null || echo "0")
        if [ "$recent_errors" -eq 0 ]; then
            print_ok "  最近100行无错误"
        else
            print_warn "  最近100行有 $recent_errors 个错误"
        fi
    else
        print_error "日志文件: 不存在"
    fi
    
    echo ""
}

# 检查系统资源
check_system_resources() {
    print_header "系统资源状态"
    
    # 内存使用
    local mem_info=$(free -m | grep "Mem:")
    local total_mem=$(echo $mem_info | awk '{print $2}')
    local used_mem=$(echo $mem_info | awk '{print $3}')
    local mem_percent=$((used_mem * 100 / total_mem))
    
    if [ "$mem_percent" -lt 80 ]; then
        print_ok "内存使用: ${mem_percent}% (${used_mem}MB/${total_mem}MB)"
    else
        print_warn "内存使用: ${mem_percent}% (${used_mem}MB/${total_mem}MB)"
    fi
    
    # 磁盘使用
    local disk_usage=$(df / | tail -1 | awk '{print $5}' | sed 's/%//')
    if [ "$disk_usage" -lt 85 ]; then
        print_ok "磁盘使用: ${disk_usage}%"
    else
        print_warn "磁盘使用: ${disk_usage}%"
    fi
    
    # 负载平均值
    local load_avg=$(uptime | awk -F'load average:' '{print $2}' | sed 's/^ *//')
    print_info "负载平均值: $load_avg"
    
    # CPU核心数
    local cpu_cores=$(nproc)
    print_info "CPU核心数: $cpu_cores"
    
    echo ""
}

# 检查网络连接
check_network() {
    print_header "网络连接状态"
    
    # 检查OSS连接
    if curl -I "https://noticefiles.oss-cn-beijing.aliyuncs.com/" >/dev/null 2>&1; then
        print_ok "阿里云OSS: 连接正常"
    else
        print_error "阿里云OSS: 连接失败"
    fi
    
    # 检查邮件服务器
    if command -v nc &> /dev/null; then
        if timeout 5 nc -z smtp.mxhichina.com 465 2>/dev/null; then
            print_ok "邮件服务器: 连接正常 (smtp.mxhichina.com:465)"
        else
            print_error "邮件服务器: 连接失败"
        fi
    else
        print_warn "邮件服务器: 无法检测 (缺少nc命令)"
    fi
    
    echo ""
}

# 显示快速操作命令
show_quick_commands() {
    print_header "快速操作命令"
    
    print_info "启动服务:"
    print_info "  开发环境: ./scripts/start-dev.sh"
    print_info "  生产环境: sudo systemctl start iacc-notice"
    
    print_info "停止服务:"
    print_info "  开发环境: ./scripts/stop.sh dev"
    print_info "  生产环境: sudo systemctl stop iacc-notice"
    
    print_info "查看日志:"
    print_info "  开发环境: tail -f logs/app.log"
    print_info "  生产环境: sudo tail -f /var/log/iacc-notice/app.log"
    
    print_info "健康检查:"
    print_info "  开发环境: curl http://localhost:8090/actuator/health"
    print_info "  生产环境: curl http://localhost:8019/actuator/health"
    
    print_info "完整健康检查: ./scripts/health-check.sh"
    
    echo ""
}

# 主函数
main() {
    local environment="$1"
    
    echo "检查时间: $(date '+%Y-%m-%d %H:%M:%S')"
    echo ""
    
    case "$environment" in
        "dev")
            check_dev_status
            ;;
        "prod")
            check_prod_status
            ;;
        "")
            check_dev_status
            check_prod_status
            check_system_resources
            check_network
            show_quick_commands
            ;;
        *)
            print_error "未知环境: $environment"
            print_info "用法: $0 [dev|prod]"
            print_info "  dev  - 只检查开发环境"
            print_info "  prod - 只检查生产环境"
            print_info "  无参数 - 检查所有环境和系统状态"
            exit 1
            ;;
    esac
    
    print_info "✅ 状态检查完成!"
}

# 运行主函数
main "$@" 