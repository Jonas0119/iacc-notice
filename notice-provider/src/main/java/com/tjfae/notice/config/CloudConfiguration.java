package com.tjfae.notice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Cloud条件配置类
 * 根据环境配置启用或禁用Spring Cloud功能
 * 
 * 开发环境: 禁用服务发现和Feign客户端
 * 生产环境: 启用完整的Spring Cloud功能
 */
@Configuration
public class CloudConfiguration {
    
    /**
     * 生产环境服务发现配置
     * 只在生产环境启用
     */
    @Configuration
    @EnableDiscoveryClient
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "prod")
    static class ProductionDiscoveryConfiguration {
        // 生产环境服务发现配置
    }
    
    /**
     * 生产环境Feign客户端配置
     * 只在生产环境启用
     */
    @Configuration
    @EnableFeignClients(basePackages = {"com.tjfae"})
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "prod")
    static class ProductionFeignConfiguration {
        // 生产环境Feign客户端配置
    }
} 