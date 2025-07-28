// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import com.tjfae.notice.response.EvidenceVerifyResp;
import com.tjfae.notice.utils.Md5Algs;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import java.io.ByteArrayOutputStream;
import com.tjfae.notice.utils.json.JsonUtils;
import com.tjfae.notice.entity.EvidenceFile;
import com.tjfae.common.core.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EvidenceVerifyServiceImpl
{
    private static final Logger logger;
    @Resource
    private BcPlatformServiceImpl bcPlatformService;
    @Resource
    private SkyChainPlatformServiceImpl skyChainPlatformService;
    
    public Object fileEvVerify(final String evSn, final MultipartFile filePart) throws Exception {
        Object obj = null;
        EvidenceFile evidenceFile = null;
        try {
            obj = this.bcPlatformService.queryByTxId(evSn);
            if (null == obj) {
                EvidenceVerifyServiceImpl.logger.error("Get evidence record failed!");
                throw new BusinessException("Get evidence record failed!");
            }
        }
        catch (final Exception e) {
            EvidenceVerifyServiceImpl.logger.warn(e.getMessage());
            throw new BusinessException("Get evidence record failed!");
        }
        evidenceFile = (EvidenceFile)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(obj), (Class)EvidenceFile.class);
        if (null == evidenceFile) {
            EvidenceVerifyServiceImpl.logger.error("Invalid file evidence!");
            throw new BusinessException("Invalid file evidence!");
        }
        evidenceFile.deserialization();
        EvidenceVerifyServiceImpl.logger.info("blockchain === " + JsonUtils.writeEntityJSON((Object)evidenceFile.getEvidenceInfo().getFileEvidenceData()));
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(filePart.getInputStream(), (OutputStream)baos);
        final String digest = Md5Algs.getMD5(baos.toByteArray());
        EvidenceVerifyServiceImpl.logger.info("digest == " + digest);
        if (!digest.equals(evidenceFile.getEvidenceInfo().getFileEvidenceData().getHash())) {
            EvidenceVerifyServiceImpl.logger.warn("Verify file evidence failed!");
            throw new BusinessException("Verify file evidence failed!");
        }
        final EvidenceVerifyResp resp = new EvidenceVerifyResp();
        resp.setBlockHash(evidenceFile.getBlockDataHash());
        resp.setEvHash(evSn);
        resp.setEvType(evidenceFile.getEvidenceInfo().getType());
        resp.setEvTime(evidenceFile.getDate());
        resp.setBlockHeight(String.valueOf(evidenceFile.getHeight()));
        return resp;
    }
    
    public Object fileSkyChainEvVerify(final String evSn, final MultipartFile filePart) throws Exception {
        Object obj = null;
        EvidenceFile evidenceFile = null;
        try {
            obj = this.skyChainPlatformService.queryByTxId(evSn);
            if (null == obj) {
                EvidenceVerifyServiceImpl.logger.error("Get evidence record failed!");
                throw new BusinessException("Get evidence record failed!");
            }
        }
        catch (final Exception e) {
            EvidenceVerifyServiceImpl.logger.warn(e.getMessage());
            throw new BusinessException("Get evidence record failed!");
        }
        evidenceFile = (EvidenceFile)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(obj), (Class)EvidenceFile.class);
        if (null == evidenceFile) {
            EvidenceVerifyServiceImpl.logger.error("Invalid file evidence!");
            throw new BusinessException("Invalid file evidence!");
        }
        evidenceFile.deserialization();
        EvidenceVerifyServiceImpl.logger.info("blockchain === " + JsonUtils.writeEntityJSON((Object)evidenceFile.getEvidenceInfo().getFileEvidenceData()));
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(filePart.getInputStream(), (OutputStream)baos);
        final String digest = Md5Algs.getMD5(baos.toByteArray());
        EvidenceVerifyServiceImpl.logger.info("digest == " + digest);
        if (!digest.equals(evidenceFile.getEvidenceInfo().getFileEvidenceData().getHash())) {
            EvidenceVerifyServiceImpl.logger.warn("Verify file evidence failed!");
            throw new BusinessException("Verify file evidence failed!");
        }
        final EvidenceVerifyResp resp = new EvidenceVerifyResp();
        resp.setBlockHash(evidenceFile.getBlockDataHash());
        resp.setEvHash(evSn);
        resp.setEvType(evidenceFile.getEvidenceInfo().getType());
        resp.setEvTime(evidenceFile.getDate());
        resp.setBlockHeight(String.valueOf(evidenceFile.getHeight()));
        return resp;
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)EvidenceVerifyServiceImpl.class);
    }
}
