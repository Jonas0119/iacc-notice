// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.controller;

import org.slf4j.LoggerFactory;
import com.tjfae.common.core.domain.R;
import com.tjfae.notice.response.PayCallbackDto;
import java.util.Iterator;
import com.tjfae.common.core.utils.StringUtils;
import com.tjfae.common.log.annotation.TjfaeLog;
import com.tjfae.notice.entity.NoticePublish;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.annotations.ApiOperation;
import com.tjfae.notice.utils.DateUtils;
import com.tjfae.notice.enums.RoleEnum;
import com.tjfae.notice.entity.NoticeAuditLog;
import java.util.Date;
import com.tjfae.notice.enums.StatusEnum;
import com.tjfae.common.core.text.UUID;
import com.tjfae.notice.request.NoticePublishReq;
import com.tjfae.notice.entity.ContractInfo;
import com.alibaba.fastjson.JSONObject;
import com.tjfae.notice.entity.NoticeInfo;
import com.tjfae.notice.request.ContractInfoReq;
import com.tjfae.notice.request.NoticeInfoReq;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tjfae.notice.request.BlockChainReq;
import java.util.Map;
import java.io.File;
import com.tjfae.notice.utils.FileUtils;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tjfae.notice.request.EvidenceInfoReq;
import com.tjfae.notice.request.EvidencePageQueryByUserReq;
import javax.servlet.http.HttpServletResponse;
import com.tjfae.notice.request.EvidenceHistoryQueryReq;
import org.springframework.validation.annotation.Validated;
import com.tjfae.notice.request.ArbitrateApplyReq;
import com.tjfae.common.core.web.page.TableDataInfo;
import java.util.ArrayList;
import com.tjfae.notice.base.Global;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.tjfae.notice.request.ArbitrateRecordsQueryReq;
import com.tjfae.notice.entity.ArbitralInstitution;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.tjfae.common.core.web.domain.AjaxResult;
import com.tjfae.notice.service.IUserCenterService;
import com.tjfae.notice.service.impl.EvidenceServiceImpl;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import com.tjfae.notice.service.impl.ArbitrateTypeServiceImpl;
import javax.annotation.Resource;
import com.tjfae.notice.service.impl.ArbitralInstitutionServiceImpl;
import org.slf4j.Logger;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "arbitrate" })
@Api(value = "\u516c\u793a\u516c\u544a-PC", tags = { "\u516c\u793a\u516c\u544a-PC" })
public class ArbitrateController extends BaseController
{
    private static final Logger log;
    @Resource
    private ArbitralInstitutionServiceImpl arbitralInstitutionService;
    @Resource
    private ArbitrateTypeServiceImpl arbitrateTypeService;
    @Resource
    private ArbitrateServiceImpl arbitrateService;
    @Resource
    private EvidenceServiceImpl evidenceService;
    @Resource
    private IUserCenterService userCenterService;
    
