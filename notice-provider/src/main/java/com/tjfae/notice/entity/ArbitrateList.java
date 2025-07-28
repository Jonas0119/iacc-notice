// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ArbitrateList
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String evidence_type;
    private String evidence_no;
    private String tx_hash;
    private String arbitrate_source;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("creator", (Object)this.getCreator()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final ArbitrateList that = (ArbitrateList)o;
        return Objects.equals(this.evidence_type, that.evidence_type) && Objects.equals(this.evidence_no, that.evidence_no) && Objects.equals(this.tx_hash, that.tx_hash) && Objects.equals(this.arbitrate_source, that.arbitrate_source) && Objects.equals(this.creator, that.creator);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.evidence_type, this.evidence_no, this.tx_hash, this.arbitrate_source, this.creator);
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getEvidence_type() {
        return this.evidence_type;
    }
    
    public String getEvidence_no() {
        return this.evidence_no;
    }
    
    public String getTx_hash() {
        return this.tx_hash;
    }
    
    public String getArbitrate_source() {
        return this.arbitrate_source;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setEvidence_type(final String evidence_type) {
        this.evidence_type = evidence_type;
    }
    
    public void setEvidence_no(final String evidence_no) {
        this.evidence_no = evidence_no;
    }
    
    public void setTx_hash(final String tx_hash) {
        this.tx_hash = tx_hash;
    }
    
    public void setArbitrate_source(final String arbitrate_source) {
        this.arbitrate_source = arbitrate_source;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
}
