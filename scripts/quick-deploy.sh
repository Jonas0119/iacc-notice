#!/bin/bash

echo "=== IACC Notice 快速部署验证 ==="

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

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

print_step() {
    echo -e "${BLUE}[STEP]${NC} $1"
}

# 快速验证开发环境
quick_verify_dev() {
    print_step "快速验证开发环境"
    
    local all_passed=true
    
    # 检查项目文件
    if [ -f "notice-provider/pom.xml" ]; then
        print_ok "项目文件存在"
    else
        print_error "项目文件缺失"
        all_passed=false
    fi
    
    # 检查配置文件
    if [ -f "notice-provider/src/main/resources/application-dev.yml" ]; then
        print_ok "开发配置文件存在"
        
        # 检查关键配置
        if grep -q "oss-cn-beijing.aliyuncs.com" "notice-provider/src/main/resources/application-dev.yml"; then
            print_ok "OSS配置正确"
        else
            print_warn "OSS配置可能不正确"
        fi
        
        if grep -q "smtp.mxhichina.com" "notice-provider/src/main/resources/application-dev.yml"; then
            print_ok "邮件配置正确"
        else
            print_warn "邮件配置可能不正确"
        fi
    else
        print_error "开发配置文件缺失"
        all_passed=false
    fi
    
    # 检查数据库
    if [ -f "notice-provider/data/iacc-notice.db" ]; then
        print_ok "SQLite数据库存在"
    else
        print_warn "SQLite数据库不存在，可能需要初始化"
    fi
    
    # 检查Java和Maven
    if command -v java &> /dev/null; then
        print_ok "Java环境可用"
    else
        print_error "Java环境不可用"
        all_passed=false
    fi
    
    if command -v mvn &> /dev/null; then
        print_ok "Maven环境可用"
    else
        print_error "Maven环境不可用"
        all_passed=false
    fi
    
    echo ""
    if [ "$all_passed" = true ]; then
        print_ok "开发环境验证通过"
        return 0
    else
        print_error "开发环境验证失败"
        return 1
    fi
}

# 快速验证生产环境
quick_verify_prod() {
    print_step "快速验证生产环境"
    
    local all_passed=true
    
    # 检查配置文件
    if [ -f "iacc-notice-pro.yaml" ]; then
        print_ok "生产配置文件存在"
    else
        print_error "生产配置文件缺失"
        all_passed=false
    fi
    
    # 检查数据库配置
    if [ -f "scripts/.prod-db-config" ]; then
        print_ok "数据库配置文件存在"
    else
        print_warn "数据库配置文件不存在，可能需要初始化"
    fi
    
    # 检查部署目录
    if [ -d "/opt/iacc-notice" ]; then
        print_ok "生产部署目录存在"
        
        if [ -f "/opt/iacc-notice/lib/notice-provider-1.0-SNAPSHOT.jar" ]; then
            print_ok "应用JAR文件存在"
        else
            print_warn "应用JAR文件不存在"
        fi
        
        if [ -f "/opt/iacc-notice/config/application.yaml" ]; then
            print_ok "部署配置文件存在"
        else
            print_warn "部署配置文件不存在"
        fi
    else
        print_warn "生产部署目录不存在"
    fi
    
    # 检查systemd服务
    if [ -f "/etc/systemd/system/iacc-notice.service" ]; then
        print_ok "systemd服务文件存在"
    else
        print_warn "systemd服务文件不存在"
    fi
    
    # 检查MySQL客户端
    if command -v mysql &> /dev/null; then
        print_ok "MySQL客户端可用"
    else
        print_warn "MySQL客户端不可用"
    fi
    
    echo ""
    if [ "$all_passed" = true ]; then
        print_ok "生产环境验证通过"
        return 0
    else
        print_error "生产环境验证失败"
        return 1
    fi
}

# 快速部署开发环境
quick_deploy_dev() {
    print_step "快速部署开发环境"
    
    print_info "开始快速部署..."
    
    # 运行部署脚本
    if ./scripts/deploy-dev.sh; then
        print_ok "开发环境部署完成"
        
        # 验证部署结果
        sleep 5
        if curl -s http://localhost:8090/actuator/health >/dev/null 2>&1; then
            print_ok "开发环境服务正常运行"
            return 0
        else
            print_warn "开发环境服务可能还在启动中"
            return 1
        fi
    else
        print_error "开发环境部署失败"
        return 1
    fi
}

