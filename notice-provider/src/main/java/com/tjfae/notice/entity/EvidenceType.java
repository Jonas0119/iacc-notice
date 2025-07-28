// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tjfae.system.api.model.LoginUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class EvidenceType
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    private String last_modifier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date last_modify_time;
    
    public EvidenceType() {
        this.create_time = new Date();
        this.last_modify_time = this.create_time;
    }
    
    public EvidenceType(final LoginUser loginUser, final String name) {
        this();
        this.creator = loginUser.getUsername();
        this.last_modifier = this.creator;
        this.name = name;
    }
    
    public void update(final LoginUser loginUser, final String name) {
        this.name = name;
        this.last_modifier = loginUser.getUsername();
        this.last_modify_time = new Date();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("name", (Object)this.getName()).append("creator", (Object)this.getCreator()).toString();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final EvidenceType that = (EvidenceType)o;
        return Objects.equals(this.name, that.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
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
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
    
    public void setLast_modifier(final String last_modifier) {
        this.last_modifier = last_modifier;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setLast_modify_time(final Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }
}
