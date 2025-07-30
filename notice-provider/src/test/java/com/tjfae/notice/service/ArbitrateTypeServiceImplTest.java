package com.tjfae.notice.service;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.service.impl.ArbitrateTypeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ArbitrateTypeServiceImpl 测试类
 * 测试仲裁类型服务相关业务逻辑
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ArbitrateTypeServiceImpl 测试")
class ArbitrateTypeServiceImplTest extends BaseTest {

    @SpyBean
    private ArbitrateTypeServiceImpl arbitrateTypeService;

    @Test
    @DisplayName("测试服务基本功能")
    void testBasicServiceFunctionality() {
        // Given - 测试服务是否正常注入
        assertNotNull(arbitrateTypeService, "ArbitrateTypeServiceImpl 应该被正确注入");
    }

    @Test
    @DisplayName("获取所有仲裁类型测试")
    void testGetAll() {
        // When & Then
        assertDoesNotThrow(() -> {
            Object result = arbitrateTypeService.getAll();
            // 可以验证返回结果的类型和结构
        }, "获取所有仲裁类型不应该抛出异常");
    }

    @Test
    @DisplayName("仲裁类型数据完整性测试")
    void testArbitrateTypeDataIntegrity() {
        // When & Then
        assertDoesNotThrow(() -> {
            Object result = arbitrateTypeService.getAll();
            assertNotNull(result, "仲裁类型列表不应该为null");
        }, "仲裁类型数据完整性检查应该通过");
    }
} 