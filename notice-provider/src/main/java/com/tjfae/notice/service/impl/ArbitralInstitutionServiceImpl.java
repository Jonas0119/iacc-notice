// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.common.core.exception.BusinessException;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tjfae.system.api.model.LoginUser;
import com.tjfae.notice.request.AddArbitralInstitutionReq;
import com.tjfae.notice.annotation.Save;
import com.tjfae.notice.annotation.SaveAll;
import java.util.List;
import com.tjfae.notice.entity.ArbitralInstitution;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationContext;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ArbitralInstitutionMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IArbitralInstitutionService;

@Service
public class ArbitralInstitutionServiceImpl implements IArbitralInstitutionService
{
    @Resource
    private ArbitralInstitutionMapper arbitralInstitutionMapper;
    @Resource
    private ApplicationContext applicationContext;
    
    @Transactional
    public int deleteById(final Long id) {
        return this.arbitralInstitutionMapper.deleteById(id);
    }
    
    public ArbitralInstitution findById(final Long id) {
        return this.arbitralInstitutionMapper.findById(id);
    }
    
    @SaveAll(value = "arbitralInstitutionServiceImpl", classType = ArbitralInstitutionServiceImpl.class)
    public void saveAll(final List<ArbitralInstitution> all) {
    }
    
    @Save(value = "arbitralInstitutionServiceImpl", classType = ArbitralInstitutionServiceImpl.class)
    public void save(final ArbitralInstitution arbitralInstitution) {
    }
    
    public int insert(final ArbitralInstitution arbitralInstitution) {
        return this.arbitralInstitutionMapper.insert(arbitralInstitution);
    }
    
    public int update(final ArbitralInstitution arbitralInstitution) {
        return this.arbitralInstitutionMapper.update(arbitralInstitution);
    }
    
    public int insertBatch(final List<ArbitralInstitution> list) {
        return this.arbitralInstitutionMapper.insertBatch((List)list);
    }
    
    public int updateBatch(final List<ArbitralInstitution> list) {
        return this.arbitralInstitutionMapper.updateBatch((List)list);
    }
    
    public Object addArbitralInstitution(final AddArbitralInstitutionReq a) throws Exception {
        final LoginUser loginUser = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final ArbitralInstitution temp = this.arbitralInstitutionMapper.findByName(a.getName());
        if (temp != null) {
            throw new BusinessException("\u5df2\u7ecf\u5b58\u5728\u540d\u79f0\u4e3a\uff1a" + a.getName() + "\u7684\u4ef2\u88c1\u673a\u6784\uff01");
        }
        final ArbitralInstitution arbitralInstitution = new ArbitralInstitution(loginUser);
        arbitralInstitution.setName(a.getName());
        arbitralInstitution.setRemark(a.getRemark());
        ((ArbitralInstitutionServiceImpl)this.applicationContext.getBean((Class)ArbitralInstitutionServiceImpl.class)).save(arbitralInstitution);
        return arbitralInstitution;
    }
    
    public List<ArbitralInstitution> search(final String name) throws Exception {
        final List<ArbitralInstitution> arbitralInstitutions = this.arbitralInstitutionMapper.search(name);
        return arbitralInstitutions;
    }
    
    public Object updateArbitralInstitution(final AddArbitralInstitutionReq req) throws Exception {
        final ArbitralInstitution arbitralInstitution = this.arbitralInstitutionMapper.findById(req.getId());
        if (arbitralInstitution == null) {
            throw new BusinessException("\u65e0\u6b64\u4ef2\u88c1\u673a\u6784\uff01");
        }
        final LoginUser user = (LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        arbitralInstitution.update(user, req.getName(), req.getRemark());
        ((ArbitralInstitutionServiceImpl)this.applicationContext.getBean((Class)ArbitralInstitutionServiceImpl.class)).save(arbitralInstitution);
        return arbitralInstitution;
    }
    
    public int deleteArbitralInstitution(final AddArbitralInstitutionReq req) throws Exception {
        final ArbitralInstitution arbitralInstitution = this.arbitralInstitutionMapper.findById(req.getId());
        if (arbitralInstitution == null) {
            throw new BusinessException("\u65e0\u6b64\u4ef2\u88c1\u673a\u6784\uff01");
        }
        return this.arbitralInstitutionMapper.deleteById(req.getId());
    }
    
    public Object getArbitralInstitution() throws Exception {
        return this.arbitralInstitutionMapper.getArbitralInstitution();
    }
}
