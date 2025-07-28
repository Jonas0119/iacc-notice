// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import java.util.List;
import com.tjfae.notice.entity.ContractInfo;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.ContractInfoMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.IContractService;

@Service
public class ContractServiceImpl implements IContractService
{
    @Resource
    private ContractInfoMapper contractInfoMapper;
    
    public int insert(final ContractInfo contractInfo) {
        return this.contractInfoMapper.insert(contractInfo);
    }
    
    public Long getTotalCount() {
        return this.contractInfoMapper.getTotalCount();
    }
    
    public ContractInfo findInfoById(final int id) {
        return this.contractInfoMapper.findById(id);
    }
    
    public ContractInfo findInfoByCode(final String code) {
        return this.contractInfoMapper.findByCode(code);
    }
    
    public List<ContractInfo> getListAll(final ContractInfo contractInfo) {
        return this.contractInfoMapper.getListAll();
    }
    
    public List<ContractInfo> getPageList(final ContractInfo contract) {
        return this.contractInfoMapper.getPageList(contract);
    }
}
