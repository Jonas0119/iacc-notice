package com.tjfae.notice.service;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.request.EvidenceHistoryQueryReq;
import com.tjfae.notice.request.EvidencePageQueryByUserReq;
import com.tjfae.notice.request.EvidenceInfoReq;
import com.tjfae.notice.service.impl.EvidenceServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * EvidenceServiceImpl 测试类
 * 测试证据服务相关业务逻辑
 */
@SpringBootTest
@ActiveProfiles("test")
@DisplayName("EvidenceServiceImpl 测试")
class EvidenceServiceImplTest extends BaseTest {

    @SpyBean
    private EvidenceServiceImpl evidenceService;

    @Test
    @DisplayName("测试服务基本功能")
    void testBasicServiceFunctionality() {
        // Given - 测试服务是否正常注入
        assertNotNull(evidenceService, "EvidenceServiceImpl 应该被正确注入");
    }

    @Test
    @DisplayName("获取用户证据分页数据")
    void testGetUserEvidencePages() {
        // Given
        EvidencePageQueryByUserReq req = createMockEvidencePageQueryByUserReq();

        // When & Then
        assertDoesNotThrow(() -> {
            Object result = evidenceService.getUserEvidencePages(req);
            // 注意：由于没有实际的数据库和复杂依赖，这里主要测试方法不会抛异常
        }, "获取用户证据分页数据不应该抛出异常");
    }

    @Test
    @DisplayName("获取证据详情")
    void testGetEvidenceDetail() {
        // Given
        EvidenceHistoryQueryReq req = createMockEvidenceHistoryQueryReq();

        // When & Then
        assertDoesNotThrow(() -> {
            evidenceService.getEvidenceDetail(req);
        }, "获取证据详情不应该抛出异常");
    }

    @Test
    @DisplayName("查询证据记录")
    void testQueryEvidenceRecords() {
        // Given
        EvidenceInfoReq req = createMockEvidenceInfoReq();

        // When & Then
        assertDoesNotThrow(() -> {
            evidenceService.queryEvidenceRecords(req);
        }, "查询证据记录不应该抛出异常");
    }

    @Test
    @DisplayName("获取交易详情")
    void testGetTxDetail() {
        // Given
        String txId = "test-tx-id";

        // When & Then
        assertDoesNotThrow(() -> {
            evidenceService.getTxDetail(txId);
        }, "获取交易详情不应该抛出异常");
    }

    @Test
    @DisplayName("测试空参数处理")
    void testNullParameterHandling() {
        // When & Then
        assertDoesNotThrow(() -> {
            evidenceService.getTxDetail(null);
            evidenceService.getTxDetail("");
        }, "空参数处理应该优雅地处理");
    }

    // === Mock对象创建方法 ===

    private EvidencePageQueryByUserReq createMockEvidencePageQueryByUserReq() {
        EvidencePageQueryByUserReq req = new EvidencePageQueryByUserReq();
        return req;
    }

    private EvidenceHistoryQueryReq createMockEvidenceHistoryQueryReq() {
        EvidenceHistoryQueryReq req = new EvidenceHistoryQueryReq();
        return req;
    }

    private EvidenceInfoReq createMockEvidenceInfoReq() {
        EvidenceInfoReq req = new EvidenceInfoReq();
        return req;
    }
} 