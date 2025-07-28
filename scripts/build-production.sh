#!/bin/bash

# ===================================================================
# IACC Notice 生产环境打包部署脚本
# 版本: 2.0
# 环境: Kubernetes生产环境
# ===================================================================

set -e  # 遇到错误立即退出

# 颜色定义
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 打印函数
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

print_header() {
    echo -e "\n${BLUE}============================================${NC}"
    echo -e "${BLUE} $1 ${NC}"
    echo -e "${BLUE}============================================${NC}\n"
}

# 脚本开始
print_header "IACC Notice 生产环境构建脚本"

# 检查当前目录
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PROJECT_ROOT="$(dirname "$SCRIPT_DIR")"
cd "$PROJECT_ROOT"

print_info "项目根目录: $PROJECT_ROOT"
print_info "构建时间: $(date '+%Y-%m-%d %H:%M:%S')"

# 环境检查
print_header "环境检查"

# 检查Java版本
print_info "检查Java环境..."
if ! command -v java &> /dev/null; then
    print_error "未找到Java环境，请安装JDK 8或更高版本"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -1 | cut -d'"' -f2)
print_success "Java版本: $JAVA_VERSION"

# 检查Maven版本
print_info "检查Maven环境..."
if ! command -v mvn &> /dev/null; then
    print_error "未找到Maven环境，请安装Maven 3.6或更高版本"
    exit 1
fi

MVN_VERSION=$(mvn -version 2>&1 | head -1 | awk '{print $3}')
print_success "Maven版本: $MVN_VERSION"

# 配置检查
print_header "生产环境配置检查"

# 检查配置文件
CONFIG_FILES=(
    "notice-provider/src/main/resources/bootstrap-pro.yml"
    "notice-provider/src/main/resources/application-prod.yml"
)

for config_file in "${CONFIG_FILES[@]}"; do
    if [ -f "$config_file" ]; then
        print_success "配置文件存在: $config_file"
    else
        print_error "配置文件缺失: $config_file"
        exit 1
    fi
done

# 验证生产环境配置
print_info "验证生产环境关键配置..."

# 检查Nacos配置
if grep -q "nacos1-headless.default.svc.cluster.local" notice-provider/src/main/resources/bootstrap-pro.yml; then
    print_success "Nacos配置正确"
else
    print_warning "请检查Nacos配置"
fi

# 检查用户中心配置
if grep -q "218.244.138.12:8086" notice-provider/src/main/resources/application-prod.yml; then
    print_success "用户中心配置正确"
else
    print_warning "请检查用户中心配置"
fi

# 检查区块链平台配置
if grep -q "qkl-fabric.pro-iacc.svc.cluster.local" notice-provider/src/main/resources/application-prod.yml; then
    print_success "区块链平台配置正确"
else
    print_warning "请检查区块链平台配置"
fi

# 项目构建
print_header "项目构建"

# 清理项目
print_info "清理项目..."
mvn clean -s maven-settings.xml -q
if [ $? -eq 0 ]; then
    print_success "项目清理完成"
else
    print_error "项目清理失败"
    exit 1
fi

# 编译项目
print_info "编译项目..."
mvn compile -s maven-settings.xml -Dmaven.test.skip=true -Dspring.profiles.active=pro
if [ $? -eq 0 ]; then
    print_success "项目编译成功"
else
    print_error "项目编译失败"
    exit 1
fi

# 运行测试（可选，生产环境建议跳过）
read -p "是否运行测试？(y/N): " run_tests
if [[ $run_tests =~ ^[Yy]$ ]]; then
    print_info "运行测试..."
    mvn test -s maven-settings.xml -Dspring.profiles.active=pro
    if [ $? -eq 0 ]; then
        print_success "测试通过"
    else
        print_warning "测试失败，但继续构建"
    fi
fi

# 打包项目
print_info "打包项目（生产环境profile: pro）..."
mvn package -s maven-settings.xml -Dmaven.test.skip=true -Dspring.profiles.active=pro
if [ $? -eq 0 ]; then
    print_success "项目打包成功"
else
    print_error "项目打包失败"
    exit 1
fi

# 构建结果检查
print_header "构建结果检查"

JAR_FILE="notice-provider/target/notice-provider-1.0-SNAPSHOT.jar"
if [ -f "$JAR_FILE" ]; then
    JAR_SIZE=$(du -h "$JAR_FILE" | cut -f1)
    print_success "JAR文件生成成功: $JAR_FILE (大小: $JAR_SIZE)"
    
    # 检查JAR包内容
    print_info "检查JAR包配置文件..."
    jar -tf "$JAR_FILE" | grep -E "(application|bootstrap|iacc-notice)" | head -10
    
else
    print_error "JAR文件生成失败"
    exit 1
fi

# 生成部署包
print_header "生成部署包"

DEPLOY_DIR="deploy-$(date +%Y%m%d_%H%M%S)"
mkdir -p "$DEPLOY_DIR"

# 复制必要文件到部署目录
cp "$JAR_FILE" "$DEPLOY_DIR/"
cp "scripts/start-production.sh" "$DEPLOY_DIR/" 2>/dev/null || echo "start-production.sh not found, creating..."

