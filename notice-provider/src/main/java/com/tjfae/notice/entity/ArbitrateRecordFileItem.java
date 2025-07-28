// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ArbitrateRecordFileItem
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long record_id;
    private String evidence_digital_code;
    private String file_name;
    private String hash;
    private String file_type;
    private String file_sn;
    private String description;
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("recordId", (Object)this.record_id).append("evidenceDigitalCode", (Object)this.evidence_digital_code).append("fileName", (Object)this.file_name).append("hash", (Object)this.hash).append("desc", (Object)this.description).toString();
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Long getRecord_id() {
        return this.record_id;
    }
    
    public String getEvidence_digital_code() {
        return this.evidence_digital_code;
    }
    
    public String getFile_name() {
        return this.file_name;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public String getFile_type() {
        return this.file_type;
    }
    
    public String getFile_sn() {
        return this.file_sn;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setRecord_id(final Long record_id) {
        this.record_id = record_id;
    }
    
    public void setEvidence_digital_code(final String evidence_digital_code) {
        this.evidence_digital_code = evidence_digital_code;
    }
    
    public void setFile_name(final String file_name) {
        this.file_name = file_name;
    }
    
    public void setHash(final String hash) {
        this.hash = hash;
    }
    
    public void setFile_type(final String file_type) {
        this.file_type = file_type;
    }
    
    public void setFile_sn(final String file_sn) {
        this.file_sn = file_sn;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateRecordFileItem)) {
            return false;
        }
        final ArbitrateRecordFileItem other = (ArbitrateRecordFileItem)o;
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
        final Object this$record_id = this.getRecord_id();
        final Object other$record_id = other.getRecord_id();
        Label_0102: {
            if (this$record_id == null) {
                if (other$record_id == null) {
                    break Label_0102;
                }
            }
            else if (this$record_id.equals(other$record_id)) {
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
        final Object this$file_name = this.getFile_name();
        final Object other$file_name = other.getFile_name();
        Label_0176: {
            if (this$file_name == null) {
                if (other$file_name == null) {
                    break Label_0176;
                }
            }
            else if (this$file_name.equals(other$file_name)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$hash = this.getHash();
        final Object other$hash = other.getHash();
        Label_0213: {
            if (this$hash == null) {
                if (other$hash == null) {
                    break Label_0213;
                }
            }
            else if (this$hash.equals(other$hash)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$file_type = this.getFile_type();
        final Object other$file_type = other.getFile_type();
        Label_0250: {
            if (this$file_type == null) {
                if (other$file_type == null) {
                    break Label_0250;
                }
            }
            else if (this$file_type.equals(other$file_type)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$file_sn = this.getFile_sn();
        final Object other$file_sn = other.getFile_sn();
        Label_0287: {
            if (this$file_sn == null) {
                if (other$file_sn == null) {
                    break Label_0287;
                }
            }
            else if (this$file_sn.equals(other$file_sn)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null) {
            if (other$description == null) {
                return true;
            }
        }
        else if (this$description.equals(other$description)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateRecordFileItem;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $record_id = this.getRecord_id();
        result = result * 59 + (($record_id == null) ? 43 : $record_id.hashCode());
        final Object $evidence_digital_code = this.getEvidence_digital_code();
        result = result * 59 + (($evidence_digital_code == null) ? 43 : $evidence_digital_code.hashCode());
        final Object $file_name = this.getFile_name();
        result = result * 59 + (($file_name == null) ? 43 : $file_name.hashCode());
        final Object $hash = this.getHash();
        result = result * 59 + (($hash == null) ? 43 : $hash.hashCode());
        final Object $file_type = this.getFile_type();
        result = result * 59 + (($file_type == null) ? 43 : $file_type.hashCode());
        final Object $file_sn = this.getFile_sn();
        result = result * 59 + (($file_sn == null) ? 43 : $file_sn.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        return result;
    }
}
