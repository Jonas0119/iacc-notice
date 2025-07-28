// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

public class CommonReq
{
    protected UserInfoReq user;
    private String userPlatform;
    private String searchType;
    private String loginAccount;
    
    public CommonReq() {
        this.searchType = "SELF";
        this.loginAccount = "";
    }
    
    public UserInfoReq getUser() {
        return this.user;
    }
    
    public String getUserPlatform() {
        return this.userPlatform;
    }
    
    public String getSearchType() {
        return this.searchType;
    }
    
    public String getLoginAccount() {
        return this.loginAccount;
    }
    
    public void setUser(final UserInfoReq user) {
        this.user = user;
    }
    
    public void setUserPlatform(final String userPlatform) {
        this.userPlatform = userPlatform;
    }
    
    public void setSearchType(final String searchType) {
        this.searchType = searchType;
    }
    
    public void setLoginAccount(final String loginAccount) {
        this.loginAccount = loginAccount;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CommonReq)) {
            return false;
        }
        final CommonReq other = (CommonReq)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        Label_0065: {
            if (this$user == null) {
                if (other$user == null) {
                    break Label_0065;
                }
            }
            else if (this$user.equals(other$user)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$userPlatform = this.getUserPlatform();
        final Object other$userPlatform = other.getUserPlatform();
        Label_0102: {
            if (this$userPlatform == null) {
                if (other$userPlatform == null) {
                    break Label_0102;
                }
            }
            else if (this$userPlatform.equals(other$userPlatform)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$searchType = this.getSearchType();
        final Object other$searchType = other.getSearchType();
        Label_0139: {
            if (this$searchType == null) {
                if (other$searchType == null) {
                    break Label_0139;
                }
            }
            else if (this$searchType.equals(other$searchType)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$loginAccount = this.getLoginAccount();
        final Object other$loginAccount = other.getLoginAccount();
        if (this$loginAccount == null) {
            if (other$loginAccount == null) {
                return true;
            }
        }
        else if (this$loginAccount.equals(other$loginAccount)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof CommonReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $user = this.getUser();
        result = result * 59 + (($user == null) ? 43 : $user.hashCode());
        final Object $userPlatform = this.getUserPlatform();
        result = result * 59 + (($userPlatform == null) ? 43 : $userPlatform.hashCode());
        final Object $searchType = this.getSearchType();
        result = result * 59 + (($searchType == null) ? 43 : $searchType.hashCode());
        final Object $loginAccount = this.getLoginAccount();
        result = result * 59 + (($loginAccount == null) ? 43 : $loginAccount.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "CommonReq(user=" + this.getUser() + ", userPlatform=" + this.getUserPlatform() + ", searchType=" + this.getSearchType() + ", loginAccount=" + this.getLoginAccount() + ")";
    }
    
    public static class UserInfoReq
    {
        private String userId;
        private String identity;
        private String name;
        private String accountType;
        
        public UserInfoReq() {
            this.identity = "";
            this.name = "";
            this.accountType = "";
        }
        
        public String getUserId() {
            return this.userId;
        }
        
        public String getIdentity() {
            return this.identity;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getAccountType() {
            return this.accountType;
        }
        
        public void setUserId(final String userId) {
            this.userId = userId;
        }
        
        public void setIdentity(final String identity) {
            this.identity = identity;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        public void setAccountType(final String accountType) {
            this.accountType = accountType;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof UserInfoReq)) {
                return false;
            }
            final UserInfoReq other = (UserInfoReq)o;
            if (!other.canEqual((Object)this)) {
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
            final Object this$identity = this.getIdentity();
            final Object other$identity = other.getIdentity();
            Label_0102: {
                if (this$identity == null) {
                    if (other$identity == null) {
                        break Label_0102;
                    }
                }
                else if (this$identity.equals(other$identity)) {
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
            final Object this$accountType = this.getAccountType();
            final Object other$accountType = other.getAccountType();
            if (this$accountType == null) {
                if (other$accountType == null) {
                    return true;
                }
            }
            else if (this$accountType.equals(other$accountType)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof UserInfoReq;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $userId = this.getUserId();
            result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
            final Object $identity = this.getIdentity();
            result = result * 59 + (($identity == null) ? 43 : $identity.hashCode());
            final Object $name = this.getName();
            result = result * 59 + (($name == null) ? 43 : $name.hashCode());
            final Object $accountType = this.getAccountType();
            result = result * 59 + (($accountType == null) ? 43 : $accountType.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "CommonReq.UserInfoReq(userId=" + this.getUserId() + ", identity=" + this.getIdentity() + ", name=" + this.getName() + ", accountType=" + this.getAccountType() + ")";
        }
    }
}
