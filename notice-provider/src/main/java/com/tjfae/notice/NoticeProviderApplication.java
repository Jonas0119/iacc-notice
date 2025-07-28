package com.tjfae.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.tjfae.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 司法存证通知公告系统主应用类
 * 
 * 支持多环境运行：
 * - 开发环境 (dev): 使用SQLite数据库，本地配置，启用Redis
 * - 生产环境 (prod): 使用MySQL数据库，Nacos配置中心，启用Spring Cloud
 */
@SpringBootApplication
@EnableCustomSwagger2
@EnableAspectJAutoProxy(exposeProxy = true)
public class NoticeProviderApplication {
    
    public static void main(String[] args) {
        try {
            System.out.println("正在启动IACC司法存证通知公告系统...");
            SpringApplication.run(NoticeProviderApplication.class, args);
            System.out.println("系统启动成功！访问地址: http://localhost:8090");
        } catch (Exception e) {
            System.err.println("应用启动失败: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