# 创建生产环境启动脚本
cat > "$DEPLOY_DIR/start-production.sh" << 'EOF'
#!/bin/bash

# IACC Notice 生产环境启动脚本

export SPRING_PROFILES_ACTIVE=pro
export JVM_OPTS="-Xms1g -Xmx2g -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError"

echo "启动IACC Notice生产环境..."
echo "JVM参数: $JVM_OPTS"
echo "Spring Profile: $SPRING_PROFILES_ACTIVE"
echo "配置来源: JAR包内置配置文件"

java $JVM_OPTS -jar notice-provider-1.0-SNAPSHOT.jar --spring.profiles.active=pro
EOF

chmod +x "$DEPLOY_DIR/start-production.sh"

# 创建Docker配置（如果需要）
cat > "$DEPLOY_DIR/Dockerfile" << 'EOF'
FROM openjdk:8-jre-alpine

WORKDIR /app

COPY notice-provider-1.0-SNAPSHOT.jar app.jar
COPY start-production.sh start-production.sh

RUN chmod +x start-production.sh

EXPOSE 8090

ENV SPRING_PROFILES_ACTIVE=pro

CMD ["./start-production.sh"]
EOF

# 创建Kubernetes部署文件
cat > "$DEPLOY_DIR/k8s-deployment.yaml" << 'EOF'
apiVersion: apps/v1
kind: Deployment
metadata:
  name: iacc-notice
  namespace: default
spec:
  replicas: 2
  selector:
    matchLabels:
      app: iacc-notice
  template:
    metadata:
      labels:
        app: iacc-notice
    spec:
      containers:
      - name: iacc-notice
        image: iacc-notice:latest
        ports:
        - containerPort: 8090
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "pro"
        resources:
          requests:
            memory: "1Gi"
            cpu: "500m"
          limits:
            memory: "2Gi"
            cpu: "1000m"
---
apiVersion: v1
kind: Service
metadata:
  name: iacc-notice-service
  namespace: default
spec:
  selector:
    app: iacc-notice
  ports:
  - port: 8090
    targetPort: 8090
  type: ClusterIP
EOF

print_success "部署包生成完成: $DEPLOY_DIR"

# 生成部署说明
cat > "$DEPLOY_DIR/README.md" << 'EOF'
# IACC Notice 生产环境部署说明

## 文件说明
- `notice-provider-1.0-SNAPSHOT.jar`: 应用主程序（包含所有配置）
- `start-production.sh`: 生产环境启动脚本
- `Dockerfile`: Docker镜像构建文件
- `k8s-deployment.yaml`: Kubernetes部署文件

## 配置说明
所有生产环境配置都已内置在JAR包中：
- 数据库配置: MySQL集群连接
- 服务注册: Nacos配置中心
- 外部服务: 用户中心、统一会员、区块链平台等
- OSS配置: 阿里云对象存储
- 邮件服务: SMTP配置

## 部署步骤

### 1. 直接部署
```bash
chmod +x start-production.sh
./start-production.sh
```

### 2. Docker部署
```bash
docker build -t iacc-notice:latest .
docker run -d -p 8090:8090 --name iacc-notice iacc-notice:latest
```

### 3. Kubernetes部署
```bash
kubectl apply -f k8s-deployment.yaml
```

## 环境要求
- JDK 8+
- 内存: 最少1GB，推荐2GB
- 外部依赖: MySQL、Redis、Nacos

## 健康检查
- 端口: 8090
- 健康检查: http://localhost:8090/actuator/health

## 注意事项
- 配置文件已内置在JAR包中，无需外部配置文件
- 通过Nacos配置中心可以动态更新部分配置
- 生产环境profile为'pro'
EOF

# 最终总结
print_header "构建完成总结"

print_success "✅ 生产环境构建成功！"
echo ""
print_info "📦 部署包位置: $DEPLOY_DIR"
print_info "📄 JAR文件: $JAR_FILE"
print_info "⚙️  配置方式: JAR包内置配置 (application-prod.yml + bootstrap-pro.yml)"
print_info "🚀 启动脚本: $DEPLOY_DIR/start-production.sh"
echo ""
print_info "📋 下一步部署操作:"
print_info "   1. 将 $DEPLOY_DIR 目录上传到生产服务器"
print_info "   2. 确保生产环境MySQL、Redis、Nacos服务可用"
print_info "   3. 执行: cd $DEPLOY_DIR && ./start-production.sh"
print_info "   4. 访问: http://服务器IP:8090/actuator/health 检查服务状态"
echo ""
print_warning "⚠️  生产环境部署前请确认:"
print_warning "   - MySQL数据库 mysql-mha.kube-mysqlmha.svc.cluster.local:3306 可访问"
print_warning "   - Nacos配置中心 nacos1-headless.default.svc.cluster.local:8848 可访问"
print_warning "   - 外部服务（用户中心、统一会员等）可访问"
print_warning "   - 阿里云OSS访问权限配置正确"
print_warning "   - 服务器防火墙规则允许8090端口"

echo ""
print_success "🎉 生产环境构建脚本执行完成！" 