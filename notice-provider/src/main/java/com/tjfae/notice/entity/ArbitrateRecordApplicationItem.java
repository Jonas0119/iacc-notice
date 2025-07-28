// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;

public class ArbitrateRecordApplicationItem
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long record_id;
    private String evidence_digital_code;
    private String image_name;
    private String image_url;
    
    public void setImage_url(final String url) {
        this.image_url = url;
        if (StringUtils.isNotBlank((CharSequence)this.image_url)) {
            final String temp = this.image_url.substring(this.image_url.lastIndexOf("/") + 1);
            if (StringUtils.isBlank((CharSequence)temp)) {
                return;
            }
            final String[] tempArray = temp.split("\\?");
            if (tempArray.length > 0) {
                this.image_name = tempArray[0];
            }
        }
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("recordId", (Object)this.record_id).append("evidenceDigitalCode", (Object)this.evidence_digital_code).append("imageName", (Object)this.image_name).append("imageUrl", (Object)this.image_url).toString();
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
    
    public String getImage_name() {
        return this.image_name;
    }
    
    public String getImage_url() {
        return this.image_url;
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
    
    public void setImage_name(final String image_name) {
        this.image_name = image_name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateRecordApplicationItem)) {
            return false;
        }
        final ArbitrateRecordApplicationItem other = (ArbitrateRecordApplicationItem)o;
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
        final Object this$image_name = this.getImage_name();
        final Object other$image_name = other.getImage_name();
        Label_0176: {
            if (this$image_name == null) {
                if (other$image_name == null) {
                    break Label_0176;
                }
            }
            else if (this$image_name.equals(other$image_name)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$image_url = this.getImage_url();
        final Object other$image_url = other.getImage_url();
        if (this$image_url == null) {
            if (other$image_url == null) {
                return true;
            }
        }
        else if (this$image_url.equals(other$image_url)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateRecordApplicationItem;
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
        final Object $image_name = this.getImage_name();
        result = result * 59 + (($image_name == null) ? 43 : $image_name.hashCode());
        final Object $image_url = this.getImage_url();
        result = result * 59 + (($image_url == null) ? 43 : $image_url.hashCode());
        return result;
    }
}
