// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity.hs;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UCCompany
{
    private static Logger logger;
    private static final long serialVersionUID = 6482186748629387039L;
    private String userId;
    private String account;
    private String name;
    private String socialCode;
    private String orgEmail;
    private String orgAddress;
    private String contacts;
    private String cellPhone;
    private String legalRepr;
    private String compProperty;
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getAccount() {
        return this.account;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSocialCode() {
        return this.socialCode;
    }
    
    public String getOrgEmail() {
        return this.orgEmail;
    }
    
    public String getOrgAddress() {
        return this.orgAddress;
    }
    
    public String getContacts() {
        return this.contacts;
    }
    
    public String getCellPhone() {
        return this.cellPhone;
    }
    
    public String getLegalRepr() {
        return this.legalRepr;
    }
    
    public String getCompProperty() {
        return this.compProperty;
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
    
    public void setSocialCode(final String socialCode) {
        this.socialCode = socialCode;
    }
    
    public void setOrgEmail(final String orgEmail) {
        this.orgEmail = orgEmail;
    }
    
    public void setOrgAddress(final String orgAddress) {
        this.orgAddress = orgAddress;
    }
    
    public void setContacts(final String contacts) {
        this.contacts = contacts;
    }
    
    public void setCellPhone(final String cellPhone) {
        this.cellPhone = cellPhone;
    }
    
    public void setLegalRepr(final String legalRepr) {
        this.legalRepr = legalRepr;
    }
    
    public void setCompProperty(final String compProperty) {
        this.compProperty = compProperty;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UCCompany)) {
            return false;
        }
        final UCCompany other = (UCCompany)o;
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
        final Object this$account = this.getAccount();
        final Object other$account = other.getAccount();
        Label_0102: {
            if (this$account == null) {
                if (other$account == null) {
                    break Label_0102;
                }
            }
            else if (this$account.equals(other$account)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0139: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0139;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$socialCode = this.getSocialCode();
        final Object other$socialCode = other.getSocialCode();
        Label_0176: {
            if (this$socialCode == null) {
                if (other$socialCode == null) {
                    break Label_0176;
                }
            }
            else if (this$socialCode.equals(other$socialCode)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$orgEmail = this.getOrgEmail();
        final Object other$orgEmail = other.getOrgEmail();
        Label_0213: {
            if (this$orgEmail == null) {
                if (other$orgEmail == null) {
                    break Label_0213;
                }
            }
            else if (this$orgEmail.equals(other$orgEmail)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$orgAddress = this.getOrgAddress();
        final Object other$orgAddress = other.getOrgAddress();
        Label_0250: {
            if (this$orgAddress == null) {
                if (other$orgAddress == null) {
                    break Label_0250;
                }
            }
            else if (this$orgAddress.equals(other$orgAddress)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$contacts = this.getContacts();
        final Object other$contacts = other.getContacts();
        Label_0287: {
            if (this$contacts == null) {
                if (other$contacts == null) {
                    break Label_0287;
                }
            }
            else if (this$contacts.equals(other$contacts)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$cellPhone = this.getCellPhone();
        final Object other$cellPhone = other.getCellPhone();
        Label_0324: {
            if (this$cellPhone == null) {
                if (other$cellPhone == null) {
                    break Label_0324;
                }
            }
            else if (this$cellPhone.equals(other$cellPhone)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$legalRepr = this.getLegalRepr();
        final Object other$legalRepr = other.getLegalRepr();
        Label_0361: {
            if (this$legalRepr == null) {
                if (other$legalRepr == null) {
                    break Label_0361;
                }
            }
            else if (this$legalRepr.equals(other$legalRepr)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$compProperty = this.getCompProperty();
        final Object other$compProperty = other.getCompProperty();
        if (this$compProperty == null) {
            if (other$compProperty == null) {
                return true;
            }
        }
        else if (this$compProperty.equals(other$compProperty)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UCCompany;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $account = this.getAccount();
        result = result * 59 + (($account == null) ? 43 : $account.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $socialCode = this.getSocialCode();
        result = result * 59 + (($socialCode == null) ? 43 : $socialCode.hashCode());
        final Object $orgEmail = this.getOrgEmail();
        result = result * 59 + (($orgEmail == null) ? 43 : $orgEmail.hashCode());
        final Object $orgAddress = this.getOrgAddress();
        result = result * 59 + (($orgAddress == null) ? 43 : $orgAddress.hashCode());
        final Object $contacts = this.getContacts();
        result = result * 59 + (($contacts == null) ? 43 : $contacts.hashCode());
        final Object $cellPhone = this.getCellPhone();
        result = result * 59 + (($cellPhone == null) ? 43 : $cellPhone.hashCode());
        final Object $legalRepr = this.getLegalRepr();
        result = result * 59 + (($legalRepr == null) ? 43 : $legalRepr.hashCode());
        final Object $compProperty = this.getCompProperty();
        result = result * 59 + (($compProperty == null) ? 43 : $compProperty.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "UCCompany(userId=" + this.getUserId() + ", account=" + this.getAccount() + ", name=" + this.getName() + ", socialCode=" + this.getSocialCode() + ", orgEmail=" + this.getOrgEmail() + ", orgAddress=" + this.getOrgAddress() + ", contacts=" + this.getContacts() + ", cellPhone=" + this.getCellPhone() + ", legalRepr=" + this.getLegalRepr() + ", compProperty=" + this.getCompProperty() + ")";
    }
    
    static {
        logger = LoggerFactory.getLogger((Class)UCCompany.class);
    }
}
