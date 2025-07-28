// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import com.tjfae.notice.utils.ZipUtils;
import com.github.pagehelper.util.StringUtil;
import com.tjfae.notice.response.PayCallbackDto;
import cn.hutool.core.util.ObjectUtil;
import com.tjfae.common.core.constant.Constants;
import com.tjfae.common.core.domain.R;
import com.tjfae.notice.response.PayDto;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.safety.Whitelist;
import org.jsoup.Jsoup;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import com.tjfae.notice.request.NoticeNotifyReq;
import com.tjfae.common.core.web.domain.AjaxResult;
import com.tjfae.notice.utils.http.IpUtil;
import com.tjfae.notice.common.util.OSSUnit;
import com.alibaba.fastjson.JSONArray;
import com.tjfae.notice.request.NoticeChannelCancelReq;
import com.tjfae.notice.enums.SceneTypeEnum;
import javax.servlet.http.HttpServletRequest;
import com.tjfae.notice.request.NoticeChannelPushReq;
import com.tjfae.notice.enums.RoleEnum;
import com.tjfae.notice.enums.PlatformTypeEnum;
import java.util.Calendar;
import com.tjfae.notice.enums.StatusEnum;
import com.tjfae.notice.request.NoticeAuditReq;
import com.tjfae.notice.entity.NoticeAuditLog;
import com.tjfae.notice.enums.SourceTypeEnum;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import com.tjfae.notice.request.NoticePublishReq;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.policy.RenderPolicy;
import com.tjfae.notice.utils.WangEditor2Word;
import com.deepoove.poi.config.Configure;
import com.tjfae.notice.entity.NoticePublish;
import com.tjfae.notice.request.NoticeInfoReq;
import org.springframework.beans.BeanUtils;
import com.tjfae.common.core.utils.SecurityUtils;
import java.util.Random;
import com.tjfae.notice.request.ContractInfoReq;
import com.alibaba.fastjson.JSON;
import com.tjfae.notice.common.util.HttpUtils;
import com.tjfae.notice.request.BlockChainReq;
import com.tjfae.notice.entity.hs.UCCompany;
import com.tjfae.notice.entity.hs.UCUserInfo;
import com.tjfae.notice.entity.hs.HSCompanyInfoBo;
import com.tjfae.notice.entity.hs.HSUserInfoBo;
import com.tjfae.notice.entity.hs.UserInfo;
import com.tjfae.notice.enums.ContractTypeEnum;
import com.tjfae.notice.entity.ContractInfo;
import com.alibaba.fastjson.JSONObject;
import com.tjfae.notice.utils.DateUtils;
import com.tjfae.notice.entity.NoticeInfo;
import java.lang.reflect.Field;
import com.aspose.words.Document;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import com.aspose.words.net.System.Data.DataRelation;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataColumn;
import com.aspose.words.net.System.Data.DataTable;
import com.tjfae.notice.entity.EvidenceReport;
import com.aliyun.oss.model.OSSObject;
import java.io.IOException;
import java.net.URLDecoder;
import com.aliyun.oss.OSS;
import com.tjfae.notice.utils.OssClientUtil;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import com.tjfae.notice.base.Global;
import java.io.OutputStream;
import java.net.URLEncoder;
import com.tjfae.notice.utils.json.JsonUtils;
import com.tjfae.notice.entity.EvidenceHistory;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.io.InputStream;
import com.tjfae.notice.entity.ToEmail;
import org.springframework.core.io.ClassPathResource;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.response.ArbitrateRecordQueryResp;
import com.tjfae.notice.request.ArbitrateRecordsQueryReq;
import java.util.Iterator;
import java.util.List;
import com.tjfae.notice.entity.ArbitrateRecordApplicationItem;
import com.tjfae.notice.entity.ArbitrateRecordFileItem;
import java.util.ArrayList;
import java.util.Date;
import com.tjfae.notice.entity.ArbitrateRecord;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.request.ArbitrateApplyReq;
import java.util.Map;
import com.tjfae.notice.config.NotifyUrlProperties;
import com.tjfae.notice.service.IContractService;
import com.tjfae.notice.service.INoticePublishService;
import com.tjfae.notice.service.INoticeService;
import java.util.concurrent.ExecutorService;
import org.springframework.beans.factory.annotation.Value;
import com.tjfae.notice.service.IUserCenterService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@Service
public class ArbitrateServiceImpl
{
    private static Logger log;
    private static Logger logger;
    @Resource
    private MailServiceImpl mailService;
    @Resource
    private ArbitrateRecordServiceImpl arbitrateRecordService;
    @Resource
    private ArbitrateRecordApplicationItemServiceImpl arbitrateRecordApplicationItemService;
    @Resource
    private ArbitrateRecordFileItemServiceImpl arbitrateRecordFileItemService;
    @Resource
    private BcPlatformServiceImpl bcPlatformService;
    @Resource
    private SkyChainPlatformServiceImpl skyChainPlatformService;
    @Resource
    private HSServiceImpl hsService;
    @Resource
    private IUserCenterService userCenterService;
    @Value("${aliyun.oss.key}")
    private String ossKey;
    @Value("${aliyun.oss.secret}")
    private String ossSecret;
    @Value("${aliyun.oss.bucket}")
    private String ossBucket;
    @Value("${aliyun.oss.endpoint}")
    private String ossEndpoint;
    @Value("${oss.productcenter.key}")
    private String productCenterOssKey;
    @Value("${oss.productcenter.secret}")
    private String productCenterOssSecret;
    @Value("${oss.productcenter.bucket}")
    private String productCenterOssBucket;
    @Value("${oss.productcenter.endpoint}")
    private String productCenterOssEndpoint;
    @Resource(name = "threadPoolInstance")
    private ExecutorService executorService;
    @Value("${bcplatform.createEvidence}")
    private String createEvidence;
    @Value("${bcplatform.skychain-createEvidence}")
    private String skyChainCreateEvidence;
    @Value("${templatepath}")
    private String templatepath;
    @Value("${defaultviews:2000}")
    private int defaultviews;
    @Value("${payUrl}")
    private String payUrl;
    @Resource
    private INoticeService noticeService;
    @Resource
    private INoticePublishService noticePublishService;
    @Resource
    private IContractService contractService;
    @Resource
    private NotifyUrlProperties notifyUrlProperties;
    private static Map<Integer, String> titleIndexMap;
    
    public Object arbitrationApplication(final ArbitrateApplyReq req) throws Exception {
        ArbitrateRecord arbitrateRecord = this.arbitrateRecordService.findByEvidenceId(req.getEvidenceId());
        if (null != arbitrateRecord) {
            ArbitrateServiceImpl.logger.warn("The arbitration has been started before!");
            throw new BusinessException("\u6b64\u8d44\u4ea7\u5df2\u7ecf\u53d1\u8d77\u8fc7\u4e89\u8bae\uff01");
        }
        final String businessSide = this.getUserId(req.getLoginAccount(), req.getUser().getUserId(), req.getSearchType());
        arbitrateRecord = new ArbitrateRecord();
        arbitrateRecord.setEvidence_digital_code(req.getEvidenceId());
        arbitrateRecord.setAcceptance_institution(req.getArbitralAgencyName());
        arbitrateRecord.setArbitrate_type_name(req.getArbitrateTypeName());
        arbitrateRecord.setCase_id(this.genArbitrateCaseId());
        arbitrateRecord.setTitle(req.getTitle());
        arbitrateRecord.setCreator(req.getUser().getUserId());
        arbitrateRecord.setBusiness_side(businessSide);
        arbitrateRecord.setUser_platform(req.getUserPlatform());
        arbitrateRecord.setCreate_time(new Date());
        arbitrateRecord.setStatus(1);
        this.arbitrateRecordService.save(arbitrateRecord);
        final List<ArbitrateRecordFileItem> fileItemList = new ArrayList<ArbitrateRecordFileItem>();
        for (final ArbitrateApplyReq.ProofInfo one : req.getProofList()) {
            final ArbitrateRecordFileItem fileItem = new ArbitrateRecordFileItem();
            fileItem.setRecord_id(arbitrateRecord.getId());
            fileItem.setEvidence_digital_code(arbitrateRecord.getEvidence_digital_code());
            fileItem.setFile_name(one.getName());
            fileItem.setHash(one.getHash());
            fileItem.setFile_type(one.getType());
            fileItem.setFile_sn(one.getSn());
            fileItem.setDescription(one.getDescription());
            fileItemList.add(fileItem);
        }
        if (fileItemList.size() > 0) {
            this.arbitrateRecordFileItemService.saveAll(fileItemList);
        }
        final List<ArbitrateRecordApplicationItem> applicationItemList = new ArrayList<ArbitrateRecordApplicationItem>();
        for (final ArbitrateApplyReq.ApplicationInfo one2 : req.getApplicationList()) {
            final ArbitrateRecordApplicationItem applicationItem = new ArbitrateRecordApplicationItem();
            applicationItem.setRecord_id(arbitrateRecord.getId());
            applicationItem.setEvidence_digital_code(arbitrateRecord.getEvidence_digital_code());
            applicationItem.setImage_name(one2.getImageName());
            applicationItem.setImage_url(one2.getImageUrl());
            applicationItemList.add(applicationItem);
        }
        if (applicationItemList.size() > 0) {
            this.arbitrateRecordApplicationItemService.saveAll((List)applicationItemList);
        }
        return null;
    }
    
    public List getArbitrationRecords(final ArbitrateRecordsQueryReq req) {
        final List<ArbitrateRecordQueryResp> resp = new ArrayList<ArbitrateRecordQueryResp>();
        if (StringUtils.isBlank(req.getUser().getUserId())) {
            ArbitrateServiceImpl.logger.warn("No user info in req!");
            return resp;
        }
        final ArbitrateRecord record = new ArbitrateRecord();
        record.setCreator(req.getUser().getUserId());
        if (StringUtils.isNotBlank(req.getUserPlatform())) {
            record.setUser_platform(req.getUserPlatform());
        }
        final List<ArbitrateRecord> recordList = this.arbitrateRecordService.findByUserInfo(record);
        for (final ArbitrateRecord one : recordList) {
            final ArbitrateRecordQueryResp oneResp = new ArbitrateRecordQueryResp();
            oneResp.update(one);
            resp.add(oneResp);
        }
        return resp;
    }
    
