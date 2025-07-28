// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import com.tjfae.notice.entity.ArbitrateType;
import com.tjfae.notice.entity.EvidenceType;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tjfae.system.api.model.LoginUser;
import java.util.ArrayList;
import com.tjfae.common.core.exception.BusinessException;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.request.AddArbitrateEvidenceTypeReq;
import com.tjfae.notice.response.ArbitrateEvidenceTypeSearchResp;
import com.tjfae.notice.request.SearchArbitrateEvidenceTypeReq;
import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import org.springframework.transaction.annotation.Transactional;
import com.tjfae.notice.entity.ArbitrateEvidenceType;
import java.util.List;
import org.springframework.context.ApplicationContext;
import com.tjfae.notice.mapper.ArbitrateTypeMapper;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ArbitrateEvidenceTypeMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitrateEvidenceTypeService;

@Service
public class ArbitrateEvidenceTypeServiceImpl implements IArbitrateEvidenceTypeService
{
    @Resource
    private ArbitrateEvidenceTypeMapper arbitrateEvidenceTypeMapper;
    @Resource
    private ArbitrateTypeMapper arbitrateTypeMapper;
    @Resource
    private ApplicationContext applicationContext;
    
    public int deleteById(final Long id) {
        return this.arbitrateEvidenceTypeMapper.deleteById(id);
    }
    
    @Transactional
    public int deleteBatch(final List<ArbitrateEvidenceType> list) {
        return this.arbitrateEvidenceTypeMapper.deleteBatch((List)list);
    }
    
    public ArbitrateEvidenceType findById(final Long id) {
        return this.arbitrateEvidenceTypeMapper.findById(id);
    }
    
    @SaveAll(value = "arbitrateEvidenceTypeServiceImpl", classType = ArbitrateEvidenceTypeServiceImpl.class)
    public void saveAll(final List<ArbitrateEvidenceType> all) {
    }
    
    @Save(value = "arbitrateEvidenceTypeServiceImpl", classType = ArbitrateEvidenceTypeServiceImpl.class)
    public void save(final ArbitrateEvidenceType arbitrateEvidenceType) {
    }
    
    public int insert(final ArbitrateEvidenceType arbitrateEvidenceType) {
        return this.arbitrateEvidenceTypeMapper.insert(arbitrateEvidenceType);
    }
    
    public int update(final ArbitrateEvidenceType arbitrateEvidenceType) {
        return this.arbitrateEvidenceTypeMapper.update(arbitrateEvidenceType);
    }
    
    public int insertBatch(final List<ArbitrateEvidenceType> list) {
        return this.arbitrateEvidenceTypeMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<ArbitrateEvidenceType> list) {
        return this.arbitrateEvidenceTypeMapper.updateBatch((List)list);
    }
    
    public List<ArbitrateEvidenceTypeSearchResp> searchArbitrateEvidenceType(final SearchArbitrateEvidenceTypeReq req) throws Exception {
        return this.arbitrateEvidenceTypeMapper.searchArbitrateEvidenceType(req);
    }
    
    public Object addArbitrateEvidenceType(final AddArbitrateEvidenceTypeReq req) throws Exception {
        if (StringUtils.isBlank(req.getArbitrate_type_name())) {
            throw new BusinessException("\u53c2\u6570\u9519\u8bef\uff01");
        }
        if (req.getEvidence_types() == null || req.getEvidence_types().isEmpty()) {
            throw new BusinessException("\u65e0\u5b58\u8bc1\u7c7b\u578b\uff01");
        }
        final ArbitrateType arbitrateType = this.arbitrateTypeMapper.findByName(req.getArbitrate_type_name());
        if (arbitrateType == null) {
            throw new BusinessException("\u65e0\u540d\u79f0\u4e3a\uff1a" + req.getArbitrate_type_name() + "\u7684\u4e89\u8bae\u7c7b\u578b\uff01");
        }
        final List<ArbitrateEvidenceType> arbitrateEvidenceTypes = new ArrayList<ArbitrateEvidenceType>();
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for (final EvidenceType e : req.getEvidence_types()) {
            final ArbitrateEvidenceType arbitrateEvidenceType = new ArbitrateEvidenceType(loginUser, arbitrateType, e);
            arbitrateEvidenceTypes.add(arbitrateEvidenceType);
        }
        if (arbitrateEvidenceTypes.isEmpty()) {
            return null;
        }
        ((ArbitrateEvidenceTypeServiceImpl)this.applicationContext.getBean((Class)ArbitrateEvidenceTypeServiceImpl.class)).saveAll(arbitrateEvidenceTypes);
        return arbitrateEvidenceTypes;
    }
    
