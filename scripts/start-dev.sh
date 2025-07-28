#!/bin/bash

echo "=== 启动开发环境 ==="

# 设置错误时退出
set -e

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

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

# 检查端口是否被占用
check_port() {
    local port=8090
    if lsof -i :$port >/dev/null 2>&1; then
        print_warn "端口 $port 已被占用"
        local pid=$(lsof -ti :$port)
        print_warn "占用进程 PID: $pid"
        
        read -p "是否终止占用进程并继续? (y/N): " confirm
        case $confirm in
            [Yy]* )
                kill $pid 2>/dev/null || true
                sleep 2
                print_info "已终止占用进程"
                ;;
            * )
                print_error "端口被占用，启动取消"
                exit 1
                ;;
        esac
    fi
}

# 检查数据库
check_database() {
    local db_file="notice-provider/data/iacc-notice.db"
    
    if [ ! -f "$db_file" ]; then
        print_warn "未找到数据库文件: $db_file"
        print_info "正在初始化数据库..."
        
        if [ -f "scripts/init-dev-database.sh" ]; then
            chmod +x scripts/init-dev-database.sh
            ./scripts/init-dev-database.sh
        else
            print_error "未找到数据库初始化脚本"
            exit 1
        fi
    else
        print_info "✅ 数据库文件存在: $db_file"
    fi
}

# 检查配置文件
check_config() {
    local config_file="notice-provider/src/main/resources/application-dev.yml"
    
    if [ ! -f "$config_file" ]; then
        print_error "未找到配置文件: $config_file"
        exit 1
    fi
    
    print_info "✅ 配置文件存在: $config_file"
}

# 启动应用
start_application() {
    print_info "启动Spring Boot应用..."
    print_info "配置环境: dev"
    print_info "监听端口: 8090"
    print_info ""
    print_info "🔗 访问链接:"
    print_info "   - 健康检查: http://localhost:8090/actuator/health"
    print_info "   - Swagger UI: http://localhost:8090/swagger-ui.html"
    print_info "   - API文档: http://localhost:8090/v2/api-docs"
    print_info ""
    print_info "💡 提示: 使用 Ctrl+C 停止应用"
    print_info ""
    
    # 进入项目目录
    cd notice-provider
    
    # 设置Maven选项
    export MAVEN_OPTS="-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true"
    
    # 启动应用 (前台运行)
    mvn spring-boot:run \
        -Dspring.profiles.active=dev \
        -s ../maven-settings.xml \
        -Dspring.main.banner-mode=console
}

# 主函数
main() {
    print_info "准备启动开发环境..."
    
    check_port
    check_database
    check_config
    
    print_info "环境检查通过，正在启动应用..."
    print_info "=========================================="
    
    # 启动应用
    start_application
}

# 处理中断信号
trap 'print_info "\n👋 应用已停止"; exit 0' INT TERM

# 运行主函数
main "$@" 
