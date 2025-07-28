// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel(value = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u63a8\u9001\u5bf9\u8c61", description = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u63a8\u9001\u5bf9\u8c61")
public class NoticePushReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("userId")
    private String userId;
    @ApiModelProperty("\u516c\u544a\u65b9")
    private String userName;
    @ApiModelProperty("\u516c\u544a\u6807\u9898")
    private String title;
    @ApiModelProperty("\u516c\u544a\u6b63\u6587")
    private String remark;
    @ApiModelProperty("\u521b\u5efa\u4eba")
    private String creator;
    @ApiModelProperty("\u521b\u5efa\u4eba\u540d\u79f0")
    private String creatorName;
    @ApiModelProperty("\u5ba2\u6237\u7ecf\u7406\u59d3\u540d")
    private String managerName;
    @ApiModelProperty("\u5ba2\u6237\u7ecf\u7406\u624b\u673a\u53f7")
    private String managerPhone;
    @ApiModelProperty("\u8ba4\u8bc1\u4e66")
    private String authUrl;
    @ApiModelProperty("\u4ed8\u6b3e\u51ed\u8bc1")
    private String payUrl;
    @ApiModelProperty(value = "\u4e1a\u52a1\u573a\u666f", hidden = true)
    private String sceneType;
    @ApiModelProperty(value = "\u5ba2\u6237\u6765\u6e90", hidden = true)
    private String source;
    @ApiModelProperty(value = "\u6570\u636e\u6765\u6e90", hidden = true)
    private String sourceTag;
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public String getCreatorName() {
        return this.creatorName;
    }
    
    public String getManagerName() {
        return this.managerName;
    }
    
    public String getManagerPhone() {
        return this.managerPhone;
    }
    
    public String getAuthUrl() {
        return this.authUrl;
    }
    
    public String getPayUrl() {
        return this.payUrl;
    }
    
    public String getSceneType() {
        return this.sceneType;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public String getSourceTag() {
        return this.sourceTag;
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
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    public void setCreatorName(final String creatorName) {
        this.creatorName = creatorName;
    }
    
    public void setManagerName(final String managerName) {
        this.managerName = managerName;
    }
    
    public void setManagerPhone(final String managerPhone) {
        this.managerPhone = managerPhone;
    }
    
    public void setAuthUrl(final String authUrl) {
        this.authUrl = authUrl;
    }
    
    public void setPayUrl(final String payUrl) {
        this.payUrl = payUrl;
    }
    
    public void setSceneType(final String sceneType) {
        this.sceneType = sceneType;
    }
    
    public void setSource(final String source) {
        this.source = source;
    }
    
    public void setSourceTag(final String sourceTag) {
        this.sourceTag = sourceTag;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticePushReq)) {
            return false;
        }
        final NoticePushReq other = (NoticePushReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        Label_0065: {
            if (this$userId == null) {
                if (other$userId == null) {
                    break Label_0065;
                }
            }
            else if (this$userId.equals(other$userId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        Label_0102: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0102;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0139: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0139;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0176: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0176;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0213: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0213;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$creatorName = this.getCreatorName();
        final Object other$creatorName = other.getCreatorName();
        Label_0250: {
            if (this$creatorName == null) {
                if (other$creatorName == null) {
                    break Label_0250;
                }
            }
            else if (this$creatorName.equals(other$creatorName)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$managerName = this.getManagerName();
        final Object other$managerName = other.getManagerName();
        Label_0287: {
            if (this$managerName == null) {
                if (other$managerName == null) {
                    break Label_0287;
                }
            }
            else if (this$managerName.equals(other$managerName)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$managerPhone = this.getManagerPhone();
        final Object other$managerPhone = other.getManagerPhone();
        Label_0324: {
            if (this$managerPhone == null) {
                if (other$managerPhone == null) {
                    break Label_0324;
                }
            }
            else if (this$managerPhone.equals(other$managerPhone)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$authUrl = this.getAuthUrl();
        final Object other$authUrl = other.getAuthUrl();
        Label_0361: {
            if (this$authUrl == null) {
                if (other$authUrl == null) {
                    break Label_0361;
                }
            }
            else if (this$authUrl.equals(other$authUrl)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$payUrl = this.getPayUrl();
        final Object other$payUrl = other.getPayUrl();
        Label_0398: {
            if (this$payUrl == null) {
                if (other$payUrl == null) {
                    break Label_0398;
                }
            }
            else if (this$payUrl.equals(other$payUrl)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$sceneType = this.getSceneType();
        final Object other$sceneType = other.getSceneType();
        Label_0435: {
            if (this$sceneType == null) {
                if (other$sceneType == null) {
                    break Label_0435;
                }
            }
            else if (this$sceneType.equals(other$sceneType)) {
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
        final Object this$sourceTag = this.getSourceTag();
        final Object other$sourceTag = other.getSourceTag();
        if (this$sourceTag == null) {
            if (other$sourceTag == null) {
                return true;
            }
        }
        else if (this$sourceTag.equals(other$sourceTag)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticePushReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $creatorName = this.getCreatorName();
        result = result * 59 + (($creatorName == null) ? 43 : $creatorName.hashCode());
        final Object $managerName = this.getManagerName();
        result = result * 59 + (($managerName == null) ? 43 : $managerName.hashCode());
        final Object $managerPhone = this.getManagerPhone();
        result = result * 59 + (($managerPhone == null) ? 43 : $managerPhone.hashCode());
        final Object $authUrl = this.getAuthUrl();
        result = result * 59 + (($authUrl == null) ? 43 : $authUrl.hashCode());
        final Object $payUrl = this.getPayUrl();
        result = result * 59 + (($payUrl == null) ? 43 : $payUrl.hashCode());
        final Object $sceneType = this.getSceneType();
        result = result * 59 + (($sceneType == null) ? 43 : $sceneType.hashCode());
        final Object $source = this.getSource();
        result = result * 59 + (($source == null) ? 43 : $source.hashCode());
        final Object $sourceTag = this.getSourceTag();
        result = result * 59 + (($sourceTag == null) ? 43 : $sourceTag.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NoticePushReq(userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", remark=" + this.getRemark() + ", creator=" + this.getCreator() + ", creatorName=" + this.getCreatorName() + ", managerName=" + this.getManagerName() + ", managerPhone=" + this.getManagerPhone() + ", authUrl=" + this.getAuthUrl() + ", payUrl=" + this.getPayUrl() + ", sceneType=" + this.getSceneType() + ", source=" + this.getSource() + ", sourceTag=" + this.getSourceTag() + ")";
    }
}
