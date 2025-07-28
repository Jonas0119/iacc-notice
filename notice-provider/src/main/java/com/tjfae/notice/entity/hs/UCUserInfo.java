// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity.hs;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UCUserInfo
{
    private static final Logger logger;
    private static final long serialVersionUID = -1L;
    private String hsToken;
    private String hsSessionId;
    private String userToken;
    private Long hsId;
    private String userId;
    private String account;
    private String name;
    private String gender;
    private String certificatesId;
    private String partCategories;
    private String isOperator;
    private String optCompCode;
    private String optCode;
    private String optCompName;
    private String optName;
    private String socialCode;
    private String sessionId;
    private String imToken;
    protected int errorCode;
    protected String errorMessage;
    private String flowerName;
    private String cellPhone;
    private String hasMobile;
    private String headImg;
    private String managerName;
    private String managerMobile;
    
    public UCUserInfo() {
        this.hsToken = "";
        this.hsSessionId = "";
        this.userToken = "";
        this.hsId = 0L;
        this.userId = "";
        this.account = "";
        this.name = "";
        this.gender = "";
        this.certificatesId = "";
        this.partCategories = "";
        this.isOperator = "";
        this.optCompCode = "";
        this.optCode = "";
        this.optCompName = "";
        this.optName = "";
        this.socialCode = "";
        this.sessionId = "";
        this.imToken = "";
        this.flowerName = "";
        this.cellPhone = "";
        this.hasMobile = "";
        this.headImg = "";
    }
    
    public String getHsToken() {
        return this.hsToken;
    }
    
    public String getHsSessionId() {
        return this.hsSessionId;
    }
    
    public String getUserToken() {
        return this.userToken;
    }
    
    public Long getHsId() {
        return this.hsId;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getAccount() {
        return this.account;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public String getCertificatesId() {
        return this.certificatesId;
    }
    
    public String getPartCategories() {
        return this.partCategories;
    }
    
    public String getIsOperator() {
        return this.isOperator;
    }
    
    public String getOptCompCode() {
        return this.optCompCode;
    }
    
    public String getOptCode() {
        return this.optCode;
    }
    
    public String getOptCompName() {
        return this.optCompName;
    }
    
    public String getOptName() {
        return this.optName;
    }
    
    public String getSocialCode() {
        return this.socialCode;
    }
    
    public String getSessionId() {
        return this.sessionId;
    }
    
    public String getImToken() {
        return this.imToken;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public String getFlowerName() {
        return this.flowerName;
    }
    
    public String getCellPhone() {
        return this.cellPhone;
    }
    
    public String getHasMobile() {
        return this.hasMobile;
    }
    
    public String getHeadImg() {
        return this.headImg;
    }
    
    public String getManagerName() {
        return this.managerName;
    }
    
    public String getManagerMobile() {
        return this.managerMobile;
    }
    
    public void setHsToken(final String hsToken) {
        this.hsToken = hsToken;
    }
    
    public void setHsSessionId(final String hsSessionId) {
        this.hsSessionId = hsSessionId;
    }
    
    public void setUserToken(final String userToken) {
        this.userToken = userToken;
    }
    
    public void setHsId(final Long hsId) {
        this.hsId = hsId;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setAccount(final String account) {
        this.account = account;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setGender(final String gender) {
        this.gender = gender;
    }
    
    public void setCertificatesId(final String certificatesId) {
        this.certificatesId = certificatesId;
    }
    
    public void setPartCategories(final String partCategories) {
        this.partCategories = partCategories;
    }
    
    public void setIsOperator(final String isOperator) {
        this.isOperator = isOperator;
    }
    
    public void setOptCompCode(final String optCompCode) {
        this.optCompCode = optCompCode;
    }
    
    public void setOptCode(final String optCode) {
        this.optCode = optCode;
    }
    
    public void setOptCompName(final String optCompName) {
        this.optCompName = optCompName;
    }
    
    public void setOptName(final String optName) {
        this.optName = optName;
    }
    
    public void setSocialCode(final String socialCode) {
        this.socialCode = socialCode;
    }
    
    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }
    
    public void setImToken(final String imToken) {
        this.imToken = imToken;
    }
    
    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }
    
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public void setFlowerName(final String flowerName) {
        this.flowerName = flowerName;
    }
    
    public void setCellPhone(final String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    public void setHasMobile(final String hasMobile) {
        this.hasMobile = hasMobile;
    }
    
    public void setHeadImg(final String headImg) {
        this.headImg = headImg;
    }
    
    public void setManagerName(final String managerName) {
        this.managerName = managerName;
    }
    
    public void setManagerMobile(final String managerMobile) {
        this.managerMobile = managerMobile;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UCUserInfo)) {
            return false;
        }
        final UCUserInfo other = (UCUserInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getErrorCode() != other.getErrorCode()) {
            return false;
        }
        final Object this$hsId = this.getHsId();
        final Object other$hsId = other.getHsId();
        Label_0078: {
            if (this$hsId == null) {
                if (other$hsId == null) {
                    break Label_0078;
                }
            }
            else if (this$hsId.equals(other$hsId)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$hsToken = this.getHsToken();
        final Object other$hsToken = other.getHsToken();
        Label_0115: {
            if (this$hsToken == null) {
                if (other$hsToken == null) {
                    break Label_0115;
                }
            }
            else if (this$hsToken.equals(other$hsToken)) {
                break Label_0115;
            }
            return false;
        }
        final Object this$hsSessionId = this.getHsSessionId();
        final Object other$hsSessionId = other.getHsSessionId();
        Label_0152: {
            if (this$hsSessionId == null) {
                if (other$hsSessionId == null) {
                    break Label_0152;
                }
            }
            else if (this$hsSessionId.equals(other$hsSessionId)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$userToken = this.getUserToken();
        final Object other$userToken = other.getUserToken();
        Label_0189: {
            if (this$userToken == null) {
                if (other$userToken == null) {
                    break Label_0189;
                }
            }
            else if (this$userToken.equals(other$userToken)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        Label_0226: {
            if (this$userId == null) {
                if (other$userId == null) {
                    break Label_0226;
                }
            }
            else if (this$userId.equals(other$userId)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$account = this.getAccount();
        final Object other$account = other.getAccount();
        Label_0263: {
            if (this$account == null) {
                if (other$account == null) {
                    break Label_0263;
                }
            }
            else if (this$account.equals(other$account)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0300: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0300;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0300;
            }
            return false;
        }
        final Object this$gender = this.getGender();
        final Object other$gender = other.getGender();
        Label_0337: {
            if (this$gender == null) {
                if (other$gender == null) {
                    break Label_0337;
                }
            }
            else if (this$gender.equals(other$gender)) {
                break Label_0337;
            }
            return false;
        }
        final Object this$certificatesId = this.getCertificatesId();
        final Object other$certificatesId = other.getCertificatesId();
        Label_0374: {
            if (this$certificatesId == null) {
                if (other$certificatesId == null) {
                    break Label_0374;
                }
            }
            else if (this$certificatesId.equals(other$certificatesId)) {
                break Label_0374;
            }
            return false;
        }
        final Object this$partCategories = this.getPartCategories();
        final Object other$partCategories = other.getPartCategories();
        Label_0411: {
            if (this$partCategories == null) {
                if (other$partCategories == null) {
                    break Label_0411;
                }
            }
            else if (this$partCategories.equals(other$partCategories)) {
                break Label_0411;
            }
            return false;
        }
        final Object this$isOperator = this.getIsOperator();
        final Object other$isOperator = other.getIsOperator();
        Label_0448: {
            if (this$isOperator == null) {
                if (other$isOperator == null) {
                    break Label_0448;
                }
            }
            else if (this$isOperator.equals(other$isOperator)) {
                break Label_0448;
            }
            return false;
        }
        final Object this$optCompCode = this.getOptCompCode();
        final Object other$optCompCode = other.getOptCompCode();
        Label_0485: {
            if (this$optCompCode == null) {
                if (other$optCompCode == null) {
                    break Label_0485;
                }
            }
            else if (this$optCompCode.equals(other$optCompCode)) {
                break Label_0485;
            }
            return false;
        }
        final Object this$optCode = this.getOptCode();
        final Object other$optCode = other.getOptCode();
        Label_0522: {
            if (this$optCode == null) {
                if (other$optCode == null) {
                    break Label_0522;
                }
            }
            else if (this$optCode.equals(other$optCode)) {
                break Label_0522;
            }
            return false;
        }
        final Object this$optCompName = this.getOptCompName();
        final Object other$optCompName = other.getOptCompName();
        Label_0559: {
            if (this$optCompName == null) {
                if (other$optCompName == null) {
                    break Label_0559;
                }
            }
            else if (this$optCompName.equals(other$optCompName)) {
                break Label_0559;
            }
            return false;
        }
        final Object this$optName = this.getOptName();
        final Object other$optName = other.getOptName();
        Label_0596: {
            if (this$optName == null) {
                if (other$optName == null) {
                    break Label_0596;
                }
            }
            else if (this$optName.equals(other$optName)) {
                break Label_0596;
            }
            return false;
        }
        final Object this$socialCode = this.getSocialCode();
        final Object other$socialCode = other.getSocialCode();
        Label_0633: {
            if (this$socialCode == null) {
                if (other$socialCode == null) {
                    break Label_0633;
                }
            }
            else if (this$socialCode.equals(other$socialCode)) {
                break Label_0633;
            }
            return false;
        }
        final Object this$sessionId = this.getSessionId();
        final Object other$sessionId = other.getSessionId();
        Label_0670: {
            if (this$sessionId == null) {
                if (other$sessionId == null) {
                    break Label_0670;
                }
            }
            else if (this$sessionId.equals(other$sessionId)) {
                break Label_0670;
            }
            return false;
        }
        final Object this$imToken = this.getImToken();
        final Object other$imToken = other.getImToken();
        Label_0707: {
            if (this$imToken == null) {
                if (other$imToken == null) {
                    break Label_0707;
                }
            }
            else if (this$imToken.equals(other$imToken)) {
                break Label_0707;
            }
            return false;
        }
        final Object this$errorMessage = this.getErrorMessage();
        final Object other$errorMessage = other.getErrorMessage();
        Label_0744: {
            if (this$errorMessage == null) {
                if (other$errorMessage == null) {
                    break Label_0744;
                }
            }
            else if (this$errorMessage.equals(other$errorMessage)) {
                break Label_0744;
            }
            return false;
        }
        final Object this$flowerName = this.getFlowerName();
        final Object other$flowerName = other.getFlowerName();
        Label_0781: {
            if (this$flowerName == null) {
                if (other$flowerName == null) {
                    break Label_0781;
                }
            }
            else if (this$flowerName.equals(other$flowerName)) {
                break Label_0781;
            }
            return false;
        }
        final Object this$cellPhone = this.getCellPhone();
        final Object other$cellPhone = other.getCellPhone();
        Label_0818: {
            if (this$cellPhone == null) {
                if (other$cellPhone == null) {
                    break Label_0818;
                }
            }
            else if (this$cellPhone.equals(other$cellPhone)) {
                break Label_0818;
            }
            return false;
        }
        final Object this$hasMobile = this.getHasMobile();
        final Object other$hasMobile = other.getHasMobile();
        Label_0855: {
            if (this$hasMobile == null) {
                if (other$hasMobile == null) {
                    break Label_0855;
                }
            }
            else if (this$hasMobile.equals(other$hasMobile)) {
                break Label_0855;
            }
            return false;
        }
        final Object this$headImg = this.getHeadImg();
        final Object other$headImg = other.getHeadImg();
        Label_0892: {
            if (this$headImg == null) {
                if (other$headImg == null) {
                    break Label_0892;
                }
            }
            else if (this$headImg.equals(other$headImg)) {
                break Label_0892;
            }
            return false;
        }
        final Object this$managerName = this.getManagerName();
        final Object other$managerName = other.getManagerName();
        Label_0929: {
            if (this$managerName == null) {
                if (other$managerName == null) {
                    break Label_0929;
                }
            }
            else if (this$managerName.equals(other$managerName)) {
                break Label_0929;
            }
            return false;
        }
        final Object this$managerMobile = this.getManagerMobile();
        final Object other$managerMobile = other.getManagerMobile();
        if (this$managerMobile == null) {
            if (other$managerMobile == null) {
                return true;
            }
        }
        else if (this$managerMobile.equals(other$managerMobile)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UCUserInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + this.getErrorCode();
        final Object $hsId = this.getHsId();
        result = result * 59 + (($hsId == null) ? 43 : $hsId.hashCode());
        final Object $hsToken = this.getHsToken();
        result = result * 59 + (($hsToken == null) ? 43 : $hsToken.hashCode());
        final Object $hsSessionId = this.getHsSessionId();
        result = result * 59 + (($hsSessionId == null) ? 43 : $hsSessionId.hashCode());
        final Object $userToken = this.getUserToken();
        result = result * 59 + (($userToken == null) ? 43 : $userToken.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $account = this.getAccount();
        result = result * 59 + (($account == null) ? 43 : $account.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $gender = this.getGender();
        result = result * 59 + (($gender == null) ? 43 : $gender.hashCode());
        final Object $certificatesId = this.getCertificatesId();
        result = result * 59 + (($certificatesId == null) ? 43 : $certificatesId.hashCode());
        final Object $partCategories = this.getPartCategories();
        result = result * 59 + (($partCategories == null) ? 43 : $partCategories.hashCode());
        final Object $isOperator = this.getIsOperator();
        result = result * 59 + (($isOperator == null) ? 43 : $isOperator.hashCode());
        final Object $optCompCode = this.getOptCompCode();
        result = result * 59 + (($optCompCode == null) ? 43 : $optCompCode.hashCode());
        final Object $optCode = this.getOptCode();
        result = result * 59 + (($optCode == null) ? 43 : $optCode.hashCode());
        final Object $optCompName = this.getOptCompName();
        result = result * 59 + (($optCompName == null) ? 43 : $optCompName.hashCode());
        final Object $optName = this.getOptName();
        result = result * 59 + (($optName == null) ? 43 : $optName.hashCode());
        final Object $socialCode = this.getSocialCode();
        result = result * 59 + (($socialCode == null) ? 43 : $socialCode.hashCode());
        final Object $sessionId = this.getSessionId();
        result = result * 59 + (($sessionId == null) ? 43 : $sessionId.hashCode());
        final Object $imToken = this.getImToken();
        result = result * 59 + (($imToken == null) ? 43 : $imToken.hashCode());
        final Object $errorMessage = this.getErrorMessage();
        result = result * 59 + (($errorMessage == null) ? 43 : $errorMessage.hashCode());
        final Object $flowerName = this.getFlowerName();
        result = result * 59 + (($flowerName == null) ? 43 : $flowerName.hashCode());
        final Object $cellPhone = this.getCellPhone();
        result = result * 59 + (($cellPhone == null) ? 43 : $cellPhone.hashCode());
        final Object $hasMobile = this.getHasMobile();
        result = result * 59 + (($hasMobile == null) ? 43 : $hasMobile.hashCode());
        final Object $headImg = this.getHeadImg();
        result = result * 59 + (($headImg == null) ? 43 : $headImg.hashCode());
        final Object $managerName = this.getManagerName();
        result = result * 59 + (($managerName == null) ? 43 : $managerName.hashCode());
        final Object $managerMobile = this.getManagerMobile();
        result = result * 59 + (($managerMobile == null) ? 43 : $managerMobile.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UCUserInfo(hsToken=" + this.getHsToken() + ", hsSessionId=" + this.getHsSessionId() + ", userToken=" + this.getUserToken() + ", hsId=" + this.getHsId() + ", userId=" + this.getUserId() + ", account=" + this.getAccount() + ", name=" + this.getName() + ", gender=" + this.getGender() + ", certificatesId=" + this.getCertificatesId() + ", partCategories=" + this.getPartCategories() + ", isOperator=" + this.getIsOperator() + ", optCompCode=" + this.getOptCompCode() + ", optCode=" + this.getOptCode() + ", optCompName=" + this.getOptCompName() + ", optName=" + this.getOptName() + ", socialCode=" + this.getSocialCode() + ", sessionId=" + this.getSessionId() + ", imToken=" + this.getImToken() + ", errorCode=" + this.getErrorCode() + ", errorMessage=" + this.getErrorMessage() + ", flowerName=" + this.getFlowerName() + ", cellPhone=" + this.getCellPhone() + ", hasMobile=" + this.getHasMobile() + ", headImg=" + this.getHeadImg() + ", managerName=" + this.getManagerName() + ", managerMobile=" + this.getManagerMobile() + ")";
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)UCUserInfo.class);
    }
}
