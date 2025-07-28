// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.response.ArbitrateEvidenceTypeSearchResp;
import com.tjfae.notice.request.SearchArbitrateEvidenceTypeReq;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tjfae.system.api.model.LoginUser;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.request.ArbitrateTypeReq;
import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.ArbitrateType;
import org.springframework.context.ApplicationContext;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ArbitrateTypeMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitrateTypeService;

@Service
public class ArbitrateTypeServiceImpl implements IArbitrateTypeService
{
    @Resource
    private ArbitrateTypeMapper arbitrateTypeMapper;
    @Resource
    private ApplicationContext applicationContext;
    
    public int deleteById(final Long id) {
        return this.arbitrateTypeMapper.deleteById(id);
    }
    
    public ArbitrateType findById(final Long id) {
        return this.arbitrateTypeMapper.findById(id);
    }
    
    @SaveAll(value = "arbitrateTypeServiceImpl", classType = ArbitrateTypeServiceImpl.class)
    public void saveAll(final List<ArbitrateType> all) {
    }
    
    @Save(value = "arbitrateTypeServiceImpl", classType = ArbitrateTypeServiceImpl.class)
    public void save(final ArbitrateType arbitrateType) {
    }
    
    public int insert(final ArbitrateType arbitrateType) {
        return this.arbitrateTypeMapper.insert(arbitrateType);
    }
    
    public int update(final ArbitrateType arbitrateType) {
        return this.arbitrateTypeMapper.update(arbitrateType);
    }
    
    public int insertBatch(final List<ArbitrateType> list) {
        return this.arbitrateTypeMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<ArbitrateType> list) {
        return this.arbitrateTypeMapper.updateBatch((List)list);
    }
    
    public Object addArbitrateType(final ArbitrateTypeReq req) throws Exception {
        ArbitrateType arbitrateType = this.arbitrateTypeMapper.findByName(req.getName());
        if (arbitrateType != null) {
            throw new BusinessException("\u540d\u79f0\u4e3a:" + req.getName() + "\u5df2\u5b58\u5728");
        }
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        arbitrateType = new ArbitrateType(loginUser, req.getName());
        ((ArbitrateTypeServiceImpl)this.applicationContext.getBean((Class)ArbitrateTypeServiceImpl.class)).save(arbitrateType);
        return arbitrateType;
    }
    
    public List<ArbitrateType> search(final String name) throws Exception {
        final List<ArbitrateType> arbitrateTypes = this.arbitrateTypeMapper.search(name);
        return arbitrateTypes;
    }
    
    public Object updateArbitrateType(final ArbitrateTypeReq req) throws Exception {
        final ArbitrateType arbitrateType = this.arbitrateTypeMapper.findById(req.getId());
        if (arbitrateType == null) {
            throw new BusinessException("\u65e0\u6b64\u8bc1\u636e\u7c7b\u578b\uff01");
        }
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        arbitrateType.update(loginUser, req.getName());
        ((ArbitrateTypeServiceImpl)this.applicationContext.getBean((Class)ArbitrateTypeServiceImpl.class)).save(arbitrateType);
        return arbitrateType;
    }
    
    @Transactional
    public Object delete(final Long id) throws Exception {
        return this.deleteById(id);
    }
    
    public Object getAll() throws Exception {
        return this.arbitrateTypeMapper.getAll();
    }
    
    public List<ArbitrateEvidenceTypeSearchResp> searchArbitrateEvidenceList(final SearchArbitrateEvidenceTypeReq req) throws Exception {
        return this.arbitrateTypeMapper.searchArbitrateEvidenceList(req);
    }
}
