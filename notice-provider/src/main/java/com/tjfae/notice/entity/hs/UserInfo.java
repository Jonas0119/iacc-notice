// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity.hs;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UserInfo
{
    private static Logger logger;
    private boolean isOperator;
    private String socialCode;
    private String userId;
    private String userName;
    private String companyName;
    private String companyAccount;
    private String userAccount;
    
    public boolean isOperator() {
        return this.isOperator;
    }
    
    public String getSocialCode() {
        return this.socialCode;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public String getCompanyAccount() {
        return this.companyAccount;
    }
    
    public String getUserAccount() {
        return this.userAccount;
    }
    
    public void setOperator(final boolean isOperator) {
        this.isOperator = isOperator;
    }
    
    public void setSocialCode(final String socialCode) {
        this.socialCode = socialCode;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public void setCompanyName(final String companyName) {
        this.companyName = companyName;
    }
    
    public void setCompanyAccount(final String companyAccount) {
        this.companyAccount = companyAccount;
    }
    
    public void setUserAccount(final String userAccount) {
        this.userAccount = userAccount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        final UserInfo other = (UserInfo)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isOperator() != other.isOperator()) {
            return false;
        }
        final Object this$socialCode = this.getSocialCode();
        final Object other$socialCode = other.getSocialCode();
        Label_0078: {
            if (this$socialCode == null) {
                if (other$socialCode == null) {
                    break Label_0078;
                }
            }
            else if (this$socialCode.equals(other$socialCode)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        Label_0115: {
            if (this$userId == null) {
                if (other$userId == null) {
                    break Label_0115;
                }
            }
            else if (this$userId.equals(other$userId)) {
                break Label_0115;
            }
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        Label_0152: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0152;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$companyName = this.getCompanyName();
        final Object other$companyName = other.getCompanyName();
        Label_0189: {
            if (this$companyName == null) {
                if (other$companyName == null) {
                    break Label_0189;
                }
            }
            else if (this$companyName.equals(other$companyName)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$companyAccount = this.getCompanyAccount();
        final Object other$companyAccount = other.getCompanyAccount();
        Label_0226: {
            if (this$companyAccount == null) {
                if (other$companyAccount == null) {
                    break Label_0226;
                }
            }
            else if (this$companyAccount.equals(other$companyAccount)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$userAccount = this.getUserAccount();
        final Object other$userAccount = other.getUserAccount();
        if (this$userAccount == null) {
            if (other$userAccount == null) {
                return true;
            }
        }
        else if (this$userAccount.equals(other$userAccount)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UserInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isOperator() ? 79 : 97);
        final Object $socialCode = this.getSocialCode();
        result = result * 59 + (($socialCode == null) ? 43 : $socialCode.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $companyName = this.getCompanyName();
        result = result * 59 + (($companyName == null) ? 43 : $companyName.hashCode());
        final Object $companyAccount = this.getCompanyAccount();
        result = result * 59 + (($companyAccount == null) ? 43 : $companyAccount.hashCode());
        final Object $userAccount = this.getUserAccount();
        result = result * 59 + (($userAccount == null) ? 43 : $userAccount.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UserInfo(isOperator=" + this.isOperator() + ", socialCode=" + this.getSocialCode() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", companyName=" + this.getCompanyName() + ", companyAccount=" + this.getCompanyAccount() + ", userAccount=" + this.getUserAccount() + ")";
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)UserInfo.class);
    }
}
