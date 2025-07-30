package com.tjfae.notice.controller;

import com.tjfae.notice.BaseTest;
import com.tjfae.notice.entity.ArbitralInstitution;
import com.tjfae.notice.entity.ArbitrateType;
import com.tjfae.notice.entity.NoticeInfo;
import com.tjfae.notice.entity.ContractInfo;
import com.tjfae.notice.entity.NoticePublish;
import com.tjfae.notice.request.*;
import com.tjfae.notice.response.ArbitrateRecordQueryResp;
import com.tjfae.notice.service.impl.ArbitralInstitutionServiceImpl;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import com.tjfae.notice.service.impl.ArbitrateTypeServiceImpl;
import com.tjfae.notice.service.impl.EvidenceServiceImpl;
import com.tjfae.notice.service.IUserCenterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ArbitrateController 测试类
 * 测试公示公告-PC端相关接口
 */
@DisplayName("ArbitrateController 测试")
class ArbitrateControllerTest extends BaseTest {

    @MockBean
    private ArbitralInstitutionServiceImpl arbitralInstitutionService;

    @MockBean
    private ArbitrateTypeServiceImpl arbitrateTypeService;

    @MockBean
    private ArbitrateServiceImpl arbitrateService;

    @MockBean
    private EvidenceServiceImpl evidenceService;

    @MockBean
    private IUserCenterService userCenterService;

    @Test
    @DisplayName("获取仲裁机构列表")
    @WithMockUser
    void testGetArbitralInstitution() throws Exception {
        // Given
        List<ArbitralInstitution> institutions = Arrays.asList(
                createMockArbitralInstitution(1L, "测试仲裁机构1"),
                createMockArbitralInstitution(2L, "测试仲裁机构2")
        );
        when(arbitralInstitutionService.getArbitralInstitution()).thenReturn(institutions);

        // When & Then
        mockMvc.perform(get("/arbitrate/getArbitralInstitution"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));

        verify(arbitralInstitutionService).getArbitralInstitution();
    }

