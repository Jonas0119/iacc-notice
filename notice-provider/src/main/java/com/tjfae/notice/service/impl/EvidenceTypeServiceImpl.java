// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tjfae.system.api.model.LoginUser;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.request.EvidenceTypeReq;
import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.EvidenceType;
import org.springframework.context.ApplicationContext;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.EvidenceTypeMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IEvidenceTypeService;

@Service
public class EvidenceTypeServiceImpl implements IEvidenceTypeService
{
    @Resource
    private EvidenceTypeMapper evidenceTypeMapper;
    @Resource
    private ApplicationContext applicationContext;
    
    public int deleteById(final Long id) {
        return this.evidenceTypeMapper.deleteById(id);
    }
    
    public EvidenceType findById(final Long id) {
        return this.evidenceTypeMapper.findById(id);
    }
    
    @SaveAll(value = "evidenceTypeServiceImpl", classType = EvidenceTypeServiceImpl.class)
    public void saveAll(final List<EvidenceType> all) {
    }
    
    @Save(value = "evidenceTypeServiceImpl", classType = EvidenceTypeServiceImpl.class)
    public void save(final EvidenceType evidenceType) {
    }
    
    public int insert(final EvidenceType evidenceType) {
        return this.evidenceTypeMapper.insert(evidenceType);
    }
    
    public int update(final EvidenceType evidenceType) {
        return this.evidenceTypeMapper.update(evidenceType);
    }
    
    public int insertBatch(final List<EvidenceType> list) {
        return this.evidenceTypeMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<EvidenceType> list) {
        return this.evidenceTypeMapper.updateBatch((List)list);
    }
    
    public Object addEvidenceType(final EvidenceTypeReq req) throws Exception {
        EvidenceType evidenceType = this.evidenceTypeMapper.findByName(req.getName());
        if (evidenceType != null) {
            throw new BusinessException("\u540d\u79f0\u4e3a:" + req.getName() + "\u5df2\u5b58\u5728");
        }
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        evidenceType = new EvidenceType(loginUser, req.getName());
        ((EvidenceTypeServiceImpl)this.applicationContext.getBean((Class)EvidenceTypeServiceImpl.class)).save(evidenceType);
        return evidenceType;
    }
    
    public List<EvidenceType> search(final String name) throws Exception {
        final List<EvidenceType> evidenceTypes = this.evidenceTypeMapper.search(name);
        return evidenceTypes;
    }
    
    public Object updateEvidenceType(final EvidenceTypeReq evidenceTypeReq) throws Exception {
        final EvidenceType evidenceType = this.evidenceTypeMapper.findById(evidenceTypeReq.getId());
        if (evidenceType == null) {
            throw new BusinessException("\u65e0\u6b64\u8bc1\u636e\u7c7b\u578b\uff01");
        }
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        evidenceType.update(loginUser, evidenceTypeReq.getName());
        ((EvidenceTypeServiceImpl)this.applicationContext.getBean((Class)EvidenceTypeServiceImpl.class)).save(evidenceType);
        return evidenceType;
    }
    
    @Transactional
    public Object delete(final Long id) throws Exception {
        return this.deleteById(id);
    }
    
    public Object getByName(final String name) throws Exception {
        final String[] names = name.split(",");
        if (names.length == 0) {
            throw new BusinessException("\u53c2\u6570\u9519\u8bef\uff01");
        }
        String queryParams = "";
        for (final String s : names) {
            queryParams = queryParams + "'" + s + "',";
        }
        queryParams = queryParams.substring(0, queryParams.length() - 1);
        return this.evidenceTypeMapper.getByName(queryParams);
    }
    
    public Object getAll() {
        return this.evidenceTypeMapper.getAll();
    }
}
