// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel(value = "\u516c\u793a\u516c\u544a\u901a\u77e5\u5bf9\u8c61", description = "\u516c\u793a\u516c\u544a\u901a\u77e5\u5bf9\u8c61")
public class NoticeNotifyReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u6e20\u9053id")
    private String channelId;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("\u72b6\u6001\uff1a0\u63d0\u4ea4 1\u901a\u8fc7 2\u9a73\u56de 3\u53d1\u5e03 4\u9690\u85cf")
    private String status;
    @ApiModelProperty("\u5ba1\u6838\u539f\u56e0")
    private String reason;
    @ApiModelProperty("\u516c\u544a\u94fe\u63a5")
    private String noticeUrl;
    @ApiModelProperty("\u516c\u544a\u7f16\u53f7")
    private String noticeCode;
    
    public String getChannelId() {
        return this.channelId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public String getNoticeUrl() {
        return this.noticeUrl;
    }
    
    public String getNoticeCode() {
        return this.noticeCode;
    }
    
    public void setChannelId(final String channelId) {
        this.channelId = channelId;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    public void setNoticeUrl(final String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
    
    public void setNoticeCode(final String noticeCode) {
        this.noticeCode = noticeCode;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticeNotifyReq)) {
            return false;
        }
        final NoticeNotifyReq other = (NoticeNotifyReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$channelId = this.getChannelId();
        final Object other$channelId = other.getChannelId();
        Label_0102: {
            if (this$channelId == null) {
                if (other$channelId == null) {
                    break Label_0102;
                }
            }
            else if (this$channelId.equals(other$channelId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0139: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0139;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        Label_0176: {
            if (this$reason == null) {
                if (other$reason == null) {
                    break Label_0176;
                }
            }
            else if (this$reason.equals(other$reason)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$noticeUrl = this.getNoticeUrl();
        final Object other$noticeUrl = other.getNoticeUrl();
        Label_0213: {
            if (this$noticeUrl == null) {
                if (other$noticeUrl == null) {
                    break Label_0213;
                }
            }
            else if (this$noticeUrl.equals(other$noticeUrl)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$noticeCode = this.getNoticeCode();
        final Object other$noticeCode = other.getNoticeCode();
        if (this$noticeCode == null) {
            if (other$noticeCode == null) {
                return true;
            }
        }
        else if (this$noticeCode.equals(other$noticeCode)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticeNotifyReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $channelId = this.getChannelId();
        result = result * 59 + (($channelId == null) ? 43 : $channelId.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $reason = this.getReason();
        result = result * 59 + (($reason == null) ? 43 : $reason.hashCode());
        final Object $noticeUrl = this.getNoticeUrl();
        result = result * 59 + (($noticeUrl == null) ? 43 : $noticeUrl.hashCode());
        final Object $noticeCode = this.getNoticeCode();
        result = result * 59 + (($noticeCode == null) ? 43 : $noticeCode.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NoticeNotifyReq(channelId=" + this.getChannelId() + ", id=" + this.getId() + ", status=" + this.getStatus() + ", reason=" + this.getReason() + ", noticeUrl=" + this.getNoticeUrl() + ", noticeCode=" + this.getNoticeCode() + ")";
    }
}
