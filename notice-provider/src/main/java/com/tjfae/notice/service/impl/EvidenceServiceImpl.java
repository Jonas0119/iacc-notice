// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import com.tjfae.notice.response.EvidenceInfoResp;
import java.util.List;
import com.tjfae.notice.request.EvidenceInfoReq;
import com.tjfae.notice.response.EvidenceDetailQueryResp;
import com.tjfae.notice.request.EvidenceHistoryQueryReq;
import java.util.Iterator;
import com.tjfae.notice.entity.EvidenceHistory;
import com.tjfae.notice.entity.EvidencePageUser;
import java.util.Map;
import com.tjfae.notice.utils.json.JsonUtils;
import java.util.HashMap;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.response.EvidencePageQueryByUserResp;
import com.tjfae.notice.request.EvidencePageQueryByUserReq;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EvidenceServiceImpl
{
    private static final Logger logger;
    @Resource
    private BcPlatformServiceImpl bcPlatformService;
    @Resource
    private ArbitrateServiceImpl arbitrateService;
    @Resource
    private SkyChainPlatformServiceImpl skyChainPlatformService;
    
    public Object getUserEvidencePages(final EvidencePageQueryByUserReq req) throws Exception {
        final EvidencePageQueryByUserResp evidencePageQueryByUserResp = new EvidencePageQueryByUserResp();
        if (StringUtils.isBlank(req.getUser().getUserId())) {
            return evidencePageQueryByUserResp;
        }
        final String userId = this.arbitrateService.getUserId(req.getLoginAccount(), req.getUser().getUserId(), req.getSearchType());
        req.getUser().setUserId(userId);
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", JsonUtils.toJson((Object)req.getUser()));
        if (StringUtils.isNotBlank(req.getUserPlatform())) {
            params.put("userPlatform", req.getUserPlatform());
        }
        else {
            params.put("userPlatform", "");
        }
        params.put("startTxId", req.getStartTxId());
        params.put("pageSize", req.getPageSize());
        EvidencePageUser evidencePageUser = null;
        try {
            EvidenceServiceImpl.logger.info("\u83b7\u53d6\u7528\u6237\u5b58\u8bc1\u53c2\u6570\u4fe1\u606f:{}", (Object)JsonUtils.toJson((Object)params));
            final Object result = this.skyChainPlatformService.queryEvidenceByUser((Map)params);
            evidencePageUser = (EvidencePageUser)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(result), (Class)EvidencePageUser.class);
            if (null != evidencePageUser) {
                for (final EvidenceHistory oneHistory : evidencePageUser.getData()) {
                    oneHistory.deserialization();
                }
            }
        }
        catch (final Exception e) {
            EvidenceServiceImpl.logger.warn(e.getMessage());
            return evidencePageQueryByUserResp;
        }
        evidencePageQueryByUserResp.update(evidencePageUser);
        return evidencePageQueryByUserResp;
    }
    
    public Object getEvidenceDetail(final EvidenceHistoryQueryReq req) throws Exception {
        EvidenceServiceImpl.logger.info("\u6839\u636ekey\u67e5\u8be2\u5386\u53f2\u4ea4\u6613\u4fe1\u606f:{}", (Object)JsonUtils.toJson((Object)req));
        final Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(req.getUserPlatform())) {
            params.put("userPlatform", req.getUserPlatform());
        }
        else {
            params.put("userPlatform", "");
        }
        params.put("evidenceId", req.getEvidenceId());
        EvidenceServiceImpl.logger.info("bcPlatform\u6839\u636ekey\u67e5\u8be2\u5386\u53f2\u4ea4\u6613\u4fe1\u606f:{}", (Object)JsonUtils.toJson((Object)params));
        final Object result = this.skyChainPlatformService.queryHistoryForKey((Map)params);
        final EvidenceHistory evidenceHistory = (EvidenceHistory)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(result), (Class)EvidenceHistory.class);
        evidenceHistory.deserialization();
        final EvidenceDetailQueryResp evidenceDetailQueryResp = new EvidenceDetailQueryResp();
        evidenceDetailQueryResp.update(evidenceHistory);
        return evidenceDetailQueryResp;
    }
    
    public List<EvidenceInfoResp> queryEvidenceRecords(final EvidenceInfoReq req) throws Exception {
        List<EvidenceInfoResp> infoRespList = new ArrayList<EvidenceInfoResp>();
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("queryAmount", "1000");
        params.put("businessType", req.getBusinessType());
        final Object result = this.skyChainPlatformService.queryEvidenceRecords((Map)params);
        EvidenceServiceImpl.logger.info("bcPlatform\u67e5\u8be2\u5b58\u8bc1\u8bb0\u5f55\u4fe1\u606f:{}", (Object)JsonUtils.toJson(result));
        if (result != null) {
            infoRespList = (List)result;
        }
        return infoRespList;
    }
    
    public Object getTxDetail(final String txId) throws Exception {
        final Object result = this.skyChainPlatformService.queryByTxId(txId);
        return result;
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)EvidenceServiceImpl.class);
    }
}
