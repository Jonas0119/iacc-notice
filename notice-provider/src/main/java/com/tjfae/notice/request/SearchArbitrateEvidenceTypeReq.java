// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

public class SearchArbitrateEvidenceTypeReq
{
    private String evidence_type_name;
    private String arbitrate_type_name;
    
    public String getEvidence_type_name() {
        return this.evidence_type_name;
    }
    
    public String getArbitrate_type_name() {
        return this.arbitrate_type_name;
    }
    
    public void setEvidence_type_name(final String evidence_type_name) {
        this.evidence_type_name = evidence_type_name;
    }
    
    public void setArbitrate_type_name(final String arbitrate_type_name) {
        this.arbitrate_type_name = arbitrate_type_name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SearchArbitrateEvidenceTypeReq)) {
            return false;
        }
        final SearchArbitrateEvidenceTypeReq other = (SearchArbitrateEvidenceTypeReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$evidence_type_name = this.getEvidence_type_name();
        final Object other$evidence_type_name = other.getEvidence_type_name();
        Label_0065: {
            if (this$evidence_type_name == null) {
                if (other$evidence_type_name == null) {
                    break Label_0065;
                }
            }
            else if (this$evidence_type_name.equals(other$evidence_type_name)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$arbitrate_type_name = this.getArbitrate_type_name();
        final Object other$arbitrate_type_name = other.getArbitrate_type_name();
        if (this$arbitrate_type_name == null) {
            if (other$arbitrate_type_name == null) {
                return true;
            }
        }
        else if (this$arbitrate_type_name.equals(other$arbitrate_type_name)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof SearchArbitrateEvidenceTypeReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $evidence_type_name = this.getEvidence_type_name();
        result = result * 59 + (($evidence_type_name == null) ? 43 : $evidence_type_name.hashCode());
        final Object $arbitrate_type_name = this.getArbitrate_type_name();
        result = result * 59 + (($arbitrate_type_name == null) ? 43 : $arbitrate_type_name.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "SearchArbitrateEvidenceTypeReq(evidence_type_name=" + this.getEvidence_type_name() + ", arbitrate_type_name=" + this.getArbitrate_type_name() + ")";
    }
}
