// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.base.Global;
import com.tjfae.notice.entity.ArbitrateRecord;

public class ArbitrateRecordQueryResp
{
    private Long id;
    private String title;
    private String caseId;
    private String arbitralAgencyName;
    private String evidenceId;
    private String arbitrateTypeName;
    private String applyTime;
    private String type;
    
    public void update(final ArbitrateRecord arbitrateRecord) {
        this.id = arbitrateRecord.getId();
        this.title = arbitrateRecord.getTitle();
        this.caseId = arbitrateRecord.getCase_id();
        this.arbitralAgencyName = arbitrateRecord.getAcceptance_institution();
        this.evidenceId = arbitrateRecord.getEvidence_digital_code();
        this.arbitrateTypeName = arbitrateRecord.getArbitrate_type_name();
        if (null != arbitrateRecord.getCreate_time()) {
            this.applyTime = Global.DateFullFormat.format(arbitrateRecord.getCreate_time());
        }
        if (StringUtils.isNotBlank(arbitrateRecord.getBusiness_side())) {
            if (arbitrateRecord.getBusiness_side().equals(arbitrateRecord.getCreator())) {
                this.type = "\u4e2a\u4eba";
            }
            else {
                this.type = "\u673a\u6784";
            }
        }
        else {
            this.type = "\u4e2a\u4eba";
        }
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getCaseId() {
        return this.caseId;
    }
    
    public String getArbitralAgencyName() {
        return this.arbitralAgencyName;
    }
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getArbitrateTypeName() {
        return this.arbitrateTypeName;
    }
    
    public String getApplyTime() {
        return this.applyTime;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setCaseId(final String caseId) {
        this.caseId = caseId;
    }
    
    public void setArbitralAgencyName(final String arbitralAgencyName) {
        this.arbitralAgencyName = arbitralAgencyName;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setArbitrateTypeName(final String arbitrateTypeName) {
        this.arbitrateTypeName = arbitrateTypeName;
    }
    
    public void setApplyTime(final String applyTime) {
        this.applyTime = applyTime;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateRecordQueryResp)) {
            return false;
        }
        final ArbitrateRecordQueryResp other = (ArbitrateRecordQueryResp)o;
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
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0102: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0102;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$caseId = this.getCaseId();
        final Object other$caseId = other.getCaseId();
        Label_0139: {
            if (this$caseId == null) {
                if (other$caseId == null) {
                    break Label_0139;
                }
            }
            else if (this$caseId.equals(other$caseId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$arbitralAgencyName = this.getArbitralAgencyName();
        final Object other$arbitralAgencyName = other.getArbitralAgencyName();
        Label_0176: {
            if (this$arbitralAgencyName == null) {
                if (other$arbitralAgencyName == null) {
                    break Label_0176;
                }
            }
            else if (this$arbitralAgencyName.equals(other$arbitralAgencyName)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0213: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0213;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$arbitrateTypeName = this.getArbitrateTypeName();
        final Object other$arbitrateTypeName = other.getArbitrateTypeName();
        Label_0250: {
            if (this$arbitrateTypeName == null) {
                if (other$arbitrateTypeName == null) {
                    break Label_0250;
                }
            }
            else if (this$arbitrateTypeName.equals(other$arbitrateTypeName)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$applyTime = this.getApplyTime();
        final Object other$applyTime = other.getApplyTime();
        Label_0287: {
            if (this$applyTime == null) {
                if (other$applyTime == null) {
                    break Label_0287;
                }
            }
            else if (this$applyTime.equals(other$applyTime)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null) {
            if (other$type == null) {
                return true;
            }
        }
        else if (this$type.equals(other$type)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateRecordQueryResp;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $caseId = this.getCaseId();
        result = result * 59 + (($caseId == null) ? 43 : $caseId.hashCode());
        final Object $arbitralAgencyName = this.getArbitralAgencyName();
        result = result * 59 + (($arbitralAgencyName == null) ? 43 : $arbitralAgencyName.hashCode());
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $arbitrateTypeName = this.getArbitrateTypeName();
        result = result * 59 + (($arbitrateTypeName == null) ? 43 : $arbitrateTypeName.hashCode());
        final Object $applyTime = this.getApplyTime();
        result = result * 59 + (($applyTime == null) ? 43 : $applyTime.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ArbitrateRecordQueryResp(id=" + this.getId() + ", title=" + this.getTitle() + ", caseId=" + this.getCaseId() + ", arbitralAgencyName=" + this.getArbitralAgencyName() + ", evidenceId=" + this.getEvidenceId() + ", arbitrateTypeName=" + this.getArbitrateTypeName() + ", applyTime=" + this.getApplyTime() + ", type=" + this.getType() + ")";
    }
}
