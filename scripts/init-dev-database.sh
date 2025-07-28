#!/bin/bash

echo "=== 开发环境数据库初始化 ==="

# 设置错误时退出
set -e

# 项目根目录
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$PROJECT_ROOT"

# 创建数据目录
echo "创建数据目录..."
mkdir -p notice-provider/data

# 数据库文件路径
DB_FILE="notice-provider/data/iacc-notice.db"
SCHEMA_FILE="notice-provider/src/main/resources/db/schema-sqlite.sql"

# 检查schema文件是否存在
if [ ! -f "$SCHEMA_FILE" ]; then
    echo "❌ 错误: 找不到数据库schema文件: $SCHEMA_FILE"
    exit 1
fi

# 如果数据库文件已存在，询问是否重新初始化
if [ -f "$DB_FILE" ]; then
    echo "⚠️  数据库文件已存在: $DB_FILE"
    read -p "是否重新初始化数据库? (y/N): " confirm
    case $confirm in
        [Yy]* )
            echo "删除现有数据库文件..."
            rm -f "$DB_FILE"
            ;;
        * )
            echo "保持现有数据库，跳过初始化。"
            exit 0
            ;;
    esac
fi

# 初始化SQLite数据库
echo "初始化SQLite数据库..."
if command -v sqlite3 &> /dev/null; then
    sqlite3 "$DB_FILE" < "$SCHEMA_FILE"
    echo "✅ 数据库初始化成功!"
else
    echo "⚠️  未找到sqlite3命令，将在应用启动时自动创建数据库"
fi

# 设置数据库文件权限
chmod 644 "$DB_FILE" 2>/dev/null || true

# 验证数据库
if [ -f "$DB_FILE" ]; then
    echo "📊 数据库文件大小: $(du -h "$DB_FILE" | cut -f1)"
    
    if command -v sqlite3 &> /dev/null; then
        echo "📋 数据表列表:"
        sqlite3 "$DB_FILE" ".tables" | tr ' ' '\n' | grep -v '^$' | sed 's/^/  - /'
    fi
else
    echo "⚠️  数据库文件未创建，将在应用启动时自动创建"
fi

echo ""
echo "🎉 开发环境数据库初始化完成!"
echo "   数据库文件: $DB_FILE"
echo "   数据库类型: SQLite"
echo ""

# 显示下一步操作
echo "📖 下一步操作:"
echo "   1. 运行 ./scripts/start-dev.sh 启动开发环境"
echo "   2. 或者手动运行: cd notice-provider && mvn spring-boot:run -Dspring.profiles.active=dev"
echo "" 