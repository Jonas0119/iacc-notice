// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service;

import java.util.List;
import com.tjfae.notice.entity.AssetsInfo;

public interface IAssetsInfoService
{
    AssetsInfo selectAssetsInfoById(final Long p0);
    
    List<AssetsInfo> selectAssetsInfoList(final AssetsInfo p0);
    
    List<AssetsInfo> selectAssetsInfoListLimit(final AssetsInfo p0);
    
    int insertAssetsInfo(final AssetsInfo p0);
    
    int updateAssetsInfo(final AssetsInfo p0);
    
    int updateTradeId(final AssetsInfo p0);
    
    int deleteAssetsInfoByIds(final Long[] p0);
    
    int deleteAssetsInfoById(final Long p0);
}
