// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class ContractInfoReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userId;
    @NotBlank(message = "\u5b58\u8bc1\u65b9\u975e\u7a7a")
    private String userName;
    private String title;
    private String type;
    private String remark;
    @NotBlank(message = "\u6587\u4ef6\u5730\u5740")
    private String fileUrl;
    @NotBlank(message = "\u6587\u4ef6hash")
    private String fileHash;
    @NotBlank(message = "\u6587\u4ef6\u540d\u79f0\u975e\u7a7a")
    private String fileName;
    private String sceneType;
    @NotBlank(message = "\u5b58\u8bc1\u7f16\u53f7\u975e\u7a7a")
    private String code;
    private String sourceTag;
    private String creator;
    private String creatorName;
    private String threeInOneFileUrl;
    private String idCardImageUrl;
    private String companyDescUrl;
    private String companyRuleUrl;
    private String resumeUrl;
    private String financeReportUrl;
    private String grayListUrl;
    private String creditReportUrl;
    private String aiRtingReportUrl;
    private String serviceReportUrl;
    private String applyReportUrl;
    private String standingBookReportUrl;
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
    
    public String getFileHash() {
        return this.fileHash;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getSceneType() {
        return this.sceneType;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getSourceTag() {
        return this.sourceTag;
    }
    
    public String getCreator() {
        return this.creator;
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
    
    public void setFileHash(final String fileHash) {
        this.fileHash = fileHash;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setSceneType(final String sceneType) {
        this.sceneType = sceneType;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setSourceTag(final String sourceTag) {
        this.sourceTag = sourceTag;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
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
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractInfoReq)) {
            return false;
        }
        final ContractInfoReq other = (ContractInfoReq)o;
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
        final Object this$fileHash = this.getFileHash();
        final Object other$fileHash = other.getFileHash();
        Label_0324: {
            if (this$fileHash == null) {
                if (other$fileHash == null) {
                    break Label_0324;
                }
            }
            else if (this$fileHash.equals(other$fileHash)) {
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
        final Object this$sourceTag = this.getSourceTag();
        final Object other$sourceTag = other.getSourceTag();
        Label_0472: {
            if (this$sourceTag == null) {
                if (other$sourceTag == null) {
                    break Label_0472;
                }
            }
            else if (this$sourceTag.equals(other$sourceTag)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0509: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0509;
                }
            }
            else if (this$creator.equals(other$creator)) {
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
        return other instanceof ContractInfoReq;
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
        final Object $fileHash = this.getFileHash();
        result = result * 59 + (($fileHash == null) ? 43 : $fileHash.hashCode());
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        final Object $sceneType = this.getSceneType();
        result = result * 59 + (($sceneType == null) ? 43 : $sceneType.hashCode());
        final Object $code = this.getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        final Object $sourceTag = this.getSourceTag();
        result = result * 59 + (($sourceTag == null) ? 43 : $sourceTag.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
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
    
    @Override
    public String toString() {
        return "ContractInfoReq(id=" + this.getId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", fileUrl=" + this.getFileUrl() + ", fileHash=" + this.getFileHash() + ", fileName=" + this.getFileName() + ", sceneType=" + this.getSceneType() + ", code=" + this.getCode() + ", sourceTag=" + this.getSourceTag() + ", creator=" + this.getCreator() + ", creatorName=" + this.getCreatorName() + ", threeInOneFileUrl=" + this.getThreeInOneFileUrl() + ", idCardImageUrl=" + this.getIdCardImageUrl() + ", companyDescUrl=" + this.getCompanyDescUrl() + ", companyRuleUrl=" + this.getCompanyRuleUrl() + ", resumeUrl=" + this.getResumeUrl() + ", financeReportUrl=" + this.getFinanceReportUrl() + ", grayListUrl=" + this.getGrayListUrl() + ", creditReportUrl=" + this.getCreditReportUrl() + ", aiRtingReportUrl=" + this.getAiRtingReportUrl() + ", serviceReportUrl=" + this.getServiceReportUrl() + ", applyReportUrl=" + this.getApplyReportUrl() + ", standingBookReportUrl=" + this.getStandingBookReportUrl() + ", promiseUrl=" + this.getPromiseUrl() + ")";
    }
}
