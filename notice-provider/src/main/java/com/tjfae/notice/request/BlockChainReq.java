// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;

public class BlockChainReq
{
    private String version;
    private String user;
    private String userPlatform;
    @NotBlank(message = "\u5b58\u8bc1ID\u4e0d\u80fd\u4e3a\u7a7a")
    private String evidenceId;
    private String description;
    @NotBlank(message = "\u5b58\u8bc1\u6570\u636e\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")
    private String type;
    @NotBlank(message = "\u5b58\u8bc1\u6570\u636e\u4e0d\u80fd\u4e3a\u7a7a")
    private String data;
    private String businessType;
    private String source;
    private String noticeUrl;
    private String platform;
    private String contractType;
    @NotEmpty(message = "\u4e1a\u52a1\u6267\u884c\u8282\u70b9\u4e0d\u80fd\u4e3a\u7a7a")
    private String businessPhase;
    
    public BlockChainReq() {
        this.version = "1.1";
        this.type = "common";
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public String getUserPlatform() {
        return this.userPlatform;
    }
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getData() {
        return this.data;
    }
    
    public String getBusinessType() {
        return this.businessType;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public String getNoticeUrl() {
        return this.noticeUrl;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public String getContractType() {
        return this.contractType;
    }
    
    public String getBusinessPhase() {
        return this.businessPhase;
    }
    
    public void setVersion(final String version) {
        this.version = version;
    }
    
    public void setUser(final String user) {
        this.user = user;
    }
    
    public void setUserPlatform(final String userPlatform) {
        this.userPlatform = userPlatform;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setData(final String data) {
        this.data = data;
    }
    
    public void setBusinessType(final String businessType) {
        this.businessType = businessType;
    }
    
    public void setSource(final String source) {
        this.source = source;
    }
    
    public void setNoticeUrl(final String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
    
    public void setContractType(final String contractType) {
        this.contractType = contractType;
    }
    
    public void setBusinessPhase(final String businessPhase) {
        this.businessPhase = businessPhase;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BlockChainReq)) {
            return false;
        }
        final BlockChainReq other = (BlockChainReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        Label_0065: {
            if (this$version == null) {
                if (other$version == null) {
                    break Label_0065;
                }
            }
            else if (this$version.equals(other$version)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        Label_0102: {
            if (this$user == null) {
                if (other$user == null) {
                    break Label_0102;
                }
            }
            else if (this$user.equals(other$user)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$userPlatform = this.getUserPlatform();
        final Object other$userPlatform = other.getUserPlatform();
        Label_0139: {
            if (this$userPlatform == null) {
                if (other$userPlatform == null) {
                    break Label_0139;
                }
            }
            else if (this$userPlatform.equals(other$userPlatform)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0176: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0176;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        Label_0213: {
            if (this$description == null) {
                if (other$description == null) {
                    break Label_0213;
                }
            }
            else if (this$description.equals(other$description)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0250: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0250;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        Label_0287: {
            if (this$data == null) {
                if (other$data == null) {
                    break Label_0287;
                }
            }
            else if (this$data.equals(other$data)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$businessType = this.getBusinessType();
        final Object other$businessType = other.getBusinessType();
        Label_0324: {
            if (this$businessType == null) {
                if (other$businessType == null) {
                    break Label_0324;
                }
            }
            else if (this$businessType.equals(other$businessType)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$source = this.getSource();
        final Object other$source = other.getSource();
        Label_0361: {
            if (this$source == null) {
                if (other$source == null) {
                    break Label_0361;
                }
            }
            else if (this$source.equals(other$source)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$noticeUrl = this.getNoticeUrl();
        final Object other$noticeUrl = other.getNoticeUrl();
        Label_0398: {
            if (this$noticeUrl == null) {
                if (other$noticeUrl == null) {
                    break Label_0398;
                }
            }
            else if (this$noticeUrl.equals(other$noticeUrl)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$platform = this.getPlatform();
        final Object other$platform = other.getPlatform();
        Label_0435: {
            if (this$platform == null) {
                if (other$platform == null) {
                    break Label_0435;
                }
            }
            else if (this$platform.equals(other$platform)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$contractType = this.getContractType();
        final Object other$contractType = other.getContractType();
        Label_0472: {
            if (this$contractType == null) {
                if (other$contractType == null) {
                    break Label_0472;
                }
            }
            else if (this$contractType.equals(other$contractType)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$businessPhase = this.getBusinessPhase();
        final Object other$businessPhase = other.getBusinessPhase();
        if (this$businessPhase == null) {
            if (other$businessPhase == null) {
                return true;
            }
        }
        else if (this$businessPhase.equals(other$businessPhase)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof BlockChainReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $version = this.getVersion();
        result = result * 59 + (($version == null) ? 43 : $version.hashCode());
        final Object $user = this.getUser();
        result = result * 59 + (($user == null) ? 43 : $user.hashCode());
        final Object $userPlatform = this.getUserPlatform();
        result = result * 59 + (($userPlatform == null) ? 43 : $userPlatform.hashCode());
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $data = this.getData();
        result = result * 59 + (($data == null) ? 43 : $data.hashCode());
        final Object $businessType = this.getBusinessType();
        result = result * 59 + (($businessType == null) ? 43 : $businessType.hashCode());
        final Object $source = this.getSource();
        result = result * 59 + (($source == null) ? 43 : $source.hashCode());
        final Object $noticeUrl = this.getNoticeUrl();
        result = result * 59 + (($noticeUrl == null) ? 43 : $noticeUrl.hashCode());
        final Object $platform = this.getPlatform();
        result = result * 59 + (($platform == null) ? 43 : $platform.hashCode());
        final Object $contractType = this.getContractType();
        result = result * 59 + (($contractType == null) ? 43 : $contractType.hashCode());
        final Object $businessPhase = this.getBusinessPhase();
        result = result * 59 + (($businessPhase == null) ? 43 : $businessPhase.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "BlockChainReq(version=" + this.getVersion() + ", user=" + this.getUser() + ", userPlatform=" + this.getUserPlatform() + ", evidenceId=" + this.getEvidenceId() + ", description=" + this.getDescription() + ", type=" + this.getType() + ", data=" + this.getData() + ", businessType=" + this.getBusinessType() + ", source=" + this.getSource() + ", noticeUrl=" + this.getNoticeUrl() + ", platform=" + this.getPlatform() + ", contractType=" + this.getContractType() + ", businessPhase=" + this.getBusinessPhase() + ")";
    }
}
