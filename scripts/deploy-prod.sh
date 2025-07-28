#!/bin/bash

echo "=== 生产环境部署脚本 ==="

# 设置错误时退出
set -e

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
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
APP_NAME="iacc-notice"
APP_VERSION="1.0-SNAPSHOT"
JAR_NAME="notice-provider-${APP_VERSION}.jar"
DEPLOY_DIR="/opt/${APP_NAME}"
LOG_DIR="/var/log/${APP_NAME}"
PID_FILE="/var/run/${APP_NAME}.pid"
SERVICE_USER="iacc"

# 检查环境依赖
check_dependencies() {
    print_step "检查生产环境依赖..."
    
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
    
    # 检查MySQL客户端
    if ! command -v mysql &> /dev/null; then
        print_warn "未找到MySQL客户端，数据库连接测试将跳过"
    fi
}

# 检查数据库连接
check_database() {
    print_step "检查数据库连接..."
    
    # 尝试从配置文件加载数据库配置
    if [ -f "scripts/.prod-db-config" ]; then
        source scripts/.prod-db-config
        print_info "加载数据库配置: $MYSQL_HOST:$MYSQL_PORT/$MYSQL_DATABASE"
    else
        print_warn "未找到数据库配置文件，请确保已运行 init-prod-database.sh"
    fi
    
    # 如果有MySQL客户端，测试连接
    if command -v mysql &> /dev/null && [ -n "$MYSQL_HOST" ]; then
        if [ -z "$MYSQL_PASSWORD" ]; then
            print_warn "请设置环境变量 MYSQL_PASSWORD 或稍后手动验证数据库连接"
        else
            if mysql -h"$MYSQL_HOST" -P"${MYSQL_PORT:-3306}" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" -e "SELECT 1;" >/dev/null 2>&1; then
                print_info "✅ 数据库连接正常"
            else
                print_error "❌ 数据库连接失败，请检查配置"
                exit 1
            fi
        fi
    fi
}

# 构建应用
build_application() {
    print_step "构建生产应用..."
    
    # 清理
    print_info "清理旧的构建文件..."
    mvn clean -s maven-settings.xml -q
    
    # 设置Maven选项
    local maven_opts="-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true"
    
    # 构建
    print_info "编译和打包应用..."
    MAVEN_OPTS="$maven_opts" mvn package \
        -s maven-settings.xml \
        -Dmaven.test.skip=true \
        -Dspring.profiles.active=prod
    
    if [ $? -eq 0 ]; then
        print_info "✅ 应用构建成功!"
    else
        print_error "❌ 应用构建失败"
        exit 1
    fi
    
    # 检查JAR文件
    local jar_file="notice-provider/target/$JAR_NAME"
    if [ -f "$jar_file" ]; then
        print_info "✅ JAR文件: $jar_file ($(du -h "$jar_file" | cut -f1))"
    else
        print_error "❌ 未找到JAR文件: $jar_file"
        exit 1
    fi
}

# 创建服务用户
create_service_user() {
    print_step "创建服务用户..."
    
    if id "$SERVICE_USER" &>/dev/null; then
        print_info "服务用户 '$SERVICE_USER' 已存在"
    else
        print_info "创建服务用户 '$SERVICE_USER'..."
        sudo useradd -r -s /bin/false -d "$DEPLOY_DIR" "$SERVICE_USER" || true
    fi
}

# 创建目录结构
create_directories() {
    print_step "创建目录结构..."
    
    # 创建部署目录
    sudo mkdir -p "$DEPLOY_DIR"/{bin,config,lib,logs}
    sudo mkdir -p "$LOG_DIR"
    
    # 设置权限
    sudo chown -R "$SERVICE_USER:$SERVICE_USER" "$DEPLOY_DIR"
    sudo chown -R "$SERVICE_USER:$SERVICE_USER" "$LOG_DIR"
    
    print_info "✅ 目录结构创建完成:"
    print_info "   - 部署目录: $DEPLOY_DIR"
    print_info "   - 日志目录: $LOG_DIR"
}

