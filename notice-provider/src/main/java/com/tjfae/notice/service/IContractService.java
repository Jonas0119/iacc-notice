// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service;

import java.util.List;
import com.tjfae.notice.entity.ContractInfo;

public interface IContractService
{
    int insert(final ContractInfo p0);
    
    Long getTotalCount();
    
    ContractInfo findInfoById(final int p0);
    
    ContractInfo findInfoByCode(final String p0);
    
    List<ContractInfo> getListAll(final ContractInfo p0);
    
    List<ContractInfo> getPageList(final ContractInfo p0);
}
