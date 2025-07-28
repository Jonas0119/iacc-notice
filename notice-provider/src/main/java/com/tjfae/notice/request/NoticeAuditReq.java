// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import org.hibernate.validator.constraints.Length;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel(value = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u5bf9\u8c61", description = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u5ba1\u6838\u5bf9\u8c61")
public class NoticeAuditReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("userId")
    private String userId;
    @ApiModelProperty("\u7528\u6237\u540d")
    private String userName;
    @ApiModelProperty("\u72b6\u6001\uff1a0\u63d0\u4ea4 1\u901a\u8fc7 2\u9a73\u56de 3\u53d1\u5e03 4\u9690\u85cf")
    private String status;
    @ApiModelProperty("\u5ba1\u6838\u539f\u56e0")
    private String reason;
    @ApiModelProperty("\u53d1\u5e03\u6e20\u9053")
    private String platform;
    @ApiModelProperty("\u516c\u544a\u94fe\u63a5")
    @Length(max = 1000, message = "\u516c\u544a\u94fe\u63a5\u6700\u5927\u957f\u5ea6\u4e3a1000\u5b57\u7b26")
    private String noticeUrl;
    @ApiModelProperty("\u5b58\u8bc1\u6587\u4ef6")
    private String fileUrl;
    @ApiModelProperty("\u6587\u4ef6\u540d\u79f0")
    private String fileName;
    @ApiModelProperty("\u6587\u4ef6hash")
    private String fileHash;
    
    public Long getId() {
        return this.id;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public String getNoticeUrl() {
        return this.noticeUrl;
    }
    
    public String getFileUrl() {
        return this.fileUrl;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getFileHash() {
        return this.fileHash;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
    
    public void setNoticeUrl(final String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
    
    public void setFileUrl(final String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileHash(final String fileHash) {
        this.fileHash = fileHash;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticeAuditReq)) {
            return false;
        }
        final NoticeAuditReq other = (NoticeAuditReq)o;
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
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        Label_0102: {
            if (this$userId == null) {
                if (other$userId == null) {
                    break Label_0102;
                }
            }
            else if (this$userId.equals(other$userId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        Label_0139: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0139;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0176: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0176;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        Label_0213: {
            if (this$reason == null) {
                if (other$reason == null) {
                    break Label_0213;
                }
            }
            else if (this$reason.equals(other$reason)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$platform = this.getPlatform();
        final Object other$platform = other.getPlatform();
        Label_0250: {
            if (this$platform == null) {
                if (other$platform == null) {
                    break Label_0250;
                }
            }
            else if (this$platform.equals(other$platform)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$noticeUrl = this.getNoticeUrl();
        final Object other$noticeUrl = other.getNoticeUrl();
        Label_0287: {
            if (this$noticeUrl == null) {
                if (other$noticeUrl == null) {
                    break Label_0287;
                }
            }
            else if (this$noticeUrl.equals(other$noticeUrl)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$fileUrl = this.getFileUrl();
        final Object other$fileUrl = other.getFileUrl();
        Label_0324: {
            if (this$fileUrl == null) {
                if (other$fileUrl == null) {
                    break Label_0324;
                }
            }
            else if (this$fileUrl.equals(other$fileUrl)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        Label_0361: {
            if (this$fileName == null) {
                if (other$fileName == null) {
                    break Label_0361;
                }
            }
            else if (this$fileName.equals(other$fileName)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$fileHash = this.getFileHash();
        final Object other$fileHash = other.getFileHash();
        if (this$fileHash == null) {
            if (other$fileHash == null) {
                return true;
            }
        }
        else if (this$fileHash.equals(other$fileHash)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticeAuditReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $reason = this.getReason();
        result = result * 59 + (($reason == null) ? 43 : $reason.hashCode());
        final Object $platform = this.getPlatform();
        result = result * 59 + (($platform == null) ? 43 : $platform.hashCode());
        final Object $noticeUrl = this.getNoticeUrl();
        result = result * 59 + (($noticeUrl == null) ? 43 : $noticeUrl.hashCode());
        final Object $fileUrl = this.getFileUrl();
        result = result * 59 + (($fileUrl == null) ? 43 : $fileUrl.hashCode());
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        final Object $fileHash = this.getFileHash();
        result = result * 59 + (($fileHash == null) ? 43 : $fileHash.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NoticeAuditReq(id=" + this.getId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", status=" + this.getStatus() + ", reason=" + this.getReason() + ", platform=" + this.getPlatform() + ", noticeUrl=" + this.getNoticeUrl() + ", fileUrl=" + this.getFileUrl() + ", fileName=" + this.getFileName() + ", fileHash=" + this.getFileHash() + ")";
    }
}
