// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.math.BigDecimal;
import com.tjfae.common.core.annotation.Excel;
import com.tjfae.common.core.web.domain.BaseEntity;

public class AssetsInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long id;
    @Excel(name = "\u9879\u76ee\u7f16\u53f7")
    private String projectCode;
    @Excel(name = "\u9879\u76ee\u540d\u79f0")
    private String projectName;
    @Excel(name = "\u4ea4\u6613\u6302\u724c\u4ef7", readConverterExp = "\u4e07=\u5143")
    private BigDecimal listingPrice;
    @Excel(name = "\u8f6c\u8ba9\u65b9\u540d\u79f0")
    private String transferName;
    @Excel(name = "\u53d7\u8ba9\u65b9\u540d\u79f0")
    private String assigneeName;
    @Excel(name = "\u9879\u76ee\u7ecf\u7406")
    private String manager;
    @Excel(name = "\u9879\u76ee\u7ecf\u7406\u90e8\u95e8")
    private String department;
    @Excel(name = "\u6302\u724c\u5f00\u59cb\u65f6\u95f4")
    private String startDate;
    @Excel(name = "\u6302\u724c\u7ed3\u675f\u65f6\u95f4")
    private String endDate;
    @Excel(name = "\u6d41\u8f6c\u72b6\u6001")
    private String flowStatus;
    @Excel(name = "\u9879\u76ee\u72b6\u6001")
    private String status;
    @Excel(name = "\u7b7e\u7ea6\u4ef7\u683c", readConverterExp = "\u4e07=\u5143")
    private BigDecimal signPrice;
    @Excel(name = "\u8d44\u4ea7\u7c7b\u578b")
    private String assetsType;
    @Excel(name = "\u8d44\u4ea7\u7c7b\u522b")
    private String assetClass;
    @Excel(name = "\u57fa\u7840\u8d44\u4ea7\u6807\u7684\u7269\u7c7b\u578b")
    private String basicsType;
    @Excel(name = "\u8d44\u4ea7\u62c5\u4fdd\u65b9\u5f0f")
    private String vouchMode;
    @Excel(name = "\u8d44\u4ea7\u6240\u5728\u7701")
    private String province;
    @Excel(name = "\u8d44\u4ea7\u6240\u5728\u5e02")
    private String city;
    @Excel(name = "\u610f\u5411\u53d7\u8ba9\u65b9\u4eba\u6570")
    private Long assigneeCount;
    @Excel(name = "\u7ade\u4ef7\u4eba\u6570")
    private Long biddingCount;
    @Excel(name = "\u5b58\u8bc1\u6587\u4ef6\u4e0a\u94feID")
    private String tradeId;
    @Excel(name = "\u5b58\u8bc1\u6587\u4ef6\u5730\u5740")
    private String filePath;
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setProjectCode(final String projectCode) {
        this.projectCode = projectCode;
    }
    
    public String getProjectCode() {
        return this.projectCode;
    }
    
    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public void setListingPrice(final BigDecimal listingPrice) {
        this.listingPrice = listingPrice;
    }
    
    public BigDecimal getListingPrice() {
        return this.listingPrice;
    }
    
    public void setTransferName(final String transferName) {
        this.transferName = transferName;
    }
    
    public String getTransferName() {
        return this.transferName;
    }
    
    public void setAssigneeName(final String assigneeName) {
        this.assigneeName = assigneeName;
    }
    
    public String getAssigneeName() {
        return this.assigneeName;
    }
    
    public void setManager(final String manager) {
        this.manager = manager;
    }
    
    public String getManager() {
        return this.manager;
    }
    
    public void setDepartment(final String department) {
        this.department = department;
    }
    
    public String getDepartment() {
        return this.department;
    }
    
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public void setEndDate(final String endDate) {
        this.endDate = endDate;
    }
    
    public String getEndDate() {
        return this.endDate;
    }
    
    public void setFlowStatus(final String flowStatus) {
        this.flowStatus = flowStatus;
    }
    
    public String getFlowStatus() {
        return this.flowStatus;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setSignPrice(final BigDecimal signPrice) {
        this.signPrice = signPrice;
    }
    
    public BigDecimal getSignPrice() {
        return this.signPrice;
    }
    
    public void setAssetsType(final String assetsType) {
        this.assetsType = assetsType;
    }
    
    public String getAssetsType() {
        return this.assetsType;
    }
    
    public void setAssetClass(final String assetClass) {
        this.assetClass = assetClass;
    }
    
    public String getAssetClass() {
        return this.assetClass;
    }
    
    public void setBasicsType(final String basicsType) {
        this.basicsType = basicsType;
    }
    
    public String getBasicsType() {
        return this.basicsType;
    }
    
    public void setVouchMode(final String vouchMode) {
        this.vouchMode = vouchMode;
    }
    
    public String getVouchMode() {
        return this.vouchMode;
    }
    
    public void setProvince(final String province) {
        this.province = province;
    }
    
    public String getProvince() {
        return this.province;
    }
    
    public void setCity(final String city) {
        this.city = city;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public void setAssigneeCount(final Long assigneeCount) {
        this.assigneeCount = assigneeCount;
    }
    
    public Long getAssigneeCount() {
        return this.assigneeCount;
    }
    
    public void setBiddingCount(final Long biddingCount) {
        this.biddingCount = biddingCount;
    }
    
    public Long getBiddingCount() {
        return this.biddingCount;
    }
    
    public void setTradeId(final String tradeId) {
        this.tradeId = tradeId;
    }
    
    public String getTradeId() {
        return this.tradeId;
    }
    
    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public String toString() {
        return new ToStringBuilder((Object)this, ToStringStyle.MULTI_LINE_STYLE).append("id", (Object)this.getId()).append("projectCode", (Object)this.getProjectCode()).append("projectName", (Object)this.getProjectName()).append("listingPrice", (Object)this.getListingPrice()).append("transferName", (Object)this.getTransferName()).append("assigneeName", (Object)this.getAssigneeName()).append("manager", (Object)this.getManager()).append("department", (Object)this.getDepartment()).append("startDate", (Object)this.getStartDate()).append("endDate", (Object)this.getEndDate()).append("flowStatus", (Object)this.getFlowStatus()).append("status", (Object)this.getStatus()).append("signPrice", (Object)this.getSignPrice()).append("assetsType", (Object)this.getAssetsType()).append("assetClass", (Object)this.getAssetClass()).append("basicsType", (Object)this.getBasicsType()).append("vouchMode", (Object)this.getVouchMode()).append("province", (Object)this.getProvince()).append("city", (Object)this.getCity()).append("assigneeCount", (Object)this.getAssigneeCount()).append("biddingCount", (Object)this.getBiddingCount()).append("tradeId", (Object)this.getTradeId()).append("filePath", (Object)this.getFilePath()).append("createTime", (Object)this.getCreateTime()).toString();
    }
}
