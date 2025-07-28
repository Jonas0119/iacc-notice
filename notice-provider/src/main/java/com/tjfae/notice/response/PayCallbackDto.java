// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class PayCallbackDto implements Serializable
{
    private static final long serialVersionUID = -3763837913616264721L;
    @ApiModelProperty("\u652f\u4ed8\u72b6\u6001")
    private String payStatus;
    @ApiModelProperty("\u4e1a\u52a1\u6d41\u6c34\u53f7")
    private String payId;
    
    public String getPayStatus() {
        return this.payStatus;
    }
    
    public String getPayId() {
        return this.payId;
    }
    
    public void setPayStatus(final String payStatus) {
        this.payStatus = payStatus;
    }
    
    public void setPayId(final String payId) {
        this.payId = payId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayCallbackDto)) {
            return false;
        }
        final PayCallbackDto other = (PayCallbackDto)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$payStatus = this.getPayStatus();
        final Object other$payStatus = other.getPayStatus();
        Label_0065: {
            if (this$payStatus == null) {
                if (other$payStatus == null) {
                    break Label_0065;
                }
            }
            else if (this$payStatus.equals(other$payStatus)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$payId = this.getPayId();
        final Object other$payId = other.getPayId();
        if (this$payId == null) {
            if (other$payId == null) {
                return true;
            }
        }
        else if (this$payId.equals(other$payId)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof PayCallbackDto;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $payStatus = this.getPayStatus();
        result = result * 59 + (($payStatus == null) ? 43 : $payStatus.hashCode());
        final Object $payId = this.getPayId();
        result = result * 59 + (($payId == null) ? 43 : $payId.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "PayCallbackDto(payStatus=" + this.getPayStatus() + ", payId=" + this.getPayId() + ")";
    }
}
