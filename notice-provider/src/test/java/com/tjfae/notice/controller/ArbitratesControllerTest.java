package com.tjfae.notice.controller;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.entity.NoticeInfo;
import com.tjfae.notice.entity.ContractInfo;
import com.tjfae.notice.entity.NoticePublish;
import com.tjfae.notice.entity.NoticeAuditLog;
import com.tjfae.notice.request.*;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.mock.web.MockMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ArbitratesController 测试类
 * 测试公示公告-后管相关接口
 */
@DisplayName("ArbitratesController 测试")
class ArbitratesControllerTest extends BaseTest {

    @MockBean
    private ExecutorService executorService;

    @MockBean
    private ArbitrateServiceImpl arbitrateService;

    @Test
    @DisplayName("下载证据")
    @WithMockUser
    void testDownloadEvidence() throws Exception {
        // Given
        EvidenceHistoryQueryReq req = createMockEvidenceHistoryQueryReq();
        String downloadUrl = "http://test.com/evidence.zip";
        when(arbitrateService.downloadEvidence(anyString(), anyString(), anyString()))
                .thenReturn(downloadUrl);

        // When & Then
        mockMvc.perform(post("/arbitrates/downloadEvidence")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        verify(arbitrateService).downloadEvidence(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("文件上传")
    @WithMockUser
    void testUploadArbitrationApplication() throws Exception {
        // Given
        MockMultipartFile file = new MockMultipartFile(
                "file", 
                "test.pdf", 
                "application/pdf", 
                "test content".getBytes()
        );
        String uploadResult = "http://test.com/uploaded/file.pdf";
        when(arbitrateService.uploadArbitrationApplication(any())).thenReturn(uploadResult);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/arbitrates/uploadArbitrationApplication")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        verify(arbitrateService).uploadArbitrationApplication(any());
    }

    @Test
    @DisplayName("保存公示公告")
    @WithMockUser
    void testSaveNotice() throws Exception {
        // Given
        NoticeInfoReq req = createMockNoticeInfoReq();
        doNothing().when(arbitrateService).saveNotice(any());

        // When & Then
        mockMvc.perform(post("/arbitrates/saveNotice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).saveNotice(any(NoticeInfoReq.class));
    }

    @Test
    @DisplayName("保存合同信息")
    @WithMockUser
    void testSaveContract() throws Exception {
        // Given
        ContractInfoReq req = createMockContractInfoReq();
        doNothing().when(arbitrateService).saveContract(any());

        // When & Then
        mockMvc.perform(post("/arbitrates/saveContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).saveContract(any(ContractInfoReq.class));
    }

    @Test
    @DisplayName("查询公告分页列表")
    @WithMockUser
    void testQueryNoticePageList() throws Exception {
        // Given
        NoticeInfo param = createMockNoticeInfo();
        List<NoticeInfo> noticeList = Arrays.asList(param);
        when(arbitrateService.queryNoticePageList(any())).thenReturn(noticeList);

        // When & Then
        mockMvc.perform(post("/arbitrates/queryNoticePageList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(param)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.rows").isArray());

        verify(arbitrateService).queryNoticePageList(any(NoticeInfo.class));
    }

    @Test
    @DisplayName("保存公告发布")
    @WithMockUser
    void testSaveNoticePub() throws Exception {
        // Given
        NoticePublishReq req = createMockNoticePublishReq();
        doNothing().when(arbitrateService).saveNoticePub(any());
        doNothing().when(arbitrateService).insertLog(any());

        // When & Then
        mockMvc.perform(post("/arbitrates/saveNoticePub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).saveNoticePub(any(NoticePublishReq.class));
        verify(arbitrateService).insertLog(any());
    }

    @Test
    @DisplayName("公告推送")
    @WithMockUser
    void testSaveNoticePush() throws Exception {
        // Given
        NoticePushReq req = createMockNoticePushReq();
        doNothing().when(arbitrateService).saveNoticePub(any());
        doNothing().when(arbitrateService).insertLog(any());

        // When & Then
        mockMvc.perform(post("/arbitrates/saveNoticePush")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).saveNoticePub(any(NoticePublishReq.class));
        verify(arbitrateService).insertLog(any());
    }

    @Test
    @DisplayName("审核公告发布")
    @WithMockUser
    void testAuditNoticePub() throws Exception {
        // Given
        NoticeAuditReq req = createMockNoticeAuditReq();
        doNothing().when(arbitrateService).auditNoticePub(any());

        // When & Then
        mockMvc.perform(post("/arbitrates/auditNoticePub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).auditNoticePub(any(NoticeAuditReq.class));
    }

    @Test
    @DisplayName("查询审核日志")
    @WithMockUser
    void testQueryAuditLogPageList() throws Exception {
        // Given
        NoticeAuditLog param = createMockNoticeAuditLog();
        List<NoticeAuditLog> logList = Arrays.asList(param);
        when(arbitrateService.queryAuditLogPageList(any())).thenReturn(logList);

        // When & Then
        mockMvc.perform(post("/arbitrates/queryAuditLogPageList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(param)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.rows").isArray());

        verify(arbitrateService).queryAuditLogPageList(any(NoticeAuditLog.class));
    }

    @Test
    @DisplayName("更新公告发布")
    @WithMockUser
    void testUpdateNoticePub() throws Exception {
        // Given
        NoticePublishReq req = createMockNoticePublishReq();
        doNothing().when(arbitrateService).updateNoticePub(any());
        doNothing().when(arbitrateService).insertLog(any());

        // When & Then
        mockMvc.perform(post("/arbitrates/updateNoticePub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).updateNoticePub(any(NoticePublishReq.class));
        verify(arbitrateService).insertLog(any());
    }

    @Test
    @DisplayName("删除公告发布")
    @WithMockUser
    void testDeleteNoticePub() throws Exception {
        // Given
        int noticeId = 1;
        doNothing().when(arbitrateService).deleteNoticePub(noticeId);

        // When & Then
        mockMvc.perform(post("/arbitrates/deleteNoticePub")
                        .param("id", String.valueOf(noticeId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).deleteNoticePub(noticeId);
    }

    // === Mock对象创建方法 ===

    private EvidenceHistoryQueryReq createMockEvidenceHistoryQueryReq() {
        EvidenceHistoryQueryReq req = new EvidenceHistoryQueryReq();
        // 简化实现
        return req;
    }

    private NoticeInfoReq createMockNoticeInfoReq() {
        NoticeInfoReq req = new NoticeInfoReq();
        req.setTitle("测试公告");
        return req;
    }

    private ContractInfoReq createMockContractInfoReq() {
        ContractInfoReq req = new ContractInfoReq();
        return req;
    }

    private NoticeInfo createMockNoticeInfo() {
        NoticeInfo info = new NoticeInfo();
        info.setId(1L);
        info.setTitle("测试公告");
        return info;
    }

    private NoticePublishReq createMockNoticePublishReq() {
        NoticePublishReq req = new NoticePublishReq();
        req.setId(1L);
        req.setTitle("发布公告标题");
        return req;
    }

    private NoticePushReq createMockNoticePushReq() {
        NoticePushReq req = new NoticePushReq();
        return req;
    }

    private NoticeAuditReq createMockNoticeAuditReq() {
        NoticeAuditReq req = new NoticeAuditReq();
        return req;
    }

    private NoticeAuditLog createMockNoticeAuditLog() {
        NoticeAuditLog log = new NoticeAuditLog();
        return log;
    }
} 