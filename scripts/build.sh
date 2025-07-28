#!/bin/bash

echo "=== IACC Notice 项目构建脚本 ==="
echo "开始构建项目..."

# 检查Java版本
echo "检查Java版本..."
java -version
if [ $? -ne 0 ]; then
    echo "错误: 未找到Java环境，请安装JDK 8或更高版本"
    exit 1
fi

# 检查Maven版本
echo "检查Maven版本..."
mvn -version
if [ $? -ne 0 ]; then
    echo "错误: 未找到Maven环境，请安装Maven 3.6或更高版本"
    exit 1
fi

# 清理项目
echo "清理项目..."
mvn clean -s maven-settings.xml -q

# 尝试编译（跳过测试）
echo "编译项目..."
mvn compile -s maven-settings.xml -Dmaven.test.skip=true -q

if [ $? -eq 0 ]; then
    echo "✅ 项目编译成功！"
    
    # 尝试打包（跳过测试）
    echo "打包项目..."
    mvn package -s maven-settings.xml -Dmaven.test.skip=true -q
    
    if [ $? -eq 0 ]; then
        echo "✅ 项目打包成功！"
        echo "JAR文件位置: notice-provider/target/notice-provider-1.0-SNAPSHOT.jar"
    else
        echo "⚠️  项目编译成功但打包失败，可能是由于缺少商业依赖"
        echo "建议: 联系项目维护者获取完整的依赖配置"
    fi
else
    echo "❌ 项目编译失败"
    echo "可能的原因:"
    echo "1. 缺少商业依赖 (如 Aspose)"
    echo "2. 网络连接问题"
    echo "3. Maven仓库配置问题"
    echo ""
    echo "建议解决方案:"
    echo "1. 联系项目维护者获取完整的依赖配置"
    echo "2. 检查网络连接"
    echo "3. 配置正确的Maven仓库"
fi

echo "=== 构建完成 ===" 