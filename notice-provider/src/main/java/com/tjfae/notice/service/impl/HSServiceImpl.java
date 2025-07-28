// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import com.tjfae.notice.entity.hs.HSCompanyInfoBo;
import java.util.Map;
import com.tjfae.notice.utils.http.MyHttpClient;
import com.tjfae.notice.utils.http.JsonResponseHandler;
import java.util.HashMap;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.entity.hs.HSUserInfoBo;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IHSService;

@Service
public class HSServiceImpl implements IHSService
{
    private static Logger logger;
    @Value("${hs.urlPath}")
    private String hsUrl;
    @Value("${hs.platformId}")
    private String hsPlatformId;
    @Value("${hs.appKey}")
    private String hsAppKey;
    @Value("${hs.success}")
    private String hsSuccessCode;
    @Value("${hs.queryUserInfoRequest}")
    private String queryUserInfoRequest;
    @Value("${hs.queryCompInfoRequest}")
    private String queryCompInfoRequest;
    
    public HSUserInfoBo.UserInfoBo queryUserInfoRequest(final String loginAccount) throws Exception {
        if (StringUtils.isBlank(loginAccount)) {
            HSServiceImpl.logger.error("loginAccount is blank!");
            throw new BusinessException("loginAccount is blank!");
        }
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("channel", this.queryUserInfoRequest);
        params.put("loginAccount", loginAccount);
        params.put("platformId", this.hsPlatformId);
        final HSUserInfoBo hsUserInfoBo = (HSUserInfoBo)MyHttpClient.execute(this.hsUrl, (Map)params, "get", JsonResponseHandler.createResponseHandler((Class)HSUserInfoBo.class));
        if (this.hsSuccessCode.equals(hsUserInfoBo.getCode())) {
            return hsUserInfoBo.getUserInfoBo();
        }
        HSServiceImpl.logger.error(hsUserInfoBo.getCode() + " " + hsUserInfoBo.getResult());
        return null;
    }
    
    public HSCompanyInfoBo.CompanyInfoBo queryCompInfoRequest(final String optCompCode, final String socialCode) throws Exception {
        if (StringUtils.isBlank(optCompCode)) {
            HSServiceImpl.logger.error("optCompCode is blank!");
            throw new BusinessException("optCompCode is blank!");
        }
        if (StringUtils.isBlank(socialCode)) {
            HSServiceImpl.logger.error("socialCode is blank!");
            throw new BusinessException("socialCode is blank!");
        }
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("channel", this.queryCompInfoRequest);
        params.put("optCompCode", optCompCode);
        params.put("platformId", this.hsPlatformId);
        final HSCompanyInfoBo hsCompanyInfoBo = (HSCompanyInfoBo)MyHttpClient.execute(this.hsUrl, (Map)params, "get", JsonResponseHandler.createResponseHandler((Class)HSCompanyInfoBo.class));
        if (this.hsSuccessCode.equals(hsCompanyInfoBo.getCode())) {
            return hsCompanyInfoBo.getCompanyInfoBo();
        }
        HSServiceImpl.logger.error(hsCompanyInfoBo.getCode() + " " + hsCompanyInfoBo.getResult());
        return null;
    }
    
    static {
        HSServiceImpl.logger = LoggerFactory.getLogger((Class)HSServiceImpl.class);
    }
}
