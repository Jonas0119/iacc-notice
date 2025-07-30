package com.tjfae.notice.service;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.service.impl.UserCenterServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UserCenterServiceImpl 测试类
 * 测试用户中心服务相关业务逻辑
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("UserCenterServiceImpl 测试")
class UserCenterServiceImplTest extends BaseTest {

    @SpyBean
    private UserCenterServiceImpl userCenterService;

    @Test
    @DisplayName("测试服务基本功能")
    void testBasicServiceFunctionality() {
        // Given - 测试服务是否正常注入
        assertNotNull(userCenterService, "UserCenterServiceImpl 应该被正确注入");
    }

    @Test
    @DisplayName("获取客户列表测试")
    void testGetCustomerList() {
        // Given
        String managerName = "testManager";

        // When & Then
        assertDoesNotThrow(() -> {
            List<Map<String, Object>> result = userCenterService.getCustomerList(managerName);
            // 可以验证返回结果的结构
        }, "获取客户列表不应该抛出异常");
    }

    @Test
    @DisplayName("空参数处理测试")
    void testNullParameterHandling() {
        // When & Then
        assertDoesNotThrow(() -> {
            userCenterService.getCustomerList(null);
            userCenterService.getCustomerList("");
        }, "空参数处理应该优雅地处理");
    }

    @Test
    @DisplayName("特殊字符处理测试")
    void testSpecialCharacterHandling() {
        // Given
        String specialName = "test@manager#123";

        // When & Then
        assertDoesNotThrow(() -> {
            userCenterService.getCustomerList(specialName);
        }, "特殊字符处理应该正常工作");
    }
} 