// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.common.core.utils.DateUtils;
import java.util.List;
import com.tjfae.notice.entity.AssetsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.tjfae.notice.mapper.AssetsInfoMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IAssetsInfoService;

@Service
public class AssetsInfoServiceImpl implements IAssetsInfoService
{
    @Autowired
    private AssetsInfoMapper assetsInfoMapper;
    
    public AssetsInfo selectAssetsInfoById(final Long id) {
        return this.assetsInfoMapper.selectAssetsInfoById(id);
    }
    
    public List<AssetsInfo> selectAssetsInfoList(final AssetsInfo assetsInfo) {
        return this.assetsInfoMapper.selectAssetsInfoList(assetsInfo);
    }
    
    public List<AssetsInfo> selectAssetsInfoListLimit(final AssetsInfo assetsInfo) {
        return this.assetsInfoMapper.selectAssetsInfoListLimit(assetsInfo);
    }
    
    public int insertAssetsInfo(final AssetsInfo assetsInfo) {
        assetsInfo.setCreateTime(DateUtils.getNowDate());
        return this.assetsInfoMapper.insertAssetsInfo(assetsInfo);
    }
    
    public int updateAssetsInfo(final AssetsInfo assetsInfo) {
        return this.assetsInfoMapper.updateAssetsInfo(assetsInfo);
    }
    
    public int updateTradeId(final AssetsInfo assetsInfo) {
        return this.assetsInfoMapper.updateTradeId(assetsInfo);
    }
    
    public int deleteAssetsInfoByIds(final Long[] ids) {
        return this.assetsInfoMapper.deleteAssetsInfoByIds(ids);
    }
    
    public int deleteAssetsInfoById(final Long id) {
        return this.assetsInfoMapper.deleteAssetsInfoById(id);
    }
}
