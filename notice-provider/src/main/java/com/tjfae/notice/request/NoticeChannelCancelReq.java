// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel(value = "\u516c\u793a\u516c\u544a\u53d6\u6d88\u5bf9\u8c61", description = "\u516c\u793a\u516c\u544a\u53d6\u6d88\u5bf9\u8c61")
public class NoticeChannelCancelReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u6e20\u9053id")
    @NotBlank(message = "\u6e20\u9053id\u4e0d\u80fd\u4e3a\u7a7a")
    @Length(max = 32, message = "\u6e20\u9053id\u5b57\u6bb5\u8d85\u957f")
    private String channelId;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("\u516c\u544a\u65b9")
    @NotBlank(message = "\u516c\u544a\u65b9\u4e0d\u80fd\u4e3a\u7a7a")
    @Length(max = 30, message = "\u516c\u544a\u65b9\u5b57\u6bb5\u8d85\u957f")
    private String userName;
    
    public String getChannelId() {
        return this.channelId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setChannelId(final String channelId) {
        this.channelId = channelId;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticeChannelCancelReq)) {
            return false;
        }
        final NoticeChannelCancelReq other = (NoticeChannelCancelReq)o;
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
        final Object this$channelId = this.getChannelId();
        final Object other$channelId = other.getChannelId();
        Label_0102: {
            if (this$channelId == null) {
                if (other$channelId == null) {
                    break Label_0102;
                }
            }
            else if (this$channelId.equals(other$channelId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null) {
            if (other$userName == null) {
                return true;
            }
        }
        else if (this$userName.equals(other$userName)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticeChannelCancelReq;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $channelId = this.getChannelId();
        result = result * 59 + (($channelId == null) ? 43 : $channelId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NoticeChannelCancelReq(channelId=" + this.getChannelId() + ", id=" + this.getId() + ", userName=" + this.getUserName() + ")";
    }
}
