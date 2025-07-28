// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EvidenceNews
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String tx_hash;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    private String remark;
    private String type;
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("creator", (Object)this.getCreator()).toString();
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getTx_hash() {
        return this.tx_hash;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setTx_hash(final String tx_hash) {
        this.tx_hash = tx_hash;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceNews)) {
            return false;
        }
        final EvidenceNews other = (EvidenceNews)o;
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
        final Object this$tx_hash = this.getTx_hash();
        final Object other$tx_hash = other.getTx_hash();
        Label_0102: {
            if (this$tx_hash == null) {
                if (other$tx_hash == null) {
                    break Label_0102;
                }
            }
            else if (this$tx_hash.equals(other$tx_hash)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0139: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0139;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$create_time = this.getCreate_time();
        final Object other$create_time = other.getCreate_time();
        Label_0176: {
            if (this$create_time == null) {
                if (other$create_time == null) {
                    break Label_0176;
                }
            }
            else if (this$create_time.equals(other$create_time)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0213: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0213;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0213;
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
        return other instanceof EvidenceNews;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $tx_hash = this.getTx_hash();
        result = result * 59 + (($tx_hash == null) ? 43 : $tx_hash.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $create_time = this.getCreate_time();
        result = result * 59 + (($create_time == null) ? 43 : $create_time.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        return result;
    }
}
