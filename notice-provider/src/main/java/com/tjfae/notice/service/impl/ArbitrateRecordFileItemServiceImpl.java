// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.ArbitrateRecordFileItem;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ArbitrateRecordFileItemMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitrateRecordFileItemService;

@Service
public class ArbitrateRecordFileItemServiceImpl implements IArbitrateRecordFileItemService
{
    @Resource
    private ArbitrateRecordFileItemMapper arbitrateRecordFileItemMapper;
    
    public int deleteById(final Long id) {
        return this.arbitrateRecordFileItemMapper.deleteById(id);
    }
    
    public ArbitrateRecordFileItem findById(final Long id) {
        return this.arbitrateRecordFileItemMapper.findById(id);
    }
    
    @SaveAll(value = "arbitrateRecordFileItemServiceImpl", classType = ArbitrateRecordFileItemServiceImpl.class)
    public void saveAll(final List<ArbitrateRecordFileItem> all) {
    }
    
    @Save(value = "arbitrateRecordFileItemServiceImpl", classType = ArbitrateRecordFileItemServiceImpl.class)
    public void save(final ArbitrateRecordFileItem arbitrateRecord) {
    }
    
    public int insert(final ArbitrateRecordFileItem arbitrateRecord) {
        return this.arbitrateRecordFileItemMapper.insert(arbitrateRecord);
    }
    
    public int update(final ArbitrateRecordFileItem arbitrateRecord) {
        return this.arbitrateRecordFileItemMapper.update(arbitrateRecord);
    }
    
    public int insertBatch(final List<ArbitrateRecordFileItem> list) {
        return this.arbitrateRecordFileItemMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<ArbitrateRecordFileItem> list) {
        return this.arbitrateRecordFileItemMapper.updateBatch((List)list);
    }
    
    public List<ArbitrateRecordFileItem> findByEvidenceId(final String evidenceId) {
        return this.arbitrateRecordFileItemMapper.findByEvidenceId(evidenceId);
    }
    
    public List<ArbitrateRecordFileItem> findByRecordId(final Long recordId) {
        return this.arbitrateRecordFileItemMapper.findByRecordId(recordId);
    }
}
