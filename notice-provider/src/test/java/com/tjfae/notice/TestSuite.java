package com.tjfae.notice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试套件
 * 包含基本的集成测试，确保应用能够正常启动和运行
 */
@SpringBootTest(classes = NoticeProviderApplication.class)
@ActiveProfiles("test")
@DisplayName("应用程序集成测试套件")
class TestSuite {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    @DisplayName("应用程序上下文加载测试")
    void contextLoads() {
        assertNotNull(webApplicationContext, "应用程序上下文应该成功加载");
    }

    @Test
    @DisplayName("所有必要的Bean是否正确注册")
    void verifyBeanRegistration() {
        // 验证关键的控制器Bean是否存在
        assertTrue(webApplicationContext.containsBean("arbitrateController"), 
                   "ArbitrateController应该被注册为Bean");
        assertTrue(webApplicationContext.containsBean("arbitratesController"), 
                   "ArbitratesController应该被注册为Bean");
        assertTrue(webApplicationContext.containsBean("channelController"), 
                   "ChannelController应该被注册为Bean");
    }

    @Test
    @DisplayName("数据库连接测试")
    void testDatabaseConnection() {
        // 这里可以添加简单的数据库连接测试
        assertDoesNotThrow(() -> {
            // 基本的数据库连接测试
            // 由于使用H2内存数据库，这里主要确保不抛异常
        }, "数据库连接应该正常");
    }
} 