    @Test
    @DisplayName("搜索仲裁机构")
    @WithMockUser
    void testSearchArbitralInstitution() throws Exception {
        // Given
        String searchName = "测试机构";
        List<ArbitralInstitution> institutions = Arrays.asList(
                createMockArbitralInstitution(1L, "测试机构1")
        );
        when(arbitralInstitutionService.search(searchName)).thenReturn(institutions);

        // When & Then
        mockMvc.perform(get("/arbitrate/searchArbitralInstitution")
                        .param("name", searchName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.rows").isArray());

        verify(arbitralInstitutionService).search(searchName);
    }

    @Test
    @DisplayName("获取仲裁类型")
    @WithMockUser
    void testGetArbitrateType() throws Exception {
        // Given
        List<ArbitrateType> types = Arrays.asList(
                createMockArbitrateType(1L, "合同争议"),
                createMockArbitrateType(2L, "知识产权")
        );
        when(arbitrateTypeService.getAll()).thenReturn(types);

        // When & Then
        mockMvc.perform(get("/arbitrate/getArbitrateType"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray());

        verify(arbitrateTypeService).getAll();
    }

    @Test
    @DisplayName("获取仲裁记录")
    @WithMockUser
    void testGetArbitrationRecords() throws Exception {
        // Given
        ArbitrateRecordsQueryReq req = createMockArbitrateRecordsQueryReq();
        List<ArbitrateRecordQueryResp> records = Arrays.asList(
                createMockArbitrateRecordQueryResp()
        );
        when(arbitrateService.getArbitrationRecords(any())).thenReturn(records);

        // When & Then
        mockMvc.perform(post("/arbitrate/getArbitrationRecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        verify(arbitrateService).getArbitrationRecords(any(ArbitrateRecordsQueryReq.class));
    }

    @Test
    @DisplayName("仲裁申请")
    @WithMockUser
    void testArbitrationApplication() throws Exception {
        // Given
        ArbitrateApplyReq req = createMockArbitrateApplyReq();
        String result = "申请成功";
        when(arbitrateService.arbitrationApplication(any())).thenReturn(result);

        // When & Then
        mockMvc.perform(post("/arbitrate/arbitrationApplication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(result));

        verify(arbitrateService).arbitrationApplication(any(ArbitrateApplyReq.class));
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
        mockMvc.perform(MockMvcRequestBuilders.multipart("/arbitrate/uploadArbitrationApplication")
                        .file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").value(uploadResult));

        verify(arbitrateService).uploadArbitrationApplication(any());
    }

    @Test
    @DisplayName("获取用户信息")
    @WithMockUser
    void testGetUserInfo() throws Exception {
        // Given
        String loginAccount = "testuser";
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId", 1);
        userInfo.put("userName", "测试用户");
        when(arbitrateService.getUserInfo(loginAccount)).thenReturn(userInfo);

        // When & Then
        mockMvc.perform(get("/arbitrate/getUserInfo")
                        .param("loginAccount", loginAccount))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.userId").value(1));

        verify(arbitrateService).getUserInfo(loginAccount);
    }

    @Test
    @DisplayName("保存公示公告")
    @WithMockUser
    void testSaveNotice() throws Exception {
        // Given
        NoticeInfoReq req = createMockNoticeInfoReq();
        doNothing().when(arbitrateService).saveNotice(any());

        // When & Then
        mockMvc.perform(post("/arbitrate/saveNotice")
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
        mockMvc.perform(post("/arbitrate/saveContract")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("成功"));

        verify(arbitrateService).saveContract(any(ContractInfoReq.class));
    }

    @Test
    @DisplayName("根据ID获取合同信息")
    @WithMockUser
    void testGetContractById() throws Exception {
        // Given
        int contractId = 1;
        ContractInfo contractInfo = createMockContractInfo();
        when(arbitrateService.findContractById(contractId)).thenReturn(contractInfo);

        // When & Then
        mockMvc.perform(post("/arbitrate/getContractById")
                        .param("id", String.valueOf(contractId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        verify(arbitrateService).findContractById(contractId);
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
        mockMvc.perform(post("/arbitrate/queryNoticePageList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(param)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.rows").isArray());

        verify(arbitrateService).queryNoticePageList(any(NoticeInfo.class));
    }

    @Test
    @DisplayName("保存公告发布 - 线下支付")
    @WithMockUser
    void testSaveNoticePubOffline() throws Exception {
        // Given
        NoticePublishReq req = createMockNoticePublishReq();
        req.setPayType("offline");
        req.setManagerName("testManager");
        
        // Mock manager validation
        List<Map<String, Object>> customers = Arrays.asList(createMockCustomer());
        when(userCenterService.getCustomerList(anyString())).thenReturn(customers);
        doNothing().when(arbitrateService).saveNoticePub(any());
        doNothing().when(arbitrateService).insertLog(any());

        // When & Then
        mockMvc.perform(post("/arbitrate/saveNoticePub")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("操作成功"));

        verify(arbitrateService).saveNoticePub(any(NoticePublishReq.class));
        verify(arbitrateService).insertLog(any());
    }

    @Test
    @DisplayName("异常处理测试")
    @WithMockUser
    void testExceptionHandling() throws Exception {
        // Given
        when(arbitralInstitutionService.getArbitralInstitution())
                .thenThrow(new RuntimeException("测试异常"));

        // When & Then
        mockMvc.perform(get("/arbitrate/getArbitralInstitution"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.msg").value("测试异常"));
    }

    // === Mock对象创建方法 ===

    private ArbitralInstitution createMockArbitralInstitution(Long id, String name) {
        ArbitralInstitution institution = new ArbitralInstitution();
        institution.setId(id);
        institution.setName(name);
        institution.setRemark("测试机构备注");
        return institution;
    }

    private ArbitrateType createMockArbitrateType(Long id, String typeName) {
        ArbitrateType type = new ArbitrateType();
        // 简化实现，只设置基本字段
        return type;
    }

    private ArbitrateRecordsQueryReq createMockArbitrateRecordsQueryReq() {
        ArbitrateRecordsQueryReq req = new ArbitrateRecordsQueryReq();
        // 简化实现，使用反射或默认值
        return req;
    }

    private ArbitrateRecordQueryResp createMockArbitrateRecordQueryResp() {
        ArbitrateRecordQueryResp resp = new ArbitrateRecordQueryResp();
        // 简化实现
        return resp;
    }

    private ArbitrateApplyReq createMockArbitrateApplyReq() {
        ArbitrateApplyReq req = new ArbitrateApplyReq();
        // 简化实现，不依赖具体字段
        return req;
    }

    private NoticeInfoReq createMockNoticeInfoReq() {
        NoticeInfoReq req = new NoticeInfoReq();
        req.setTitle("测试公告");
        // 简化实现，不依赖可能不存在的字段
        return req;
    }

    private ContractInfoReq createMockContractInfoReq() {
        ContractInfoReq req = new ContractInfoReq();
        // 简化实现，避免依赖具体字段名
        return req;
    }

    private ContractInfo createMockContractInfo() {
        ContractInfo info = new ContractInfo();
        info.setId(1L);
        // 简化实现，避免依赖具体字段名
        return info;
    }

    private NoticeInfo createMockNoticeInfo() {
        NoticeInfo info = new NoticeInfo();
        info.setId(1L);
        info.setTitle("测试公告");
        // 简化实现，避免依赖具体字段名
        return info;
    }

    private NoticePublishReq createMockNoticePublishReq() {
        NoticePublishReq req = new NoticePublishReq();
        req.setId(1L);
        req.setTitle("发布公告标题");
        // 简化实现，只设置必要字段
        req.setManagerName("testManager");
        return req;
    }

    private Map<String, Object> createMockCustomer() {
        Map<String, Object> customer = new HashMap<>();
        customer.put("userName", "testManager");
        customer.put("phonenumber", "13800138000");
        customer.put("roles", Arrays.asList(createMockRole()));
        return customer;
    }

    private Map<String, Object> createMockRole() {
        Map<String, Object> role = new HashMap<>();
        role.put("roleKey", "manager");
        return role;
    }
} 