// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.slf4j.LoggerFactory;
import java.util.List;
import com.tjfae.notice.entity.hs.UCCompany;
import com.tjfae.notice.utils.http.MyHttpClient;
import com.tjfae.notice.utils.http.JsonResponseHandler;
import java.util.Map;
import com.tjfae.notice.utils.json.JsonUtils;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.entity.hs.UCUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IUserCenterService;

@Service
@RefreshScope
public class UserCenterServiceImpl implements IUserCenterService
{
    private static final Logger logger;
    @Value("${usercenter.url}")
    private String usercenterUrl;
    @Value("${system.url}")
    private String systemUrl;
    
    public UCUserInfo queryUserInfoRequest(final String loginAccount) throws Exception {
        if (StringUtils.isBlank(loginAccount)) {
            UserCenterServiceImpl.logger.error("loginAccount is blank!");
            throw new BusinessException("loginAccount is blank!");
        }
        try {
            final String uri = this.usercenterUrl + "/userApp/queryUserInfoByCode?loginAccount=" + loginAccount;
            UserCenterServiceImpl.logger.info("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u4fe1\u606f\u53c2\u6570\uff1a{}", (Object)JsonUtils.toJson((Object)uri));
            final Map<String, Object> result = (Map<String, Object>)MyHttpClient.execute(uri, (Map)null, "post", JsonResponseHandler.createResponseHandler((Class)Map.class));
            UserCenterServiceImpl.logger.info("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u4fe1\u606f\u7ed3\u679c\uff1a{}", (Object)JsonUtils.toJson((Object)result));
            if (result == null) {
                return null;
            }
            final UCUserInfo userInfoBo = (UCUserInfo)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(result.get("data")), (Class)UCUserInfo.class);
            return userInfoBo;
        }
        catch (final Exception e) {
            UserCenterServiceImpl.logger.error("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u4fe1\u606f\u5f02\u5e38{}", (Throwable)e);
            throw new BusinessException("\u7528\u6237\u4fe1\u606f\u67e5\u8be2\u5931\u8d25");
        }
    }
    
    public UCCompany queryCompInfoRequest(final String optCompCode) throws Exception {
        if (StringUtils.isBlank(optCompCode)) {
            UserCenterServiceImpl.logger.error("optCompCode is blank!");
            throw new BusinessException("optCompCode is blank!");
        }
        try {
            final String uri = this.usercenterUrl + "/userApp/queryCompInfoByCode?optCompCode=" + optCompCode;
            UserCenterServiceImpl.logger.info("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u4f01\u4e1a\u4fe1\u606f\u53c2\u6570\uff1a{}", (Object)JsonUtils.toJson((Object)uri));
            final Map<String, Object> result = (Map<String, Object>)MyHttpClient.execute(uri, (Map)null, "post", JsonResponseHandler.createResponseHandler((Class)Map.class));
            UserCenterServiceImpl.logger.info("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u4f01\u4e1a\u4fe1\u606f\u7ed3\u679c\uff1a{}", (Object)JsonUtils.toJson((Object)result));
            if (result == null) {
                return null;
            }
            final UCCompany company = (UCCompany)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(result.get("data")), (Class)UCCompany.class);
            return company;
        }
        catch (final Exception e) {
            UserCenterServiceImpl.logger.error("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u4f01\u4e1a\u4fe1\u606f\u5f02\u5e38{}", (Throwable)e);
            throw new BusinessException("\u7528\u6237\u4f01\u4e1a\u4fe1\u606f\u67e5\u8be2\u5931\u8d25");
        }
    }
    
    public List<Map<String, Object>> getCustomerList(final String managerName) throws Exception {
        if (StringUtils.isBlank(managerName)) {
            UserCenterServiceImpl.logger.error("managerName is blank!");
            throw new BusinessException("managerName is blank!");
        }
        try {
            final String uri = this.systemUrl + "/user/tradersPageList?userName=" + managerName;
            UserCenterServiceImpl.logger.info("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u5ba2\u6237\u7ecf\u7406\u53c2\u6570\uff1a{}", (Object)JsonUtils.toJson((Object)uri));
            final Map<String, Object> result = (Map<String, Object>)MyHttpClient.execute(uri, (Map)null, "post", JsonResponseHandler.createResponseHandler((Class)Map.class));
            UserCenterServiceImpl.logger.info("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u5ba2\u6237\u7ecf\u7406\u7ed3\u679c\uff1a{}", (Object)JsonUtils.toJson((Object)result));
            if (result == null) {
                return null;
            }
            final List<Map<String, Object>> company = (List<Map<String, Object>>)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(result.get("rows")), (Class)List.class);
            return company;
        }
        catch (final Exception e) {
            UserCenterServiceImpl.logger.error("\u3010\u7528\u6237\u4e2d\u5fc3\u3011\u67e5\u8be2\u5ba2\u6237\u7ecf\u7406\u5f02\u5e38{}", (Throwable)e);
            throw new BusinessException("\u5ba2\u6237\u7ecf\u7406\u67e5\u8be2\u5931\u8d25");
        }
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)UserCenterServiceImpl.class);
    }
}
