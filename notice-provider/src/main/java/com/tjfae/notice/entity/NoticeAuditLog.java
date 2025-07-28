// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.tjfae.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.tjfae.common.core.web.domain.BaseEntity;

@ApiModel(value = "\u5ba1\u6279\u6d41\u7a0b\u5bf9\u8c61", description = "\u5ba1\u6279\u6d41\u7a0b\u5bf9\u8c61")
public class NoticeAuditLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id;")
    private Long id;
    @ApiModelProperty("\u516c\u544aid;")
    private Long noticeId;
    @ApiModelProperty(value = "userId", hidden = true)
    private String userId;
    @Excel(name = "\u7528\u6237\u540d")
    @ApiModelProperty("\u7528\u6237\u540d")
    private String userName;
    @Excel(name = "\u89d2\u8272")
    @ApiModelProperty("\u89d2\u8272")
    private String role;
    @Excel(name = "\u5ba1\u6279\u610f\u89c1")
    @ApiModelProperty("\u5ba1\u6279\u610f\u89c1")
    private String reason;
    @Excel(name = "\u7c7b\u578b")
    @ApiModelProperty(value = "\u7c7b\u578b", hidden = true)
    private String type;
    @Excel(name = "\u5907\u6ce8")
    @ApiModelProperty("\u5907\u6ce8")
    private String remark;
    @Excel(name = "\u521b\u5efa\u4eba")
    @ApiModelProperty("\u521b\u5efa\u4eba")
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("\u521b\u5efa\u65f6\u95f4")
    private Date createTime;
    @Excel(name = "\u521b\u5efa\u4eba\u540d\u79f0")
    @ApiModelProperty("\u521b\u5efa\u4eba\u540d\u79f0")
    private String creatorName;
    @ApiModelProperty("\u5f00\u59cb\u65f6\u95f4")
    private String beginTime;
    @ApiModelProperty("\u7ed3\u675f\u65f6\u95f4")
    private String endTime;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getNoticeId() {
        return this.noticeId;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getRole() {
        return this.role;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public String getCreatorName() {
        return this.creatorName;
    }
    
    public String getBeginTime() {
        return this.beginTime;
    }
    
    public String getEndTime() {
        return this.endTime;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setNoticeId(final Long noticeId) {
        this.noticeId = noticeId;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public void setRole(final String role) {
        this.role = role;
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setCreatorName(final String creatorName) {
        this.creatorName = creatorName;
    }
    
    public void setBeginTime(final String beginTime) {
        this.beginTime = beginTime;
    }
    
    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticeAuditLog)) {
            return false;
        }
        final NoticeAuditLog other = (NoticeAuditLog)o;
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
        final Object this$noticeId = this.getNoticeId();
        final Object other$noticeId = other.getNoticeId();
        Label_0102: {
            if (this$noticeId == null) {
                if (other$noticeId == null) {
                    break Label_0102;
                }
            }
            else if (this$noticeId.equals(other$noticeId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        Label_0139: {
            if (this$userId == null) {
                if (other$userId == null) {
                    break Label_0139;
                }
            }
            else if (this$userId.equals(other$userId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        Label_0176: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0176;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        Label_0213: {
            if (this$role == null) {
                if (other$role == null) {
                    break Label_0213;
                }
            }
            else if (this$role.equals(other$role)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        Label_0250: {
            if (this$reason == null) {
                if (other$reason == null) {
                    break Label_0250;
                }
            }
            else if (this$reason.equals(other$reason)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0287: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0287;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0324: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0324;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0361: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0361;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0398: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0398;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$creatorName = this.getCreatorName();
        final Object other$creatorName = other.getCreatorName();
        Label_0435: {
            if (this$creatorName == null) {
                if (other$creatorName == null) {
                    break Label_0435;
                }
            }
            else if (this$creatorName.equals(other$creatorName)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$beginTime = this.getBeginTime();
        final Object other$beginTime = other.getBeginTime();
        Label_0472: {
            if (this$beginTime == null) {
                if (other$beginTime == null) {
                    break Label_0472;
                }
            }
            else if (this$beginTime.equals(other$beginTime)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$endTime = this.getEndTime();
        final Object other$endTime = other.getEndTime();
        if (this$endTime == null) {
            if (other$endTime == null) {
                return true;
            }
        }
        else if (this$endTime.equals(other$endTime)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticeAuditLog;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $noticeId = this.getNoticeId();
        result = result * 59 + (($noticeId == null) ? 43 : $noticeId.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $role = this.getRole();
        result = result * 59 + (($role == null) ? 43 : $role.hashCode());
        final Object $reason = this.getReason();
        result = result * 59 + (($reason == null) ? 43 : $reason.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $creatorName = this.getCreatorName();
        result = result * 59 + (($creatorName == null) ? 43 : $creatorName.hashCode());
        final Object $beginTime = this.getBeginTime();
        result = result * 59 + (($beginTime == null) ? 43 : $beginTime.hashCode());
        final Object $endTime = this.getEndTime();
        result = result * 59 + (($endTime == null) ? 43 : $endTime.hashCode());
        return result;
    }
    
    public String toString() {
        return "NoticeAuditLog(id=" + this.getId() + ", noticeId=" + this.getNoticeId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", role=" + this.getRole() + ", reason=" + this.getReason() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", creator=" + this.getCreator() + ", createTime=" + this.getCreateTime() + ", creatorName=" + this.getCreatorName() + ", beginTime=" + this.getBeginTime() + ", endTime=" + this.getEndTime() + ")";
    }
}
