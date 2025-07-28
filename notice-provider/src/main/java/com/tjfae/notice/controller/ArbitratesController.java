// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.controller;

import org.slf4j.LoggerFactory;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.common.log.annotation.TjfaeLog;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.request.NoticeAuditReq;
import com.tjfae.notice.entity.NoticePublish;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import com.tjfae.notice.enums.SourceTypeEnum;
import com.tjfae.notice.enums.SceneTypeEnum;
import com.tjfae.notice.request.NoticePushReq;
import io.swagger.annotations.ApiOperation;
import com.tjfae.notice.utils.DateUtils;
import com.tjfae.notice.enums.RoleEnum;
import com.tjfae.notice.entity.NoticeAuditLog;
import com.tjfae.notice.enums.StatusEnum;
import com.tjfae.notice.request.NoticePublishReq;
import java.io.IOException;
import com.tjfae.common.core.utils.poi.ExcelUtil;
import com.tjfae.notice.entity.ContractInfo;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.tjfae.common.core.web.page.TableDataInfo;
import com.tjfae.notice.entity.NoticeInfo;
import com.tjfae.notice.request.ContractInfoReq;
import com.tjfae.notice.request.NoticeInfoReq;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.annotation.Validated;
import com.tjfae.notice.request.BlockChainReq;
import java.util.Map;
import java.io.File;
import com.tjfae.notice.utils.FileUtils;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import com.tjfae.common.core.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.RequestBody;
import com.tjfae.notice.request.EvidenceHistoryQueryReq;
import com.tjfae.notice.service.impl.ArbitrateServiceImpl;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/arbitrates" })
@Api(value = "\u516c\u793a\u516c\u544a-\u540e\u7ba1", tags = { "\u516c\u793a\u516c\u544a-\u540e\u7ba1" })
public class ArbitratesController extends BaseController
{
    private static final Logger log;
    @Resource(name = "threadPoolInstance")
    private ExecutorService executorService;
    @Resource
    private ArbitrateServiceImpl arbitrateService;
    
    @PostMapping({ "/downloadEvidence" })
    public Object downloadEvidence(@RequestBody final EvidenceHistoryQueryReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.downloadEvidence(req.getEvidenceId(), req.getUserPlatform(), req.getToMail());
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(obj);
    }
    