    public Object updateArbitrateEvidenceType(final AddArbitrateEvidenceTypeReq req) throws Exception {
        if (StringUtils.isBlank(req.getArbitrate_type_name())) {
            throw new BusinessException("\u53c2\u6570\u9519\u8bef\uff01");
        }
        if (req.getEvidence_types() == null || req.getEvidence_types().isEmpty()) {
            throw new BusinessException("\u65e0\u5b58\u8bc1\u7c7b\u578b\uff01");
        }
        final ArbitrateType arbitrateType = this.arbitrateTypeMapper.findByName(req.getArbitrate_type_name());
        if (arbitrateType == null) {
            throw new BusinessException("\u65e0\u540d\u79f0\u4e3a\uff1a" + req.getArbitrate_type_name() + "\u7684\u4e89\u8bae\u7c7b\u578b\uff01");
        }
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final List<ArbitrateEvidenceType> arbitrateEvidenceTypes = this.arbitrateEvidenceTypeMapper.findByArbitrateTypeName(req.getArbitrate_type_name());
        final Map<String, ArbitrateEvidenceType> map = new HashMap<String, ArbitrateEvidenceType>();
        for (final ArbitrateEvidenceType a : arbitrateEvidenceTypes) {
            map.put(a.getEvidence_type_name(), a);
        }
        final Map<String, EvidenceType> evidenceTypeMap = new HashMap<String, EvidenceType>();
        for (final EvidenceType e : req.getEvidence_types()) {
            evidenceTypeMap.put(e.getName(), e);
        }
        final List<ArbitrateEvidenceType> needToUpdate = new ArrayList<ArbitrateEvidenceType>();
        for (final Map.Entry<String, EvidenceType> entry : evidenceTypeMap.entrySet()) {
            if (map.containsKey(entry.getKey())) {
                final ArbitrateEvidenceType arbitrateEvidenceType = map.get(entry.getKey());
                arbitrateEvidenceTypes.remove(map.get(entry.getKey()));
                arbitrateEvidenceType.update(loginUser, arbitrateType, entry.getValue());
                needToUpdate.add(arbitrateEvidenceType);
            }
            else {
                final ArbitrateEvidenceType arbitrateEvidenceType = new ArbitrateEvidenceType(loginUser, arbitrateType, entry.getValue());
                needToUpdate.add(arbitrateEvidenceType);
            }
        }
        if (!needToUpdate.isEmpty()) {
            ((ArbitrateEvidenceTypeServiceImpl)this.applicationContext.getBean((Class)ArbitrateEvidenceTypeServiceImpl.class)).saveAll(needToUpdate);
        }
        if (!arbitrateEvidenceTypes.isEmpty()) {
            ((ArbitrateEvidenceTypeServiceImpl)this.applicationContext.getBean((Class)ArbitrateEvidenceTypeServiceImpl.class)).deleteBatch(arbitrateEvidenceTypes);
        }
        return needToUpdate;
    }
    
    public Object deleteByArbitrateTypeName(final AddArbitrateEvidenceTypeReq req) throws Exception {
        if (StringUtils.isBlank(req.getArbitrate_type_name())) {
            throw new BusinessException("\u4e89\u8bae\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a\uff01");
        }
        return this.arbitrateEvidenceTypeMapper.deleteArbitrateTypeName(req.getArbitrate_type_name());
    }
}
