// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.ArbitrateRecordApplicationItem;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ArbitrateRecordApplicationItemMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitrateRecordApplicationItemService;

@Service
public class ArbitrateRecordApplicationItemServiceImpl implements IArbitrateRecordApplicationItemService
{
    @Resource
    private ArbitrateRecordApplicationItemMapper arbitrateRecordApplicationItemMapper;
    
    public int deleteById(final Long id) {
        return this.arbitrateRecordApplicationItemMapper.deleteById(id);
    }
    
    public ArbitrateRecordApplicationItem findById(final Long id) {
        return this.arbitrateRecordApplicationItemMapper.findById(id);
    }
    
    @SaveAll(value = "arbitrateRecordApplicationItemServiceImpl", classType = ArbitrateRecordApplicationItemServiceImpl.class)
    public void saveAll(final List<ArbitrateRecordApplicationItem> all) {
    }
    
    @Save(value = "arbitrateRecordApplicationItemServiceImpl", classType = ArbitrateRecordApplicationItemServiceImpl.class)
    public void save(final ArbitrateRecordApplicationItem arbitrateRecord) {
    }
    
    public int insert(final ArbitrateRecordApplicationItem arbitrateRecord) {
        return this.arbitrateRecordApplicationItemMapper.insert(arbitrateRecord);
    }
    
    public int update(final ArbitrateRecordApplicationItem arbitrateRecord) {
        return this.arbitrateRecordApplicationItemMapper.update(arbitrateRecord);
    }
    
    public int insertBatch(final List<ArbitrateRecordApplicationItem> list) {
        return this.arbitrateRecordApplicationItemMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<ArbitrateRecordApplicationItem> list) {
        return this.arbitrateRecordApplicationItemMapper.updateBatch((List)list);
    }
    
    public List<ArbitrateRecordApplicationItem> findByEvidenceId(final String evidenceId) {
        return this.arbitrateRecordApplicationItemMapper.findByEvidenceId(evidenceId);
    }
    
    public List<ArbitrateRecordApplicationItem> findByRecordId(final Long recordId) {
        return this.arbitrateRecordApplicationItemMapper.findByRecordId(recordId);
    }
}
