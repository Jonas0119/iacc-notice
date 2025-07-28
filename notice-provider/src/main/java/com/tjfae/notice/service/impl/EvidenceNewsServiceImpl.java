// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.EvidenceNews;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.EvidenceNewsMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IEvidenceNewsService;

@Service
public class EvidenceNewsServiceImpl implements IEvidenceNewsService
{
    @Resource
    private EvidenceNewsMapper evidenceNewsMapper;
    
    public int deleteById(final Long id) {
        return 0;
    }
    
    public EvidenceNews findById(final Long id) {
        return null;
    }
    
    @SaveAll(value = "evidenceNewsServiceImpl", classType = EvidenceNewsServiceImpl.class)
    public void saveAll(final List<EvidenceNews> all) {
    }
    
    @Save(value = "evidenceNewsServiceImpl", classType = EvidenceNewsServiceImpl.class)
    public void save(final EvidenceNews evidenceNews) {
    }
    
    public int insert(final EvidenceNews evidenceNews) {
        return 0;
    }
    
    public int update(final EvidenceNews evidenceNews) {
        return 0;
    }
    
    public int insertBatch(final List<EvidenceNews> list) {
        return this.evidenceNewsMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<EvidenceNews> list) {
        return this.evidenceNewsMapper.updateBatch((List)list);
    }
    
    public List<EvidenceNews> getNews() {
        return this.evidenceNewsMapper.getNews();
    }
    
    public Long getCount() {
        return this.evidenceNewsMapper.getCount();
    }
}
