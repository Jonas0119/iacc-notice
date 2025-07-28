// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel(value = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u6e20\u9053\u63a8\u9001\u5bf9\u8c61", description = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u6e20\u9053\u63a8\u9001\u5bf9\u8c61")
public class NoticeChannelPushReq implements Serializable
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
    @ApiModelProperty("\u516c\u544a\u6807\u9898")
    @NotBlank(message = "\u516c\u544a\u6807\u9898\u4e0d\u80fd\u4e3a\u7a7a")
    @Length(max = 50, message = "\u516c\u544a\u6807\u9898\u5b57\u6bb5\u8d85\u957f")
    private String title;
    @ApiModelProperty("\u516c\u544a\u6b63\u6587")
    @NotBlank(message = "\u516c\u544a\u6b63\u6587\u4e0d\u80fd\u4e3a\u7a7a")
    private String remark;
    @ApiModelProperty("\u8ba4\u520a\u4e66")
    @NotBlank(message = "\u8ba4\u520a\u4e66\u4e0d\u80fd\u4e3a\u7a7a")
    @Length(max = 1000, message = "\u8ba4\u520a\u4e66\u5b57\u6bb5\u8d85\u957f")
    private String authUrl;
    @ApiModelProperty("\u4ed8\u6b3e\u51ed\u8bc1")
    @NotBlank(message = "\u4ed8\u6b3e\u51ed\u8bc1\u4e0d\u80fd\u4e3a\u7a7a")
    @Length(max = 1000, message = "\u4ed8\u6b3e\u51ed\u8bc1\u6bb5\u8d85\u957f")
    private String payUrl;
    
    public String getChannelId() {
        return this.channelId;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public String getAuthUrl() {
        return this.authUrl;
    }
    
    public String getPayUrl() {
        return this.payUrl;
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
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setAuthUrl(final String authUrl) {
        this.authUrl = authUrl;
    }
    
    public void setPayUrl(final String payUrl) {
        this.payUrl = payUrl;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticeChannelPushReq)) {
            return false;
        }
        final NoticeChannelPushReq other = (NoticeChannelPushReq)o;
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
        Label_0139: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0139;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0176: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0176;
                }
            }
            else if (this$title.equals(other$title)) {
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
        final Object this$authUrl = this.getAuthUrl();
        final Object other$authUrl = other.getAuthUrl();
        Label_0250: {
            if (this$authUrl == null) {
                if (other$authUrl == null) {
                    break Label_0250;
                }
            }
            else if (this$authUrl.equals(other$authUrl)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$payUrl = this.getPayUrl();
        final Object other$payUrl = other.getPayUrl();
        if (this$payUrl == null) {
            if (other$payUrl == null) {
                return true;
            }
        }
        else if (this$payUrl.equals(other$payUrl)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticeChannelPushReq;
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
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $authUrl = this.getAuthUrl();
        result = result * 59 + (($authUrl == null) ? 43 : $authUrl.hashCode());
        final Object $payUrl = this.getPayUrl();
        result = result * 59 + (($payUrl == null) ? 43 : $payUrl.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "NoticeChannelPushReq(channelId=" + this.getChannelId() + ", id=" + this.getId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", remark=" + this.getRemark() + ", authUrl=" + this.getAuthUrl() + ", payUrl=" + this.getPayUrl() + ")";
    }
}
