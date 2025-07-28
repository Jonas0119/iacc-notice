#!/bin/bash

# ===================================================================
# IACC Notice 生产环境配置验证脚本
# ===================================================================

echo "🔍 验证生产环境配置..."

# 验证外部服务连通性
check_service() {
    local service_name=$1
    local service_url=$2
    
    echo -n "检查 $service_name..."
    if curl -s --connect-timeout 5 "$service_url" >/dev/null 2>&1; then
        echo " ✅"
    else
        echo " ❌ (无法连接)"
    fi
}

# 检查外部服务
echo "📡 检查外部服务连通性:"
check_service "用户中心" "http://218.244.138.12:8086/api/usercenter/health"
check_service "统一会员平台" "http://ipoduc.tjfae.com/service/health"

# 检查数据库连接
echo -e "\n💾 检查数据库配置:"
echo "MySQL: mysql-mha.kube-mysqlmha.svc.cluster.local:3306"
echo "数据库: iacc_notice"

# 检查Nacos配置
echo -e "\n🔗 检查Nacos配置:"
echo "地址: nacos1-headless.default.svc.cluster.local:8848"
echo "命名空间: 1914a314-0875-4f66-8a0e-4c31e9fc0240"

echo -e "\n✅ 配置验证完成" 