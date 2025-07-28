#!/bin/bash

echo "=== 停止 IACC Notice 服务 ==="

# 颜色定义
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
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

# 停止开发环境
stop_dev() {
    print_info "停止开发环境服务..."
    
    local dev_pids=$(pgrep -f "spring-boot:run.*dev" 2>/dev/null)
    
    if [ -n "$dev_pids" ]; then
        print_info "发现开发环境进程: $dev_pids"
        
        # 优雅停止
        kill $dev_pids 2>/dev/null
        
        # 等待进程停止
        local count=0
        while [ $count -lt 30 ]; do
            if ! pgrep -f "spring-boot:run.*dev" >/dev/null 2>&1; then
                print_info "✅ 开发环境服务已停止"
                return 0
            fi
            sleep 1
            count=$((count + 1))
        done
        
        # 强制停止
        print_warn "优雅停止超时，强制终止进程..."
        kill -9 $dev_pids 2>/dev/null
        
        if ! pgrep -f "spring-boot:run.*dev" >/dev/null 2>&1; then
            print_info "✅ 开发环境服务已强制停止"
        else
            print_error "❌ 无法停止开发环境服务"
            return 1
        fi
    else
        print_info "开发环境服务未运行"
    fi
}

# 停止生产环境
stop_prod() {
    print_info "停止生产环境服务..."
    
    # 使用systemctl停止
    if command -v systemctl &> /dev/null && systemctl is-active iacc-notice >/dev/null 2>&1; then
        print_info "使用systemctl停止生产服务..."
        if sudo systemctl stop iacc-notice; then
            print_info "✅ 生产环境服务已停止 (systemctl)"
            return 0
        else
            print_error "systemctl停止失败，尝试手动停止..."
        fi
    fi
    
    # 手动停止
    local pid_file="/var/run/iacc-notice.pid"
    
    if [ -f "$pid_file" ]; then
        local pid=$(cat "$pid_file")
        
        if kill -0 $pid 2>/dev/null; then
            print_info "发现生产环境进程: $pid"
            
            # 优雅停止
            kill $pid 2>/dev/null
            
            # 等待进程停止
            local count=0
            while [ $count -lt 30 ]; do
                if ! kill -0 $pid 2>/dev/null; then
                    print_info "✅ 生产环境服务已停止"
                    sudo rm -f "$pid_file"
                    return 0
                fi
                sleep 1
                count=$((count + 1))
            done
            
            # 强制停止
            print_warn "优雅停止超时，强制终止进程..."
            kill -9 $pid 2>/dev/null
            
            if ! kill -0 $pid 2>/dev/null; then
                print_info "✅ 生产环境服务已强制停止"
                sudo rm -f "$pid_file"
            else
                print_error "❌ 无法停止生产环境服务"
                return 1
            fi
        else
            print_warn "PID文件存在但进程未运行，清理PID文件"
            sudo rm -f "$pid_file"
        fi
    else
        print_info "生产环境服务未运行 (无PID文件)"
    fi
}

# 检查Java进程
check_java_processes() {
    print_info "检查残留的Java进程..."
    
    local java_processes=$(pgrep -f "iacc-notice|notice-provider" 2>/dev/null)
    
    if [ -n "$java_processes" ]; then
        print_warn "发现可能的残留进程:"
        ps aux | grep -E "(iacc-notice|notice-provider)" | grep -v grep | while IFS= read -r line; do
            print_warn "  $line"
        done
        
        read -p "是否强制终止这些进程? (y/N): " confirm
        case $confirm in
            [Yy]* )
                kill -9 $java_processes 2>/dev/null
                print_info "✅ 残留进程已清理"
                ;;
            * )
                print_info "保留残留进程"
                ;;
        esac
    else
        print_info "✅ 无残留Java进程"
    fi
}

# 检查端口占用
check_ports() {
    print_info "检查端口占用..."
    
    # 检查8090端口
    if lsof -i :8090 >/dev/null 2>&1; then
        local process=$(lsof -i :8090 | tail -1 | awk '{print $1, $2}')
        print_warn "端口8090仍被占用: $process"
    else
        print_info "✅ 端口8090已释放"
    fi
    
    # 检查8019端口
    if lsof -i :8019 >/dev/null 2>&1; then
        local process=$(lsof -i :8019 | tail -1 | awk '{print $1, $2}')
        print_warn "端口8019仍被占用: $process"
    else
        print_info "✅ 端口8019已释放"
    fi
}

# 主函数
main() {
    local environment="$1"
    
    case "$environment" in
        "dev")
            stop_dev
            ;;
        "prod")
            stop_prod
            ;;
        "all"|"")
            stop_dev
            stop_prod
            ;;
        *)
            print_error "未知环境: $environment"
            print_info "用法: $0 [dev|prod|all]"
            print_info "  dev  - 停止开发环境"
            print_info "  prod - 停止生产环境"
            print_info "  all  - 停止所有环境 (默认)"
            exit 1
            ;;
    esac
    
    check_java_processes
    check_ports
    
    print_info ""
    print_info "🛑 服务停止完成!"
    print_info ""
    print_info "📖 下一步操作:"
    print_info "   - 启动开发环境: ./scripts/start-dev.sh"
    print_info "   - 启动生产环境: sudo systemctl start iacc-notice"
    print_info "   - 查看服务状态: ./scripts/status.sh"
}

# 运行主函数
main "$@" 