# 快速部署生产环境
quick_deploy_prod() {
    print_step "快速部署生产环境"
    
    print_info "开始快速部署..."
    
    # 检查权限
    if [ "$EUID" -eq 0 ]; then
        print_info "以root用户运行"
    else
        print_info "需要sudo权限"
    fi
    
    # 运行部署脚本
    if ./scripts/deploy-prod.sh; then
        print_ok "生产环境部署完成"
        
        # 验证部署结果
        sleep 10
        if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
            print_ok "生产环境服务正常运行"
            return 0
        else
            print_warn "生产环境服务可能还在启动中"
            return 1
        fi
    else
        print_error "生产环境部署失败"
        return 1
    fi
}

# 显示部署状态摘要
show_deployment_summary() {
    print_step "部署状态摘要"
    
    echo ""
    echo "==================== 服务状态 ===================="
    
    # 开发环境状态
    if pgrep -f "spring-boot:run.*dev" > /dev/null; then
        print_ok "开发环境: 运行中"
        if curl -s http://localhost:8090/actuator/health >/dev/null 2>&1; then
            local health=$(curl -s http://localhost:8090/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
            print_info "  健康状态: $health"
            print_info "  访问地址: http://localhost:8090/swagger-ui.html"
        fi
    else
        print_warn "开发环境: 未运行"
    fi
    
    # 生产环境状态
    if [ -f "/var/run/iacc-notice.pid" ] && kill -0 $(cat /var/run/iacc-notice.pid) 2>/dev/null; then
        print_ok "生产环境: 运行中"
        if curl -s http://localhost:8019/actuator/health >/dev/null 2>&1; then
            local health=$(curl -s http://localhost:8019/actuator/health | grep -o '"status":"[^"]*"' | cut -d'"' -f4)
            print_info "  健康状态: $health"
            print_info "  访问地址: http://localhost:8019/swagger-ui.html"
        fi
    else
        print_warn "生产环境: 未运行"
    fi
    
    echo ""
    echo "==================== 快速命令 ===================="
    print_info "启动开发环境: ./scripts/start-dev.sh"
    print_info "启动生产环境: sudo systemctl start iacc-notice"
    print_info "查看服务状态: ./scripts/status.sh"
    print_info "健康检查: ./scripts/health-check.sh"
    print_info "停止所有服务: ./scripts/stop.sh all"
    
    echo ""
}

# 主菜单
show_menu() {
    echo ""
    echo "==================== 快速部署菜单 ===================="
    echo "1. 验证开发环境"
    echo "2. 验证生产环境"
    echo "3. 部署开发环境"
    echo "4. 部署生产环境"
    echo "5. 显示状态摘要"
    echo "6. 完整健康检查"
    echo "0. 退出"
    echo ""
    read -p "请选择操作 (0-6): " choice
    
    case $choice in
        1)
            quick_verify_dev
            ;;
        2)
            quick_verify_prod
            ;;
        3)
            quick_deploy_dev
            ;;
        4)
            quick_deploy_prod
            ;;
        5)
            show_deployment_summary
            ;;
        6)
            ./scripts/health-check.sh
            ;;
        0)
            print_info "退出快速部署工具"
            exit 0
            ;;
        *)
            print_error "无效选择"
            ;;
    esac
}

# 主函数
main() {
    local action="$1"
    
    if [ -n "$action" ]; then
        # 命令行模式
        case "$action" in
            "verify-dev")
                quick_verify_dev
                ;;
            "verify-prod")
                quick_verify_prod
                ;;
            "deploy-dev")
                quick_deploy_dev
                ;;
            "deploy-prod")
                quick_deploy_prod
                ;;
            "status")
                show_deployment_summary
                ;;
            "help"|"-h"|"--help")
                echo "用法: $0 [action]"
                echo ""
                echo "Actions:"
                echo "  verify-dev   - 验证开发环境"
                echo "  verify-prod  - 验证生产环境"
                echo "  deploy-dev   - 部署开发环境"
                echo "  deploy-prod  - 部署生产环境"
                echo "  status       - 显示状态摘要"
                echo "  help         - 显示帮助"
                echo ""
                echo "不带参数运行将进入交互式菜单模式"
                ;;
            *)
                print_error "未知操作: $action"
                print_info "运行 $0 help 查看可用操作"
                exit 1
                ;;
        esac
    else
        # 交互式菜单模式
        while true; do
            show_menu
            echo ""
            read -p "按Enter继续..."
        done
    fi
}

# 运行主函数
main "$@" 