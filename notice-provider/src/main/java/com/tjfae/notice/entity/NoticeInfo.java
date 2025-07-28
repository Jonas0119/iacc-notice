// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.tjfae.common.core.annotation.Excel;
import com.tjfae.common.core.web.domain.BaseEntity;

public class NoticeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userId;
    @Excel(name = "\u7528\u6237\u540d")
    private String userName;
    @Excel(name = "\u6807\u9898")
    private String title;
    @Excel(name = "\u5b58\u8bc1\u7c7b\u578b")
    private String type;
    @Excel(name = "\u63cf\u8ff0")
    private String remark;
    @Excel(name = "\u6587\u4ef6\u5730\u5740")
    private String fileUrl;
    @Excel(name = "\u521b\u5efa\u4eba")
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Excel(name = "\u4e1a\u52a1\u573a\u666f")
    private String sceneType;
    @Excel(name = "\u5b58\u8bc1\u7f16\u53f7")
    private String code;
    @Excel(name = "\u5ba2\u6237\u6765\u6e90")
    private String source;
    @Excel(name = "\u5b58\u8bc1\u5e73\u53f0")
    private String platform;
    private String noticeUrl;
    @Excel(name = "\u5b58\u8bc1hash")
    private String hash;
    @Excel(name = "\u6570\u636e\u6765\u6e90")
    private String sourceTag;
    @Excel(name = "\u521b\u5efa\u4eba\u540d\u79f0")
    private String creatorName;
    private String beginTime;
    private String endTime;
    
    public Long getId() {
        return this.id;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public String getFileUrl() {
        return this.fileUrl;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public String getSceneType() {
        return this.sceneType;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public String getNoticeUrl() {
        return this.noticeUrl;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public String getSourceTag() {
        return this.sourceTag;
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
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setFileUrl(final String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSceneType(final String sceneType) {
        this.sceneType = sceneType;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setSource(final String source) {
        this.source = source;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
    
    public void setNoticeUrl(final String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
    
    public void setHash(final String hash) {
        this.hash = hash;
    }
    
    public void setSourceTag(final String sourceTag) {
        this.sourceTag = sourceTag;
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
        if (!(o instanceof NoticeInfo)) {
            return false;
        }
        final NoticeInfo other = (NoticeInfo)o;
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
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0176: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0176;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0213: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0213;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0250: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0250;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$fileUrl = this.getFileUrl();
        final Object other$fileUrl = other.getFileUrl();
        Label_0287: {
            if (this$fileUrl == null) {
                if (other$fileUrl == null) {
                    break Label_0287;
                }
            }
            else if (this$fileUrl.equals(other$fileUrl)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0324: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0324;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0361: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0361;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$sceneType = this.getSceneType();
        final Object other$sceneType = other.getSceneType();
        Label_0398: {
            if (this$sceneType == null) {
                if (other$sceneType == null) {
                    break Label_0398;
                }
            }
            else if (this$sceneType.equals(other$sceneType)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        Label_0435: {
            if (this$code == null) {
                if (other$code == null) {
                    break Label_0435;
                }
            }
            else if (this$code.equals(other$code)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$source = this.getSource();
        final Object other$source = other.getSource();
        Label_0472: {
            if (this$source == null) {
                if (other$source == null) {
                    break Label_0472;
                }
            }
            else if (this$source.equals(other$source)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$platform = this.getPlatform();
        final Object other$platform = other.getPlatform();
        Label_0509: {
            if (this$platform == null) {
                if (other$platform == null) {
                    break Label_0509;
                }
            }
            else if (this$platform.equals(other$platform)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$noticeUrl = this.getNoticeUrl();
        final Object other$noticeUrl = other.getNoticeUrl();
        Label_0546: {
            if (this$noticeUrl == null) {
                if (other$noticeUrl == null) {
                    break Label_0546;
                }
            }
            else if (this$noticeUrl.equals(other$noticeUrl)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$hash = this.getHash();
        final Object other$hash = other.getHash();
        Label_0583: {
            if (this$hash == null) {
                if (other$hash == null) {
                    break Label_0583;
                }
            }
            else if (this$hash.equals(other$hash)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$sourceTag = this.getSourceTag();
        final Object other$sourceTag = other.getSourceTag();
        Label_0620: {
            if (this$sourceTag == null) {
                if (other$sourceTag == null) {
                    break Label_0620;
                }
            }
            else if (this$sourceTag.equals(other$sourceTag)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$creatorName = this.getCreatorName();
        final Object other$creatorName = other.getCreatorName();
        Label_0657: {
            if (this$creatorName == null) {
                if (other$creatorName == null) {
                    break Label_0657;
                }
            }
            else if (this$creatorName.equals(other$creatorName)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$beginTime = this.getBeginTime();
        final Object other$beginTime = other.getBeginTime();
        Label_0694: {
            if (this$beginTime == null) {
                if (other$beginTime == null) {
                    break Label_0694;
                }
            }
            else if (this$beginTime.equals(other$beginTime)) {
                break Label_0694;
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
        return other instanceof NoticeInfo;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $fileUrl = this.getFileUrl();
        result = result * 59 + (($fileUrl == null) ? 43 : $fileUrl.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sceneType = this.getSceneType();
        result = result * 59 + (($sceneType == null) ? 43 : $sceneType.hashCode());
        final Object $code = this.getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        final Object $source = this.getSource();
        result = result * 59 + (($source == null) ? 43 : $source.hashCode());
        final Object $platform = this.getPlatform();
        result = result * 59 + (($platform == null) ? 43 : $platform.hashCode());
        final Object $noticeUrl = this.getNoticeUrl();
        result = result * 59 + (($noticeUrl == null) ? 43 : $noticeUrl.hashCode());
        final Object $hash = this.getHash();
        result = result * 59 + (($hash == null) ? 43 : $hash.hashCode());
        final Object $sourceTag = this.getSourceTag();
        result = result * 59 + (($sourceTag == null) ? 43 : $sourceTag.hashCode());
        final Object $creatorName = this.getCreatorName();
        result = result * 59 + (($creatorName == null) ? 43 : $creatorName.hashCode());
        final Object $beginTime = this.getBeginTime();
        result = result * 59 + (($beginTime == null) ? 43 : $beginTime.hashCode());
        final Object $endTime = this.getEndTime();
        result = result * 59 + (($endTime == null) ? 43 : $endTime.hashCode());
        return result;
    }
    
    public String toString() {
        return "NoticeInfo(id=" + this.getId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", fileUrl=" + this.getFileUrl() + ", creator=" + this.getCreator() + ", createTime=" + this.getCreateTime() + ", sceneType=" + this.getSceneType() + ", code=" + this.getCode() + ", source=" + this.getSource() + ", platform=" + this.getPlatform() + ", noticeUrl=" + this.getNoticeUrl() + ", hash=" + this.getHash() + ", sourceTag=" + this.getSourceTag() + ", creatorName=" + this.getCreatorName() + ", beginTime=" + this.getBeginTime() + ", endTime=" + this.getEndTime() + ")";
    }
}
