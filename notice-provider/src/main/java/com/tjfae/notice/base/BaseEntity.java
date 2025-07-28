// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class BaseEntity
{
    protected String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date create_time;
    protected boolean is_delete;
    protected String last_modifier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date last_modify_time;
    
    public BaseEntity() {
        this.create_time = new Date();
    }
    
    public BaseEntity(final String creator) {
        this.creator = creator;
        this.create_time = new Date();
    }
    
    public void init(final String creator) {
        this.creator = creator;
        this.create_time = new Date();
        this.update(creator);
    }
    
    public void update(final String lastModifier) {
        this.last_modify_time = new Date();
        this.last_modifier = lastModifier;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("creator", (Object)this.getCreator()).append("createTime", (Object)this.getCreate_time()).append("lastModifier", (Object)this.getLast_modifier()).append("lastModifyTime", (Object)this.getLast_modify_time()).append("delete", this.is_delete()).toString();
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreate_time() {
        return this.create_time;
    }
    
    public boolean is_delete() {
        return this.is_delete;
    }
    
    public String getLast_modifier() {
        return this.last_modifier;
    }
    
    public Date getLast_modify_time() {
        return this.last_modify_time;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
    
    public void set_delete(final boolean is_delete) {
        this.is_delete = is_delete;
    }
    
    public void setLast_modifier(final String last_modifier) {
        this.last_modifier = last_modifier;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setLast_modify_time(final Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        final BaseEntity other = (BaseEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.is_delete() != other.is_delete()) {
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0078: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0078;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0078;
            }
            return false;
        }
        final Object this$create_time = this.getCreate_time();
        final Object other$create_time = other.getCreate_time();
        Label_0115: {
            if (this$create_time == null) {
                if (other$create_time == null) {
                    break Label_0115;
                }
            }
            else if (this$create_time.equals(other$create_time)) {
                break Label_0115;
            }
            return false;
        }
        final Object this$last_modifier = this.getLast_modifier();
        final Object other$last_modifier = other.getLast_modifier();
        Label_0152: {
            if (this$last_modifier == null) {
                if (other$last_modifier == null) {
                    break Label_0152;
                }
            }
            else if (this$last_modifier.equals(other$last_modifier)) {
                break Label_0152;
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
        return other instanceof BaseEntity;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.is_delete() ? 79 : 97);
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
}
