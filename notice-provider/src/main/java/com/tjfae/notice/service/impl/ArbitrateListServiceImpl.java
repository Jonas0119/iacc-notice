// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.ArbitrateRecord;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitrateRecordService;

@Service
public class ArbitrateListServiceImpl implements IArbitrateRecordService
{
    public int deleteById(final Long id) {
        return 0;
    }
    
    public ArbitrateRecord findById(final Long id) {
        return null;
    }
    
    @SaveAll(value = "arbitrateListServiceImpl", classType = ArbitrateListServiceImpl.class)
    public void saveAll(final List<ArbitrateRecord> all) {
    }
    
    @Save(value = "arbitrateListServiceImpl", classType = ArbitrateListServiceImpl.class)
    public void save(final ArbitrateRecord arbitrateRecord) {
    }
    
    public int insert(final ArbitrateRecord arbitrateRecord) {
        return 0;
    }
    
    public int update(final ArbitrateRecord arbitrateRecord) {
        return 0;
    }
    
    public int insertBatch(final List<ArbitrateRecord> list) {
        return 0;
    }
    
    public int updateBatch(final List<ArbitrateRecord> list) {
        return 0;
    }
}
