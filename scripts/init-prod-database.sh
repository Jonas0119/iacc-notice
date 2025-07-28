#!/bin/bash

echo "=== 生产环境数据库初始化 ==="

# 设置错误时退出
set -e

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

# 配置参数 (可通过环境变量覆盖)
MYSQL_HOST="${MYSQL_HOST:-localhost}"
MYSQL_PORT="${MYSQL_PORT:-3306}"
MYSQL_ROOT_USER="${MYSQL_ROOT_USER:-root}"
MYSQL_DATABASE="${MYSQL_DATABASE:-iacc_notice}"
MYSQL_USER="${MYSQL_USER:-iacc_notice}"
MYSQL_PASSWORD="${MYSQL_PASSWORD:-iacc_notice123}"
SCHEMA_FILE="notice-provider/src/main/resources/db/schema-mysql.sql"

echo "配置信息:"
echo "  数据库主机: $MYSQL_HOST:$MYSQL_PORT"
echo "  数据库名称: $MYSQL_DATABASE"
echo "  数据库用户: $MYSQL_USER"
echo ""

# 检查schema文件是否存在
if [ ! -f "$SCHEMA_FILE" ]; then
    echo "❌ 错误: 找不到数据库schema文件: $SCHEMA_FILE"
    exit 1
fi

# 检查MySQL客户端
if ! command -v mysql &> /dev/null; then
    echo "❌ 错误: 未找到mysql客户端，请先安装MySQL客户端"
    echo "   Ubuntu/Debian: sudo apt-get install mysql-client"
    echo "   CentOS/RHEL: sudo yum install mysql"
    echo "   macOS: brew install mysql-client"
    exit 1
fi

# 获取root密码
if [ -z "$MYSQL_ROOT_PASSWORD" ]; then
    echo "请输入MySQL root用户密码:"
    read -s MYSQL_ROOT_PASSWORD
    echo ""
fi

# 测试数据库连接
echo "测试数据库连接..."
if ! mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "SELECT 1;" >/dev/null 2>&1; then
    echo "❌ 错误: 无法连接到MySQL数据库"
    echo "   请检查主机地址、端口、用户名和密码"
    exit 1
fi

echo "✅ 数据库连接成功!"

# 检查数据库是否已存在
if mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "USE $MYSQL_DATABASE;" >/dev/null 2>&1; then
    echo "⚠️  数据库 '$MYSQL_DATABASE' 已存在"
    read -p "是否重新初始化数据库? (y/N): " confirm
    case $confirm in
        [Yy]* )
            echo "删除现有数据库..."
            mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "DROP DATABASE IF EXISTS $MYSQL_DATABASE;"
            ;;
        * )
            echo "保持现有数据库，跳过初始化。"
            exit 0
            ;;
    esac
fi

# 创建数据库和用户
echo "创建数据库和用户..."
mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" <<EOF
CREATE DATABASE $MYSQL_DATABASE CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS '$MYSQL_USER'@'%' IDENTIFIED BY '$MYSQL_PASSWORD';
GRANT ALL PRIVILEGES ON $MYSQL_DATABASE.* TO '$MYSQL_USER'@'%';
FLUSH PRIVILEGES;
EOF

echo "✅ 数据库和用户创建成功!"

# 初始化数据库表结构
echo "初始化数据库表结构..."
mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" < "$SCHEMA_FILE"

echo "✅ 数据库表结构初始化完成!"

# 验证数据库
echo "📋 验证数据库表:"
mysql -h"$MYSQL_HOST" -P"$MYSQL_PORT" -u"$MYSQL_USER" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" -e "SHOW TABLES;" | grep -v "Tables_in_" | sed 's/^/  - /'

echo ""
echo "🎉 生产环境数据库初始化完成!"
echo "   数据库类型: MySQL"
echo "   数据库名称: $MYSQL_DATABASE"
echo "   数据库用户: $MYSQL_USER"
echo "   数据库主机: $MYSQL_HOST:$MYSQL_PORT"
echo ""

# 保存连接信息到配置文件
cat > scripts/.prod-db-config <<EOF
# 生产环境数据库配置 (由init-prod-database.sh生成)
MYSQL_HOST=$MYSQL_HOST
MYSQL_PORT=$MYSQL_PORT
MYSQL_DATABASE=$MYSQL_DATABASE
MYSQL_USER=$MYSQL_USER
# 注意: 密码应通过环境变量或安全配置管理工具提供
EOF

echo "📖 下一步操作:"
echo "   1. 编辑生产配置文件: iacc-notice-pro.yaml"
echo "   2. 运行 ./scripts/deploy-prod.sh 部署生产环境"
echo "   3. 运行 ./scripts/start-prod.sh 启动生产服务"
echo "" 