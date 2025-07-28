// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service;

import java.util.Map;
import java.util.List;
import com.tjfae.notice.entity.hs.UCCompany;
import com.tjfae.notice.entity.hs.UCUserInfo;

public interface IUserCenterService
{
    UCUserInfo queryUserInfoRequest(final String p0) throws Exception;
    
    UCCompany queryCompInfoRequest(final String p0) throws Exception;
    
    List<Map<String, Object>> getCustomerList(final String p0) throws Exception;
}
