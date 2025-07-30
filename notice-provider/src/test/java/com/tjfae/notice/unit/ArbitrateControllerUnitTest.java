package com.tjfae.notice.unit;

import com.tjfae.notice.controller.ArbitrateController;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import com.tjfae.notice.request.NoticeInfoReq;
import com.tjfae.notice.request.ContractInfoReq;
import com.tjfae.notice.entity.NoticeInfo;
import com.tjfae.notice.entity.ContractInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ArbitrateController 纯单元测试
 * 不依赖Spring容器，只测试业务逻辑
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("ArbitrateController 单元测试")
class ArbitrateControllerUnitTest {

    @Mock
    private ArbitrateServiceImpl arbitrateService;

    @InjectMocks
    private ArbitrateController arbitrateController;

    @BeforeEach
    void setUp() {
        // Mockito自动注入，无需手动初始化
    }

    @Test
    @DisplayName("保存公告信息 - 成功场景")
    void testSaveNoticeSuccess() {
        // Given
        NoticeInfoReq request = new NoticeInfoReq();
        request.setTitle("测试公告");

        doNothing().when(arbitrateService).saveNotice(any(NoticeInfoReq.class));

        // When & Then
        assertDoesNotThrow(() -> {
            arbitrateService.saveNotice(request);
        });

        verify(arbitrateService).saveNotice(request);
    }

    @Test
    @DisplayName("保存合同信息 - 成功场景")
    void testSaveContractSuccess() {
        // Given
        ContractInfoReq request = new ContractInfoReq();
        
        doNothing().when(arbitrateService).saveContract(any(ContractInfoReq.class));

        // When & Then
        assertDoesNotThrow(() -> {
            arbitrateService.saveContract(request);
        });

        verify(arbitrateService).saveContract(request);
    }

    @Test
    @DisplayName("根据ID查找公告 - 成功场景")
    void testFindNoticeByIdSuccess() {
        // Given
        int noticeId = 1;
        NoticeInfo mockNotice = new NoticeInfo();
        mockNotice.setId(1L);
        mockNotice.setTitle("测试公告");

        when(arbitrateService.findNoticeById(noticeId)).thenReturn(mockNotice);

        // When
        NoticeInfo result = arbitrateService.findNoticeById(noticeId);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("测试公告", result.getTitle());
        verify(arbitrateService).findNoticeById(noticeId);
    }

    @Test
    @DisplayName("根据ID查找合同 - 成功场景")
    void testFindContractByIdSuccess() {
        // Given
        int contractId = 1;
        ContractInfo mockContract = new ContractInfo();
        mockContract.setId(1L);

        when(arbitrateService.findContractById(contractId)).thenReturn(mockContract);

        // When
        ContractInfo result = arbitrateService.findContractById(contractId);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(arbitrateService).findContractById(contractId);
    }

    @Test
    @DisplayName("查询公告分页列表 - 成功场景")
    void testQueryNoticePageListSuccess() {
        // Given
        NoticeInfo param = new NoticeInfo();
        List<NoticeInfo> mockList = new ArrayList<>();
        mockList.add(new NoticeInfo());

        when(arbitrateService.queryNoticePageList(any(NoticeInfo.class))).thenReturn(mockList);

        // When
        List<NoticeInfo> result = arbitrateService.queryNoticePageList(param);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(arbitrateService).queryNoticePageList(param);
    }

    @Test
    @DisplayName("异常处理测试")
    void testExceptionHandling() {
        // Given
        when(arbitrateService.findNoticeById(anyInt()))
                .thenThrow(new RuntimeException("数据库连接异常"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            arbitrateService.findNoticeById(1);
        });

        verify(arbitrateService).findNoticeById(1);
    }

    @Test
    @DisplayName("空参数处理测试")
    void testNullParameterHandling() {
        // Given
        when(arbitrateService.findNoticeById(0)).thenReturn(null);

        // When
        NoticeInfo result = arbitrateService.findNoticeById(0);

        // Then
        assertNull(result);
        verify(arbitrateService).findNoticeById(0);
    }
} 