# 部署应用文件
deploy_files() {
    print_step "部署应用文件..."
    
    local jar_file="notice-provider/target/$JAR_NAME"
    local config_file="iacc-notice-pro.yaml"
    
    # 停止运行中的服务
    if [ -f "$PID_FILE" ]; then
        print_info "停止运行中的服务..."
        sudo kill $(cat "$PID_FILE") 2>/dev/null || true
        sleep 3
        sudo rm -f "$PID_FILE"
    fi
    
    # 复制JAR文件
    print_info "复制JAR文件..."
    sudo cp "$jar_file" "$DEPLOY_DIR/lib/"
    
    # 复制配置文件
    if [ -f "$config_file" ]; then
        print_info "复制配置文件..."
        sudo cp "$config_file" "$DEPLOY_DIR/config/application.yaml"
    else
        print_warn "未找到生产配置文件: $config_file"
    fi
    
    # 设置权限
    sudo chown -R "$SERVICE_USER:$SERVICE_USER" "$DEPLOY_DIR"
    
    print_info "✅ 应用文件部署完成"
}

# 创建启动脚本
create_startup_script() {
    print_step "创建启动脚本..."
    
    # 创建启动脚本
    sudo tee "$DEPLOY_DIR/bin/start.sh" > /dev/null <<EOF
#!/bin/bash

# IACC Notice 生产环境启动脚本
APP_NAME="$APP_NAME"
APP_HOME="$DEPLOY_DIR"
JAR_FILE="\$APP_HOME/lib/$JAR_NAME"
CONFIG_FILE="\$APP_HOME/config/application.yaml"
PID_FILE="$PID_FILE"
LOG_FILE="$LOG_DIR/app.log"

# JVM参数
JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
JAVA_OPTS="\$JAVA_OPTS -Djava.awt.headless=true"
JAVA_OPTS="\$JAVA_OPTS -Dfile.encoding=UTF-8"
JAVA_OPTS="\$JAVA_OPTS -Duser.timezone=Asia/Shanghai"

# Spring Boot参数
SPRING_OPTS="--spring.profiles.active=prod"
SPRING_OPTS="\$SPRING_OPTS --spring.config.location=file:\$CONFIG_FILE"
SPRING_OPTS="\$SPRING_OPTS --server.port=8019"
SPRING_OPTS="\$SPRING_OPTS --logging.file.name=\$LOG_FILE"

# 检查是否已运行
if [ -f "\$PID_FILE" ]; then
    PID=\$(cat "\$PID_FILE")
    if kill -0 "\$PID" 2>/dev/null; then
        echo "应用已在运行 (PID: \$PID)"
        exit 1
    else
        rm -f "\$PID_FILE"
    fi
fi

# 启动应用
echo "启动 \$APP_NAME..."
echo "JAR文件: \$JAR_FILE"
echo "配置文件: \$CONFIG_FILE"
echo "日志文件: \$LOG_FILE"
echo "PID文件: \$PID_FILE"

nohup java \$JAVA_OPTS -jar "\$JAR_FILE" \$SPRING_OPTS > "\$LOG_FILE" 2>&1 &
PID=\$!

# 保存PID
echo \$PID > "\$PID_FILE"

echo "应用启动完成 (PID: \$PID)"
echo "查看日志: tail -f \$LOG_FILE"
echo "检查状态: curl http://localhost:8019/actuator/health"
EOF

    # 创建停止脚本
    sudo tee "$DEPLOY_DIR/bin/stop.sh" > /dev/null <<EOF
#!/bin/bash

APP_NAME="$APP_NAME"
PID_FILE="$PID_FILE"

if [ ! -f "\$PID_FILE" ]; then
    echo "应用未运行"
    exit 1
fi

PID=\$(cat "\$PID_FILE")

echo "停止 \$APP_NAME (PID: \$PID)..."
kill \$PID

# 等待进程停止
for i in {1..30}; do
    if ! kill -0 \$PID 2>/dev/null; then
        echo "应用已停止"
        rm -f "\$PID_FILE"
        exit 0
    fi
    sleep 1
done

# 强制停止
echo "强制停止应用..."
kill -9 \$PID 2>/dev/null || true
rm -f "\$PID_FILE"
echo "应用已强制停止"
EOF

    # 设置执行权限
    sudo chmod +x "$DEPLOY_DIR/bin/start.sh"
    sudo chmod +x "$DEPLOY_DIR/bin/stop.sh"
    sudo chown "$SERVICE_USER:$SERVICE_USER" "$DEPLOY_DIR/bin/"*.sh
    
    print_info "✅ 启动脚本创建完成"
}

