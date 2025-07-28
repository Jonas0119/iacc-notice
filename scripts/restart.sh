#!/bin/bash

echo "=== 重启 IACC Notice 服务 ==="

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

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

# 重启开发环境
restart_dev() {
    print_info "重启开发环境..."
    
    # 停止开发环境
    if ./scripts/stop.sh dev; then
        print_info "✅ 开发环境已停止"
    else
        print_error "❌ 停止开发环境失败"
        return 1
    fi
    
    # 等待端口释放
    sleep 3
    
    # 启动开发环境
    print_info "启动开发环境..."
    ./scripts/start-dev.sh &
    
    # 等待启动
    print_info "等待服务启动..."
    local count=0
    while [ $count -lt 60 ]; do
        if curl -s http://localhost:8090/actuator/health >/dev/null 2>&1; then
            print_info "✅ 开发环境重启成功!"
            return 0
        fi
        sleep 2
        count=$((count + 2))
        if [ $((count % 10)) -eq 0 ]; then
            print_info "  等待中... (${count}s)"
        fi
    done
    
    print_error "❌ 开发环境启动超时"
    return 1
}

# 重启生产环境
restart_prod() {
    print_info "重启生产环境..."
    
    # 检查systemctl是否可用
    if command -v systemctl &> /dev/null; then
        print_info "使用systemctl重启生产服务..."
        if sudo systemctl restart iacc-notice; then
            print_info "✅ 生产环境重启命令已执行"
        else
            print_error "❌ systemctl重启失败"
            return 1
        fi
        
        # 等待服务启动
        print_info "等待服务启动..."
        local count=0
        while [ $count -lt 60 ]; do
            if systemctl is-active iacc-notice >/dev/null 2>&1; then
                if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
                    print_info "✅ 生产环境重启成功!"
                    return 0
                fi
            fi
            sleep 2
            count=$((count + 2))
            if [ $((count % 10)) -eq 0 ]; then
                print_info "  等待中... (${count}s)"
            fi
        done
        
        print_error "❌ 生产环境启动超时"
        return 1
    else
        print_warn "systemctl不可用，使用手动方式重启..."
        
        # 手动停止和启动
        if ./scripts/stop.sh prod; then
            print_info "✅ 生产环境已停止"
        else
            print_error "❌ 停止生产环境失败"
            return 1
        fi
        
        sleep 5
        
        # 启动生产环境
        if [ -x "/opt/iacc-notice/bin/start.sh" ]; then
            print_info "启动生产环境..."
            sudo -u iacc /opt/iacc-notice/bin/start.sh
            
            # 等待启动
            local count=0
            while [ $count -lt 60 ]; do
                if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
                    print_info "✅ 生产环境重启成功!"
                    return 0
                fi
                sleep 2
                count=$((count + 2))
                if [ $((count % 10)) -eq 0 ]; then
                    print_info "  等待中... (${count}s)"
                fi
            done
            
            print_error "❌ 生产环境启动超时"
            return 1
        else
            print_error "❌ 未找到生产环境启动脚本"
            return 1
        fi
    fi
}

# 主函数
main() {
    local environment="$1"
    
    case "$environment" in
        "dev")
            restart_dev
            ;;
        "prod")
            restart_prod
            ;;
        "all")
            restart_dev
            echo ""
            restart_prod
            ;;
        "")
            print_error "请指定要重启的环境"
            print_info "用法: $0 [dev|prod|all]"
            print_info "  dev  - 重启开发环境"
            print_info "  prod - 重启生产环境"
            print_info "  all  - 重启所有环境"
            exit 1
            ;;
        *)
            print_error "未知环境: $environment"
            print_info "用法: $0 [dev|prod|all]"
            exit 1
            ;;
    esac
    
    if [ $? -eq 0 ]; then
        print_info ""
        print_info "🔄 重启完成!"
        print_info ""
        print_info "📖 验证命令:"
        if [ "$environment" = "dev" ] || [ "$environment" = "all" ]; then
            print_info "   开发环境: curl http://localhost:8090/actuator/health"
        fi
        if [ "$environment" = "prod" ] || [ "$environment" = "all" ]; then
            print_info "   生产环境: curl http://localhost:8019/actuator/health"
        fi
        print_info "   服务状态: ./scripts/status.sh"
    else
        print_error ""
        print_error "💥 重启失败!"
        print_error "请查看日志文件排查问题"
    fi
}

# 运行主函数
main "$@" 