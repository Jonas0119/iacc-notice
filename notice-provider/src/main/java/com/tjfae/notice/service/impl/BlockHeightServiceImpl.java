// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.BlockHeight;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.BlockHeightMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IBlockHeightService;

@Service
public class BlockHeightServiceImpl implements IBlockHeightService
{
    @Resource
    private BlockHeightMapper blockHeightMapper;
    
    public int deleteById(final Long id) {
        return 0;
    }
    
    public BlockHeight findById(final Long id) {
        return null;
    }
    
    @SaveAll(value = "blockHeightServiceImpl", classType = BlockHeightServiceImpl.class)
    public void saveAll(final List<BlockHeight> all) {
    }
    
    @Save(value = "blockHeightServiceImpl", classType = BlockHeightServiceImpl.class)
    public void save(final BlockHeight blockHeight) {
    }
    
    public int insert(final BlockHeight blockHeight) {
        return this.blockHeightMapper.insert(blockHeight);
    }
    
    public int update(final BlockHeight blockHeight) {
        return 0;
    }
    
    public int insertBatch(final List<BlockHeight> list) {
        return 0;
    }
    
    public int updateBatch(final List<BlockHeight> list) {
        return 0;
    }
    
    public BlockHeight getByChannelName(final String channelName) {
        return this.blockHeightMapper.getByChannelName(channelName);
    }
    
    public int updateByChannelName(final BlockHeight b) {
        return this.blockHeightMapper.updateByChannelName(b);
    }
}