    @GetMapping({ "/evidenceReportPreview" })
    public void evidenceReportPreview(final EvidenceHistoryQueryReq req, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.evidenceReportPreview(req.getEvidenceId(), req.getUserPlatform(), response);
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @PostMapping({ "/uploadArbitrationApplication" })
    public Object uploadArbitrationApplication(@RequestParam("file") final MultipartFile file) {
        Object obj = null;
        try {
            obj = this.arbitrateService.uploadArbitrationApplication(file);
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
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
            ArbitratesController.log.warn(e.getMessage());
        }
    }
    
    @PostMapping({ "/uploadArbitrationApplicationNew" })
    public Object uploadArbitrationApplicationNew(@RequestParam("file") final MultipartFile file) {
        final Map<String, Object> map = new HashMap<String, Object>();
        try {
            final Object obj = this.arbitrateService.uploadArbitrationApplication(file);
            ArbitratesController.log.info("\u6587\u4ef6\u4e0a\u4f20\u5730\u5740:" + obj.toString());
            final File files = FileUtils.transferToFile(file);
            final String fileHash = FileUtils.getFileHash(files);
            final File f = new File(files.toURI());
            if (f.delete()) {
                ArbitratesController.log.info("\u4e34\u65f6\u6587\u4ef6\u5220\u9664\u6210\u529f");
            }
            else {
                ArbitratesController.log.info("\u4e34\u65f6\u6587\u4ef6\u5220\u9664\u5931\u8d25");
            }
            map.put("path", obj);
            map.put("hash", fileHash);
        }
        catch (final Exception e) {
            ArbitratesController.log.error("\u4e0a\u94fe\u6587\u4ef6\u4e0a\u4f20\u5931\u8d25\uff1a", (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success((Object)map);
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/upBlockchain" })
    public AjaxResult upBlockchain(@Validated @RequestBody final BlockChainReq param) {
        String resp = null;
        try {
            resp = this.arbitrateService.upBlockchain(param);
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
            ArbitratesController.log.error("\u4fdd\u5b58\u516c\u793a\u516c\u544a\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
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
            ArbitratesController.log.error("\u5408\u540c\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
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
            ArbitratesController.log.error("\u67e5\u8be2\u5408\u540c\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/getNoticeById" })
    public AjaxResult getNoticeById(final int id) {
        try {
            return AjaxResult.success((Object)this.arbitrateService.findNoticeById(id));
        }
        catch (final Exception e) {
            ArbitratesController.log.error("\u67e5\u8be2\u516c\u544a\u5b58\u8bc1\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryNoticePageList" })
    public TableDataInfo queryNoticePageList(final NoticeInfo param) {
        this.startPage();
        ArbitratesController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        final List<NoticeInfo> infoList = this.arbitrateService.queryNoticePageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryContractPageList" })
    public TableDataInfo queryContractPageList(final ContractInfo param) {
        this.startPage();
        ArbitratesController.log.info("\u67e5\u8be2\u5408\u540c\u5b58\u8bc1\u5206\u9875\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        final List<ContractInfo> infoList = this.arbitrateService.queryContractPageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @PostMapping({ "/downloadEvidenceNew" })
    public Object downloadEvidenceNew(@RequestBody final EvidenceHistoryQueryReq req) {
        Object obj = null;
        try {
            obj = this.arbitrateService.downloadEvidence(req.getEvidenceId(), req.getUserPlatform(), req.getToMail());
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
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
            ArbitratesController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @GetMapping({ "/contractReportPreview" })
    public void contractReportPreview(final int id, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.contractReportPreview(id, response);
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @GetMapping({ "/exportContract" })
    public void exportContract(final HttpServletResponse response, final ContractInfo param) throws IOException {
        final List<ContractInfo> list = this.arbitrateService.queryContractPageList(param);
        final ExcelUtil<ContractInfo> util = (ExcelUtil<ContractInfo>)new ExcelUtil((Class)ContractInfo.class);
        util.exportExcel(response, (List)list, "\u5408\u540c\u5b58\u8bc1\u6570\u636e");
    }
    
    @GetMapping({ "/exportNotice" })
    public void exportNotice(final HttpServletResponse response, final NoticeInfo param) throws IOException {
        final List<NoticeInfo> list = this.arbitrateService.queryNoticePageList(param);
        final ExcelUtil<NoticeInfo> util = (ExcelUtil<NoticeInfo>)new ExcelUtil((Class)NoticeInfo.class);
        util.exportExcel(response, (List)list, "\u516c\u793a\u516c\u544a\u5b58\u8bc1\u6570\u636e");
    }
    
    @ApiOperation(value = "\u4fdd\u5b58\u516c\u793a\u516c\u544a\u53d1\u5e03", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/saveNoticePub" })
    public AjaxResult saveNoticePub(@Validated @RequestBody final NoticePublishReq param) {
        try {
            param.setStatus(StatusEnum.SUBMIT.getValue());
            this.arbitrateService.saveNoticePub(param);
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
            ArbitratesController.log.error("\u4fdd\u5b58\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @ApiOperation(value = "\u516c\u793a\u516c\u544a\u63a8\u9001", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/saveNoticePush" })
    public AjaxResult saveNoticePush(@Validated @RequestBody final NoticePushReq req) {
        try {
            final NoticePublishReq param = new NoticePublishReq();
            param.setSceneType(SceneTypeEnum.AUCTION.getValue());
            param.setSource(SourceTypeEnum.AUCTION.getLabel());
            param.setSourceTag(SourceTypeEnum.AUCTION.getValue());
            param.setStatus(StatusEnum.SUBMIT.getValue());
            BeanUtils.copyProperties((Object)req, (Object)param, this.getNullPropertyNames((Object)req));
            this.arbitrateService.saveNoticePub(param);
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
            ArbitratesController.log.error("\u4fdd\u5b58\u516c\u793a\u516c\u544a\u63a8\u9001\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u4fe1\u606f", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u4fe1\u606f", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/getNoticePubById" })
    public AjaxResult getNoticePubById(final int id) {
        try {
            return AjaxResult.success((Object)this.arbitrateService.findNoticePubById(id));
        }
        catch (final Exception e) {
            ArbitratesController.log.error("\u67e5\u8be2\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryNoticePubPageList" })
    public TableDataInfo queryNoticePubPageList(final NoticePublish param) {
        this.startPage();
        ArbitratesController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        param.setStatuses("0,1,2,3,4,6");
        final List<NoticePublish> infoList = this.arbitrateService.queryNoticePubPageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u5217\u8868", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u5217\u8868", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryNoticePubAuditPageList" })
    public TableDataInfo queryNoticePubAuditPageList(final NoticePublish param) {
        this.startPage();
        ArbitratesController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        param.setStatuses("0,1");
        final List<NoticePublish> infoList = this.arbitrateService.queryNoticePubPageList(param);
        return this.getDataTable((List)infoList);
    }
    
    @ApiOperation(value = "\u9884\u89c8\u516c\u793a\u516c\u544a\u53d1\u5e03\u62a5\u544a", produces = "application/json")
    @GetMapping({ "/noticePubReportPreview" })
    public void noticePubReportPreview(final int id, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.noticePubReportPreview(id, response);
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @ApiOperation(value = "\u4e0b\u8f7d\u516c\u793a\u516c\u544a\u53d1\u5e03\u62a5\u544a", produces = "application/json")
    @GetMapping({ "/noticePubReportDownload" })
    public void noticePubReportDownload(final int id, final HttpServletResponse response) {
        Object obj = null;
        try {
            obj = this.arbitrateService.noticePubReportDownload(id, response);
        }
        catch (final Exception e) {
            ArbitratesController.log.error(e.toString(), (Throwable)e);
        }
    }
    
    @GetMapping({ "/exportNoticePub" })
    @ApiOperation(value = "\u5bfc\u51fa\u516c\u793a\u516c\u544a\u53d1\u5e03\u6570\u636e", produces = "application/json")
    public void exportNoticePub(final HttpServletResponse response, final NoticePublish param) throws IOException {
        final List<NoticePublish> list = this.arbitrateService.queryNoticePubPageList(param);
        final ExcelUtil<NoticePublish> util = (ExcelUtil<NoticePublish>)new ExcelUtil((Class)NoticePublish.class);
        util.exportExcel(response, (List)list, "\u516c\u793a\u516c\u544a\u53d1\u5e03\u6570\u636e");
    }
    
    @TjfaeLog
    @ApiOperation(value = "\u5ba1\u6838\u516c\u793a\u516c\u544a\u53d1\u5e03", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/auditNoticePub" })
    public AjaxResult auditNoticePub(@Validated @RequestBody final NoticeAuditReq param) {
        try {
            this.arbitrateService.auditNoticePub(param);
        }
        catch (final BusinessException e) {
            ArbitratesController.log.error("\u5ba1\u6838\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25BusinessException", (Throwable)e);
            return AjaxResult.error(e.getMessage());
        }
        catch (final Exception e2) {
            ArbitratesController.log.error("\u5ba1\u6838\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25Exception", (Throwable)e2);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @ApiOperation(value = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u65e5\u5fd7\u5217\u8868", produces = "application/json")
    @ApiResponses({ @ApiResponse(code = 200, message = "\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u65e5\u5fd7\u5217\u8868", response = NoticePublish.class) })
    @RequestMapping(method = { RequestMethod.POST }, value = { "/queryAuditLogPageList" })
    public TableDataInfo queryAuditLogPageList(final NoticeAuditLog param) {
        this.startPage();
        ArbitratesController.log.info("\u67e5\u8be2\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u65e5\u5fd7\u5217\u8868\u67e5\u8be2\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)param));
        final List<NoticeAuditLog> logList = this.arbitrateService.queryAuditLogPageList(param);
        return this.getDataTable((List)logList);
    }
    
    @ApiOperation(value = "\u7f16\u8f91\u516c\u793a\u516c\u544a\u53d1\u5e03", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/updateNoticePub" })
    public AjaxResult updateNoticePub(@Validated @RequestBody final NoticePublishReq param) {
        try {
            param.setStatus(StatusEnum.SUBMIT.getValue());
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
            ArbitratesController.log.error("\u7f16\u8f91\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    @ApiOperation(value = "\u7f16\u8f91\u516c\u793a\u516c\u544a\u53d1\u5e03\u516c\u544a\u6b63\u6587", produces = "application/json")
    @RequestMapping(method = { RequestMethod.POST }, value = { "/updateNoticePubContent" })
    public AjaxResult updateNoticePubContent(@Validated @RequestBody final NoticePublishReq param) {
        try {
            final NoticePublishReq req = new NoticePublishReq();
            final NoticePublish noticePub = this.arbitrateService.findNoticePubById(param.getId().intValue());
            BeanUtils.copyProperties((Object)noticePub, (Object)req);
            req.setRemark(param.getRemark());
            req.setNoticeUrl(param.getNoticeUrl());
            this.arbitrateService.updateNoticePub(req);
            if (StringUtils.isNotEmpty(param.getNoticeUrl()) && !param.getNoticeUrl().equals(noticePub.getNoticeUrl())) {
                this.executorService.execute(() -> {
                    BeanUtils.copyProperties((Object)req, (Object)noticePub);
                    this.arbitrateService.urlNotify(noticePub);
                    return;
                });
            }
        }
        catch (final Exception e) {
            ArbitratesController.log.error("\u7f16\u8f91\u516c\u793a\u516c\u544a\u53d1\u5e03\u516c\u544a\u6b63\u6587\u5931\u8d25", (Throwable)e);
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
            ArbitratesController.log.error("\u5220\u9664\u516c\u793a\u516c\u544a\u53d1\u5e03\u5931\u8d25", (Throwable)e);
            return AjaxResult.error();
        }
        return AjaxResult.success("\u6210\u529f");
    }
    
    static {
        log = LoggerFactory.getLogger((Class)ArbitratesController.class);
    }
}
