// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service;

import java.util.List;
import com.tjfae.notice.entity.NoticeInfo;

public interface INoticeService
{
    int insert(final NoticeInfo p0);
    
    Long getTotalCount();
    
    NoticeInfo findInfoById(final int p0);
    
    NoticeInfo findInfoByCode(final String p0);
    
    List<NoticeInfo> getListAll(final NoticeInfo p0);
    
    List<NoticeInfo> getPageList(final NoticeInfo p0);
}