    @GetMapping({ "getArbitralInstitution" })
    public Object getArbitralInstitution() {
        Object obj = null;
        try {
            obj = this.arbitralInstitutionService.getArbitralInstitution();
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @GetMapping({ "/searchArbitralInstitution" })
    public Object search(final String name) {
        List<ArbitralInstitution> obj = null;
        try {
            this.startPage();
            obj = this.arbitralInstitutionService.search(name);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return this.getDataTable((List)obj);
    }
    
    @GetMapping({ "getArbitrateType" })
    public Object getArbitrateType() {
        Object obj = null;
        try {
            obj = this.arbitrateTypeService.getAll();
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @PostMapping({ "getArbitrationRecords" })
    public Object getArbitrationRecords(@RequestBody final ArbitrateRecordsQueryReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.getArbitrationRecords(req);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @PostMapping({ "getArbitrationRecordPage" })
    public Object getArbitrationRecordPage(@RequestBody final ArbitrateRecordsQueryReq req) {
        List obj = null;
        try {
            Global.startPage(Integer.parseInt(req.getPageIndex()), Integer.parseInt(req.getPageSize()));
            obj = this.arbitrateService.getArbitrationRecords(req);
            final TableDataInfo tableDataInfo = this.getDataTable(obj);
            return AjaxResult.success((Object)tableDataInfo);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.success((Object)this.getDataTable((List)new ArrayList()));
        }
    }
    
    @PostMapping({ "arbitrationApplication" })
    public Object arbitrationApplication(@Validated @RequestBody final ArbitrateApplyReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.arbitrationApplication(req);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @PostMapping({ "sendArbitrationApplicationEmail" })
    public Object sendArbitrationApplicationEmail(@RequestBody final EvidenceHistoryQueryReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.sendArbitrationApplicationEmail(req.getToMail());
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @PostMapping({ "downloadEvidence" })
    public Object downloadEvidence(@RequestBody final EvidenceHistoryQueryReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.downloadEvidence(req.getEvidenceId(), req.getUserPlatform(), req.getToMail());
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @GetMapping({ "evidenceReportPreview" })
    public void evidenceReportPreview(final EvidenceHistoryQueryReq req, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.evidenceReportPreview(req.getEvidenceId(), req.getUserPlatform(), response);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @PostMapping({ "queryEvidenceByUser" })
    public Object queryEvidenceByUser(@RequestBody final EvidencePageQueryByUserReq req) {
        final Object obj = null;
        try {
            return this.evidenceService.getUserEvidencePages(req);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
    }
    
    @GetMapping({ "queryHistoryForKey" })
    public Object queryHistoryForKey(final EvidenceHistoryQueryReq req) {
        final Object obj = null;
        try {
            return this.evidenceService.getEvidenceDetail(req);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
    }
    
    @GetMapping({ "queryEvidenceRecords" })
    public AjaxResult queryEvidenceRecords(final EvidenceInfoReq req) {
        final Object obj = null;
        try {
            return AjaxResult.success((Object)this.evidenceService.queryEvidenceRecords(req));
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
    }
    
    @GetMapping({ "queryByTxId" })
    public Object queryByTxId(final String txId) {
        final Object obj = null;
        try {
            return this.evidenceService.getTxDetail(txId);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
    }
    
    @PostMapping({ "uploadArbitrationApplication" })
    public Object uploadArbitrationApplication(@RequestParam("file") final MultipartFile file) {
        Object obj = null;
        try {
            obj = this.arbitrateService.uploadArbitrationApplication(file);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @GetMapping({ "getUserInfo" })
    public Object getUserInfo(@RequestParam("loginAccount") final String loginAccount) {
        Object obj = null;
        try {
            obj = this.arbitrateService.getUserInfo(loginAccount);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @GetMapping({ "/previewEvidence" })
    public void previewEvidence(final HttpServletResponse httpServletResponse, @RequestParam("url") final String url) {
        try {
            this.arbitrateService.previewEvidence(httpServletResponse, url);
        }
        catch (final Exception e) {
            ArbitrateController.log.warn(e.getMessage());
        }
    }
    
    @GetMapping({ "/testProductCenterOss" })
    public void testProductCenterOss(final HttpServletResponse httpServletResponse, @RequestParam("url") final String url) {
        try {
            this.arbitrateService.testProductCenterOss(httpServletResponse, url);
        }
        catch (final Exception e) {
            ArbitrateController.log.warn(e.getMessage());
        }
    }
    
    @PostMapping({ "uploadArbitrationApplicationNew" })
    public Object uploadArbitrationApplicationNew(@RequestParam("file") final MultipartFile file) {
        final Map<String, Object> map = new HashMap<String, Object>();
        try {
            final Object obj = this.arbitrateService.uploadArbitrationApplication(file);
            ArbitrateController.log.info("\u6587\u4ef6\u4e0a\u4f20\u5730\u5740:" + obj.toString());
            final File files = FileUtils.transferToFile(file);
            final String fileHash = FileUtils.getFileHash(files);
            final File f = new File(files.toURI());
            if (f.delete()) {
                ArbitrateController.log.info("\u4e34\u65f6\u6587\u4ef6\u5220\u9664\u6210\u529f");
            }
            else {
                ArbitrateController.log.info("\u4e34\u65f6\u6587\u4ef6\u5220\u9664\u5931\u8d25");
            }
            map.put("path", obj);
            map.put("hash", fileHash);
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u4e0a\u94fe\u6587\u4ef6\u4e0a\u4f20\u5931\u8d25\uff1a", (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success((Object)map);
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/upBlockchain" })
    public AjaxResult upBlockchain(@Validated @RequestBody final BlockChainReq param) {
        String resp = null;
        try {
            resp = this.arbitrateService.upBlockSkyChain(param);
        }
        catch (final Exception e) {
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f", (Object)resp);
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/saveNotice" })
    public AjaxResult saveNotice(@Validated @RequestBody final NoticeInfoReq param) {
        try {
            this.arbitrateService.saveNotice(param);
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u4fdd\u5b58\u516c\u793a\u516c\u544a\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/saveContract" })
    public AjaxResult saveContract(@Validated @RequestBody final ContractInfoReq param) {
        try {
            this.arbitrateService.saveContract(param);
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u5408\u540c\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/getContractById" })
    public AjaxResult getContractById(final int id) {
        try {
            return AjaxResult.success((Object)this.arbitrateService.findContractById(id));
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u67e5\u8be2\u5408\u540c\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/getNoticeById" })
    public AjaxResult getNoticeById(final int id) {
        try {
            return AjaxResult.success((Object)this.arbitrateService.findNoticeById(id));
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u67e5\u8be2\u516c\u544a\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @PostMapping({ "downloadEvidenceNew" })
    public Object downloadEvidenceNew(@RequestBody final EvidenceHistoryQueryReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.downloadEvidence(req.getEvidenceId(), req.getUserPlatform(), req.getToMail());
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @GetMapping({ "/noticeReportPreview" })
    public void noticeReportPreview(final int id, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.noticeReportPreview(id, response);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @GetMapping({ "/contractReportPreview" })
    public void contractReportPreview(final int id, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.contractReportPreview(id, response);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryNoticePageList" })
    public TableDataInfo queryNoticePageList(final NoticeInfo param) {
        this.startPage();
        ArbitrateController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        final List<NoticeInfo> infoList = this.arbitrateService.queryNoticePageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryContractPageList" })
    public TableDataInfo queryContractPageList(final ContractInfo param) {
        this.startPage();
        ArbitrateController.log.info("\u67e5\u8be2\u5408\u540c\u5b58\u8bc1\u5206\u9875\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        final List<ContractInfo> infoList = this.arbitrateService.queryContractPageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @ApiOperation(value = "\u4fdd\u5b58\u516c\u793a\u516c\u544a\u53d1\u5e03", produces = "application/json")
    @Transactional(rollbackFor = { Exception.class })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/saveNoticePub" })
    public AjaxResult saveNoticePub(@Validated @RequestBody final NoticePublishReq param) {
        try {
            ArbitrateController.log.info("saveNoticePub \u5f00\u59cb\u5ba2\u6237\u7ecf\u7406\u67e5\u8be2\uff1a{}", (Object)param.getManagerName());
            final boolean flag = this.getManager(param);
            if (!flag) {
                return AjaxResult.error("\u8be5\u5ba2\u6237\u7ecf\u7406\u4e0d\u5b58\u5728\uff0c\u8bf7\u67e5\u8bc1\u540e\u8f93\u5165");
            }
            ArbitrateController.log.info("saveNoticePub \u5b8c\u6210\u5ba2\u6237\u7ecf\u7406\u67e5\u8be2");
            ArbitrateController.log.info("saveNoticePub \u5f00\u59cb\u4fdd\u5b58\u6570\u636e");
            if ("online".equals(param.getPayType())) {
                final String orderId = UUID.randomUUID().toString().replace("-", "");
                param.setPayOrderId(orderId);
            }
            param.setStatus(StatusEnum.SUBMIT.getValue());
            param.setPayTime(new Date());
            param.setPayStatus("2");
            this.arbitrateService.saveNoticePub(param);
            final NoticeAuditLog noticeAuditLog = new NoticeAuditLog();
            noticeAuditLog.setNoticeId(param.getId());
            noticeAuditLog.setUserId(param.getUserId());
            noticeAuditLog.setUserName(param.getUserName());
            noticeAuditLog.setCreator(param.getCreator());
            noticeAuditLog.setCreatorName(param.getCreatorName());
            noticeAuditLog.setRole(RoleEnum.SUBMIT.getLabel());
            noticeAuditLog.setReason(StatusEnum.SUBMIT.getLabel());
            noticeAuditLog.setCreateTime(DateUtils.getCurrentDate());
            this.arbitrateService.insertLog(noticeAuditLog);
            ArbitrateController.log.info("saveNoticePub \u5b8c\u6210\u4fdd\u5b58\u6570\u636e");
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u4fdd\u5b58\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error("\u4fdd\u5b58\u5931\u8d25");
        }
        String frontUrl = null;
        if ("online".equals(param.getPayType())) {
            frontUrl = this.arbitrateService.payNotice(param);
        }
        return AjaxResult.success("\u64cd\u4f5c\u6210\u529f", (Object)frontUrl);
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u4fe1\u606f", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u4fe1\u606f", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/getNoticePubById" })
    public AjaxResult getNoticePubById(final int id) {
        try {
            return AjaxResult.success((Object)this.arbitrateService.findNoticePubById(id));
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u67e5\u8be2\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u7edf\u8ba1\u6d4f\u89c8\u91cf", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u7edf\u8ba1\u6d4f\u89c8\u91cf", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/viewNoticePubById" })
    public AjaxResult viewNoticePubById(final NoticePublish noticePublish) {
        try {
            if (noticePublish.getViews() == null) {
                noticePublish.setViews(1);
            }
            this.arbitrateService.viewNoticePubById(noticePublish);
            final NoticePublish data = this.arbitrateService.findNoticePubById(noticePublish.getId().intValue());
            if (data == null || (!StatusEnum.PUBLISH.getValue().equals(data.getStatus()) && !StatusEnum.HISTORY.getValue().equals(data.getStatus()))) {
                return AjaxResult.error("\u516c\u544a\u4e0d\u5b58\u5728");
            }
            return AjaxResult.success((Object)data);
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u67e5\u8be2\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @ApiOperation(value = "\u9884\u89c8\u516c\u793a\u516c\u544a\u53d1\u5e03\u62a5\u544a", produces = "application/json")
    @GetMapping({ "/noticePubReportPreview" })
    public void noticePubReportPreview(final int id, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.noticePubReportPreview(id, response);
        }
        catch (final Exception e) {
            ArbitrateController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryNoticePubPageList" })
    public TableDataInfo queryNoticePubPageList(final NoticePublish param) {
        this.startPage();
        ArbitrateController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        param.setStatuses("0,1,2,3,4");
        final List<NoticePublish> infoList = this.arbitrateService.queryNoticePubPageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @TjfaeLog
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5c55\u793a\u5217\u8868", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5c55\u793a\u5217\u8868", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryNoticePubShowPageList" })
    public TableDataInfo queryNoticePubShowPageList(final NoticePublish param) {
        this.startPage();
        ArbitrateController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5c55\u793a\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        param.setStatuses("3,5");
        param.setPublishTime(new Date());
        final List<NoticePublish> infoList = this.arbitrateService.queryNoticePubPageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u65e5\u5fd7\u5217\u8868", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u65e5\u5fd7\u5217\u8868", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryAuditLogPageList" })
    public TableDataInfo queryAuditLogPageList(final NoticeAuditLog param) {
        this.startPage();
        ArbitrateController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u65e5\u5fd7\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        final List<NoticeAuditLog> logList = this.arbitrateService.queryAuditLogPageList(param);
        return this.getDataTable((List)logList);
    }
    
    @TjfaeLog
    @ApiOperation(value = "\u7f16\u8f91\u516c\u793a\u516c\u544a\u53d1\u5e03", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/updateNoticePub" })
    public AjaxResult updateNoticePub(@Validated @RequestBody final NoticePublishReq param) {
        try {
            param.setStatus(StatusEnum.SUBMIT.getValue());
            final boolean flag = this.getManager(param);
            if (!flag) {
                return AjaxResult.error("\u8be5\u5ba2\u6237\u7ecf\u7406\u4e0d\u5b58\u5728\uff0c\u8bf7\u67e5\u8bc1\u540e\u8f93\u5165");
            }
            this.arbitrateService.updateNoticePub(param);
            final NoticeAuditLog log = new NoticeAuditLog();
            log.setNoticeId(param.getId());
            log.setUserId(param.getUserId());
            log.setUserName(param.getUserName());
            log.setCreator(param.getCreator());
            log.setCreatorName(param.getCreatorName());
            log.setRole(RoleEnum.SUBMIT.getLabel());
            log.setReason(StatusEnum.SUBMIT.getLabel());
            log.setCreateTime(DateUtils.getCurrentDate());
            this.arbitrateService.insertLog(log);
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u7f16\u8f91\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @ApiOperation(value = "\u5220\u9664\u516c\u793a\u516c\u544a\u53d1\u5e03", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/deleteNoticePub" })
    public AjaxResult deleteNoticePub(final int id) {
        try {
            this.arbitrateService.deleteNoticePub(id);
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u5220\u9664\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    public boolean getManager(final NoticePublishReq param) {
        boolean flag = true;
        try {
            if (StringUtils.isNotEmpty(param.getManagerName())) {
                flag = false;
                final List<Map<String, Object>> customerList = this.userCenterService.getCustomerList(param.getManagerName());
                for (final Map<String, Object> customer : customerList) {
                    if (param.getManagerName().equals(customer.get("userName"))) {
                        ArbitrateController.log.info("getManager \u5df2\u67e5\u8be2\u5230\u5ba2\u6237\u7ecf\u7406\uff1a{}", (Object)param.getManagerName());
                        if (customer.get("roles") != null) {
                            final List<Map<String, Object>> roles = (List<Map<String, Object>>) customer.get("roles");
                            for (final Map<String, Object> role : roles) {
                                if ("no".equals(role.get("roleKey"))) {
                                    ArbitrateController.log.info("getManager roleKey\u4e3ano");
                                    return false;
                                }
                            }
                        }
                        ArbitrateController.log.info("getManager flag=true");
                        param.setManagerPhone(customer.get("phonenumber").toString());
                        flag = true;
                    }
                }
            }
        }
        catch (final Exception e) {
            ArbitrateController.log.error("\u5ba2\u6237\u7ecf\u7406\u67e5\u8be2\u5f02\u5e38\uff1a", (Throwable)e);
        }
        return flag;
    }
    
    @TjfaeLog
    @PostMapping({ "/pay/payCallback" })
    public R payCallback(@RequestBody final PayCallbackDto payCallbackDto) {
        return this.arbitrateService.updateOrderStatus(payCallbackDto);
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ArbitrateController.class);
    }
}
