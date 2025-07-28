// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ArbitrateRecord
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String evidence_digital_code;
    private String acceptance_institution;
    private String arbitrate_type_name;
    private String case_id;
    private String title;
    private String creator;
    private String business_side;
    private String user_platform;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    private Integer status;
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("acceptanceInstitution", (Object)this.acceptance_institution).append("caseId", (Object)this.case_id).append("title", (Object)this.getTitle()).append("creator", (Object)this.getCreator()).toString();
    }
    
    public ArbitrateRecord() {
        this.user_platform = "";
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getEvidence_digital_code() {
        return this.evidence_digital_code;
    }
    
    public String getAcceptance_institution() {
        return this.acceptance_institution;
    }
    
    public String getArbitrate_type_name() {
        return this.arbitrate_type_name;
    }
    
    public String getCase_id() {
        return this.case_id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public String getBusiness_side() {
        return this.business_side;
    }
    
    public String getUser_platform() {
        return this.user_platform;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setEvidence_digital_code(final String evidence_digital_code) {
        this.evidence_digital_code = evidence_digital_code;
    }
    
    public void setAcceptance_institution(final String acceptance_institution) {
        this.acceptance_institution = acceptance_institution;
    }
    
    public void setArbitrate_type_name(final String arbitrate_type_name) {
        this.arbitrate_type_name = arbitrate_type_name;
    }
    
    public void setCase_id(final String case_id) {
        this.case_id = case_id;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    public void setBusiness_side(final String business_side) {
        this.business_side = business_side;
    }
    
    public void setUser_platform(final String user_platform) {
        this.user_platform = user_platform;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateRecord)) {
            return false;
        }
        final ArbitrateRecord other = (ArbitrateRecord)o;
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
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_0102: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_0102;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$evidence_digital_code = this.getEvidence_digital_code();
        final Object other$evidence_digital_code = other.getEvidence_digital_code();
        Label_0139: {
            if (this$evidence_digital_code == null) {
                if (other$evidence_digital_code == null) {
                    break Label_0139;
                }
            }
            else if (this$evidence_digital_code.equals(other$evidence_digital_code)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$acceptance_institution = this.getAcceptance_institution();
        final Object other$acceptance_institution = other.getAcceptance_institution();
        Label_0176: {
            if (this$acceptance_institution == null) {
                if (other$acceptance_institution == null) {
                    break Label_0176;
                }
            }
            else if (this$acceptance_institution.equals(other$acceptance_institution)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$arbitrate_type_name = this.getArbitrate_type_name();
        final Object other$arbitrate_type_name = other.getArbitrate_type_name();
        Label_0213: {
            if (this$arbitrate_type_name == null) {
                if (other$arbitrate_type_name == null) {
                    break Label_0213;
                }
            }
            else if (this$arbitrate_type_name.equals(other$arbitrate_type_name)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$case_id = this.getCase_id();
        final Object other$case_id = other.getCase_id();
        Label_0250: {
            if (this$case_id == null) {
                if (other$case_id == null) {
                    break Label_0250;
                }
            }
            else if (this$case_id.equals(other$case_id)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0287: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0287;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0324: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0324;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$business_side = this.getBusiness_side();
        final Object other$business_side = other.getBusiness_side();
        Label_0361: {
            if (this$business_side == null) {
                if (other$business_side == null) {
                    break Label_0361;
                }
            }
            else if (this$business_side.equals(other$business_side)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$user_platform = this.getUser_platform();
        final Object other$user_platform = other.getUser_platform();
        Label_0398: {
            if (this$user_platform == null) {
                if (other$user_platform == null) {
                    break Label_0398;
                }
            }
            else if (this$user_platform.equals(other$user_platform)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$create_time = this.getCreate_time();
        final Object other$create_time = other.getCreate_time();
        if (this$create_time == null) {
            if (other$create_time == null) {
                return true;
            }
        }
        else if (this$create_time.equals(other$create_time)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateRecord;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $evidence_digital_code = this.getEvidence_digital_code();
        result = result * 59 + (($evidence_digital_code == null) ? 43 : $evidence_digital_code.hashCode());
        final Object $acceptance_institution = this.getAcceptance_institution();
        result = result * 59 + (($acceptance_institution == null) ? 43 : $acceptance_institution.hashCode());
        final Object $arbitrate_type_name = this.getArbitrate_type_name();
        result = result * 59 + (($arbitrate_type_name == null) ? 43 : $arbitrate_type_name.hashCode());
        final Object $case_id = this.getCase_id();
        result = result * 59 + (($case_id == null) ? 43 : $case_id.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $business_side = this.getBusiness_side();
        result = result * 59 + (($business_side == null) ? 43 : $business_side.hashCode());
        final Object $user_platform = this.getUser_platform();
        result = result * 59 + (($user_platform == null) ? 43 : $user_platform.hashCode());
        final Object $create_time = this.getCreate_time();
        result = result * 59 + (($create_time == null) ? 43 : $create_time.hashCode());
        return result;
    }
}
