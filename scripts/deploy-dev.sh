#!/bin/bash

echo "=== 开发环境部署脚本 ==="

# 设置错误时退出
set -e

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 打印带颜色的消息
print_info() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warn() {
    echo -e "${YELLOW}[WARN]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# 检查环境依赖
check_dependencies() {
    print_info "检查环境依赖..."
    
    # 检查Java
    if ! command -v java &> /dev/null; then
        print_error "未找到Java环境，请安装JDK 8或更高版本"
        exit 1
    fi
    
    local java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    print_info "Java版本: $java_version"
    
    # 检查Maven
    if ! command -v mvn &> /dev/null; then
        print_error "未找到Maven环境，请安装Maven 3.6或更高版本"
        exit 1
    fi
    
    local maven_version=$(mvn -version 2>&1 | head -n1 | awk '{print $3}')
    print_info "Maven版本: $maven_version"
}

# 清理环境
clean_environment() {
    print_info "清理开发环境..."
    
    # 停止可能运行的服务
    if pgrep -f "spring-boot:run.*dev" > /dev/null; then
        print_warn "检测到运行中的开发服务，正在停止..."
        pkill -f "spring-boot:run.*dev" || true
        sleep 3
    fi
    
    # 清理Maven构建文件
    print_info "清理Maven构建文件..."
    mvn clean -s maven-settings.xml -q
}

# 初始化数据库
init_database() {
    print_info "初始化开发环境数据库..."
    
    if [ -f "scripts/init-dev-database.sh" ]; then
        chmod +x scripts/init-dev-database.sh
        ./scripts/init-dev-database.sh
    else
        print_warn "未找到数据库初始化脚本，手动创建数据目录..."
        mkdir -p notice-provider/data
    fi
}

# 编译项目
compile_project() {
    print_info "编译项目..."
    
    # 设置Maven选项
    local maven_opts="-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true"
    
    # 编译项目
    MAVEN_OPTS="$maven_opts" mvn compile -s maven-settings.xml -Dmaven.test.skip=true
    
    if [ $? -eq 0 ]; then
        print_info "项目编译成功!"
    else
        print_error "项目编译失败，请查看错误信息"
        exit 1
    fi
}

# 验证配置
verify_configuration() {
    print_info "验证开发环境配置..."
    
    local config_file="notice-provider/src/main/resources/application-dev.yml"
    
    if [ ! -f "$config_file" ]; then
        print_error "未找到开发环境配置文件: $config_file"
        exit 1
    fi
    
    # 检查关键配置项
    if grep -q "sqlite" "$config_file"; then
        print_info "✅ 数据库配置: SQLite"
    else
        print_warn "⚠️  未检测到SQLite配置"
    fi
    
    if grep -q "smtp.mxhichina.com" "$config_file"; then
        print_info "✅ 邮件配置: 已配置"
    else
        print_warn "⚠️  邮件配置可能需要检查"
    fi
    
    if grep -q "oss-cn-beijing.aliyuncs.com" "$config_file"; then
        print_info "✅ OSS配置: 已配置"
    else
        print_warn "⚠️  OSS配置可能需要检查"
    fi
}

# 创建启动脚本
create_startup_script() {
    print_info "创建启动脚本..."
    
    if [ ! -f "scripts/start-dev.sh" ]; then
        cat > scripts/start-dev.sh <<'EOF'
#!/bin/bash
echo "=== 启动开发环境 ==="
cd "$(dirname "$0")/.."
cd notice-provider
MAVEN_OPTS="-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true" \
mvn spring-boot:run -Dspring.profiles.active=dev -s ../maven-settings.xml
EOF
        chmod +x scripts/start-dev.sh
        print_info "✅ 创建启动脚本: scripts/start-dev.sh"
    fi
}

# 主要部署流程
main() {
    print_info "开始开发环境部署..."
    
    check_dependencies
    clean_environment
    init_database
    compile_project
    verify_configuration
    create_startup_script
    
    print_info ""
    print_info "🎉 开发环境部署完成!"
    print_info ""
    print_info "📖 下一步操作:"
    print_info "   1. 启动开发服务: ./scripts/start-dev.sh"
    print_info "   2. 查看应用状态: curl http://localhost:8090/actuator/health"
    print_info "   3. 访问Swagger UI: http://localhost:8090/swagger-ui.html"
    print_info ""
    print_info "📂 重要文件位置:"
    print_info "   - 配置文件: notice-provider/src/main/resources/application-dev.yml"
    print_info "   - 数据库文件: notice-provider/data/iacc-notice.db"
    print_info "   - 日志文件: logs/app.log"
    print_info ""
    
    # 询问是否立即启动
    read -p "是否立即启动开发环境? (y/N): " confirm
    case $confirm in
        [Yy]* )
            print_info "正在启动开发环境..."
            ./scripts/start-dev.sh
            ;;
        * )
            print_info "稍后可运行 ./scripts/start-dev.sh 启动开发环境"
            ;;
    esac
}

# 运行主函数
main "$@" 