// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tjfae.system.api.model.LoginUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class ArbitrateType
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String creator;
    private String last_modifier;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date last_modify_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    
    public ArbitrateType() {
        this.create_time = new Date();
        this.last_modify_time = this.create_time;
    }
    
    public ArbitrateType(final LoginUser loginUser, final String name) {
        this();
        this.name = name;
        this.creator = loginUser.getUsername();
        this.last_modifier = this.creator;
    }
    
    public void update(final LoginUser loginUser, final String name) {
        this.last_modifier = loginUser.getUsername();
        this.name = name;
        this.last_modify_time = new Date();
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("name", (Object)this.getName()).append("creator", (Object)this.getCreator()).toString();
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
    
    public String getLast_modifier() {
        return this.last_modifier;
    }
    
    public Date getLast_modify_time() {
        return this.last_modify_time;
    }
    
    public Date getCreate_time() {
        return this.create_time;
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
    
    public void setLast_modifier(final String last_modifier) {
        this.last_modifier = last_modifier;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setLast_modify_time(final Date last_modify_time) {
        this.last_modify_time = last_modify_time;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateType)) {
            return false;
        }
        final ArbitrateType other = (ArbitrateType)o;
        if (!other.canEqual((Object)this)) {
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
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0102: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0102;
                }
            }
            else if (this$name.equals(other$name)) {
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
        final Object this$last_modifier = this.getLast_modifier();
        final Object other$last_modifier = other.getLast_modifier();
        Label_0176: {
            if (this$last_modifier == null) {
                if (other$last_modifier == null) {
                    break Label_0176;
                }
            }
            else if (this$last_modifier.equals(other$last_modifier)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$last_modify_time = this.getLast_modify_time();
        final Object other$last_modify_time = other.getLast_modify_time();
        Label_0213: {
            if (this$last_modify_time == null) {
                if (other$last_modify_time == null) {
                    break Label_0213;
                }
            }
            else if (this$last_modify_time.equals(other$last_modify_time)) {
                break Label_0213;
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
        return other instanceof ArbitrateType;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $last_modifier = this.getLast_modifier();
        result = result * 59 + (($last_modifier == null) ? 43 : $last_modifier.hashCode());
        final Object $last_modify_time = this.getLast_modify_time();
        result = result * 59 + (($last_modify_time == null) ? 43 : $last_modify_time.hashCode());
        final Object $create_time = this.getCreate_time();
        result = result * 59 + (($create_time == null) ? 43 : $create_time.hashCode());
        return result;
    }
}
