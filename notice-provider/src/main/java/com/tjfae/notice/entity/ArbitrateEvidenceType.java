// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import com.tjfae.system.api.model.LoginUser;
import java.util.Date;

public class ArbitrateEvidenceType
{
    private Long id;
    private String arbitrate_type_name;
    private String evidence_type_name;
    private String creator;
    private Date create_time;
    private String last_modifier;
    private Date last_modify_time;
    private boolean required;
    
    public ArbitrateEvidenceType() {
        this.required = true;
        this.create_time = new Date();
        this.last_modify_time = this.create_time;
    }
    
    public ArbitrateEvidenceType(final LoginUser loginUser) {
        this();
        this.creator = loginUser.getUsername();
        this.last_modifier = this.creator;
    }
    
    public ArbitrateEvidenceType(final LoginUser loginUser, final ArbitrateType at, final EvidenceType et) {
        this(loginUser);
        this.arbitrate_type_name = at.getName();
        this.evidence_type_name = et.getName();
    }
    
    public void update(final LoginUser loginUser, final ArbitrateType at, final EvidenceType et) {
        this.last_modifier = loginUser.getUsername();
        this.last_modify_time = new Date();
        this.arbitrate_type_name = at.getName();
        this.evidence_type_name = et.getName();
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getArbitrate_type_name() {
        return this.arbitrate_type_name;
    }
    
    public String getEvidence_type_name() {
        return this.evidence_type_name;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }
    
    public String getLast_modifier() {
        return this.last_modifier;
    }
    
    public Date getLast_modify_time() {
        return this.last_modify_time;
    }
    
    public boolean isRequired() {
        return this.required;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setArbitrate_type_name(final String arbitrate_type_name) {
        this.arbitrate_type_name = arbitrate_type_name;
    }
    
    public void setEvidence_type_name(final String evidence_type_name) {
        this.evidence_type_name = evidence_type_name;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
    
    public void setLast_modifier(final String last_modifier) {
        this.last_modifier = last_modifier;
    }
    
    public void setLast_modify_time(final Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }
    
    public void setRequired(final boolean required) {
        this.required = required;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateEvidenceType)) {
            return false;
        }
        final ArbitrateEvidenceType other = (ArbitrateEvidenceType)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.isRequired() != other.isRequired()) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0078: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0078;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$arbitrate_type_name = this.getArbitrate_type_name();
        final Object other$arbitrate_type_name = other.getArbitrate_type_name();
        Label_0115: {
            if (this$arbitrate_type_name == null) {
                if (other$arbitrate_type_name == null) {
                    break Label_0115;
                }
            }
            else if (this$arbitrate_type_name.equals(other$arbitrate_type_name)) {
                break Label_0115;
            }
            return false;
        }
        final Object this$evidence_type_name = this.getEvidence_type_name();
        final Object other$evidence_type_name = other.getEvidence_type_name();
        Label_0152: {
            if (this$evidence_type_name == null) {
                if (other$evidence_type_name == null) {
                    break Label_0152;
                }
            }
            else if (this$evidence_type_name.equals(other$evidence_type_name)) {
                break Label_0152;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0189: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0189;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0189;
            }
            return false;
        }
        final Object this$create_time = this.getCreate_time();
        final Object other$create_time = other.getCreate_time();
        Label_0226: {
            if (this$create_time == null) {
                if (other$create_time == null) {
                    break Label_0226;
                }
            }
            else if (this$create_time.equals(other$create_time)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$last_modifier = this.getLast_modifier();
        final Object other$last_modifier = other.getLast_modifier();
        Label_0263: {
            if (this$last_modifier == null) {
                if (other$last_modifier == null) {
                    break Label_0263;
                }
            }
            else if (this$last_modifier.equals(other$last_modifier)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$last_modify_time = this.getLast_modify_time();
        final Object other$last_modify_time = other.getLast_modify_time();
        if (this$last_modify_time == null) {
            if (other$last_modify_time == null) {
                return true;
            }
        }
        else if (this$last_modify_time.equals(other$last_modify_time)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateEvidenceType;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.isRequired() ? 79 : 97);
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $arbitrate_type_name = this.getArbitrate_type_name();
        result = result * 59 + (($arbitrate_type_name == null) ? 43 : $arbitrate_type_name.hashCode());
        final Object $evidence_type_name = this.getEvidence_type_name();
        result = result * 59 + (($evidence_type_name == null) ? 43 : $evidence_type_name.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $create_time = this.getCreate_time();
        result = result * 59 + (($create_time == null) ? 43 : $create_time.hashCode());
        final Object $last_modifier = this.getLast_modifier();
        result = result * 59 + (($last_modifier == null) ? 43 : $last_modifier.hashCode());
        final Object $last_modify_time = this.getLast_modify_time();
        result = result * 59 + (($last_modify_time == null) ? 43 : $last_modify_time.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ArbitrateEvidenceType(id=" + this.getId() + ", arbitrate_type_name=" + this.getArbitrate_type_name() + ", evidence_type_name=" + this.getEvidence_type_name() + ", creator=" + this.getCreator() + ", create_time=" + this.getCreate_time() + ", last_modifier=" + this.getLast_modifier() + ", last_modify_time=" + this.getLast_modify_time() + ", required=" + this.isRequired() + ")";
    }
}
