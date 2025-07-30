package com.tjfae.notice.service;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.entity.ArbitralInstitution;
import com.tjfae.notice.service.impl.ArbitralInstitutionServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ArbitralInstitutionServiceImpl 测试类
 * 测试仲裁机构服务相关业务逻辑
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ArbitralInstitutionServiceImpl 测试")
class ArbitralInstitutionServiceImplTest extends BaseTest {

    @SpyBean
    private ArbitralInstitutionServiceImpl arbitralInstitutionService;

    @Test
    @DisplayName("测试服务基本功能")
    void testBasicServiceFunctionality() {
        // Given - 测试服务是否正常注入
        assertNotNull(arbitralInstitutionService, "ArbitralInstitutionServiceImpl 应该被正确注入");
    }

    @Test
    @DisplayName("获取仲裁机构列表测试")
    void testGetArbitralInstitution() {
        // When & Then
        assertDoesNotThrow(() -> {
            Object result = arbitralInstitutionService.getArbitralInstitution();
            // 可以验证返回结果的类型和结构
        }, "获取仲裁机构列表不应该抛出异常");
    }

    @Test
    @DisplayName("搜索仲裁机构测试")
    void testSearchArbitralInstitution() {
        // Given
        String searchName = "测试机构";

        // When & Then
        assertDoesNotThrow(() -> {
            List<ArbitralInstitution> result = arbitralInstitutionService.search(searchName);
            // 可以验证搜索结果
        }, "搜索仲裁机构不应该抛出异常");
    }

    @Test
    @DisplayName("空搜索条件测试")
    void testSearchWithNullOrEmpty() {
        // When & Then
        assertDoesNotThrow(() -> {
            arbitralInstitutionService.search(null);
            arbitralInstitutionService.search("");
            arbitralInstitutionService.search("   ");
        }, "空搜索条件应该被正确处理");
    }

    @Test
    @DisplayName("特殊字符搜索测试")
    void testSearchWithSpecialCharacters() {
        // Given
        String specialSearch = "测试@机构#123";

        // When & Then
        assertDoesNotThrow(() -> {
            arbitralInstitutionService.search(specialSearch);
        }, "特殊字符搜索应该正常工作");
    }
} 