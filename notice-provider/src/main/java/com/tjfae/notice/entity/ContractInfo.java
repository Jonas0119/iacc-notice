// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.tjfae.common.core.annotation.Excel;
import com.tjfae.notice.base.BaseEntity;

public class ContractInfo extends BaseEntity
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
    @Excel(name = "\u5b58\u8bc1hash")
    private String hash;
    @Excel(name = "\u6570\u636e\u6765\u6e90")
    private String sourceTag;
    @Excel(name = "\u521b\u5efa\u4eba\u540d\u79f0")
    private String creatorName;
    @Excel(name = "\u4e09\u8bc1\u5408\u4e00")
    private String threeInOneFileUrl;
    @Excel(name = "\u6cd5\u4eba\u4ee3\u8868\u8eab\u4efd\u8bc1")
    private String idCardImageUrl;
    @Excel(name = "\u516c\u53f8\u7b80\u4ecb")
    private String companyDescUrl;
    @Excel(name = "\u516c\u53f8\u7ae0\u7a0b\u9644\u4ef6")
    private String companyRuleUrl;
    @Excel(name = "\u9ad8\u7ba1\u7b80\u5386\u9644\u4ef6")
    private String resumeUrl;
    @Excel(name = "\u8d22\u52a1\u62a5\u8868")
    private String financeReportUrl;
    @Excel(name = "\u7070\u540d\u5355\u7ed3\u679c")
    private String grayListUrl;
    @Excel(name = "\u5f81\u4fe1\u62a5\u544a")
    private String creditReportUrl;
    @Excel(name = "\u8bc4\u7ea7\u62a5\u544a")
    private String aiRtingReportUrl;
    @Excel(name = "\u670d\u52a1\u534f\u8bae\u4e66")
    private String serviceReportUrl;
    @Excel(name = "\u7533\u8bf7\u4e66")
    private String applyReportUrl;
    @Excel(name = "\u4e1a\u52a1\u53f0\u8d26")
    private String standingBookReportUrl;
    @Excel(name = "\u627f\u8bfa\u4e66")
    private String promiseUrl;
    
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
    
    public String getHash() {
        return this.hash;
    }
    
    public String getSourceTag() {
        return this.sourceTag;
    }
    
    public String getCreatorName() {
        return this.creatorName;
    }
    
    public String getThreeInOneFileUrl() {
        return this.threeInOneFileUrl;
    }
    
    public String getIdCardImageUrl() {
        return this.idCardImageUrl;
    }
    
    public String getCompanyDescUrl() {
        return this.companyDescUrl;
    }
    
    public String getCompanyRuleUrl() {
        return this.companyRuleUrl;
    }
    
    public String getResumeUrl() {
        return this.resumeUrl;
    }
    
    public String getFinanceReportUrl() {
        return this.financeReportUrl;
    }
    
    public String getGrayListUrl() {
        return this.grayListUrl;
    }
    
    public String getCreditReportUrl() {
        return this.creditReportUrl;
    }
    
    public String getAiRtingReportUrl() {
        return this.aiRtingReportUrl;
    }
    
    public String getServiceReportUrl() {
        return this.serviceReportUrl;
    }
    
    public String getApplyReportUrl() {
        return this.applyReportUrl;
    }
    
    public String getStandingBookReportUrl() {
        return this.standingBookReportUrl;
    }
    
    public String getPromiseUrl() {
        return this.promiseUrl;
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
    
    public void setHash(final String hash) {
        this.hash = hash;
    }
    
    public void setSourceTag(final String sourceTag) {
        this.sourceTag = sourceTag;
    }
    
    public void setCreatorName(final String creatorName) {
        this.creatorName = creatorName;
    }
    
    public void setThreeInOneFileUrl(final String threeInOneFileUrl) {
        this.threeInOneFileUrl = threeInOneFileUrl;
    }
    
    public void setIdCardImageUrl(final String idCardImageUrl) {
        this.idCardImageUrl = idCardImageUrl;
    }
    
    public void setCompanyDescUrl(final String companyDescUrl) {
        this.companyDescUrl = companyDescUrl;
    }
    
    public void setCompanyRuleUrl(final String companyRuleUrl) {
        this.companyRuleUrl = companyRuleUrl;
    }
    
    public void setResumeUrl(final String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
    
    public void setFinanceReportUrl(final String financeReportUrl) {
        this.financeReportUrl = financeReportUrl;
    }
    
    public void setGrayListUrl(final String grayListUrl) {
        this.grayListUrl = grayListUrl;
    }
    
    public void setCreditReportUrl(final String creditReportUrl) {
        this.creditReportUrl = creditReportUrl;
    }
    
    public void setAiRtingReportUrl(final String aiRtingReportUrl) {
        this.aiRtingReportUrl = aiRtingReportUrl;
    }
    
    public void setServiceReportUrl(final String serviceReportUrl) {
        this.serviceReportUrl = serviceReportUrl;
    }
    
    public void setApplyReportUrl(final String applyReportUrl) {
        this.applyReportUrl = applyReportUrl;
    }
    
    public void setStandingBookReportUrl(final String standingBookReportUrl) {
        this.standingBookReportUrl = standingBookReportUrl;
    }
    
    public void setPromiseUrl(final String promiseUrl) {
        this.promiseUrl = promiseUrl;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractInfo)) {
            return false;
        }
        final ContractInfo other = (ContractInfo)o;
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
        final Object this$hash = this.getHash();
        final Object other$hash = other.getHash();
        Label_0472: {
            if (this$hash == null) {
                if (other$hash == null) {
                    break Label_0472;
                }
            }
            else if (this$hash.equals(other$hash)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$sourceTag = this.getSourceTag();
        final Object other$sourceTag = other.getSourceTag();
        Label_0509: {
            if (this$sourceTag == null) {
                if (other$sourceTag == null) {
                    break Label_0509;
                }
            }
            else if (this$sourceTag.equals(other$sourceTag)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$creatorName = this.getCreatorName();
        final Object other$creatorName = other.getCreatorName();
        Label_0546: {
            if (this$creatorName == null) {
                if (other$creatorName == null) {
                    break Label_0546;
                }
            }
            else if (this$creatorName.equals(other$creatorName)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$threeInOneFileUrl = this.getThreeInOneFileUrl();
        final Object other$threeInOneFileUrl = other.getThreeInOneFileUrl();
        Label_0583: {
            if (this$threeInOneFileUrl == null) {
                if (other$threeInOneFileUrl == null) {
                    break Label_0583;
                }
            }
            else if (this$threeInOneFileUrl.equals(other$threeInOneFileUrl)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$idCardImageUrl = this.getIdCardImageUrl();
        final Object other$idCardImageUrl = other.getIdCardImageUrl();
        Label_0620: {
            if (this$idCardImageUrl == null) {
                if (other$idCardImageUrl == null) {
                    break Label_0620;
                }
            }
            else if (this$idCardImageUrl.equals(other$idCardImageUrl)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$companyDescUrl = this.getCompanyDescUrl();
        final Object other$companyDescUrl = other.getCompanyDescUrl();
        Label_0657: {
            if (this$companyDescUrl == null) {
                if (other$companyDescUrl == null) {
                    break Label_0657;
                }
            }
            else if (this$companyDescUrl.equals(other$companyDescUrl)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$companyRuleUrl = this.getCompanyRuleUrl();
        final Object other$companyRuleUrl = other.getCompanyRuleUrl();
        Label_0694: {
            if (this$companyRuleUrl == null) {
                if (other$companyRuleUrl == null) {
                    break Label_0694;
                }
            }
            else if (this$companyRuleUrl.equals(other$companyRuleUrl)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$resumeUrl = this.getResumeUrl();
        final Object other$resumeUrl = other.getResumeUrl();
        Label_0731: {
            if (this$resumeUrl == null) {
                if (other$resumeUrl == null) {
                    break Label_0731;
                }
            }
            else if (this$resumeUrl.equals(other$resumeUrl)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$financeReportUrl = this.getFinanceReportUrl();
        final Object other$financeReportUrl = other.getFinanceReportUrl();
        Label_0768: {
            if (this$financeReportUrl == null) {
                if (other$financeReportUrl == null) {
                    break Label_0768;
                }
            }
            else if (this$financeReportUrl.equals(other$financeReportUrl)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$grayListUrl = this.getGrayListUrl();
        final Object other$grayListUrl = other.getGrayListUrl();
        Label_0805: {
            if (this$grayListUrl == null) {
                if (other$grayListUrl == null) {
                    break Label_0805;
                }
            }
            else if (this$grayListUrl.equals(other$grayListUrl)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$creditReportUrl = this.getCreditReportUrl();
        final Object other$creditReportUrl = other.getCreditReportUrl();
        Label_0842: {
            if (this$creditReportUrl == null) {
                if (other$creditReportUrl == null) {
                    break Label_0842;
                }
            }
            else if (this$creditReportUrl.equals(other$creditReportUrl)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$aiRtingReportUrl = this.getAiRtingReportUrl();
        final Object other$aiRtingReportUrl = other.getAiRtingReportUrl();
        Label_0879: {
            if (this$aiRtingReportUrl == null) {
                if (other$aiRtingReportUrl == null) {
                    break Label_0879;
                }
            }
            else if (this$aiRtingReportUrl.equals(other$aiRtingReportUrl)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$serviceReportUrl = this.getServiceReportUrl();
        final Object other$serviceReportUrl = other.getServiceReportUrl();
        Label_0916: {
            if (this$serviceReportUrl == null) {
                if (other$serviceReportUrl == null) {
                    break Label_0916;
                }
            }
            else if (this$serviceReportUrl.equals(other$serviceReportUrl)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$applyReportUrl = this.getApplyReportUrl();
        final Object other$applyReportUrl = other.getApplyReportUrl();
        Label_0953: {
            if (this$applyReportUrl == null) {
                if (other$applyReportUrl == null) {
                    break Label_0953;
                }
            }
            else if (this$applyReportUrl.equals(other$applyReportUrl)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$standingBookReportUrl = this.getStandingBookReportUrl();
        final Object other$standingBookReportUrl = other.getStandingBookReportUrl();
        Label_0990: {
            if (this$standingBookReportUrl == null) {
                if (other$standingBookReportUrl == null) {
                    break Label_0990;
                }
            }
            else if (this$standingBookReportUrl.equals(other$standingBookReportUrl)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$promiseUrl = this.getPromiseUrl();
        final Object other$promiseUrl = other.getPromiseUrl();
        if (this$promiseUrl == null) {
            if (other$promiseUrl == null) {
                return true;
            }
        }
        else if (this$promiseUrl.equals(other$promiseUrl)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ContractInfo;
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
        final Object $hash = this.getHash();
        result = result * 59 + (($hash == null) ? 43 : $hash.hashCode());
        final Object $sourceTag = this.getSourceTag();
        result = result * 59 + (($sourceTag == null) ? 43 : $sourceTag.hashCode());
        final Object $creatorName = this.getCreatorName();
        result = result * 59 + (($creatorName == null) ? 43 : $creatorName.hashCode());
        final Object $threeInOneFileUrl = this.getThreeInOneFileUrl();
        result = result * 59 + (($threeInOneFileUrl == null) ? 43 : $threeInOneFileUrl.hashCode());
        final Object $idCardImageUrl = this.getIdCardImageUrl();
        result = result * 59 + (($idCardImageUrl == null) ? 43 : $idCardImageUrl.hashCode());
        final Object $companyDescUrl = this.getCompanyDescUrl();
        result = result * 59 + (($companyDescUrl == null) ? 43 : $companyDescUrl.hashCode());
        final Object $companyRuleUrl = this.getCompanyRuleUrl();
        result = result * 59 + (($companyRuleUrl == null) ? 43 : $companyRuleUrl.hashCode());
        final Object $resumeUrl = this.getResumeUrl();
        result = result * 59 + (($resumeUrl == null) ? 43 : $resumeUrl.hashCode());
        final Object $financeReportUrl = this.getFinanceReportUrl();
        result = result * 59 + (($financeReportUrl == null) ? 43 : $financeReportUrl.hashCode());
        final Object $grayListUrl = this.getGrayListUrl();
        result = result * 59 + (($grayListUrl == null) ? 43 : $grayListUrl.hashCode());
        final Object $creditReportUrl = this.getCreditReportUrl();
        result = result * 59 + (($creditReportUrl == null) ? 43 : $creditReportUrl.hashCode());
        final Object $aiRtingReportUrl = this.getAiRtingReportUrl();
        result = result * 59 + (($aiRtingReportUrl == null) ? 43 : $aiRtingReportUrl.hashCode());
        final Object $serviceReportUrl = this.getServiceReportUrl();
        result = result * 59 + (($serviceReportUrl == null) ? 43 : $serviceReportUrl.hashCode());
        final Object $applyReportUrl = this.getApplyReportUrl();
        result = result * 59 + (($applyReportUrl == null) ? 43 : $applyReportUrl.hashCode());
        final Object $standingBookReportUrl = this.getStandingBookReportUrl();
        result = result * 59 + (($standingBookReportUrl == null) ? 43 : $standingBookReportUrl.hashCode());
        final Object $promiseUrl = this.getPromiseUrl();
        result = result * 59 + (($promiseUrl == null) ? 43 : $promiseUrl.hashCode());
        return result;
    }
    
    public String toString() {
        return "ContractInfo(id=" + this.getId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", fileUrl=" + this.getFileUrl() + ", creator=" + this.getCreator() + ", createTime=" + this.getCreateTime() + ", sceneType=" + this.getSceneType() + ", code=" + this.getCode() + ", hash=" + this.getHash() + ", sourceTag=" + this.getSourceTag() + ", creatorName=" + this.getCreatorName() + ", threeInOneFileUrl=" + this.getThreeInOneFileUrl() + ", idCardImageUrl=" + this.getIdCardImageUrl() + ", companyDescUrl=" + this.getCompanyDescUrl() + ", companyRuleUrl=" + this.getCompanyRuleUrl() + ", resumeUrl=" + this.getResumeUrl() + ", financeReportUrl=" + this.getFinanceReportUrl() + ", grayListUrl=" + this.getGrayListUrl() + ", creditReportUrl=" + this.getCreditReportUrl() + ", aiRtingReportUrl=" + this.getAiRtingReportUrl() + ", serviceReportUrl=" + this.getServiceReportUrl() + ", applyReportUrl=" + this.getApplyReportUrl() + ", standingBookReportUrl=" + this.getStandingBookReportUrl() + ", promiseUrl=" + this.getPromiseUrl() + ")";
    }
}
