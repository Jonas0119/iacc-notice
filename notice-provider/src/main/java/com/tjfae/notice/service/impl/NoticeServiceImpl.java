// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.service.impl;

import java.util.List;
import com.tjfae.notice.entity.NoticeInfo;
import javax.annotation.Resource;
import com.tjfae.notice.mapper.NoticeInfoMapper;
import org.springframework.stereotype.Service;
import com.tjfae.notice.service.INoticeService;

@Service
public class NoticeServiceImpl implements INoticeService
{
    @Resource
    private NoticeInfoMapper noticeInfoMapper;
    
    public int insert(final NoticeInfo noticeInfo) {
        return this.noticeInfoMapper.insert(noticeInfo);
    }
    
    public Long getTotalCount() {
        return this.noticeInfoMapper.getTotalCount();
    }
    
    public NoticeInfo findInfoById(final int id) {
        return this.noticeInfoMapper.findById(id);
    }
    
    public NoticeInfo findInfoByCode(final String code) {
        return this.noticeInfoMapper.findByCode(code);
    }
    
    public List<NoticeInfo> getListAll(final NoticeInfo noticeInfo) {
        return this.noticeInfoMapper.getListAll();
    }
    
    public List<NoticeInfo> getPageList(final NoticeInfo noticeInfo) {
        return this.noticeInfoMapper.getPageList(noticeInfo);
    }
}
