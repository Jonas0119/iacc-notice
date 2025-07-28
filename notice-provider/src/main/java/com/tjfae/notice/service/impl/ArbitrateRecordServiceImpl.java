// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import java.util.Map;
import com.tjfae.notice.entity.ArbitrateRecordFileItem;
import com.tjfae.notice.entity.ArbitrateRecordApplicationItem;
import java.util.HashMap;
import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.ArbitrateRecord;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ArbitrateRecordMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitrateRecordService;

@Service
public class ArbitrateRecordServiceImpl implements IArbitrateRecordService
{
    @Resource
    private ArbitrateRecordMapper arbitrateRecordMapper;
    @Resource
    private ArbitrateRecordApplicationItemServiceImpl arbitrateRecordApplicationItemService;
    @Resource
    private ArbitrateRecordFileItemServiceImpl arbitrateRecordFileItemService;
    
    public int deleteById(final Long id) {
        return this.arbitrateRecordMapper.deleteById(id);
    }
    
    public ArbitrateRecord findById(final Long id) {
        return this.arbitrateRecordMapper.findById(id);
    }
    
    @SaveAll(value = "arbitrateRecordServiceImpl", classType = ArbitrateRecordServiceImpl.class)
    public void saveAll(final List<ArbitrateRecord> all) {
    }
    
    @Save(value = "arbitrateRecordServiceImpl", classType = ArbitrateRecordServiceImpl.class)
    public void save(final ArbitrateRecord arbitrateRecord) {
    }
    
    public int insert(final ArbitrateRecord arbitrateRecord) {
        return this.arbitrateRecordMapper.insert(arbitrateRecord);
    }
    
    public int update(final ArbitrateRecord arbitrateRecord) {
        return this.arbitrateRecordMapper.update(arbitrateRecord);
    }
    
    public int insertBatch(final List<ArbitrateRecord> list) {
        return this.arbitrateRecordMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<ArbitrateRecord> list) {
        return this.arbitrateRecordMapper.updateBatch((List)list);
    }
    
    public Long getCount() {
        return this.arbitrateRecordMapper.getCount();
    }
    
    public ArbitrateRecord findByEvidenceId(final String evidenceId) {
        return this.arbitrateRecordMapper.findByEvidenceId(evidenceId);
    }
    
    public List<ArbitrateRecord> findByUserInfo(final ArbitrateRecord record) {
        return this.arbitrateRecordMapper.findByUserInfo(record);
    }
    
    public List<ArbitrateRecord> search(final String institutionName) {
        return this.arbitrateRecordMapper.search(institutionName);
    }
    
    public Object viewArbitrateRecordDetail(final Long arbitrateRecordId) {
        final List<ArbitrateRecordApplicationItem> arbitrateRecordApplicationItems = this.arbitrateRecordApplicationItemService.findByRecordId(arbitrateRecordId);
        final List<ArbitrateRecordFileItem> arbitrateRecordFileItems = this.arbitrateRecordFileItemService.findByRecordId(arbitrateRecordId);
        final Map<String, Object> res = new HashMap<String, Object>();
        res.put("applicationItem", arbitrateRecordApplicationItems);
        res.put("fileItem", arbitrateRecordFileItems);
        return res;
    }
}
