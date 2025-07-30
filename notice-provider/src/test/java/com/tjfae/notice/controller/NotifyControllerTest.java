package com.tjfae.notice.controller;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.entity.NoticePublish;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import com.tjfae.common.core.web.domain.AjaxResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * NotifyController 测试类
 * 测试通知接口相关功能
 */
@DisplayName("NotifyController 测试")
class NotifyControllerTest extends BaseTest {

    @MockBean
    private ArbitrateServiceImpl arbitrateService;

    @Test
    @DisplayName("公示公告审核结果通知")
    @WithMockUser
    void testStatusNotify() throws Exception {
        // Given
        Long noticeId = 123L;
        AjaxResult expectedResult = AjaxResult.success("通知发送成功");
        when(arbitrateService.statusNotify(any(NoticePublish.class))).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(post("/notify/statusNotify/{id}", noticeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("通知发送成功"));

        // Verify
        verify(arbitrateService).statusNotify(any(NoticePublish.class));
    }

    @Test
    @DisplayName("公示公告链接通知")
    @WithMockUser
    void testUrlNotify() throws Exception {
        // Given
        Long noticeId = 456L;
        AjaxResult expectedResult = AjaxResult.success("链接通知发送成功");
        when(arbitrateService.urlNotify(any(NoticePublish.class))).thenReturn(expectedResult);

        // When & Then
        mockMvc.perform(post("/notify/urlNotify/{id}", noticeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("链接通知发送成功"));

        // Verify
        verify(arbitrateService).urlNotify(any(NoticePublish.class));
    }

    @Test
    @DisplayName("状态通知异常处理")
    @WithMockUser
    void testStatusNotifyWithException() throws Exception {
        // Given
        Long noticeId = 789L;
        when(arbitrateService.statusNotify(any(NoticePublish.class)))
                .thenThrow(new RuntimeException("通知服务异常"));

        // When & Then
        mockMvc.perform(post("/notify/statusNotify/{id}", noticeId))
                .andExpect(status().isOk());

        verify(arbitrateService).statusNotify(any(NoticePublish.class));
    }

    @Test
    @DisplayName("链接通知异常处理")
    @WithMockUser
    void testUrlNotifyWithException() throws Exception {
        // Given
        Long noticeId = 101L;
        when(arbitrateService.urlNotify(any(NoticePublish.class)))
                .thenThrow(new RuntimeException("链接通知服务异常"));

        // When & Then
        mockMvc.perform(post("/notify/urlNotify/{id}", noticeId))
                .andExpect(status().isOk());

        verify(arbitrateService).urlNotify(any(NoticePublish.class));
    }

    @Test
    @DisplayName("测试通知ID参数传递正确性")
    @WithMockUser
    void testNotifyIdParameter() throws Exception {
        // Given
        Long testId = 999L;
        AjaxResult mockResult = AjaxResult.success();
        when(arbitrateService.statusNotify(any(NoticePublish.class))).thenReturn(mockResult);

        // When
        mockMvc.perform(post("/notify/statusNotify/{id}", testId))
                .andExpect(status().isOk());

        // Then - 验证传递的ID是否正确
        verify(arbitrateService).statusNotify(argThat(noticePublish -> 
            noticePublish.getId().equals(testId)
        ));
    }
} 