# 创建systemd服务
create_systemd_service() {
    print_step "创建systemd服务..."
    
    sudo tee "/etc/systemd/system/${APP_NAME}.service" > /dev/null <<EOF
[Unit]
Description=IACC Notice Application
After=network.target

[Service]
Type=forking
User=$SERVICE_USER
Group=$SERVICE_USER
ExecStart=$DEPLOY_DIR/bin/start.sh
ExecStop=$DEPLOY_DIR/bin/stop.sh
PIDFile=$PID_FILE
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF

    # 重新加载systemd
    sudo systemctl daemon-reload
    sudo systemctl enable "$APP_NAME"
    
    print_info "✅ systemd服务创建完成"
    print_info "   启动服务: sudo systemctl start $APP_NAME"
    print_info "   查看状态: sudo systemctl status $APP_NAME"
}

# 验证部署
verify_deployment() {
    print_step "验证部署..."
    
    # 检查文件
    local jar_file="$DEPLOY_DIR/lib/$JAR_NAME"
    if [ -f "$jar_file" ]; then
        print_info "✅ JAR文件: $jar_file"
    else
        print_error "❌ 未找到JAR文件"
        exit 1
    fi
    
    # 检查配置
    local config_file="$DEPLOY_DIR/config/application.yaml"
    if [ -f "$config_file" ]; then
        print_info "✅ 配置文件: $config_file"
    else
        print_warn "⚠️  未找到配置文件"
    fi
    
    print_info "✅ 部署验证完成"
}

# 主函数
main() {
    print_info "开始生产环境部署..."
    print_info "目标用户: $SERVICE_USER"
    print_info "部署目录: $DEPLOY_DIR"
    print_info ""
    
    # 检查权限
    if [ "$EUID" -eq 0 ]; then
        print_warn "正在以root用户运行部署脚本"
    else
        print_info "需要sudo权限来创建系统服务和目录"
    fi
    
    check_dependencies
    check_database
    build_application
    create_service_user
    create_directories
    deploy_files
    create_startup_script
    create_systemd_service
    verify_deployment
    
    print_info ""
    print_info "🎉 生产环境部署完成!"
    print_info ""
    print_info "📖 下一步操作:"
    print_info "   1. 启动服务: sudo systemctl start $APP_NAME"
    print_info "   2. 查看状态: sudo systemctl status $APP_NAME"
    print_info "   3. 查看日志: sudo tail -f $LOG_DIR/app.log"
    print_info "   4. 检查健康: curl http://localhost:8019/actuator/health"
    print_info ""
    print_info "📂 重要文件位置:"
    print_info "   - 应用目录: $DEPLOY_DIR"
    print_info "   - 配置文件: $DEPLOY_DIR/config/application.yaml"
    print_info "   - 日志目录: $LOG_DIR"
    print_info "   - 服务文件: /etc/systemd/system/${APP_NAME}.service"
    print_info ""
    
    # 询问是否立即启动
    read -p "是否立即启动生产服务? (y/N): " confirm
    case $confirm in
        [Yy]* )
            print_info "正在启动生产服务..."
            sudo systemctl start "$APP_NAME"
            sleep 5
            sudo systemctl status "$APP_NAME"
            ;;
        * )
            print_info "稍后可运行 sudo systemctl start $APP_NAME 启动服务"
            ;;
    esac
}

# 运行主函数
main "$@" 