// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import com.tjfae.notice.entity.NoticeAuditLog;
import java.util.List;
import com.tjfae.notice.entity.NoticePublish;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.NoticePublishMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.INoticePublishService;

@Service
public class NoticePublishServiceImpl implements INoticePublishService
{
    @Resource
    private NoticePublishMapper NoticePublishMapper;
    
    public int insert(final NoticePublish noticePublish) {
        return this.NoticePublishMapper.insert(noticePublish);
    }
    
    public Long getTotalCount() {
        return this.NoticePublishMapper.getTotalCount();
    }
    
    public NoticePublish findInfoById(final int id) {
        return this.NoticePublishMapper.findById(id);
    }
    
    public NoticePublish findInfoByCode(final String code) {
        return this.NoticePublishMapper.findByCode(code);
    }
    
    public List<NoticePublish> getListAll(final NoticePublish noticePublish) {
        return this.NoticePublishMapper.getListAll();
    }
    
    public List<NoticePublish> getPageList(final NoticePublish noticePublish) {
        return this.NoticePublishMapper.getPageList(noticePublish);
    }
    
    public int insertLog(final NoticeAuditLog noticeAuditLog) {
        return this.NoticePublishMapper.insertLog(noticeAuditLog);
    }
    
    public NoticeAuditLog findLogById(final int id) {
        return this.NoticePublishMapper.findLogById(id);
    }
    
    public List<NoticeAuditLog> getLogPageList(final NoticeAuditLog noticeAuditLog) {
        return this.NoticePublishMapper.getLogPageList(noticeAuditLog);
    }
    
    public int updateNoticePub(final NoticePublish noticePublish) {
        return this.NoticePublishMapper.update(noticePublish);
    }
    
    public int deleteNoticePub(final int id) {
        return this.NoticePublishMapper.delete(id);
    }
    
    public int viewNoticePub(final NoticePublish noticePublish) {
        return this.NoticePublishMapper.view(noticePublish);
    }
    
    public String selectMaxCode(final String year) {
        return this.NoticePublishMapper.selectMaxCode(year);
    }
    
    public int updateByOrderId(final NoticePublish noticePublish) {
        return this.NoticePublishMapper.updateByOrderId(noticePublish);
    }
}
