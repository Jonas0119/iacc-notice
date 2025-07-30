package com.tjfae.notice.unit;

import com.tjfae.notice.controller.NotifyController;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import com.tjfae.notice.entity.NoticePublish;
import com.tjfae.common.core.web.domain.AjaxResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * NotifyController 纯单元测试
 * 不依赖Spring容器，只测试业务逻辑
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("NotifyController 单元测试")
class NotifyControllerUnitTest {

    @Mock
    private ArbitrateServiceImpl arbitrateService;

    @InjectMocks
    private NotifyController notifyController;

    @BeforeEach
    void setUp() {
        // Mockito自动注入
    }

    @Test
    @DisplayName("状态通知 - 成功场景")
    void testStatusNotifySuccess() {
        // Given
        Long noticeId = 123L;
        AjaxResult expectedResult = AjaxResult.success("通知发送成功");
        when(arbitrateService.statusNotify(any(NoticePublish.class))).thenReturn(expectedResult);

        // When
        AjaxResult result = notifyController.statusNotify(noticeId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("通知发送成功", result.get("msg"));
        verify(arbitrateService).statusNotify(argThat(notice -> notice.getId().equals(noticeId)));
    }

    @Test
    @DisplayName("链接通知 - 成功场景")
    void testUrlNotifySuccess() {
        // Given
        Long noticeId = 456L;
        AjaxResult expectedResult = AjaxResult.success("链接通知发送成功");
        when(arbitrateService.urlNotify(any(NoticePublish.class))).thenReturn(expectedResult);

        // When
        AjaxResult result = notifyController.urlNotify(noticeId);

        // Then
        assertNotNull(result);
        assertEquals(200, result.get("code"));
        assertEquals("链接通知发送成功", result.get("msg"));
        verify(arbitrateService).urlNotify(argThat(notice -> notice.getId().equals(noticeId)));
    }

    @Test
    @DisplayName("状态通知 - 异常处理")
    void testStatusNotifyWithException() {
        // Given
        Long noticeId = 789L;
        when(arbitrateService.statusNotify(any(NoticePublish.class)))
                .thenThrow(new RuntimeException("通知服务异常"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            notifyController.statusNotify(noticeId);
        });

        verify(arbitrateService).statusNotify(any(NoticePublish.class));
    }

    @Test
    @DisplayName("链接通知 - 异常处理")
    void testUrlNotifyWithException() {
        // Given
        Long noticeId = 101L;
        when(arbitrateService.urlNotify(any(NoticePublish.class)))
                .thenThrow(new RuntimeException("链接通知服务异常"));

        // When & Then
        assertThrows(RuntimeException.class, () -> {
            notifyController.urlNotify(noticeId);
        });

        verify(arbitrateService).urlNotify(any(NoticePublish.class));
    }

    @Test
    @DisplayName("参数为null的处理")
    void testNullParameterHandling() {
        // Given
        when(arbitrateService.statusNotify(any(NoticePublish.class)))
                .thenReturn(AjaxResult.error("参数错误"));

        // When
        AjaxResult result = notifyController.statusNotify(null);

        // Then
        assertNotNull(result);
        verify(arbitrateService).statusNotify(argThat(notice -> notice.getId() == null));
    }

    @Test
    @DisplayName("边界值测试")
    void testBoundaryValues() {
        // Given
        Long maxId = Long.MAX_VALUE;
        Long minId = 0L;
        AjaxResult successResult = AjaxResult.success("成功");

        when(arbitrateService.statusNotify(any(NoticePublish.class))).thenReturn(successResult);

        // When & Then
        assertDoesNotThrow(() -> {
            notifyController.statusNotify(maxId);
            notifyController.statusNotify(minId);
        });

        verify(arbitrateService, times(2)).statusNotify(any(NoticePublish.class));
    }
} 