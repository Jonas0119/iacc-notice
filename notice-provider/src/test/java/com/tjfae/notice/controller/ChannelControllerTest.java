package com.tjfae.notice.controller;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.request.NoticeChannelPushReq;
import com.tjfae.notice.request.NoticeChannelCancelReq;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ChannelController 测试类
 * 测试渠道推送相关接口
 */
@DisplayName("ChannelController 测试")
class ChannelControllerTest extends BaseTest {

    @MockBean
    private ArbitrateServiceImpl arbitrateService;

    @Test
    @DisplayName("公示公告推送")
    @WithMockUser
    void testNoticePush() throws Exception {
        // Given
        NoticeChannelPushReq req = createMockNoticeChannelPushReq();
        Long expectedId = 123L;
        when(arbitrateService.noticePush(any(NoticeChannelPushReq.class), any(HttpServletRequest.class)))
                .thenReturn(expectedId);

        // When & Then
        mockMvc.perform(post("/api/channel/noticePush")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(expectedId));

        verify(arbitrateService).noticePush(any(NoticeChannelPushReq.class), any(HttpServletRequest.class));
    }

    @Test
    @DisplayName("公示公告修改")
    @WithMockUser
    void testNoticeUpdate() throws Exception {
        // Given
        NoticeChannelPushReq req = createMockNoticeChannelPushReq();
        doNothing().when(arbitrateService).noticeUpdate(any(NoticeChannelPushReq.class), any(HttpServletRequest.class));

        // When & Then
        mockMvc.perform(post("/api/channel/noticeUpdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).noticeUpdate(any(NoticeChannelPushReq.class), any(HttpServletRequest.class));
    }

    @Test
    @DisplayName("公示公告取消")
    @WithMockUser
    void testNoticeCancel() throws Exception {
        // Given
        NoticeChannelCancelReq req = createMockNoticeChannelCancelReq();
        doNothing().when(arbitrateService).noticeCancel(any(NoticeChannelCancelReq.class), any(HttpServletRequest.class));

        // When & Then
        mockMvc.perform(post("/api/channel/noticeCancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).noticeCancel(any(NoticeChannelCancelReq.class), any(HttpServletRequest.class));
    }

    @Test
    @DisplayName("异常处理测试")
    @WithMockUser
    void testExceptionHandling() throws Exception {
        // Given
        NoticeChannelPushReq req = createMockNoticeChannelPushReq();
        when(arbitrateService.noticePush(any(NoticeChannelPushReq.class), any(HttpServletRequest.class)))
                .thenThrow(new RuntimeException("推送失败"));

        // When & Then
        mockMvc.perform(post("/api/channel/noticePush")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500));
    }

    // === Mock对象创建方法 ===

    private NoticeChannelPushReq createMockNoticeChannelPushReq() {
        NoticeChannelPushReq req = new NoticeChannelPushReq();
        // 简化实现，避免依赖具体字段名
        return req;
    }

    private NoticeChannelCancelReq createMockNoticeChannelCancelReq() {
        NoticeChannelCancelReq req = new NoticeChannelCancelReq();
        // 简化实现，避免依赖具体字段名
        return req;
    }
} 