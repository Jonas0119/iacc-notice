// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.utils.http.MyHttpClient;
import com.tjfae.notice.utils.http.JsonResponseHandler;
import com.tjfae.notice.utils.json.JsonUtils;
import com.tjfae.notice.response.ResultJson;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BcPlatformServiceImpl
{
    private static Logger logger;
    @Value("${bcplatform.url}")
    private String baseUrl;
    
    public Object queryEvidenceByUser(final Map<String, Object> params) throws Exception {
        final String url = this.baseUrl + "/queryEvidenceByUser";
        final ResultJson res = (ResultJson)MyHttpClient.execute(url, JsonUtils.toJson((Object)params), JsonResponseHandler.createResponseHandler((Class)ResultJson.class));
        if ("200".equals(res.getCode())) {
            return res.getData();
        }
        throw new BusinessException(res.getMsg());
    }
    
    public Object queryHistoryForKey(final Map<String, Object> params) throws Exception {
        final String url = this.baseUrl + "/queryHistoryForKey";
        final ResultJson res = (ResultJson)MyHttpClient.execute(url, JsonUtils.toJson((Object)params), JsonResponseHandler.createResponseHandler((Class)ResultJson.class));
        if ("200".equals(res.getCode())) {
            return res.getData();
        }
        throw new BusinessException(res.getMsg());
    }
    
    public Object queryByTxId(final String txId) throws Exception {
        final String url = this.baseUrl + "/queryByTxId/" + txId;
        final ResultJson res = (ResultJson)MyHttpClient.execute(url, (Map)null, "get", JsonResponseHandler.createResponseHandler((Class)ResultJson.class));
        if ("200".equals(res.getCode())) {
            return res.getData();
        }
        throw new BusinessException(res.getMsg());
    }
    
    public Object queryEvidenceRecords(final Map<String, Object> params) throws Exception {
        final String url = this.baseUrl + "/queryEvidenceRecords";
        final ResultJson res = (ResultJson)MyHttpClient.execute(url, JsonUtils.toJson((Object)params), JsonResponseHandler.createResponseHandler((Class)ResultJson.class));
        if ("200".equals(res.getCode())) {
            return res.getData();
        }
        throw new BusinessException(res.getMsg());
    }
    
    static {
        BcPlatformServiceImpl.logger = LoggerFactory.getLogger((Class)BcPlatformServiceImpl.class);
    }
}
