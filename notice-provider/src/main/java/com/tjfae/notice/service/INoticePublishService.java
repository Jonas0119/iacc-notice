// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service;

import com.tjfae.notice.entity.NoticeAuditLog;
import java.util.List;
import com.tjfae.notice.entity.NoticePublish;

public interface INoticePublishService
{
    int insert(final NoticePublish p0);
    
    Long getTotalCount();
    
    NoticePublish findInfoById(final int p0);
    
    NoticePublish findInfoByCode(final String p0);
    
    List<NoticePublish> getListAll(final NoticePublish p0);
    
    List<NoticePublish> getPageList(final NoticePublish p0);
    
    int insertLog(final NoticeAuditLog p0);
    
    NoticeAuditLog findLogById(final int p0);
    
    List<NoticeAuditLog> getLogPageList(final NoticeAuditLog p0);
    
    int updateNoticePub(final NoticePublish p0);
    
    int deleteNoticePub(final int p0);
    
    int viewNoticePub(final NoticePublish p0);
    
    String selectMaxCode(final String p0);
    
    int updateByOrderId(final NoticePublish p0);
}
