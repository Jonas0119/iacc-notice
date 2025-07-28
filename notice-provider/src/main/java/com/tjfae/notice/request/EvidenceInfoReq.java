// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

public class EvidenceInfoReq
{
    private static final long serialVersionUID = 1L;
    private String type;
    private String desc;
    private String evidenceId;
    private String businessType;
    
    public String getType() {
        return this.type;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getBusinessType() {
        return this.businessType;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setBusinessType(final String businessType) {
        this.businessType = businessType;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceInfoReq)) {
            return false;
        }
        final EvidenceInfoReq other = (EvidenceInfoReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0065: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0065;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$desc = this.getDesc();
        final Object other$desc = other.getDesc();
        Label_0102: {
            if (this$desc == null) {
                if (other$desc == null) {
                    break Label_0102;
                }
            }
            else if (this$desc.equals(other$desc)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0139: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0139;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$businessType = this.getBusinessType();
        final Object other$businessType = other.getBusinessType();
        if (this$businessType == null) {
            if (other$businessType == null) {
                return true;
            }
        }
        else if (this$businessType.equals(other$businessType)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceInfoReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $desc = this.getDesc();
        result = result * 59 + (($desc == null) ? 43 : $desc.hashCode());
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $businessType = this.getBusinessType();
        result = result * 59 + (($businessType == null) ? 43 : $businessType.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceInfoReq(type=" + this.getType() + ", desc=" + this.getDesc() + ", evidenceId=" + this.getEvidenceId() + ", businessType=" + this.getBusinessType() + ")";
    }
}
