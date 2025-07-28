// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity.hs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.LoggerFactory;
import com.tjfae.notice.utils.json.JsonUtils;
import org.springframework.data.annotation.Transient;
import org.slf4j.Logger;

public class HSUserInfoBo extends HSResponseBase
{
    private static Logger logger;
    private Object userInfoBo;
    @Transient
    private UserInfoBo userInfoBoObj;
    
    public UserInfoBo getUserInfoBo() {
        if (this.userInfoBoObj == null) {
            try {
                this.userInfoBoObj = (UserInfoBo)JsonUtils.jsonStr2Object(JsonUtils.writeEntityJSON(this.userInfoBo), (Class)UserInfoBo.class);
            }
            catch (final Exception e) {
                HSUserInfoBo.logger.error(e.getMessage(), (Throwable)e);
            }
        }
        return this.userInfoBoObj;
    }
    
    public UserInfoBo getUserInfoBoObj() {
        return this.userInfoBoObj;
    }
    
    public void setUserInfoBo(final Object userInfoBo) {
        this.userInfoBo = userInfoBo;
    }
    
    public void setUserInfoBoObj(final UserInfoBo userInfoBoObj) {
        this.userInfoBoObj = userInfoBoObj;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HSUserInfoBo)) {
            return false;
        }
        final HSUserInfoBo other = (HSUserInfoBo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$userInfoBo = this.getUserInfoBo();
        final Object other$userInfoBo = other.getUserInfoBo();
        Label_0065: {
            if (this$userInfoBo == null) {
                if (other$userInfoBo == null) {
                    break Label_0065;
                }
            }
            else if (this$userInfoBo.equals(other$userInfoBo)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$userInfoBoObj = this.getUserInfoBoObj();
        final Object other$userInfoBoObj = other.getUserInfoBoObj();
        if (this$userInfoBoObj == null) {
            if (other$userInfoBoObj == null) {
                return true;
            }
        }
        else if (this$userInfoBoObj.equals(other$userInfoBoObj)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HSUserInfoBo;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userInfoBo = this.getUserInfoBo();
        result = result * 59 + (($userInfoBo == null) ? 43 : $userInfoBo.hashCode());
        final Object $userInfoBoObj = this.getUserInfoBoObj();
        result = result * 59 + (($userInfoBoObj == null) ? 43 : $userInfoBoObj.hashCode());
        return result;
    }
    
    public String toString() {
        return "HSUserInfoBo(userInfoBo=" + this.getUserInfoBo() + ", userInfoBoObj=" + this.getUserInfoBoObj() + ")";
    }
    
    static {
        HSUserInfoBo.logger = LoggerFactory.getLogger((Class)HSUserInfoBo.class);
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserInfoBo
    {
        private String setedPayPwd;
        private String agentPromoteCode;
        private String urgentRemarks;
        private String isOperator;
        private String optCode;
        private String optCompName;
        private String optName;
        private String broker;
        private String account;
        private String holderAccount;
        private String fundAccount;
        private String userId;
        private String job;
        private String cellPhone;
        private String officePhone;
        private String homePhone;
        private String perEmail;
        private String perZipcode;
        private String personAddr;
        private String gender;
        private String commonRemark;
        private String certificatesId;
        private String partCategories;
        private String partCategories2;
        private String provinceCode;
        private String cityCode;
        private String districtCode;
        private String userChannel;
        private String urgentName;
        private String urgentMobile;
        private String urgentRlate;
        private String urgentRegistry;
        private String urgentCertNo;
        private String urgentValidDates;
        private String urgentJob;
        private String urgentAddr;
        private String urgentEmail;
        private String optCompCode;
        private String name;
        
        public UserInfoBo() {
            this.setedPayPwd = "";
            this.agentPromoteCode = "";
            this.urgentRemarks = "";
            this.isOperator = "";
            this.optCode = "";
            this.optCompName = "";
            this.optName = "";
            this.broker = "";
            this.account = "";
            this.holderAccount = "";
            this.fundAccount = "";
            this.userId = "";
            this.job = "";
            this.cellPhone = "";
            this.officePhone = "";
            this.homePhone = "";
            this.perEmail = "";
            this.perZipcode = "";
            this.personAddr = "";
            this.gender = "";
            this.commonRemark = "";
            this.certificatesId = "";
            this.partCategories = "";
            this.partCategories2 = "";
            this.provinceCode = "";
            this.cityCode = "";
            this.districtCode = "";
            this.userChannel = "";
            this.urgentName = "";
            this.urgentMobile = "";
            this.urgentRlate = "";
            this.urgentRegistry = "";
            this.urgentCertNo = "";
            this.urgentValidDates = "";
            this.urgentJob = "";
            this.urgentAddr = "";
            this.urgentEmail = "";
            this.optCompCode = "";
            this.name = "";
        }
        
        public String getSetedPayPwd() {
            return this.setedPayPwd;
        }
        
        public String getAgentPromoteCode() {
            return this.agentPromoteCode;
        }
        
        public String getUrgentRemarks() {
            return this.urgentRemarks;
        }
        
        public String getIsOperator() {
            return this.isOperator;
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
        
        public String getBroker() {
            return this.broker;
        }
        
        public String getAccount() {
            return this.account;
        }
        
        public String getHolderAccount() {
            return this.holderAccount;
        }
        
        public String getFundAccount() {
            return this.fundAccount;
        }
        
        public String getUserId() {
            return this.userId;
        }
        
        public String getJob() {
            return this.job;
        }
        
        public String getCellPhone() {
            return this.cellPhone;
        }
        
        public String getOfficePhone() {
            return this.officePhone;
        }
        
        public String getHomePhone() {
            return this.homePhone;
        }
        
        public String getPerEmail() {
            return this.perEmail;
        }
        
        public String getPerZipcode() {
            return this.perZipcode;
        }
        
        public String getPersonAddr() {
            return this.personAddr;
        }
        
        public String getGender() {
            return this.gender;
        }
        
        public String getCommonRemark() {
            return this.commonRemark;
        }
        
        public String getCertificatesId() {
            return this.certificatesId;
        }
        
        public String getPartCategories() {
            return this.partCategories;
        }
        
        public String getPartCategories2() {
            return this.partCategories2;
        }
        
        public String getProvinceCode() {
            return this.provinceCode;
        }
        
        public String getCityCode() {
            return this.cityCode;
        }
        
        public String getDistrictCode() {
            return this.districtCode;
        }
        
        public String getUserChannel() {
            return this.userChannel;
        }
        
        public String getUrgentName() {
            return this.urgentName;
        }
        
        public String getUrgentMobile() {
            return this.urgentMobile;
        }
        
        public String getUrgentRlate() {
            return this.urgentRlate;
        }
        
        public String getUrgentRegistry() {
            return this.urgentRegistry;
        }
        
        public String getUrgentCertNo() {
            return this.urgentCertNo;
        }
        
        public String getUrgentValidDates() {
            return this.urgentValidDates;
        }
        
        public String getUrgentJob() {
            return this.urgentJob;
        }
        
        public String getUrgentAddr() {
            return this.urgentAddr;
        }
        
        public String getUrgentEmail() {
            return this.urgentEmail;
        }
        
        public String getOptCompCode() {
            return this.optCompCode;
        }
        
        public String getName() {
            return this.name;
        }
        
        public void setSetedPayPwd(final String setedPayPwd) {
            this.setedPayPwd = setedPayPwd;
        }
        
        public void setAgentPromoteCode(final String agentPromoteCode) {
            this.agentPromoteCode = agentPromoteCode;
        }
        
        public void setUrgentRemarks(final String urgentRemarks) {
            this.urgentRemarks = urgentRemarks;
        }
        
        public void setIsOperator(final String isOperator) {
            this.isOperator = isOperator;
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
        
        public void setBroker(final String broker) {
            this.broker = broker;
        }
        
        public void setAccount(final String account) {
            this.account = account;
        }
        
        public void setHolderAccount(final String holderAccount) {
            this.holderAccount = holderAccount;
        }
        
        public void setFundAccount(final String fundAccount) {
            this.fundAccount = fundAccount;
        }
        
        public void setUserId(final String userId) {
            this.userId = userId;
        }
        
        public void setJob(final String job) {
            this.job = job;
        }
        
        public void setCellPhone(final String cellPhone) {
            this.cellPhone = cellPhone;
        }
        
        public void setOfficePhone(final String officePhone) {
            this.officePhone = officePhone;
        }
        
        public void setHomePhone(final String homePhone) {
            this.homePhone = homePhone;
        }
        
        public void setPerEmail(final String perEmail) {
            this.perEmail = perEmail;
        }
        
        public void setPerZipcode(final String perZipcode) {
            this.perZipcode = perZipcode;
        }
        
        public void setPersonAddr(final String personAddr) {
            this.personAddr = personAddr;
        }
        
        public void setGender(final String gender) {
            this.gender = gender;
        }
        
        public void setCommonRemark(final String commonRemark) {
            this.commonRemark = commonRemark;
        }
        
        public void setCertificatesId(final String certificatesId) {
            this.certificatesId = certificatesId;
        }
        
        public void setPartCategories(final String partCategories) {
            this.partCategories = partCategories;
        }
        
        public void setPartCategories2(final String partCategories2) {
            this.partCategories2 = partCategories2;
        }
        
        public void setProvinceCode(final String provinceCode) {
            this.provinceCode = provinceCode;
        }
        
        public void setCityCode(final String cityCode) {
            this.cityCode = cityCode;
        }
        
        public void setDistrictCode(final String districtCode) {
            this.districtCode = districtCode;
        }
        
        public void setUserChannel(final String userChannel) {
            this.userChannel = userChannel;
        }
        
        public void setUrgentName(final String urgentName) {
            this.urgentName = urgentName;
        }
        
        public void setUrgentMobile(final String urgentMobile) {
            this.urgentMobile = urgentMobile;
        }
        
        public void setUrgentRlate(final String urgentRlate) {
            this.urgentRlate = urgentRlate;
        }
        
        public void setUrgentRegistry(final String urgentRegistry) {
            this.urgentRegistry = urgentRegistry;
        }
        
        public void setUrgentCertNo(final String urgentCertNo) {
            this.urgentCertNo = urgentCertNo;
        }
        
        public void setUrgentValidDates(final String urgentValidDates) {
            this.urgentValidDates = urgentValidDates;
        }
        
        public void setUrgentJob(final String urgentJob) {
            this.urgentJob = urgentJob;
        }
        
        public void setUrgentAddr(final String urgentAddr) {
            this.urgentAddr = urgentAddr;
        }
        
        public void setUrgentEmail(final String urgentEmail) {
            this.urgentEmail = urgentEmail;
        }
        
        public void setOptCompCode(final String optCompCode) {
            this.optCompCode = optCompCode;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UserInfoBo)) {
                return false;
            }
            final UserInfoBo other = (UserInfoBo)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$setedPayPwd = this.getSetedPayPwd();
            final Object other$setedPayPwd = other.getSetedPayPwd();
            Label_0065: {
                if (this$setedPayPwd == null) {
                    if (other$setedPayPwd == null) {
                        break Label_0065;
                    }
                }
                else if (this$setedPayPwd.equals(other$setedPayPwd)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$agentPromoteCode = this.getAgentPromoteCode();
            final Object other$agentPromoteCode = other.getAgentPromoteCode();
            Label_0102: {
                if (this$agentPromoteCode == null) {
                    if (other$agentPromoteCode == null) {
                        break Label_0102;
                    }
                }
                else if (this$agentPromoteCode.equals(other$agentPromoteCode)) {
                    break Label_0102;
                }
                return false;
            }
            final Object this$urgentRemarks = this.getUrgentRemarks();
            final Object other$urgentRemarks = other.getUrgentRemarks();
            Label_0139: {
                if (this$urgentRemarks == null) {
                    if (other$urgentRemarks == null) {
                        break Label_0139;
                    }
                }
                else if (this$urgentRemarks.equals(other$urgentRemarks)) {
                    break Label_0139;
                }
                return false;
            }
            final Object this$isOperator = this.getIsOperator();
            final Object other$isOperator = other.getIsOperator();
            Label_0176: {
                if (this$isOperator == null) {
                    if (other$isOperator == null) {
                        break Label_0176;
                    }
                }
                else if (this$isOperator.equals(other$isOperator)) {
                    break Label_0176;
                }
                return false;
            }
            final Object this$optCode = this.getOptCode();
            final Object other$optCode = other.getOptCode();
            Label_0213: {
                if (this$optCode == null) {
                    if (other$optCode == null) {
                        break Label_0213;
                    }
                }
                else if (this$optCode.equals(other$optCode)) {
                    break Label_0213;
                }
                return false;
            }
            final Object this$optCompName = this.getOptCompName();
            final Object other$optCompName = other.getOptCompName();
            Label_0250: {
                if (this$optCompName == null) {
                    if (other$optCompName == null) {
                        break Label_0250;
                    }
                }
                else if (this$optCompName.equals(other$optCompName)) {
                    break Label_0250;
                }
                return false;
            }
            final Object this$optName = this.getOptName();
            final Object other$optName = other.getOptName();
            Label_0287: {
                if (this$optName == null) {
                    if (other$optName == null) {
                        break Label_0287;
                    }
                }
                else if (this$optName.equals(other$optName)) {
                    break Label_0287;
                }
                return false;
            }
            final Object this$broker = this.getBroker();
            final Object other$broker = other.getBroker();
            Label_0324: {
                if (this$broker == null) {
                    if (other$broker == null) {
                        break Label_0324;
                    }
                }
                else if (this$broker.equals(other$broker)) {
                    break Label_0324;
                }
                return false;
            }
            final Object this$account = this.getAccount();
            final Object other$account = other.getAccount();
            Label_0361: {
                if (this$account == null) {
                    if (other$account == null) {
                        break Label_0361;
                    }
                }
                else if (this$account.equals(other$account)) {
                    break Label_0361;
                }
                return false;
            }
            final Object this$holderAccount = this.getHolderAccount();
            final Object other$holderAccount = other.getHolderAccount();
            Label_0398: {
                if (this$holderAccount == null) {
                    if (other$holderAccount == null) {
                        break Label_0398;
                    }
                }
                else if (this$holderAccount.equals(other$holderAccount)) {
                    break Label_0398;
                }
                return false;
            }
            final Object this$fundAccount = this.getFundAccount();
            final Object other$fundAccount = other.getFundAccount();
            Label_0435: {
                if (this$fundAccount == null) {
                    if (other$fundAccount == null) {
                        break Label_0435;
                    }
                }
                else if (this$fundAccount.equals(other$fundAccount)) {
                    break Label_0435;
                }
                return false;
            }
            final Object this$userId = this.getUserId();
            final Object other$userId = other.getUserId();
            Label_0472: {
                if (this$userId == null) {
                    if (other$userId == null) {
                        break Label_0472;
                    }
                }
                else if (this$userId.equals(other$userId)) {
                    break Label_0472;
                }
                return false;
            }
            final Object this$job = this.getJob();
            final Object other$job = other.getJob();
            Label_0509: {
                if (this$job == null) {
                    if (other$job == null) {
                        break Label_0509;
                    }
                }
                else if (this$job.equals(other$job)) {
                    break Label_0509;
                }
                return false;
            }
            final Object this$cellPhone = this.getCellPhone();
            final Object other$cellPhone = other.getCellPhone();
            Label_0546: {
                if (this$cellPhone == null) {
                    if (other$cellPhone == null) {
                        break Label_0546;
                    }
                }
                else if (this$cellPhone.equals(other$cellPhone)) {
                    break Label_0546;
                }
                return false;
            }
            final Object this$officePhone = this.getOfficePhone();
            final Object other$officePhone = other.getOfficePhone();
            Label_0583: {
                if (this$officePhone == null) {
                    if (other$officePhone == null) {
                        break Label_0583;
                    }
                }
                else if (this$officePhone.equals(other$officePhone)) {
                    break Label_0583;
                }
                return false;
            }
            final Object this$homePhone = this.getHomePhone();
            final Object other$homePhone = other.getHomePhone();
            Label_0620: {
                if (this$homePhone == null) {
                    if (other$homePhone == null) {
                        break Label_0620;
                    }
                }
                else if (this$homePhone.equals(other$homePhone)) {
                    break Label_0620;
                }
                return false;
            }
            final Object this$perEmail = this.getPerEmail();
            final Object other$perEmail = other.getPerEmail();
            Label_0657: {
                if (this$perEmail == null) {
                    if (other$perEmail == null) {
                        break Label_0657;
                    }
                }
                else if (this$perEmail.equals(other$perEmail)) {
                    break Label_0657;
                }
                return false;
            }
            final Object this$perZipcode = this.getPerZipcode();
            final Object other$perZipcode = other.getPerZipcode();
            Label_0694: {
                if (this$perZipcode == null) {
                    if (other$perZipcode == null) {
                        break Label_0694;
                    }
                }
                else if (this$perZipcode.equals(other$perZipcode)) {
                    break Label_0694;
                }
                return false;
            }
            final Object this$personAddr = this.getPersonAddr();
            final Object other$personAddr = other.getPersonAddr();
            Label_0731: {
                if (this$personAddr == null) {
                    if (other$personAddr == null) {
                        break Label_0731;
                    }
                }
                else if (this$personAddr.equals(other$personAddr)) {
                    break Label_0731;
                }
                return false;
            }
            final Object this$gender = this.getGender();
            final Object other$gender = other.getGender();
            Label_0768: {
                if (this$gender == null) {
                    if (other$gender == null) {
                        break Label_0768;
                    }
                }
                else if (this$gender.equals(other$gender)) {
                    break Label_0768;
                }
                return false;
            }
            final Object this$commonRemark = this.getCommonRemark();
            final Object other$commonRemark = other.getCommonRemark();
            Label_0805: {
                if (this$commonRemark == null) {
                    if (other$commonRemark == null) {
                        break Label_0805;
                    }
                }
                else if (this$commonRemark.equals(other$commonRemark)) {
                    break Label_0805;
                }
                return false;
            }
            final Object this$certificatesId = this.getCertificatesId();
            final Object other$certificatesId = other.getCertificatesId();
            Label_0842: {
                if (this$certificatesId == null) {
                    if (other$certificatesId == null) {
                        break Label_0842;
                    }
                }
                else if (this$certificatesId.equals(other$certificatesId)) {
                    break Label_0842;
                }
                return false;
            }
            final Object this$partCategories = this.getPartCategories();
            final Object other$partCategories = other.getPartCategories();
            Label_0879: {
                if (this$partCategories == null) {
                    if (other$partCategories == null) {
                        break Label_0879;
                    }
                }
                else if (this$partCategories.equals(other$partCategories)) {
                    break Label_0879;
                }
                return false;
            }
            final Object this$partCategories2 = this.getPartCategories2();
            final Object other$partCategories2 = other.getPartCategories2();
            Label_0916: {
                if (this$partCategories2 == null) {
                    if (other$partCategories2 == null) {
                        break Label_0916;
                    }
                }
                else if (this$partCategories2.equals(other$partCategories2)) {
                    break Label_0916;
                }
                return false;
            }
            final Object this$provinceCode = this.getProvinceCode();
            final Object other$provinceCode = other.getProvinceCode();
            Label_0953: {
                if (this$provinceCode == null) {
                    if (other$provinceCode == null) {
                        break Label_0953;
                    }
                }
                else if (this$provinceCode.equals(other$provinceCode)) {
                    break Label_0953;
                }
                return false;
            }
            final Object this$cityCode = this.getCityCode();
            final Object other$cityCode = other.getCityCode();
            Label_0990: {
                if (this$cityCode == null) {
                    if (other$cityCode == null) {
                        break Label_0990;
                    }
                }
                else if (this$cityCode.equals(other$cityCode)) {
                    break Label_0990;
                }
                return false;
            }
            final Object this$districtCode = this.getDistrictCode();
            final Object other$districtCode = other.getDistrictCode();
            Label_1027: {
                if (this$districtCode == null) {
                    if (other$districtCode == null) {
                        break Label_1027;
                    }
                }
                else if (this$districtCode.equals(other$districtCode)) {
                    break Label_1027;
                }
                return false;
            }
            final Object this$userChannel = this.getUserChannel();
            final Object other$userChannel = other.getUserChannel();
            Label_1064: {
                if (this$userChannel == null) {
                    if (other$userChannel == null) {
                        break Label_1064;
                    }
                }
                else if (this$userChannel.equals(other$userChannel)) {
                    break Label_1064;
                }
                return false;
            }
            final Object this$urgentName = this.getUrgentName();
            final Object other$urgentName = other.getUrgentName();
            Label_1101: {
                if (this$urgentName == null) {
                    if (other$urgentName == null) {
                        break Label_1101;
                    }
                }
                else if (this$urgentName.equals(other$urgentName)) {
                    break Label_1101;
                }
                return false;
            }
            final Object this$urgentMobile = this.getUrgentMobile();
            final Object other$urgentMobile = other.getUrgentMobile();
            Label_1138: {
                if (this$urgentMobile == null) {
                    if (other$urgentMobile == null) {
                        break Label_1138;
                    }
                }
                else if (this$urgentMobile.equals(other$urgentMobile)) {
                    break Label_1138;
                }
                return false;
            }
            final Object this$urgentRlate = this.getUrgentRlate();
            final Object other$urgentRlate = other.getUrgentRlate();
            Label_1175: {
                if (this$urgentRlate == null) {
                    if (other$urgentRlate == null) {
                        break Label_1175;
                    }
                }
                else if (this$urgentRlate.equals(other$urgentRlate)) {
                    break Label_1175;
                }
                return false;
            }
            final Object this$urgentRegistry = this.getUrgentRegistry();
            final Object other$urgentRegistry = other.getUrgentRegistry();
            Label_1212: {
                if (this$urgentRegistry == null) {
                    if (other$urgentRegistry == null) {
                        break Label_1212;
                    }
                }
                else if (this$urgentRegistry.equals(other$urgentRegistry)) {
                    break Label_1212;
                }
                return false;
            }
            final Object this$urgentCertNo = this.getUrgentCertNo();
            final Object other$urgentCertNo = other.getUrgentCertNo();
            Label_1249: {
                if (this$urgentCertNo == null) {
                    if (other$urgentCertNo == null) {
                        break Label_1249;
                    }
                }
                else if (this$urgentCertNo.equals(other$urgentCertNo)) {
                    break Label_1249;
                }
                return false;
            }
            final Object this$urgentValidDates = this.getUrgentValidDates();
            final Object other$urgentValidDates = other.getUrgentValidDates();
            Label_1286: {
                if (this$urgentValidDates == null) {
                    if (other$urgentValidDates == null) {
                        break Label_1286;
                    }
                }
                else if (this$urgentValidDates.equals(other$urgentValidDates)) {
                    break Label_1286;
                }
                return false;
            }
            final Object this$urgentJob = this.getUrgentJob();
            final Object other$urgentJob = other.getUrgentJob();
            Label_1323: {
                if (this$urgentJob == null) {
                    if (other$urgentJob == null) {
                        break Label_1323;
                    }
                }
                else if (this$urgentJob.equals(other$urgentJob)) {
                    break Label_1323;
                }
                return false;
            }
            final Object this$urgentAddr = this.getUrgentAddr();
            final Object other$urgentAddr = other.getUrgentAddr();
            Label_1360: {
                if (this$urgentAddr == null) {
                    if (other$urgentAddr == null) {
                        break Label_1360;
                    }
                }
                else if (this$urgentAddr.equals(other$urgentAddr)) {
                    break Label_1360;
                }
                return false;
            }
            final Object this$urgentEmail = this.getUrgentEmail();
            final Object other$urgentEmail = other.getUrgentEmail();
            Label_1397: {
                if (this$urgentEmail == null) {
                    if (other$urgentEmail == null) {
                        break Label_1397;
                    }
                }
                else if (this$urgentEmail.equals(other$urgentEmail)) {
                    break Label_1397;
                }
                return false;
            }
            final Object this$optCompCode = this.getOptCompCode();
            final Object other$optCompCode = other.getOptCompCode();
            Label_1434: {
                if (this$optCompCode == null) {
                    if (other$optCompCode == null) {
                        break Label_1434;
                    }
                }
                else if (this$optCompCode.equals(other$optCompCode)) {
                    break Label_1434;
                }
                return false;
            }
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            if (this$name == null) {
                if (other$name == null) {
                    return true;
                }
            }
            else if (this$name.equals(other$name)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof UserInfoBo;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $setedPayPwd = this.getSetedPayPwd();
            result = result * 59 + (($setedPayPwd == null) ? 43 : $setedPayPwd.hashCode());
            final Object $agentPromoteCode = this.getAgentPromoteCode();
            result = result * 59 + (($agentPromoteCode == null) ? 43 : $agentPromoteCode.hashCode());
            final Object $urgentRemarks = this.getUrgentRemarks();
            result = result * 59 + (($urgentRemarks == null) ? 43 : $urgentRemarks.hashCode());
            final Object $isOperator = this.getIsOperator();
            result = result * 59 + (($isOperator == null) ? 43 : $isOperator.hashCode());
            final Object $optCode = this.getOptCode();
            result = result * 59 + (($optCode == null) ? 43 : $optCode.hashCode());
            final Object $optCompName = this.getOptCompName();
            result = result * 59 + (($optCompName == null) ? 43 : $optCompName.hashCode());
            final Object $optName = this.getOptName();
            result = result * 59 + (($optName == null) ? 43 : $optName.hashCode());
            final Object $broker = this.getBroker();
            result = result * 59 + (($broker == null) ? 43 : $broker.hashCode());
            final Object $account = this.getAccount();
            result = result * 59 + (($account == null) ? 43 : $account.hashCode());
            final Object $holderAccount = this.getHolderAccount();
            result = result * 59 + (($holderAccount == null) ? 43 : $holderAccount.hashCode());
            final Object $fundAccount = this.getFundAccount();
            result = result * 59 + (($fundAccount == null) ? 43 : $fundAccount.hashCode());
            final Object $userId = this.getUserId();
            result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
            final Object $job = this.getJob();
            result = result * 59 + (($job == null) ? 43 : $job.hashCode());
            final Object $cellPhone = this.getCellPhone();
            result = result * 59 + (($cellPhone == null) ? 43 : $cellPhone.hashCode());
            final Object $officePhone = this.getOfficePhone();
            result = result * 59 + (($officePhone == null) ? 43 : $officePhone.hashCode());
            final Object $homePhone = this.getHomePhone();
            result = result * 59 + (($homePhone == null) ? 43 : $homePhone.hashCode());
            final Object $perEmail = this.getPerEmail();
            result = result * 59 + (($perEmail == null) ? 43 : $perEmail.hashCode());
            final Object $perZipcode = this.getPerZipcode();
            result = result * 59 + (($perZipcode == null) ? 43 : $perZipcode.hashCode());
            final Object $personAddr = this.getPersonAddr();
            result = result * 59 + (($personAddr == null) ? 43 : $personAddr.hashCode());
            final Object $gender = this.getGender();
            result = result * 59 + (($gender == null) ? 43 : $gender.hashCode());
            final Object $commonRemark = this.getCommonRemark();
            result = result * 59 + (($commonRemark == null) ? 43 : $commonRemark.hashCode());
            final Object $certificatesId = this.getCertificatesId();
            result = result * 59 + (($certificatesId == null) ? 43 : $certificatesId.hashCode());
            final Object $partCategories = this.getPartCategories();
            result = result * 59 + (($partCategories == null) ? 43 : $partCategories.hashCode());
            final Object $partCategories2 = this.getPartCategories2();
            result = result * 59 + (($partCategories2 == null) ? 43 : $partCategories2.hashCode());
            final Object $provinceCode = this.getProvinceCode();
            result = result * 59 + (($provinceCode == null) ? 43 : $provinceCode.hashCode());
            final Object $cityCode = this.getCityCode();
            result = result * 59 + (($cityCode == null) ? 43 : $cityCode.hashCode());
            final Object $districtCode = this.getDistrictCode();
            result = result * 59 + (($districtCode == null) ? 43 : $districtCode.hashCode());
            final Object $userChannel = this.getUserChannel();
            result = result * 59 + (($userChannel == null) ? 43 : $userChannel.hashCode());
            final Object $urgentName = this.getUrgentName();
            result = result * 59 + (($urgentName == null) ? 43 : $urgentName.hashCode());
            final Object $urgentMobile = this.getUrgentMobile();
            result = result * 59 + (($urgentMobile == null) ? 43 : $urgentMobile.hashCode());
            final Object $urgentRlate = this.getUrgentRlate();
            result = result * 59 + (($urgentRlate == null) ? 43 : $urgentRlate.hashCode());
            final Object $urgentRegistry = this.getUrgentRegistry();
            result = result * 59 + (($urgentRegistry == null) ? 43 : $urgentRegistry.hashCode());
            final Object $urgentCertNo = this.getUrgentCertNo();
            result = result * 59 + (($urgentCertNo == null) ? 43 : $urgentCertNo.hashCode());
            final Object $urgentValidDates = this.getUrgentValidDates();
            result = result * 59 + (($urgentValidDates == null) ? 43 : $urgentValidDates.hashCode());
            final Object $urgentJob = this.getUrgentJob();
            result = result * 59 + (($urgentJob == null) ? 43 : $urgentJob.hashCode());
            final Object $urgentAddr = this.getUrgentAddr();
            result = result * 59 + (($urgentAddr == null) ? 43 : $urgentAddr.hashCode());
            final Object $urgentEmail = this.getUrgentEmail();
            result = result * 59 + (($urgentEmail == null) ? 43 : $urgentEmail.hashCode());
            final Object $optCompCode = this.getOptCompCode();
            result = result * 59 + (($optCompCode == null) ? 43 : $optCompCode.hashCode());
            final Object $name = this.getName();
            result = result * 59 + (($name == null) ? 43 : $name.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "HSUserInfoBo.UserInfoBo(setedPayPwd=" + this.getSetedPayPwd() + ", agentPromoteCode=" + this.getAgentPromoteCode() + ", urgentRemarks=" + this.getUrgentRemarks() + ", isOperator=" + this.getIsOperator() + ", optCode=" + this.getOptCode() + ", optCompName=" + this.getOptCompName() + ", optName=" + this.getOptName() + ", broker=" + this.getBroker() + ", account=" + this.getAccount() + ", holderAccount=" + this.getHolderAccount() + ", fundAccount=" + this.getFundAccount() + ", userId=" + this.getUserId() + ", job=" + this.getJob() + ", cellPhone=" + this.getCellPhone() + ", officePhone=" + this.getOfficePhone() + ", homePhone=" + this.getHomePhone() + ", perEmail=" + this.getPerEmail() + ", perZipcode=" + this.getPerZipcode() + ", personAddr=" + this.getPersonAddr() + ", gender=" + this.getGender() + ", commonRemark=" + this.getCommonRemark() + ", certificatesId=" + this.getCertificatesId() + ", partCategories=" + this.getPartCategories() + ", partCategories2=" + this.getPartCategories2() + ", provinceCode=" + this.getProvinceCode() + ", cityCode=" + this.getCityCode() + ", districtCode=" + this.getDistrictCode() + ", userChannel=" + this.getUserChannel() + ", urgentName=" + this.getUrgentName() + ", urgentMobile=" + this.getUrgentMobile() + ", urgentRlate=" + this.getUrgentRlate() + ", urgentRegistry=" + this.getUrgentRegistry() + ", urgentCertNo=" + this.getUrgentCertNo() + ", urgentValidDates=" + this.getUrgentValidDates() + ", urgentJob=" + this.getUrgentJob() + ", urgentAddr=" + this.getUrgentAddr() + ", urgentEmail=" + this.getUrgentEmail() + ", optCompCode=" + this.getOptCompCode() + ", name=" + this.getName() + ")";
        }
    }
}
