// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import com.tjfae.notice.entity.EvidenceType;
import java.util.List;

public class AddArbitrateEvidenceTypeReq
{
    private Long id;
    private String arbitrate_type_name;
    private List<EvidenceType> evidence_types;
    
    public Long getId() {
        return this.id;
    }
    
    public String getArbitrate_type_name() {
        return this.arbitrate_type_name;
    }
    
    public List<EvidenceType> getEvidence_types() {
        return this.evidence_types;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setArbitrate_type_name(final String arbitrate_type_name) {
        this.arbitrate_type_name = arbitrate_type_name;
    }
    
    public void setEvidence_types(final List<EvidenceType> evidence_types) {
        this.evidence_types = evidence_types;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AddArbitrateEvidenceTypeReq)) {
            return false;
        }
        final AddArbitrateEvidenceTypeReq other = (AddArbitrateEvidenceTypeReq)o;
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
        final Object this$arbitrate_type_name = this.getArbitrate_type_name();
        final Object other$arbitrate_type_name = other.getArbitrate_type_name();
        Label_0102: {
            if (this$arbitrate_type_name == null) {
                if (other$arbitrate_type_name == null) {
                    break Label_0102;
                }
            }
            else if (this$arbitrate_type_name.equals(other$arbitrate_type_name)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$evidence_types = this.getEvidence_types();
        final Object other$evidence_types = other.getEvidence_types();
        if (this$evidence_types == null) {
            if (other$evidence_types == null) {
                return true;
            }
        }
        else if (this$evidence_types.equals(other$evidence_types)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof AddArbitrateEvidenceTypeReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $arbitrate_type_name = this.getArbitrate_type_name();
        result = result * 59 + (($arbitrate_type_name == null) ? 43 : $arbitrate_type_name.hashCode());
        final Object $evidence_types = this.getEvidence_types();
        result = result * 59 + (($evidence_types == null) ? 43 : $evidence_types.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "AddArbitrateEvidenceTypeReq(id=" + this.getId() + ", arbitrate_type_name=" + this.getArbitrate_type_name() + ", evidence_types=" + this.getEvidence_types() + ")";
    }
}
