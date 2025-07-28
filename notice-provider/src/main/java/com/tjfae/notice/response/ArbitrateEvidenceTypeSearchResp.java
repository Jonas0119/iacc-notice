// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

public class ArbitrateEvidenceTypeSearchResp
{
    private String arbitrate_type_name;
    private String evidence_type_names;
    
    public String getArbitrate_type_name() {
        return this.arbitrate_type_name;
    }
    
    public String getEvidence_type_names() {
        return this.evidence_type_names;
    }
    
    public void setArbitrate_type_name(final String arbitrate_type_name) {
        this.arbitrate_type_name = arbitrate_type_name;
    }
    
    public void setEvidence_type_names(final String evidence_type_names) {
        this.evidence_type_names = evidence_type_names;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateEvidenceTypeSearchResp)) {
            return false;
        }
        final ArbitrateEvidenceTypeSearchResp other = (ArbitrateEvidenceTypeSearchResp)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$arbitrate_type_name = this.getArbitrate_type_name();
        final Object other$arbitrate_type_name = other.getArbitrate_type_name();
        Label_0065: {
            if (this$arbitrate_type_name == null) {
                if (other$arbitrate_type_name == null) {
                    break Label_0065;
                }
            }
            else if (this$arbitrate_type_name.equals(other$arbitrate_type_name)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$evidence_type_names = this.getEvidence_type_names();
        final Object other$evidence_type_names = other.getEvidence_type_names();
        if (this$evidence_type_names == null) {
            if (other$evidence_type_names == null) {
                return true;
            }
        }
        else if (this$evidence_type_names.equals(other$evidence_type_names)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateEvidenceTypeSearchResp;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $arbitrate_type_name = this.getArbitrate_type_name();
        result = result * 59 + (($arbitrate_type_name == null) ? 43 : $arbitrate_type_name.hashCode());
        final Object $evidence_type_names = this.getEvidence_type_names();
        result = result * 59 + (($evidence_type_names == null) ? 43 : $evidence_type_names.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ArbitrateEvidenceTypeSearchResp(arbitrate_type_name=" + this.getArbitrate_type_name() + ", evidence_type_names=" + this.getEvidence_type_names() + ")";
    }
}
