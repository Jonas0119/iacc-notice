// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import java.io.Serializable;

public class NoticeInfoReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userId;
    private String userName;
    private String title;
    private String type;
    private String remark;
    private String fileUrl;
    private String creator;
    private String createTime;
    private String sceneType;
    private String code;
    private String source;
    private String platform;
    private String noticeUrl;
    private String fileName;
    private String fileHash;
    private String sourceTag;
    private String creatorName;
    
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
    
    public String getCreateTime() {
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
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getFileHash() {
        return this.fileHash;
    }
    
    public String getSourceTag() {
        return this.sourceTag;
    }
    
    public String getCreatorName() {
        return this.creatorName;
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
    
    public void setCreateTime(final String createTime) {
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
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileHash(final String fileHash) {
        this.fileHash = fileHash;
    }
    
    public void setSourceTag(final String sourceTag) {
        this.sourceTag = sourceTag;
    }
    
    public void setCreatorName(final String creatorName) {
        this.creatorName = creatorName;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticeInfoReq)) {
            return false;
        }
        final NoticeInfoReq other = (NoticeInfoReq)o;
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
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        Label_0583: {
            if (this$fileName == null) {
                if (other$fileName == null) {
                    break Label_0583;
                }
            }
            else if (this$fileName.equals(other$fileName)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$fileHash = this.getFileHash();
        final Object other$fileHash = other.getFileHash();
        Label_0620: {
            if (this$fileHash == null) {
                if (other$fileHash == null) {
                    break Label_0620;
                }
            }
            else if (this$fileHash.equals(other$fileHash)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$sourceTag = this.getSourceTag();
        final Object other$sourceTag = other.getSourceTag();
        Label_0657: {
            if (this$sourceTag == null) {
                if (other$sourceTag == null) {
                    break Label_0657;
                }
            }
            else if (this$sourceTag.equals(other$sourceTag)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$creatorName = this.getCreatorName();
        final Object other$creatorName = other.getCreatorName();
        if (this$creatorName == null) {
            if (other$creatorName == null) {
                return true;
            }
        }
        else if (this$creatorName.equals(other$creatorName)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticeInfoReq;
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
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        final Object $fileHash = this.getFileHash();
        result = result * 59 + (($fileHash == null) ? 43 : $fileHash.hashCode());
        final Object $sourceTag = this.getSourceTag();
        result = result * 59 + (($sourceTag == null) ? 43 : $sourceTag.hashCode());
        final Object $creatorName = this.getCreatorName();
        result = result * 59 + (($creatorName == null) ? 43 : $creatorName.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NoticeInfoReq(id=" + this.getId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", fileUrl=" + this.getFileUrl() + ", creator=" + this.getCreator() + ", createTime=" + this.getCreateTime() + ", sceneType=" + this.getSceneType() + ", code=" + this.getCode() + ", source=" + this.getSource() + ", platform=" + this.getPlatform() + ", noticeUrl=" + this.getNoticeUrl() + ", fileName=" + this.getFileName() + ", fileHash=" + this.getFileHash() + ", sourceTag=" + this.getSourceTag() + ", creatorName=" + this.getCreatorName() + ")";
    }
}