    public Object sendArbitrationApplicationEmail(final String to) throws Exception {
        final ClassPathResource classPathResource = new ClassPathResource("ArbitrationApplication.docx");
        final InputStream inputStream = classPathResource.getInputStream();
        final String filename = "\u4ef2\u88c1\u7533\u8bf7\u4e66\u6a21\u677f.docx";
        final ToEmail toEmail = new ToEmail(to, "\u6765\u81ea\u53f8\u6cd5\u5b58\u8bc1\u7684\u4ef2\u88c1\u7533\u8bf7\u4e66\u6a21\u677f", "\u8bf7\u53c2\u8003\u9644\u4ef6\u4fe1\u606f");
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 发送仲裁申请邮件的异步任务
                    // TODO: 实现邮件发送逻辑
                    log.info("异步发送仲裁申请邮件到: {}", to);
                } catch (Exception ex) {
                    log.error("发送仲裁申请邮件失败", ex);
                }
            }
        });
        return null;
    }
    
    public Object downloadEvidence(final String evidenceId, final String userPlatform, final String to) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("userPlatform", userPlatform);
        params.put("evidenceId", evidenceId);
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 下载证据的异步任务
                    log.info("异步下载证据: evidenceId={}, userPlatform={}", evidenceId, userPlatform);
                } catch (Exception ex) {
                    log.error("下载证据失败", ex);
                }
            }
        });
        return null;
    }
    
    public Object evidenceReportPreview(final String evidenceId, final String userPlatform, final HttpServletResponse response) throws Exception {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("userPlatform", userPlatform);
        params.put("evidenceId", evidenceId);
        Object obj = null;
        EvidenceHistory evidenceHistory = null;
        try {
            obj = this.skyChainPlatformService.queryHistoryForKey((Map)params);
            evidenceHistory = (EvidenceHistory)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(obj), (Class)EvidenceHistory.class);
            evidenceHistory.deserialization();
            final List<EvidenceHistory.EvidenceInfo> infos = evidenceHistory.getHistoryDataObj();
            for (final EvidenceHistory.EvidenceInfo e : infos) {
                e.deserialization();
            }
        }
        catch (final Exception e2) {
            throw new BusinessException("\u83b7\u53d6\u5b58\u8bc1id\uff1a" + evidenceId + "\u7684\u8bc1\u636e\u5931\u8d25\uff01");
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("EvidenceReport.pdf", "UTF-8"));
        response.setHeader("Cache-Control", "no-cache");
        final InputStream inputStream = this.buildEvidenceReport(evidenceHistory, false);
        AsposeUtil.wordToPdf(inputStream, (OutputStream)response.getOutputStream());
        return null;
    }
    
    private String genArbitrateCaseId() {
        final StringBuilder caseId = new StringBuilder();
        caseId.append("zc");
        final Date now = new Date();
        String timeStr = Global.DateNoFormat.format(now);
        if (timeStr.length() > 12) {
            timeStr = timeStr.substring(timeStr.length() - 12);
        }
        else {
            for (int i = 0; i < 12 - timeStr.length(); ++i) {
                caseId.append("0");
            }
        }
        caseId.append(timeStr);
        return caseId.toString();
    }
    
    public Object uploadArbitrationApplication(final MultipartFile file) {
        final String fileName = file.getOriginalFilename();
        final String newFileName = UUID.randomUUID().toString() + fileName;
        final OSS ossClient = OssClientUtil.getOssClient(this.ossEndpoint, this.ossKey, this.ossSecret, this.ossBucket);
        if (null == ossClient) {
            ArbitrateServiceImpl.logger.warn("Get oss client failed for bucket:" + this.ossBucket);
            throw new BusinessException("Get oss client failed for bucket:" + this.ossBucket);
        }
        if (!OssClientUtil.uploadFile(ossClient, this.ossBucket, file, newFileName)) {
            ArbitrateServiceImpl.logger.warn("Upload file:" + fileName + " failed");
            throw new BusinessException("Upload file:" + fileName + " failed");
        }
        return this.getArbitrationApplicationFileUrl(newFileName);
    }
    
    public String getArbitrationApplicationFileUrl(final String fileName) {
        String fileUrl = "";
        final OSS ossClient = OssClientUtil.getOssClient(this.ossEndpoint, this.ossKey, this.ossSecret, this.ossBucket);
        if (null == ossClient) {
            ArbitrateServiceImpl.logger.warn("Get oss client failed for bucket:" + this.ossBucket);
            throw new BusinessException("Get oss client failed for bucket:" + this.ossBucket);
        }
        fileUrl = OssClientUtil.getFileUrl(ossClient, this.ossBucket, fileName);
        if (StringUtils.isNotBlank(fileUrl)) {
            final String[] fileUrlArr = fileUrl.split("\\?");
            if (fileUrlArr.length > 0) {
                fileUrl = fileUrlArr[0];
            }
        }
        return fileUrl;
    }
    
    public void previewEvidence(final HttpServletResponse httpResponse, String url) throws Exception {
        url = URLDecoder.decode(url, "UTF-8");
        url = url.replace("+", "%2B");
        InputStream fileStream = null;
        if (url.contains(this.productCenterOssBucket)) {
            final OSS ossClient = OssClientUtil.getOssClient(this.productCenterOssEndpoint, this.productCenterOssKey, this.productCenterOssSecret, this.productCenterOssBucket);
            final String oldStr = "https://" + this.productCenterOssBucket + "." + this.productCenterOssEndpoint + "/";
            final String fileName = url.replace(oldStr, "");
            final OSSObject ossObject = OssClientUtil.downloadFile(ossClient, this.productCenterOssBucket, fileName);
            fileStream = ossObject.getObjectContent();
        }
        else {
            fileStream = OssClientUtil.downloadFile(url);
        }
        httpResponse.setContentType("application/octet-stream;charset=UTF-8");
        httpResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("evidence.pdf", "UTF-8"));
        httpResponse.setHeader("Cache-Control", "no-cache");
        final byte[] buffer = new byte[1024];
        OutputStream os = null;
        try {
            os = (OutputStream)httpResponse.getOutputStream();
            int len;
            while ((len = fileStream.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.flush();
            System.out.println("success");
        }
        catch (final Exception e) {
            e.printStackTrace();
            if (fileStream != null) {
                try {
                    fileStream.close();
                }
                catch (final IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                }
                catch (final Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        finally {
            if (fileStream != null) {
                try {
                    fileStream.close();
                }
                catch (final IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                }
                catch (final Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }
    
    public void testProductCenterOss(final HttpServletResponse httpResponse, String url) throws Exception {
        url = URLDecoder.decode(url, "UTF-8");
        url = url.replace("+", "%2B");
        final OSS ossClient = OssClientUtil.getOssClient(this.productCenterOssEndpoint, this.productCenterOssKey, this.productCenterOssSecret, this.productCenterOssBucket);
        final String oldStr = "https://" + this.productCenterOssBucket + "." + this.productCenterOssEndpoint + "/";
        final String fileName = url.replace(oldStr, "");
        final String downloadFileName = url.substring(url.lastIndexOf("/") + 1);
        ArbitrateServiceImpl.logger.info("fileName === " + fileName);
        final OSSObject ossObject = OssClientUtil.downloadFile(ossClient, this.productCenterOssBucket, fileName);
        final InputStream fileStream = ossObject.getObjectContent();
        httpResponse.setContentType("application/octet-stream;charset=UTF-8");
        httpResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
        httpResponse.setHeader("Cache-Control", "no-cache");
        final byte[] buffer = new byte[1024];
        OutputStream os = null;
        try {
            os = (OutputStream)httpResponse.getOutputStream();
            int len;
            while ((len = fileStream.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.flush();
            System.out.println("success");
        }
        catch (final Exception e) {
            e.printStackTrace();
            if (fileStream != null) {
                try {
                    fileStream.close();
                }
                catch (final IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                }
                catch (final Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        finally {
            if (fileStream != null) {
                try {
                    fileStream.close();
                }
                catch (final IOException e3) {
                    e3.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                }
                catch (final Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
    }
    
    private InputStream buildEvidenceReport(final EvidenceHistory evidenceHistory, final boolean isPdf) throws Exception {
        final ClassPathResource classPathResource = new ClassPathResource("EvidenceReport.docx");
        final InputStream inputStream = classPathResource.getInputStream();
        final EvidenceReport evidenceReport = new EvidenceReport(evidenceHistory);
        final Document doc = AsposeUtil.getDoc(inputStream);
        final DataTable countStatistics = new DataTable("countStatistics");
        countStatistics.getColumns().add("typeName");
        countStatistics.getColumns().add("count");
        final DataTable evidenceStatus = new DataTable("evidenceStatus");
        evidenceStatus.getColumns().add("count");
        evidenceStatus.getColumns().add("typeName");
        final DataTable statisticsList = new DataTable("statisticsList");
        statisticsList.getColumns().add(new DataColumn("Id", (Class)Integer.class));
        statisticsList.getColumns().add("typeName");
        statisticsList.getColumns().add("count");
        final DataTable descriptionList = new DataTable("descriptionList");
        descriptionList.getColumns().add(new DataColumn("statisticsListId", (Class)Integer.class));
        descriptionList.getColumns().add("description");
        final DataTable typeList = new DataTable("typeList");
        typeList.getColumns().add(new DataColumn("Id", (Class)Integer.class));
        typeList.getColumns().add("typeName");
        final DataTable infoList = new DataTable("infoList");
        infoList.getColumns().add(new DataColumn("typeListId", (Class)Integer.class));
        infoList.getColumns().add("description");
        infoList.getColumns().add("fileName");
        infoList.getColumns().add("evidenceId");
        infoList.getColumns().add("hash");
        infoList.getColumns().add("storeTime");
        final Map<String, List<EvidenceReport.Info>> infoMap = evidenceReport.getInfoMap();
        int index = 0;
        for (final Map.Entry<String, List<EvidenceReport.Info>> entry : infoMap.entrySet()) {
            countStatistics.getRows().add(new Object[] { entry.getKey(), entry.getValue().size() });
            evidenceStatus.getRows().add(new Object[] { entry.getValue().size(), entry.getKey() });
            statisticsList.getRows().add(new Object[] { index, entry.getKey(), entry.getValue().size() });
            typeList.getRows().add(new Object[] { index, ArbitrateServiceImpl.titleIndexMap.get(index + 2) + entry.getKey() });
            int j = 1;
            for (final EvidenceReport.Info ei : entry.getValue()) {
                descriptionList.getRows().add(new Object[] { index, j + "." + ei.getFileName() });
                infoList.getRows().add(new Object[] { index, ei.getFileDescription(), ei.getFileName(), ei.getSn(), ei.getTxId(), ei.getDate() });
                ++j;
            }
            ++index;
        }
        final DataSet dataSet = new DataSet();
        dataSet.getTables().add(statisticsList);
        dataSet.getTables().add(descriptionList);
        dataSet.getRelations().add(new DataRelation("DescriptionListForStatisticsList", statisticsList.getColumns().get("Id"), descriptionList.getColumns().get("statisticsListId")));
        final DataSet dataSet2 = new DataSet();
        dataSet2.getTables().add(typeList);
        dataSet2.getTables().add(infoList);
        dataSet2.getRelations().add(new DataRelation("InfoListForTypeList", typeList.getColumns().get("Id"), infoList.getColumns().get("typeListId")));
        final String[] keys = new String[8];
        final String[] values = new String[8];
        int i = 0;
        final Class cls = evidenceReport.getClass();
        final Field[] declaredFields;
        final Field[] fields = declaredFields = cls.getDeclaredFields();
        for (final Field f : declaredFields) {
            f.setAccessible(true);
            if ("java.lang.String".equals(f.getGenericType().getTypeName())) {
                keys[i] = f.getName();
                if ("guide".equals(f.getName())) {
                    values[i] = ArbitrateServiceImpl.titleIndexMap.get(index + 2) + f.get(evidenceReport).toString();
                }
                else {
                    values[i] = ((f.get(evidenceReport) == null) ? "" : f.get(evidenceReport).toString());
                }
                ++i;
            }
            else if ("java.lang.Integer".equals(f.getGenericType().getTypeName())) {
                keys[i] = f.getName();
                values[i] = f.get(evidenceReport).toString();
                ++i;
            }
        }
        doc.getMailMerge().executeWithRegions(countStatistics);
        doc.getMailMerge().executeWithRegions(evidenceStatus);
        doc.getMailMerge().executeWithRegions(dataSet);
        doc.getMailMerge().executeWithRegions(dataSet2);
        doc.getMailMerge().execute(keys, (Object[])values);
        doc.updateFields();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (isPdf) {
            doc.save((OutputStream)outputStream, 40);
        }
        else {
            doc.save((OutputStream)outputStream, 20);
        }
        final ByteArrayInputStream inputStream2 = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream2;
    }
    
    private InputStream buildNoticeReport(final NoticeInfo noticeInfo, final boolean isPdf) throws Exception {
        final Document doc = AsposeUtil.getDoc(this.templatepath + "noticeReport.docx");
        final String[] keys = new String[7];
        final String[] values = new String[7];
        int i = 0;
        final Class cls = noticeInfo.getClass();
        final Field[] declaredFields;
        final Field[] fields = declaredFields = cls.getDeclaredFields();
        for (final Field f : declaredFields) {
            f.setAccessible(true);
            if (f.getName().equals("userName")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
            if (f.getName().equals("title")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
            if (f.getName().equals("createTime")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, (Date)f.get(noticeInfo)));
                ++i;
            }
            if (f.getName().equals("hash")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
            if (f.getName().equals("remark")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
        }
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1keys index\uff1a" + i);
        keys[i] = "type";
        values[i] = "\u7f51\u9875\u5b58\u8bc1";
        ++i;
        if (noticeInfo.getSourceTag().equals("zssj")) {
            keys[i] = "userNameType";
            values[i] = "\u5b58\u8bc1\u65b9";
            ++i;
        }
        else {
            keys[i] = "userNameType";
            values[i] = "\u53d1\u5e03\u65b9";
            ++i;
        }
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1keys\uff1a" + JSONObject.toJSONString((Object)keys));
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1values\uff1a" + JSONObject.toJSONString((Object)values));
        doc.getMailMerge().execute(keys, (Object[])values);
        doc.updateFields();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (isPdf) {
            doc.save((OutputStream)outputStream, 40);
        }
        else {
            doc.save((OutputStream)outputStream, 20);
        }
        final ByteArrayInputStream inputStream1 = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream1;
    }
    
    private InputStream buildContractReport(final ContractInfo info, final boolean isPdf) throws Exception {
        final ClassPathResource classPathResource = new ClassPathResource("contractReport.docx");
        final InputStream inputStream = classPathResource.getInputStream();
        final Document doc = AsposeUtil.getDoc(inputStream);
        final String[] keys = new String[6];
        final String[] values = new String[6];
        int i = 0;
        final Class cls = info.getClass();
        final Field[] declaredFields;
        final Field[] fields = declaredFields = cls.getDeclaredFields();
        for (final Field f : declaredFields) {
            f.setAccessible(true);
            if (f.getName().equals("userName")) {
                keys[i] = f.getName();
                values[i] = ((f.get(info) == null) ? "" : f.get(info).toString());
                ++i;
            }
            if (f.getName().equals("title")) {
                keys[i] = f.getName();
                values[i] = ((f.get(info) == null) ? "" : f.get(info).toString());
                ++i;
            }
            if (f.getName().equals("createTime")) {
                keys[i] = f.getName();
                values[i] = ((f.get(info) == null) ? "" : DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, (Date)f.get(info)));
                ++i;
            }
            if (f.getName().equals("hash")) {
                keys[i] = f.getName();
                values[i] = ((f.get(info) == null) ? "" : f.get(info).toString());
                ++i;
            }
            if (f.getName().equals("remark")) {
                keys[i] = f.getName();
                values[i] = ((f.get(info) == null) ? "" : f.get(info).toString());
                ++i;
            }
            if (f.getName().equals("type")) {
                keys[i] = f.getName();
                final String value = (f.get(info) == null) ? "" : f.get(info).toString();
                values[i] = ContractTypeEnum.getLableByValue(value);
                ++i;
            }
        }
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1keys\uff1a" + JSONObject.toJSONString((Object)keys));
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1values\uff1a" + JSONObject.toJSONString((Object)values));
        doc.getMailMerge().execute(keys, (Object[])values);
        doc.updateFields();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (isPdf) {
            doc.save((OutputStream)outputStream, 40);
        }
        else {
            doc.save((OutputStream)outputStream, 20);
        }
        final ByteArrayInputStream inputStream2 = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream2;
    }
    
    public Object getUserInfo(final String loginAccount) throws Exception {
        final UserInfo userInfo = this.getUserInfo(loginAccount, null);
        return userInfo;
    }
    
    public String getUserId(final String loginAccount, String userId, final String searchType) throws Exception {
        final UserInfo userInfo = this.getUserInfo(loginAccount, userId);
        if ("OPERATOR".equals(searchType)) {
            if (userInfo.isOperator()) {
                userId = userInfo.getSocialCode();
            }
            else {
                userId = userInfo.getUserAccount();
            }
        }
        else {
            userId = userInfo.getUserAccount();
        }
        return userId;
    }
    
    private HSUserInfo getHSUserInfo(final String loginAccount, final String userId) throws Exception {
        final HSUserInfoBo.UserInfoBo hsUserInfoBo = this.hsService.queryUserInfoRequest(loginAccount);
        if (hsUserInfoBo == null) {
            ArbitrateServiceImpl.logger.error("\u83b7\u53d6UserInfoBo\u5931\u8d25\uff01loginAccount == " + loginAccount);
            throw new BusinessException("\u83b7\u53d6UserInfoBo\u5931\u8d25\uff01");
        }
        final HSUserInfo hsUserInfo = new HSUserInfo();
        if (!"Y".equals(hsUserInfoBo.getIsOperator())) {
            hsUserInfo.setOperator(false);
            hsUserInfo.setUserName(hsUserInfoBo.getName());
            hsUserInfo.setUserId(hsUserInfoBo.getUserId());
            hsUserInfo.setSocialCode(hsUserInfoBo.getUserId());
            hsUserInfo.setUserAccount(hsUserInfoBo.getAccount());
            return hsUserInfo;
        }
        hsUserInfo.setOperator(true);
        final HSCompanyInfoBo.CompanyInfoBo companyInfoBo = this.hsService.queryCompInfoRequest(hsUserInfoBo.getOptCompCode(), loginAccount);
        if (companyInfoBo == null) {
            ArbitrateServiceImpl.logger.error("\u83b7\u53d6companyInfoBo\u5931\u8d25\uff01loginAccount == " + loginAccount);
            throw new BusinessException("\u83b7\u53d6companyInfoBo\u5931\u8d25\uff01");
        }
        hsUserInfo.setSocialCode(companyInfoBo.getSocialCode());
        hsUserInfo.setUserId(companyInfoBo.getUserId());
        hsUserInfo.setCompanyName(companyInfoBo.getName());
        hsUserInfo.setUserName(hsUserInfoBo.getName());
        hsUserInfo.setCompanyAccount(companyInfoBo.getAccount());
        hsUserInfo.setUserAccount(hsUserInfoBo.getAccount());
        return hsUserInfo;
    }
    
    private UserInfo getUserInfo(final String account, final String userId) throws Exception {
        final UCUserInfo ucUserInfo = this.userCenterService.queryUserInfoRequest(account);
        if (ucUserInfo == null) {
            ArbitrateServiceImpl.logger.error("UCUserInfo\uff01loginAccount == " + account);
            throw new BusinessException("\u83b7\u53d6UCUserInfo\u5931\u8d25\uff01");
        }
        final UserInfo userInfo = new UserInfo();
        if (!"Y".equals(ucUserInfo.getIsOperator())) {
            userInfo.setOperator(false);
            userInfo.setUserName(ucUserInfo.getName());
            userInfo.setUserId(ucUserInfo.getUserId());
            userInfo.setSocialCode(ucUserInfo.getUserId());
            userInfo.setUserAccount(ucUserInfo.getAccount());
            return userInfo;
        }
        userInfo.setOperator(true);
        final UCCompany company = this.userCenterService.queryCompInfoRequest(ucUserInfo.getOptCompCode());
        if (company == null) {
            ArbitrateServiceImpl.logger.error("\u83b7\u53d6company\u5931\u8d25\uff01loginAccount == " + account);
            throw new BusinessException("\u83b7\u53d6company\u5931\u8d25\uff01");
        }
        userInfo.setSocialCode(company.getSocialCode());
        userInfo.setUserId(company.getUserId());
        userInfo.setCompanyName(company.getName());
        userInfo.setUserName(ucUserInfo.getName());
        userInfo.setCompanyAccount(company.getAccount());
        userInfo.setUserAccount(ucUserInfo.getAccount());
        return userInfo;
    }
    
    public String upBlockSkyChain(final BlockChainReq blockChainReq) {
        final HttpUtils httpUtils = new HttpUtils();
        String tradeId = null;
        final String conResult = httpUtils.sendJsonPost("\u5929\u7a7a\u94fe\u53f8\u6cd5\u5b58\u8bc1\u4fe1\u606f\u4e0a\u94fe", this.skyChainCreateEvidence, JsonUtils.beanToMap((Object)blockChainReq));
        ArbitrateServiceImpl.logger.info("\u3010\u5929\u7a7a\u94fe\u53f8\u6cd5\u5b58\u8bc1\u3011\u4e0a\u94fe\u8bf7\u6c42\u7ed3\u679c\uff1a" + JSONObject.toJSONString((Object)conResult));
        try {
            final JSONObject obj = JSON.parseObject(conResult);
            final String code = obj.getString("code");
            if ("200".equals(code)) {
                tradeId = obj.getString("data");
                return tradeId;
            }
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.info("\u5929\u7a7a\u94fe\u53f8\u6cd5\u5b58\u8bc1\u4fe1\u606f\u4e0a\u94fe,\u89e3\u6790\u533a\u5757\u94fe\u8fd4\u56de\u5185\u5bb9\u5931\u8d25\uff1a", (Throwable)e);
        }
        return tradeId;
    }
    
    public String upBlockchain(final BlockChainReq blockChainReq) {
        final HttpUtils httpUtils = new HttpUtils();
        String tradeId = null;
        final String conResult = httpUtils.sendJsonPost("\u516c\u793a\u516c\u544a/\u5408\u540c\u5b58\u8bc1\u4fe1\u606f\u4e0a\u94fe", this.createEvidence, JsonUtils.beanToMap((Object)blockChainReq));
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a/\u5408\u540c\u5b58\u8bc1\u4fe1\u606f\u3011\u4e0a\u94fe\u8bf7\u6c42\u7ed3\u679c\uff1a" + JSONObject.toJSONString((Object)conResult));
        try {
            final JSONObject obj = JSON.parseObject(conResult);
            final String code = obj.getString("code");
            if ("200".equals(code)) {
                tradeId = obj.getString("data");
                return tradeId;
            }
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.info("\u516c\u793a\u516c\u544a/\u5408\u540c\u5b58\u8bc1\u4fe1\u606f\u4e0a\u94fe,\u89e3\u6790\u533a\u5757\u94fe\u8fd4\u56de\u5185\u5bb9\u5931\u8d25\uff1a", (Throwable)e);
        }
        return tradeId;
    }
    
    public void saveContract(final ContractInfoReq req) {
        ArbitrateServiceImpl.logger.info("\u3010\u5408\u540c\u5b58\u8bc1\u3011\u63d0\u4ea4\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)req));
        String userId = req.getUserId();
        if (StringUtils.isBlank(userId)) {
            userId = StringUtils.uuid();
        }
        req.setCode(StringUtils.uuid());
        String hash = "";
        try {
            final BlockChainReq blockChainReq = new BlockChainReq();
            blockChainReq.setEvidenceId(req.getCode());
            blockChainReq.setBusinessType("contract");
            blockChainReq.setDescription(req.getRemark());
            blockChainReq.setType("file");
            blockChainReq.setVersion("1.1");
            blockChainReq.setUserPlatform("");
            final JSONObject user = new JSONObject();
            user.put("userId", (Object)userId);
            user.put("name", (Object)req.getUserName());
            final JSONObject data = new JSONObject();
            final Random random = new Random();
            final int num = random.nextInt(100);
            final String sn = "DD" + System.currentTimeMillis() + num;
            data.put("url", (Object)req.getFileUrl());
            data.put("hash", (Object)req.getFileHash());
            data.put("type", (Object)"\u4e1a\u52a1\u5b58\u8bc1");
            data.put("description", (Object)"\u4e1a\u52a1\u5b58\u8bc1");
            data.put("sn", (Object)sn);
            data.put("name", (Object)req.getFileName());
            blockChainReq.setUser(user.toJSONString());
            blockChainReq.setData(data.toJSONString());
            hash = this.upBlockchain(blockChainReq);
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.error("\u5408\u540c\u5b58\u8bc1\u4e0a\u94fe\u5931\u8d25", (Throwable)e);
            throw new BusinessException("\u5408\u540c\u5b58\u8bc1\u4e0a\u94fe\u5931\u8d25");
        }
        String creator = req.getCreator();
        final String creatorName = req.getCreatorName();
        if (StringUtils.isBlank(creator)) {
            try {
                creator = SecurityUtils.getUsername();
            }
            catch (final Exception e2) {
                ArbitrateServiceImpl.logger.error("\u3010\u5408\u540c\u5b58\u8bc1\u3011\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5f02\u5e38\uff1a", (Throwable)e2);
            }
        }
        final ContractInfo contractInfo = new ContractInfo();
        BeanUtils.copyProperties((Object)req, (Object)contractInfo);
        contractInfo.setHash(hash);
        contractInfo.setCreateTime(DateUtils.getCurrentDate());
        contractInfo.setCreator(creator);
        contractInfo.setCreatorName(StringUtils.isBlank(creatorName) ? creator : creatorName);
        ArbitrateServiceImpl.logger.info("\u3010\u5408\u540c\u5b58\u8bc1\u3011\u4fdd\u5b58\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)contractInfo));
        this.contractService.insert(contractInfo);
    }
    
    public void saveNotice(final NoticeInfoReq req) {
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a\u3011\u63d0\u4ea4\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)req));
        String userId = req.getUserId();
        if (StringUtils.isBlank(userId)) {
            userId = StringUtils.uuid();
        }
        String hash = "";
        try {
            final BlockChainReq blockChainReq = new BlockChainReq();
            blockChainReq.setEvidenceId(req.getCode());
            blockChainReq.setBusinessType("notice");
            blockChainReq.setDescription(req.getRemark());
            blockChainReq.setType("file");
            blockChainReq.setVersion("1.1");
            blockChainReq.setUserPlatform("");
            blockChainReq.setBusinessPhase("submit");
            final JSONObject user = new JSONObject();
            user.put("userId", (Object)userId);
            user.put("name", (Object)req.getUserName());
            final JSONObject data = new JSONObject();
            final Random random = new Random();
            final int num = random.nextInt(100);
            final String sn = "DD" + System.currentTimeMillis() + num;
            data.put("url", (Object)req.getFileUrl());
            data.put("hash", (Object)req.getFileHash());
            data.put("type", (Object)"\u6587\u4ef6\u5b58\u8bc1");
            data.put("description", (Object)"\u516c\u544a\u5b58\u8bc1");
            data.put("sn", (Object)sn);
            data.put("name", (Object)req.getFileName());
            blockChainReq.setUser(user.toJSONString());
            blockChainReq.setData(data.toJSONString());
            hash = this.upBlockchain(blockChainReq);
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.error("\u516c\u793a\u516c\u544a\u5b58\u8bc1\u4e0a\u94fe\u5931\u8d25", (Throwable)e);
            throw new BusinessException("\u516c\u793a\u516c\u544a\u5b58\u8bc1\u4e0a\u94fe\u5931\u8d25");
        }
        String creator = req.getCreator();
        final String creatorName = req.getCreatorName();
        if (StringUtils.isBlank(creator)) {
            try {
                creator = SecurityUtils.getUsername();
            }
            catch (final Exception e2) {
                ArbitrateServiceImpl.logger.error("\u3010\u516c\u793a\u516c\u544a\u3011\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5f02\u5e38\uff1a", (Throwable)e2);
            }
        }
        final NoticeInfo noticeInfo = new NoticeInfo();
        BeanUtils.copyProperties((Object)req, (Object)noticeInfo);
        noticeInfo.setHash(hash);
        noticeInfo.setCreateTime(DateUtils.getCurrentDate());
        noticeInfo.setCreator(creator);
        noticeInfo.setCreatorName(StringUtils.isBlank(creatorName) ? creator : creatorName);
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a\u3011\u4fdd\u5b58\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)noticeInfo));
        this.noticeService.insert(noticeInfo);
    }
    
    public List<NoticeInfo> queryNoticePageList(final NoticeInfo info) {
        return this.noticeService.getPageList(info);
    }
    
    public List<ContractInfo> queryContractPageList(final ContractInfo contractInfo) {
        return this.contractService.getPageList(contractInfo);
    }
    
    public Object noticeReportPreview(final int id, final HttpServletResponse response) {
        try {
            final NoticeInfo info = this.noticeService.findInfoById(id);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("noticeReport.pdf", "UTF-8"));
            response.setHeader("Cache-Control", "no-cache");
            final InputStream inputStream = this.buildNoticeReport(info, false);
            AsposeUtil.wordToPdf(inputStream, (OutputStream)response.getOutputStream());
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.error("\u516c\u793a\u516c\u544a\u5b58\u9884\u89c8\u5b58\u8bc1\u62a5\u544a\u5931\u8d25", (Throwable)e);
        }
        return null;
    }
    
    public Object contractReportPreview(final int id, final HttpServletResponse response) {
        try {
            final ContractInfo info = this.contractService.findInfoById(id);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("contractReport.pdf", "UTF-8"));
            response.setHeader("Cache-Control", "no-cache");
            final InputStream inputStream = this.buildContractReport(info, false);
            AsposeUtil.wordToPdf(inputStream, (OutputStream)response.getOutputStream());
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.error("\u5408\u540c\u5b58\u8bc1\u5b58\u9884\u89c8\u5b58\u8bc1\u62a5\u544a\u5931\u8d25", (Throwable)e);
        }
        return null;
    }
    
    public ContractInfo findContractById(final int id) {
        return this.contractService.findInfoById(id);
    }
    
    public NoticeInfo findNoticeById(final int id) {
        return this.noticeService.findInfoById(id);
    }
    
    private InputStream buildNoticePubReport(final NoticePublish noticeInfo, final boolean isPdf) throws Exception {
        final Document doc = AsposeUtil.getDoc(this.templatepath + "noticeReport.docx");
        final String[] keys = new String[7];
        final String[] values = new String[7];
        int i = 0;
        final Class cls = noticeInfo.getClass();
        final Field[] declaredFields;
        final Field[] fields = declaredFields = cls.getDeclaredFields();
        for (final Field f : declaredFields) {
            f.setAccessible(true);
            if (f.getName().equals("userName")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
            if (f.getName().equals("title")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
            if (f.getName().equals("publishTime")) {
                keys[i] = "createTime";
                values[i] = ((f.get(noticeInfo) == null) ? "" : DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, (Date)f.get(noticeInfo)));
                ++i;
            }
            if (f.getName().equals("hash")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : f.get(noticeInfo).toString());
                ++i;
            }
            if (f.getName().equals("remark")) {
                keys[i] = f.getName();
                values[i] = ((f.get(noticeInfo) == null) ? "" : convert(f.get(noticeInfo).toString()));
                ++i;
            }
        }
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1keys index\uff1a" + i);
        keys[i] = "type";
        values[i] = "\u516c\u793a\u516c\u544a\u5b58\u50a8";
        ++i;
        if (noticeInfo.getSourceTag().equals("zssj")) {
            keys[i] = "userNameType";
            values[i] = "\u5b58\u8bc1\u65b9";
            ++i;
        }
        else {
            keys[i] = "userNameType";
            values[i] = "\u53d1\u5e03\u65b9";
            ++i;
        }
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1keys\uff1a" + JSONObject.toJSONString((Object)keys));
        ArbitrateServiceImpl.logger.info("\u5b58\u8bc1values\uff1a" + JSONObject.toJSONString((Object)values));
        doc.getMailMerge().execute(keys, (Object[])values);
        doc.updateFields();
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (isPdf) {
            doc.save((OutputStream)outputStream, 40);
        }
        else {
            doc.save((OutputStream)outputStream, 20);
        }
        final ByteArrayInputStream inputStream1 = new ByteArrayInputStream(outputStream.toByteArray());
        return inputStream1;
    }
    
    private InputStream buildNoticePubReportDownload(final NoticePublish noticeInfo, final boolean isPdf) {
        XWPFTemplate template = null;
        ByteArrayOutputStream baos = null;
        ByteArrayInputStream is = null;
        try {
            final Configure config = Configure.newBuilder().bind("remark", (RenderPolicy)WangEditor2Word.policy((String)null, 688, 1920)).build();
            template = XWPFTemplate.compile(this.templatepath + "noticePublish.docx", config).render((Object)noticeInfo);
            ArbitrateServiceImpl.log.info("\u5b58\u8bc1\u62a5\u544a\u586b\u5145\u5b8c\u6210...");
            baos = new ByteArrayOutputStream(1024);
            template.write((OutputStream)baos);
            is = new ByteArrayInputStream(baos.toByteArray());
            ArbitrateServiceImpl.log.info("\u9884\u89c8\u516c\u544a\u5b58\u8bc1\u62a5\u544a\u6784\u5efainputStream\u5b8c\u6210...");
        }
        catch (final Exception e) {
            e.printStackTrace();
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            }
            catch (final Exception e1) {
                e1.printStackTrace();
            }
            try {
                if (template != null) {
                    template.close();
                }
            }
            catch (final Exception e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            }
            catch (final Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (template != null) {
                    template.close();
                }
            }
            catch (final Exception e2) {
                e2.printStackTrace();
            }
        }
        return is;
    }
    
    @Transactional(rollbackFor = { Exception.class })
    public void saveNoticePub(final NoticePublishReq req) {
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a\u3011\u63d0\u4ea4\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)req));
        String userId = req.getUserId();
        if (StringUtils.isBlank(userId)) {
            userId = StringUtils.uuid();
        }
        final String hash = "";
        String creator = req.getCreator();
        final String creatorName = req.getCreatorName();
        if (StringUtils.isBlank(creator)) {
            try {
                creator = SecurityUtils.getUsername();
            }
            catch (final Exception e) {
                ArbitrateServiceImpl.logger.error("\u3010\u516c\u793a\u516c\u544a\u3011\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5f02\u5e38\uff1a", (Throwable)e);
            }
        }
        final NoticePublish noticePublish = new NoticePublish();
        BeanUtils.copyProperties((Object)req, (Object)noticePublish);
        noticePublish.setHash(hash);
        noticePublish.setCreateTime(DateUtils.getCurrentDate());
        noticePublish.setCreator(creator);
        noticePublish.setCreatorName(StringUtils.isBlank(creatorName) ? creator : creatorName);
        noticePublish.setViews(this.defaultviews);
        noticePublish.setPayAmount(new BigDecimal(StringUtils.isEmpty(req.getPayAmount()) ? "0" : req.getPayAmount()));
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a\u3011\u4fdd\u5b58\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)noticePublish));
        this.noticePublishService.insert(noticePublish);
        req.setId(noticePublish.getId());
    }
    
    public List<NoticePublish> queryNoticePubPageList(final NoticePublish info) {
        final List<NoticePublish> pageList = this.noticePublishService.getPageList(info);
        for (final NoticePublish noticePublish : pageList) {
            final SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByValue(noticePublish.getSourceTag());
            if (sourceTypeEnum != null) {
                noticePublish.setSourceType(sourceTypeEnum.getType());
            }
        }
        return pageList;
    }
    
    public Object noticePubReportPreview(final int id, final HttpServletResponse response) {
        try {
            final NoticePublish info = this.noticePublishService.findInfoById(id);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("noticeReport.pdf", "UTF-8"));
            response.setHeader("Cache-Control", "no-cache");
            final InputStream inputStream = this.buildNoticePubReport(info, false);
            AsposeUtil.wordToPdf(inputStream, (OutputStream)response.getOutputStream());
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.error("\u516c\u793a\u516c\u544a\u5b58\u9884\u89c8\u5b58\u8bc1\u62a5\u544a\u5931\u8d25", (Throwable)e);
        }
        return null;
    }
    
    public Object noticePubReportDownload(final int id, final HttpServletResponse response) throws IOException {
        InputStream inputStream = null;
        try {
            final NoticePublish info = this.noticePublishService.findInfoById(id);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("noticeReport.docx", "UTF-8"));
            response.setHeader("Cache-Control", "no-cache");
            inputStream = this.buildNoticePubReportDownload(info, false);
            AsposeUtil.wordDownload(inputStream, (OutputStream)response.getOutputStream());
            ArbitrateServiceImpl.log.info("\u5b58\u8bc1\u62a5\u544a\u4e0b\u8f7d\u5b8c\u6210");
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.logger.error("\u516c\u793a\u516c\u544a\u5b58\u9884\u89c8\u5b58\u8bc1\u62a5\u544a\u5931\u8d25", (Throwable)e);
        }
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return null;
    }
    
    public NoticePublish findNoticePubById(final int id) {
        final NoticePublish noticePublish = this.noticePublishService.findInfoById(id);
        final SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByValue(noticePublish.getSourceTag());
        if (sourceTypeEnum != null) {
            noticePublish.setSourceType(sourceTypeEnum.getType());
        }
        return noticePublish;
    }
    
    public int viewNoticePubById(final NoticePublish noticePublish) {
        return this.noticePublishService.viewNoticePub(noticePublish);
    }
    
    public void insertLog(final NoticeAuditLog log) {
        this.noticePublishService.insertLog(log);
    }
    
    public List<NoticeAuditLog> queryAuditLogPageList(final NoticeAuditLog log) {
        return this.noticePublishService.getLogPageList(log);
    }
    
    public void updateNoticePub(final NoticePublishReq req) {
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a\u3011\u7f16\u8f91\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)req));
        String userId = req.getUserId();
        if (StringUtils.isBlank(userId)) {
            userId = StringUtils.uuid();
        }
        String hash = "";
        if (req.getFileUrl() != null && req.getFileHash() != null) {
            try {
                final BlockChainReq blockChainReq = new BlockChainReq();
                blockChainReq.setEvidenceId(req.getCode());
                blockChainReq.setBusinessType("notice");
                blockChainReq.setDescription(req.getRemark());
                blockChainReq.setType("file");
                blockChainReq.setVersion("1.1");
                blockChainReq.setUserPlatform("");
                blockChainReq.setBusinessPhase("publish");
                final JSONObject user = new JSONObject();
                user.put("userId", (Object)userId);
                user.put("name", (Object)req.getUserName());
                final JSONObject data = new JSONObject();
                final Random random = new Random();
                final int num = random.nextInt(100);
                final String sn = "DD" + System.currentTimeMillis() + num;
                data.put("url", (Object)req.getFileUrl());
                data.put("hash", (Object)req.getFileHash());
                data.put("type", (Object)"\u6587\u4ef6\u5b58\u8bc1");
                data.put("description", (Object)"\u516c\u544a\u5b58\u8bc1");
                data.put("sn", (Object)sn);
                data.put("name", (Object)req.getFileName());
                blockChainReq.setUser(user.toJSONString());
                blockChainReq.setData(data.toJSONString());
                hash = this.upBlockchain(blockChainReq);
            }
            catch (final Exception e) {
                ArbitrateServiceImpl.logger.error("\u516c\u793a\u516c\u544a\u5b58\u8bc1\u4e0a\u94fe\u5931\u8d25", (Throwable)e);
                throw new BusinessException("\u516c\u793a\u516c\u544a\u5b58\u8bc1\u4e0a\u94fe\u5931\u8d25");
            }
        }
        String creator = req.getCreator();
        final String creatorName = req.getCreatorName();
        if (StringUtils.isBlank(creator)) {
            try {
                creator = SecurityUtils.getUsername();
            }
            catch (final Exception e2) {
                ArbitrateServiceImpl.logger.error("\u3010\u516c\u793a\u516c\u544a\u3011\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5f02\u5e38\uff1a", (Throwable)e2);
            }
        }
        final NoticePublish noticePublish = new NoticePublish();
        BeanUtils.copyProperties((Object)req, (Object)noticePublish);
        if (StringUtils.isNotBlank(hash)) {
            noticePublish.setHash(hash);
        }
        noticePublish.setUpdateTime(DateUtils.getCurrentDate());
        noticePublish.setCreator(creator);
        noticePublish.setCreatorName(StringUtils.isBlank(creatorName) ? creator : creatorName);
        ArbitrateServiceImpl.logger.info("\u3010\u516c\u793a\u516c\u544a\u3011\u7f16\u8f91\u53c2\u6570\uff1a" + JSONObject.toJSONString((Object)noticePublish));
        this.noticePublishService.updateNoticePub(noticePublish);
    }
    
    public void auditNoticePub(final NoticeAuditReq param) {
        final NoticePublishReq req = new NoticePublishReq();
        final NoticePublish noticePub = this.findNoticePubById(param.getId().intValue());
        if (StatusEnum.OUTCANCEL.getValue().equals(noticePub.getStatus())) {
            throw new BusinessException("\u6e20\u9053\u65b9\u5df2\u53d6\u6d88\u516c\u544a\uff0c\u4e0d\u5141\u8bb8\u5ba1\u6838");
        }
        BeanUtils.copyProperties((Object)noticePub, (Object)req);
        BeanUtils.copyProperties((Object)param, (Object)req);
        if (!param.getStatus().equals(StatusEnum.BACK.getValue()) && StringUtils.isNotEmpty(param.getPlatform())) {
            final int year = Calendar.getInstance().get(1);
            String numCode = "GGZDL__" + year + "0000";
            final String maxCode = this.selectMaxCode(year + "");
            if (!StringUtils.isEmpty(maxCode)) {
                numCode = maxCode;
            }
            final String platform = PlatformTypeEnum.ZB.getValue().equals(param.getPlatform()) ? "ZB" : "ZF";
            final int num = Integer.parseInt(numCode.substring(11));
            final String code = "GGZDL" + platform + year + String.format("%04d", num + 1);
            req.setCode(code);
        }
        if (param.getStatus().equals(StatusEnum.PUBLISH.getValue())) {
            req.setPublishTime(DateUtils.getCurrentDate());
        }
        req.setUserId(noticePub.getUserId());
        req.setUserName(noticePub.getUserName());
        this.updateNoticePub(req);
        final SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByValue(noticePub.getSourceTag());
        if (StringUtils.isNotEmpty(param.getPlatform()) || StringUtils.isNotEmpty(param.getReason()) || StringUtils.isNotEmpty(param.getFileUrl()) || StringUtils.isNotEmpty(param.getNoticeUrl())) {
            final NoticeAuditLog log = new NoticeAuditLog();
            log.setNoticeId(req.getId());
            log.setUserId(param.getUserId());
            log.setUserName(param.getUserName());
            log.setCreator(param.getUserId());
            log.setCreatorName(param.getUserName());
            log.setRole(RoleEnum.AUDIT.getLabel());
            log.setReason(StatusEnum.getLableByValue(req.getStatus()));
            log.setRemark(req.getReason());
            log.setCreateTime(DateUtils.getCurrentDate());
            this.insertLog(log);
        }
        if ("2".equals(sourceTypeEnum.getType())) {
            if (param.getStatus().equals(StatusEnum.PASS.getValue()) || param.getStatus().equals(StatusEnum.BACK.getValue())) {
                this.executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            log.info("审核通知发布 - 状态为通过或退回");
                        } catch (Exception ex) {
                            log.error("审核通知发布任务失败", ex);
                        }
                    }
                });
            }
            if (param.getStatus().equals(StatusEnum.PUBLISH.getValue()) && StringUtils.isNotEmpty(param.getNoticeUrl()) && !param.getNoticeUrl().equals(noticePub.getNoticeUrl())) {
                this.executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            log.info("审核通知发布 - 状态为发布");
                        } catch (Exception ex) {
                            log.error("审核通知发布任务失败", ex);
                        }
                    }
                });
            }
            if (param.getStatus().equals(StatusEnum.HIDDEN.getValue())) {
                this.executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            log.info("审核通知发布 - 状态为隐藏");
                        } catch (Exception ex) {
                            log.error("审核通知发布任务失败", ex);
                        }
                    }
                });
            }
        }
    }
    
    public Long noticePush(final NoticeChannelPushReq req, final HttpServletRequest request) {
        ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u63a8\u9001\u53c2\u6570\uff1a{}", (Object)JSONObject.toJSONString((Object)req));
        final SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByValue(req.getChannelId());
        if (sourceTypeEnum == null || "1".equals(sourceTypeEnum.getType())) {
            throw new BusinessException("\u4e0d\u652f\u6301\u7684\u6e20\u9053\u53f7");
        }
        this.whitesValidated(req.getChannelId(), request);
        this.getFileUploadOss(req);
        final NoticePublishReq param = new NoticePublishReq();
        param.setSceneType(SceneTypeEnum.AUCTION.getValue());
        param.setSource(sourceTypeEnum.getLabel());
        param.setSourceTag(sourceTypeEnum.getValue());
        param.setStatus(StatusEnum.SUBMIT.getValue());
        BeanUtils.copyProperties((Object)req, (Object)param);
        this.saveNoticePub(param);
        final NoticeAuditLog log = new NoticeAuditLog();
        log.setNoticeId(param.getId());
        log.setUserId(param.getUserId());
        log.setUserName(param.getUserName());
        log.setCreator(param.getCreator());
        log.setCreatorName(param.getCreatorName());
        log.setRole(RoleEnum.SUBMIT.getLabel());
        log.setReason(StatusEnum.SUBMIT.getLabel());
        log.setCreateTime(DateUtils.getCurrentDate());
        this.insertLog(log);
        return param.getId();
    }
    
    public void noticeUpdate(final NoticeChannelPushReq req, final HttpServletRequest request) {
        ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u4fee\u6539\u53c2\u6570\uff1a{}", (Object)JSONObject.toJSONString((Object)req));
        final SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByValue(req.getChannelId());
        if (sourceTypeEnum == null || "1".equals(sourceTypeEnum.getType())) {
            throw new BusinessException("\u4e0d\u652f\u6301\u7684\u6e20\u9053\u53f7");
        }
        if (req.getId() == null) {
            throw new BusinessException("\u516c\u544aid\u4e0d\u80fd\u4e3a\u7a7a");
        }
        final NoticePublish data = this.findNoticePubById(req.getId().intValue());
        if (data == null || !sourceTypeEnum.getValue().equals(data.getSourceTag())) {
            throw new BusinessException("\u516c\u544a\u4e0d\u5b58\u5728");
        }
        if (!StatusEnum.BACK.getValue().equals(data.getStatus()) && !StatusEnum.SUBMIT.getValue().equals(data.getStatus()) && !StatusEnum.OUTCANCEL.getValue().equals(data.getStatus())) {
            throw new BusinessException("\u5f53\u524d\u516c\u544a\u72b6\u6001\u4e3a\u3010" + StatusEnum.getByValue(data.getStatus()).getLabel() + "\u3011\u4e0d\u652f\u6301\u4fee\u6539");
        }
        this.whitesValidated(req.getChannelId(), request);
        this.getFileUploadOss(req);
        final NoticePublishReq param = new NoticePublishReq();
        BeanUtils.copyProperties((Object)req, (Object)param);
        param.setStatus(StatusEnum.SUBMIT.getValue());
        this.updateNoticePub(param);
        final NoticeAuditLog log = new NoticeAuditLog();
        log.setNoticeId(param.getId());
        log.setUserId(param.getUserId());
        log.setUserName(param.getUserName());
        log.setCreator(param.getCreator());
        log.setCreatorName(param.getCreatorName());
        log.setRole(RoleEnum.SUBMIT.getLabel());
        log.setReason(StatusEnum.SUBMIT.getLabel());
        log.setCreateTime(DateUtils.getCurrentDate());
        this.insertLog(log);
    }
    
    public void noticeCancel(final NoticeChannelCancelReq req, final HttpServletRequest request) {
        ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u53d6\u6d88\u53c2\u6570\uff1a{}", (Object)JSONObject.toJSONString((Object)req));
        final SourceTypeEnum sourceTypeEnum = SourceTypeEnum.getByValue(req.getChannelId());
        if (sourceTypeEnum == null || "1".equals(sourceTypeEnum.getType())) {
            throw new BusinessException("\u4e0d\u652f\u6301\u7684\u6e20\u9053\u53f7");
        }
        if (req.getId() == null) {
            throw new BusinessException("\u516c\u544aid\u4e0d\u80fd\u4e3a\u7a7a");
        }
        NoticePublish data = null;
        try {
            data = this.findNoticePubById(req.getId().intValue());
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.log.error("noticeCancel\u65b9\u6cd5findNoticePubById\u5931\u8d25", (Throwable)e);
            throw new BusinessException("\u516c\u544a\u53d6\u6d88\u5931\u8d25\uff0c\u4e0d\u5b58\u5728\u6b64\u516c\u544a");
        }
        if (data == null || !sourceTypeEnum.getValue().equals(data.getSourceTag())) {
            throw new BusinessException("\u516c\u544a\u4e0d\u5b58\u5728");
        }
        if (StatusEnum.HIDDEN.getValue().equals(data.getStatus()) || StatusEnum.HISTORY.getValue().equals(data.getStatus()) || StatusEnum.PASS.getValue().equals(data.getStatus()) || StatusEnum.PUBLISH.getValue().equals(data.getStatus())) {
            throw new BusinessException("\u5f53\u524d\u516c\u544a\u72b6\u6001\u4e3a\u3010" + StatusEnum.getByValue(data.getStatus()).getLabel() + "\u3011\u4e0d\u652f\u6301\u53d6\u6d88");
        }
        this.whitesValidated(req.getChannelId(), request);
        final NoticePublishReq param = new NoticePublishReq();
        BeanUtils.copyProperties((Object)req, (Object)param);
        param.setStatus(StatusEnum.OUTCANCEL.getValue());
        this.updateNoticePub(param);
        final NoticeAuditLog log = new NoticeAuditLog();
        log.setNoticeId(param.getId());
        log.setUserId(param.getUserId());
        log.setUserName(param.getUserName());
        log.setCreator(param.getCreator());
        log.setCreatorName(param.getCreatorName());
        log.setRole(RoleEnum.SUBMIT.getLabel());
        log.setReason(StatusEnum.OUTCANCEL.getLabel());
        log.setCreateTime(DateUtils.getCurrentDate());
        this.insertLog(log);
    }
    
    public void deleteNoticePub(final int id) {
        this.noticePublishService.deleteNoticePub(id);
    }
    
    private void getFileUploadOss(final NoticeChannelPushReq req) {
        if (req.getAuthUrl() != null) {
            final JSONArray authUrl = (JSONArray)JSONObject.parseObject(req.getAuthUrl(), (Class)JSONArray.class);
            final JSONArray authUrlNew = new JSONArray();
            for (final Object authObject : authUrl) {
                final JSONObject authJson = (JSONObject)authObject;
                final String url = authJson.getString("url");
                final String name = authJson.getString("name");
                final String path = OSSUnit.uploadInfo(url, name);
                if (StringUtils.isEmpty(path)) {
                    ArbitrateServiceImpl.log.error("\u8ba4\u520a\u4e66\u9644\u4ef6\u4e0a\u4f20oss\u5931\u8d25,path={}", (Object)path);
                    throw new BusinessException("\u516c\u544a\u63a8\u9001\u5931\u8d25");
                }
                authJson.put("url", (Object)path);
                authUrlNew.add((Object)authJson);
            }
            req.setAuthUrl(authUrlNew.toJSONString());
        }
        if (req.getPayUrl() != null) {
            final JSONArray payUrl = (JSONArray)JSONObject.parseObject(req.getPayUrl(), (Class)JSONArray.class);
            final JSONArray payUrlNew = new JSONArray();
            for (final Object payObject : payUrl) {
                final JSONObject payJson = (JSONObject)payObject;
                final String url = payJson.getString("url");
                final String name = payJson.getString("name");
                final String path = OSSUnit.uploadInfo(url, name);
                if (StringUtils.isEmpty(path)) {
                    ArbitrateServiceImpl.log.error("\u4ed8\u6b3e\u51ed\u8bc1\u9644\u4ef6\u4e0a\u4f20oss\u5931\u8d25,path={}", (Object)path);
                    throw new BusinessException("\u516c\u544a\u63a8\u9001\u5931\u8d25");
                }
                payJson.put("url", (Object)path);
                payUrlNew.add((Object)payJson);
            }
            req.setPayUrl(payUrlNew.toJSONString());
        }
    }
    
    public void whitesValidated(final String channelId, final HttpServletRequest request) {
        final String ipAddr = IpUtil.getIpAddr(request);
        ArbitrateServiceImpl.log.info("\u6e20\u9053\uff1a{}\uff0cip\uff1a{}", (Object)channelId, (Object)ipAddr);
        if (StringUtils.isEmpty(ipAddr)) {
            throw new BusinessException("ip\u5730\u5740\u5f02\u5e38");
        }
        final NotifyUrlProperties.Config config = this.notifyUrlProperties.getBySource(channelId);
        if (config != null && StringUtils.isNotEmpty(config.getWhites())) {
            final String[] whites = config.getWhites().split(",");
            if (!com.tjfae.common.core.utils.StringUtils.matches(ipAddr, whites)) {
                throw new BusinessException("ip\u5730\u5740\u975e\u6cd5");
            }
        }
    }
    
    public AjaxResult statusNotify(NoticePublish noticePublish) {
        if (noticePublish == null || noticePublish.getId() == null) {
            return AjaxResult.error("\u6570\u636e\u4e0d\u6b63\u786e");
        }
        if (noticePublish.getStatus() == null) {
            noticePublish = this.noticePublishService.findInfoById(noticePublish.getId().intValue());
            if (StatusEnum.OUTCANCEL.getValue().equals(noticePublish.getStatus())) {
                return AjaxResult.error("\u516c\u544a\u5df2\u88ab\u53d1\u5e03\u65b9\u53d6\u6d88\uff0c\u63a8\u9001\u5931\u8d25");
            }
        }
        final NotifyUrlProperties.Config config = this.notifyUrlProperties.getBySource(noticePublish.getSourceTag());
        if (config == null || config.getUrlNotify() == null) {
            return AjaxResult.error(noticePublish.getSource() + "\u5ba1\u6838\u7ed3\u679c\u901a\u77e5\u5730\u5740\u672a\u914d\u7f6e");
        }
        final String url = config.getStatusNotify();
        final NoticeNotifyReq req = new NoticeNotifyReq();
        req.setId(noticePublish.getId());
        req.setStatus(noticePublish.getStatus());
        req.setNoticeCode(noticePublish.getCode());
        req.setReason(noticePublish.getReason());
        if (StatusEnum.BACK.getValue().equals(noticePublish.getStatus())) {
            req.setReason(noticePublish.getReason());
        }
        ArbitrateServiceImpl.log.info("\u516c\u544a\u5ba1\u6838\u5f02\u6b65\u63a8\u9001\u53c2\u6570NoticeNotifyReq={}", (Object)req);
        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<NoticeNotifyReq> request = (HttpEntity<NoticeNotifyReq>)new HttpEntity((Object)req, (MultiValueMap)headers);
            final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(10000);
            factory.setReadTimeout(10000);
            final RestTemplate restTemplate = new RestTemplate((ClientHttpRequestFactory)factory);
            ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u5ba1\u6838\u7ed3\u679c\u901a\u77e5\u8bf7\u6c42\u53c2\u6570\uff1a[{}]", (Object)JSONObject.toJSONString((Object)req));
            final ResponseEntity<String> responseEntity = (ResponseEntity<String>)restTemplate.postForEntity(url, (Object)request, (Class)String.class, new Object[0]);
            final String responseEntityBody = (String)responseEntity.getBody();
            ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u5ba1\u6838\u7ed3\u679c\u901a\u77e5\u54cd\u5e94\u53c2\u6570\uff1a[{}]", (Object)JSONObject.toJSONString((Object)responseEntityBody));
            if ("200".equals(JSONObject.parseObject(responseEntityBody).getString("code"))) {
                return AjaxResult.success("\u63a8\u9001\u6210\u529f");
            }
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u5ba1\u6838\u7ed3\u679c\u901a\u77e5\u5f02\u5e38\uff1a[{}]", (Throwable)e);
            return AjaxResult.error("\u63a8\u9001\u5931\u8d25");
        }
        return AjaxResult.error("\u63a8\u9001\u5931\u8d25");
    }
    
    public AjaxResult urlNotify(NoticePublish noticePublish) {
        if (noticePublish == null || noticePublish.getId() == null) {
            return AjaxResult.error("\u6570\u636e\u4e0d\u6b63\u786e");
        }
        if (noticePublish.getNoticeUrl() == null) {
            noticePublish = this.noticePublishService.findInfoById(noticePublish.getId().intValue());
            if (StatusEnum.OUTCANCEL.getValue().equals(noticePublish.getStatus())) {
                return AjaxResult.error("\u516c\u544a\u5df2\u88ab\u53d1\u5e03\u65b9\u53d6\u6d88\uff0c\u63a8\u9001\u5931\u8d25");
            }
        }
        final NotifyUrlProperties.Config config = this.notifyUrlProperties.getBySource(noticePublish.getSourceTag());
        if (config == null || config.getUrlNotify() == null) {
            return AjaxResult.error(noticePublish.getSource() + "\u94fe\u63a5\u901a\u77e5\u5730\u5740\u672a\u914d\u7f6e");
        }
        final String url = config.getUrlNotify();
        final NoticeNotifyReq req = new NoticeNotifyReq();
        req.setId(noticePublish.getId());
        req.setStatus(noticePublish.getStatus());
        req.setNoticeUrl(noticePublish.getNoticeUrl());
        ArbitrateServiceImpl.log.info("\u516c\u544a\u94fe\u63a5\u901a\u77e5\u53c2\u6570NoticeNotifyReq={}", (Object)req);
        try {
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            final HttpEntity<NoticeNotifyReq> request = (HttpEntity<NoticeNotifyReq>)new HttpEntity((Object)req, (MultiValueMap)headers);
            final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
            factory.setConnectTimeout(10000);
            factory.setReadTimeout(10000);
            final RestTemplate restTemplate = new RestTemplate((ClientHttpRequestFactory)factory);
            ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u94fe\u63a5\u901a\u77e5\u8bf7\u6c42\u53c2\u6570\uff1a[{}]", (Object)JSONObject.toJSONString((Object)req));
            final ResponseEntity<String> responseEntity = (ResponseEntity<String>)restTemplate.postForEntity(url, (Object)request, (Class)String.class, new Object[0]);
            final String responseEntityBody = (String)responseEntity.getBody();
            ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u94fe\u63a5\u901a\u77e5\u54cd\u5e94\u53c2\u6570\uff1a[{}]", (Object)JSONObject.toJSONString((Object)responseEntityBody));
            if ("200".equals(JSONObject.parseObject(responseEntityBody).getString("code"))) {
                return AjaxResult.success("\u63a8\u9001\u6210\u529f");
            }
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u94fe\u63a5\u901a\u77e5\u63a5\u53e3\u5f02\u5e38\uff1a[{}]", (Throwable)e);
            return AjaxResult.error("\u63a8\u9001\u5931\u8d25");
        }
        return AjaxResult.error("\u63a8\u9001\u5931\u8d25");
    }
    
    public String selectMaxCode(final String year) {
        return this.noticePublishService.selectMaxCode(year);
    }
    
    public static String convert(final String html) {
        if (StringUtils.isEmpty(html)) {
            return "";
        }
        final org.jsoup.nodes.Document document = Jsoup.parse(html);
        final org.jsoup.nodes.Document.OutputSettings outputSettings = new org.jsoup.nodes.Document.OutputSettings().prettyPrint(false);
        document.outputSettings(outputSettings);
        document.select("br").append("\\n");
        document.select("p").prepend("\\n");
        final String newHtml = document.html().replaceAll("\\\\n", "\n");
        final String plainText = Jsoup.clean(newHtml, "", Whitelist.none(), outputSettings);
        final String result = StringEscapeUtils.unescapeHtml(plainText.trim());
        return result;
    }
    
    @Transactional(rollbackFor = { Exception.class })
    public String payNotice(final NoticePublishReq param) {
        ArbitrateServiceImpl.log.info("\u516c\u793a\u516c\u544a\u652f\u4ed8\u63a5\u53e3\u5f00\u59cb");
        final PayDto payDto = new PayDto();
        payDto.setUserId(param.getUserId());
        payDto.setOrderId(param.getPayOrderId());
        payDto.setOrderAmount(new BigDecimal(param.getPayAmount()));
        payDto.setOrderType("zdl_notice");
        payDto.setProductName("\u4e2d\u8fbe\u94fe\u516c\u793a\u516c\u544a\u670d\u52a1");
        payDto.setProductId("zdl_notice_" + DateUtils.dateTimeNow());
        payDto.setBusinessType("notice");
        payDto.setOrderDesc("\u4e2d\u8fbe\u94fe\u7ebf\u4e0a\u652f\u4ed8\uff1a" + param.getId());
        payDto.setPayFinishUrl(param.getCallBackUrl());
        final HttpUtils httpUtils = new HttpUtils();
        String payResult;
        try {
            payResult = httpUtils.sendJsonPost("\u4e2d\u8fbe\u94fe\u516c\u793a\u516c\u544a\u652f\u4ed8", this.payUrl, JsonUtils.beanToMap((Object)payDto));
            ArbitrateServiceImpl.log.info("\u8c03\u7528\u652f\u4ed8\u8fd4\u56de\u7ed3\u679cpayResult={}", (Object)JSON.toJSONString((Object)payResult));
        }
        catch (final Exception e) {
            ArbitrateServiceImpl.log.error("\u8c03\u7528\u652f\u4ed8\u5f02\u5e38,orderId=" + payDto.getOrderId(), (Throwable)e);
            throw new BusinessException("\u652f\u4ed8\u5931\u8d25\uff0c\u8bf7\u8054\u7cfb\u5f00\u53d1\u4eba\u5458\uff01");
        }
        if (StringUtils.isEmpty(payResult)) {
            throw new BusinessException("\u8ba2\u5355\u652f\u4ed8\u5931\u8d25\uff0c\u652f\u4ed8\u8fd4\u56de\u4e3anull");
        }
        final JSONObject jsonObject = JSON.parseObject(payResult);
        final R resultJson = (R)JSON.toJavaObject((JSON)jsonObject, (Class)R.class);
        if (!Constants.SUCCESS.equals(resultJson.getCode()) || ObjectUtil.isEmpty(resultJson.getData())) {
            ArbitrateServiceImpl.log.error("\u8ba2\u5355 {} \u652f\u4ed8\u5931\u8d25", (Object)payDto.getOrderId());
            throw new BusinessException("\u8ba2\u5355\u652f\u4ed8\u5931\u8d25\uff0c\u539f\u56e0\uff1a{}", resultJson.getMsg());
        }
        final String frontUrl = (String)resultJson.getData();
        ArbitrateServiceImpl.log.info("\u8ba2\u5355 {} \u8c03\u7528\u652f\u4ed8\u6210\u529f, frontUrl={}", (Object)payDto.getOrderId(), (Object)frontUrl);
        if (StringUtils.isEmpty(frontUrl)) {
            throw new BusinessException("\u8ba2\u5355\u652f\u4ed8\u5931\u8d25\uff0cfrontUrl\u4e3anull");
        }
        return frontUrl;
    }
    
    public R updateOrderStatus(final PayCallbackDto payCallbackDto) {
        ArbitrateServiceImpl.log.info("\u652f\u4ed8\u901a\u77e5\u652f\u4ed8\u7ed3\u679c\uff1a" + JSON.toJSONString((Object)payCallbackDto));
        if (payCallbackDto == null) {
            return R.error("\u652f\u4ed8\u56de\u8c03\u5931\u8d25,payCallbackDto\u4e3a\u7a7a");
        }
        if (StringUtil.isEmpty(payCallbackDto.getPayId())) {
            return R.error("\u652f\u4ed8\u56de\u8c03\u5931\u8d25,payId\u4e3a\u7a7a");
        }
        if (StringUtil.isEmpty(payCallbackDto.getPayStatus())) {
            return R.error("\u652f\u4ed8\u56de\u8c03\u5931\u8d25,payStatus\u4e3a\u7a7a");
        }
        final Date nowDate = DateUtils.getCurrentDate();
        final NoticePublish noticePublish = new NoticePublish();
        noticePublish.setPayOrderId(payCallbackDto.getPayId());
        noticePublish.setUpdateTime(nowDate);
        noticePublish.setPayCallbackTime(nowDate);
        noticePublish.setPayStatus(payCallbackDto.getPayStatus());
        ArbitrateServiceImpl.log.info("\u652f\u4ed8\u56de\u8c03\u66f4\u65b0\u6570\u636e\uff1a" + JSONObject.toJSONString((Object)noticePublish));
        final int updatePayStatusNum = this.noticePublishService.updateByOrderId(noticePublish);
        ArbitrateServiceImpl.log.info("\u652f\u4ed8\u6210\u529f\uff0c\u66f4\u65b0\u652f\u4ed8\u72b6\u6001\u5b8c\u6210,updatePayStatusNum={}", (Object)updatePayStatusNum);
        return R.success();
    }
    
    static {
        ArbitrateServiceImpl.log = LoggerFactory.getLogger((Class)ArbitrateServiceImpl.class);
        ArbitrateServiceImpl.logger = LoggerFactory.getLogger((Class)ArbitrateServiceImpl.class);
        (ArbitrateServiceImpl.titleIndexMap = new HashMap()).put(1, "\u4e00\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(2, "\u4e8c\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(3, "\u4e09\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(4, "\u56db\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(5, "\u4e94\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(6, "\u516d\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(7, "\u4e03\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(8, "\u516b\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(9, "\u4e5d\u3001");
        ArbitrateServiceImpl.titleIndexMap.put(10, "\u5341\u3001");
    }
    
    private class HSUserInfo
    {
        private boolean isOperator;
        private String socialCode;
        private String userId;
        private String userName;
        private String companyName;
        private String companyAccount;
        private String userAccount;
        
        public HSUserInfo() {
            // 内部类构造函数
        }
        
        public boolean isOperator() {
            return this.isOperator;
        }
        
        public String getSocialCode() {
            return this.socialCode;
        }
        
        public String getUserId() {
            return this.userId;
        }
        
        public String getUserName() {
            return this.userName;
        }
        
        public String getCompanyName() {
            return this.companyName;
        }
        
        public String getCompanyAccount() {
            return this.companyAccount;
        }
        
        public String getUserAccount() {
            return this.userAccount;
        }
        
        public void setOperator(final boolean isOperator) {
            this.isOperator = isOperator;
        }
        
        public void setSocialCode(final String socialCode) {
            this.socialCode = socialCode;
        }
        
        public void setUserId(final String userId) {
            this.userId = userId;
        }
        
        public void setUserName(final String userName) {
            this.userName = userName;
        }
        
        public void setCompanyName(final String companyName) {
            this.companyName = companyName;
        }
        
        public void setCompanyAccount(final String companyAccount) {
            this.companyAccount = companyAccount;
        }
        
        public void setUserAccount(final String userAccount) {
            this.userAccount = userAccount;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof HSUserInfo)) {
                return false;
            }
            final HSUserInfo other = (HSUserInfo)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            if (this.isOperator() != other.isOperator()) {
                return false;
            }
            final Object this$socialCode = this.getSocialCode();
            final Object other$socialCode = other.getSocialCode();
            Label_0078: {
                if (this$socialCode == null) {
                    if (other$socialCode == null) {
                        break Label_0078;
                    }
                }
                else if (this$socialCode.equals(other$socialCode)) {
                    break Label_0078;
                }
                return false;
            }
            final Object this$userId = this.getUserId();
            final Object other$userId = other.getUserId();
            Label_0115: {
                if (this$userId == null) {
                    if (other$userId == null) {
                        break Label_0115;
                    }
                }
                else if (this$userId.equals(other$userId)) {
                    break Label_0115;
                }
                return false;
            }
            final Object this$userName = this.getUserName();
            final Object other$userName = other.getUserName();
            Label_0152: {
                if (this$userName == null) {
                    if (other$userName == null) {
                        break Label_0152;
                    }
                }
                else if (this$userName.equals(other$userName)) {
                    break Label_0152;
                }
                return false;
            }
            final Object this$companyName = this.getCompanyName();
            final Object other$companyName = other.getCompanyName();
            Label_0189: {
                if (this$companyName == null) {
                    if (other$companyName == null) {
                        break Label_0189;
                    }
                }
                else if (this$companyName.equals(other$companyName)) {
                    break Label_0189;
                }
                return false;
            }
            final Object this$companyAccount = this.getCompanyAccount();
            final Object other$companyAccount = other.getCompanyAccount();
            Label_0226: {
                if (this$companyAccount == null) {
                    if (other$companyAccount == null) {
                        break Label_0226;
                    }
                }
                else if (this$companyAccount.equals(other$companyAccount)) {
                    break Label_0226;
                }
                return false;
            }
            final Object this$userAccount = this.getUserAccount();
            final Object other$userAccount = other.getUserAccount();
            if (this$userAccount == null) {
                if (other$userAccount == null) {
                    return true;
                }
            }
            else if (this$userAccount.equals(other$userAccount)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof HSUserInfo;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * 59 + (this.isOperator() ? 79 : 97);
            final Object $socialCode = this.getSocialCode();
            result = result * 59 + (($socialCode == null) ? 43 : $socialCode.hashCode());
            final Object $userId = this.getUserId();
            result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
            final Object $userName = this.getUserName();
            result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
            final Object $companyName = this.getCompanyName();
            result = result * 59 + (($companyName == null) ? 43 : $companyName.hashCode());
            final Object $companyAccount = this.getCompanyAccount();
            result = result * 59 + (($companyAccount == null) ? 43 : $companyAccount.hashCode());
            final Object $userAccount = this.getUserAccount();
            result = result * 59 + (($userAccount == null) ? 43 : $userAccount.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "ArbitrateServiceImpl.HSUserInfo(isOperator=" + this.isOperator() + ", socialCode=" + this.getSocialCode() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", companyName=" + this.getCompanyName() + ", companyAccount=" + this.getCompanyAccount() + ", userAccount=" + this.getUserAccount() + ")";
        }
    }
}
