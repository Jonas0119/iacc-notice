// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import io.swagger.annotations.ApiModelProperty;

public class PayDto
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("\u8ba2\u5355\u8868\u4e3b\u952e")
    @NotEmpty(message = "\u8ba2\u5355id\u4e0d\u80fd\u4e3a\u7a7a")
    private String orderId;
    @ApiModelProperty("\u8ba2\u5355\u91d1\u989d")
    @NotNull(message = "\u8ba2\u5355\u91d1\u989d\u4e0d\u80fd\u4e3a\u7a7a")
    private BigDecimal orderAmount;
    @ApiModelProperty("\u8ba2\u5355\u7c7b\u578b")
    private String orderType;
    @ApiModelProperty("\u4e1a\u52a1\u7c7b\u578b")
    @NotEmpty(message = "\u4e1a\u52a1\u7c7b\u578b\u4e0d\u80fd\u4e3a\u7a7a")
    private String businessType;
    @ApiModelProperty("\u8ba2\u5355\u8be6\u60c5")
    @NotEmpty(message = "\u8ba2\u5355\u8be6\u60c5\u4e0d\u80fd\u4e3a\u7a7a")
    private String orderDesc;
    @ApiModelProperty("\u5546\u54c1\u540d\u79f0")
    @NotEmpty(message = "\u5546\u54c1\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private String productName;
    @ApiModelProperty("\u5546\u54c1\u7f16\u53f7")
    @NotEmpty(message = "\u5546\u54c1\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a")
    private String productId;
    @ApiModelProperty("\u6536\u94f6\u53f0\u786e\u8ba4\u8fd4\u56deurl")
    private String payFinishUrl;
    @ApiModelProperty("\u7528\u6237id")
    private String userId;
    
    public String getOrderId() {
        return this.orderId;
    }
    
    public BigDecimal getOrderAmount() {
        return this.orderAmount;
    }
    
    public String getOrderType() {
        return this.orderType;
    }
    
    public String getBusinessType() {
        return this.businessType;
    }
    
    public String getOrderDesc() {
        return this.orderDesc;
    }
    
    public String getProductName() {
        return this.productName;
    }
    
    public String getProductId() {
        return this.productId;
    }
    
    public String getPayFinishUrl() {
        return this.payFinishUrl;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }
    
    public void setOrderAmount(final BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
    
    public void setOrderType(final String orderType) {
        this.orderType = orderType;
    }
    
    public void setBusinessType(final String businessType) {
        this.businessType = businessType;
    }
    
    public void setOrderDesc(final String orderDesc) {
        this.orderDesc = orderDesc;
    }
    
    public void setProductName(final String productName) {
        this.productName = productName;
    }
    
    public void setProductId(final String productId) {
        this.productId = productId;
    }
    
    public void setPayFinishUrl(final String payFinishUrl) {
        this.payFinishUrl = payFinishUrl;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PayDto)) {
            return false;
        }
        final PayDto other = (PayDto)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        Label_0065: {
            if (this$orderId == null) {
                if (other$orderId == null) {
                    break Label_0065;
                }
            }
            else if (this$orderId.equals(other$orderId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$orderAmount = this.getOrderAmount();
        final Object other$orderAmount = other.getOrderAmount();
        Label_0102: {
            if (this$orderAmount == null) {
                if (other$orderAmount == null) {
                    break Label_0102;
                }
            }
            else if (this$orderAmount.equals(other$orderAmount)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$orderType = this.getOrderType();
        final Object other$orderType = other.getOrderType();
        Label_0139: {
            if (this$orderType == null) {
                if (other$orderType == null) {
                    break Label_0139;
                }
            }
            else if (this$orderType.equals(other$orderType)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$businessType = this.getBusinessType();
        final Object other$businessType = other.getBusinessType();
        Label_0176: {
            if (this$businessType == null) {
                if (other$businessType == null) {
                    break Label_0176;
                }
            }
            else if (this$businessType.equals(other$businessType)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$orderDesc = this.getOrderDesc();
        final Object other$orderDesc = other.getOrderDesc();
        Label_0213: {
            if (this$orderDesc == null) {
                if (other$orderDesc == null) {
                    break Label_0213;
                }
            }
            else if (this$orderDesc.equals(other$orderDesc)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$productName = this.getProductName();
        final Object other$productName = other.getProductName();
        Label_0250: {
            if (this$productName == null) {
                if (other$productName == null) {
                    break Label_0250;
                }
            }
            else if (this$productName.equals(other$productName)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        Label_0287: {
            if (this$productId == null) {
                if (other$productId == null) {
                    break Label_0287;
                }
            }
            else if (this$productId.equals(other$productId)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$payFinishUrl = this.getPayFinishUrl();
        final Object other$payFinishUrl = other.getPayFinishUrl();
        Label_0324: {
            if (this$payFinishUrl == null) {
                if (other$payFinishUrl == null) {
                    break Label_0324;
                }
            }
            else if (this$payFinishUrl.equals(other$payFinishUrl)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null) {
            if (other$userId == null) {
                return true;
            }
        }
        else if (this$userId.equals(other$userId)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof PayDto;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $orderId = this.getOrderId();
        result = result * 59 + (($orderId == null) ? 43 : $orderId.hashCode());
        final Object $orderAmount = this.getOrderAmount();
        result = result * 59 + (($orderAmount == null) ? 43 : $orderAmount.hashCode());
        final Object $orderType = this.getOrderType();
        result = result * 59 + (($orderType == null) ? 43 : $orderType.hashCode());
        final Object $businessType = this.getBusinessType();
        result = result * 59 + (($businessType == null) ? 43 : $businessType.hashCode());
        final Object $orderDesc = this.getOrderDesc();
        result = result * 59 + (($orderDesc == null) ? 43 : $orderDesc.hashCode());
        final Object $productName = this.getProductName();
        result = result * 59 + (($productName == null) ? 43 : $productName.hashCode());
        final Object $productId = this.getProductId();
        result = result * 59 + (($productId == null) ? 43 : $productId.hashCode());
        final Object $payFinishUrl = this.getPayFinishUrl();
        result = result * 59 + (($payFinishUrl == null) ? 43 : $payFinishUrl.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "PayDto(orderId=" + this.getOrderId() + ", orderAmount=" + this.getOrderAmount() + ", orderType=" + this.getOrderType() + ", businessType=" + this.getBusinessType() + ", orderDesc=" + this.getOrderDesc() + ", productName=" + this.getProductName() + ", productId=" + this.getProductId() + ", payFinishUrl=" + this.getPayFinishUrl() + ", userId=" + this.getUserId() + ")";
    }
}
