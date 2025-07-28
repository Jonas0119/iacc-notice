// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

public class EvidenceHistoryQueryReq
{
    private String userPlatform;
    private String evidenceId;
    private String toMail;
    
    public EvidenceHistoryQueryReq() {
        this.userPlatform = "";
    }
    
    public String getUserPlatform() {
        return this.userPlatform;
    }
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getToMail() {
        return this.toMail;
    }
    
    public void setUserPlatform(final String userPlatform) {
        this.userPlatform = userPlatform;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setToMail(final String toMail) {
        this.toMail = toMail;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceHistoryQueryReq)) {
            return false;
        }
        final EvidenceHistoryQueryReq other = (EvidenceHistoryQueryReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$userPlatform = this.getUserPlatform();
        final Object other$userPlatform = other.getUserPlatform();
        Label_0065: {
            if (this$userPlatform == null) {
                if (other$userPlatform == null) {
                    break Label_0065;
                }
            }
            else if (this$userPlatform.equals(other$userPlatform)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0102: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0102;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$toMail = this.getToMail();
        final Object other$toMail = other.getToMail();
        if (this$toMail == null) {
            if (other$toMail == null) {
                return true;
            }
        }
        else if (this$toMail.equals(other$toMail)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceHistoryQueryReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userPlatform = this.getUserPlatform();
        result = result * 59 + (($userPlatform == null) ? 43 : $userPlatform.hashCode());
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $toMail = this.getToMail();
        result = result * 59 + (($toMail == null) ? 43 : $toMail.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceHistoryQueryReq(userPlatform=" + this.getUserPlatform() + ", evidenceId=" + this.getEvidenceId() + ", toMail=" + this.getToMail() + ")";
    }
}
