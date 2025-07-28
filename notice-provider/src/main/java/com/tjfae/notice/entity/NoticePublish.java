// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import com.tjfae.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.tjfae.common.core.web.domain.BaseEntity;

@ApiModel(value = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u5bf9\u8c61", description = "\u516c\u793a\u516c\u544a\u53d1\u5e03\u5bf9\u8c61")
public class NoticePublish extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("id;")
    private Long id;
    @ApiModelProperty(value = "userId", hidden = true)
    private String userId;
    @Excel(name = "\u516c\u544a\u65b9", sort = 4)
    @ApiModelProperty("\u516c\u544a\u65b9")
    private String userName;
    @Excel(name = "\u516c\u544a\u540d\u79f0", sort = 3)
    @ApiModelProperty("\u516c\u544a\u6807\u9898")
    private String title;
    @ApiModelProperty(value = "\u5b58\u8bc1\u7c7b\u578b", hidden = true)
    private String type;
    @ApiModelProperty("\u516c\u544a\u6b63\u6587")
    private String remark;
    @ApiModelProperty("\u5b58\u8bc1\u6587\u4ef6")
    private String fileUrl;
    @ApiModelProperty("\u6587\u4ef6\u540d\u79f0")
    private String fileName;
    @ApiModelProperty("\u6587\u4ef6hash")
    private String fileHash;
    @ApiModelProperty("\u521b\u5efa\u4eba")
    private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("\u521b\u5efa\u65f6\u95f4")
    private Date createTime;
    @Excel(name = "\u4e1a\u52a1\u573a\u666f", readConverterExp = "1=\u62cd\u5356", sort = 2)
    @ApiModelProperty("\u4e1a\u52a1\u573a\u666f")
    private String sceneType;
    @Excel(name = "\u516c\u544a\u7f16\u53f7", sort = 1)
    @ApiModelProperty("\u516c\u544a\u7f16\u53f7")
    private String code;
    @Excel(name = "\u6765\u6e90", sort = 5)
    @ApiModelProperty(value = "\u5ba2\u6237\u6765\u6e90", hidden = true)
    private String source;
    @ApiModelProperty(value = "\u5ba2\u6237\u6765\u6e90\u7c7b\u578b", hidden = true)
    private String sourceType;
    @ApiModelProperty("\u53d1\u5e03\u6e20\u9053")
    private String platform;
    @Excel(name = "\u516c\u544a\u94fe\u63a5", sort = 8)
    @ApiModelProperty("\u516c\u544a\u94fe\u63a5")
    private String noticeUrl;
    @ApiModelProperty("\u5b58\u8bc1hash")
    private String hash;
    @ApiModelProperty(value = "\u6570\u636e\u6765\u6e90", hidden = true)
    private String sourceTag;
    @ApiModelProperty("\u521b\u5efa\u4eba\u540d\u79f0")
    private String creatorName;
    @ApiModelProperty("\u5f00\u59cb\u65f6\u95f4")
    private String beginTime;
    @ApiModelProperty("\u7ed3\u675f\u65f6\u95f4")
    private String endTime;
    @Excel(name = "\u9879\u76ee\u7ecf\u7406", sort = 6)
    @ApiModelProperty("\u5ba2\u6237\u7ecf\u7406\u59d3\u540d")
    private String managerName;
    @ApiModelProperty("\u5ba2\u6237\u7ecf\u7406\u624b\u673a\u53f7")
    private String managerPhone;
    @ApiModelProperty("\u8ba4\u8bc1\u4e66")
    private String authUrl;
    @ApiModelProperty("\u4ed8\u6b3e\u51ed\u8bc1")
    private String payUrl;
    @ApiModelProperty("\u72b6\u6001\uff1a0\u63d0\u4ea4 1\u901a\u8fc7 2\u9a73\u56de 3\u53d1\u5e03 4\u9690\u85cf")
    private String status;
    @Excel(name = "\u5f53\u524d\u72b6\u6001", readConverterExp = "0=\u63d0\u4ea4,1=\u901a\u8fc7,2=\u9a73\u56de,3=\u53d1\u5e03,4=\u9690\u85cf", sort = 9)
    @ApiModelProperty(value = "\u72b6\u6001", hidden = true)
    private String statuses;
    @ApiModelProperty("\u5ba1\u6838\u539f\u56e0")
    private String reason;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("\u66f4\u65b0\u65f6\u95f4")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "\u53d1\u5e03\u65f6\u95f4", sort = 7, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("\u53d1\u5e03\u65f6\u95f4")
    private Date publishTime;
    @ApiModelProperty("\u6d4f\u89c8\u91cf")
    private Integer views;
    @ApiModelProperty("\u652f\u4ed8\u8ba2\u5355\u6d41\u6c34\u53f7")
    private String payOrderId;
    @ApiModelProperty("\u652f\u4ed8\u65b9\u5f0f\uff1aoffline-\u7ebf\u4e0b\u652f\u4ed8\uff0conline-\u7ebf\u4e0a\u652f\u4ed8")
    private String payType;
    @ApiModelProperty("\u652f\u4ed8\u91d1\u989d(\u5143)")
    private BigDecimal payAmount;
    @ApiModelProperty("\u662f\u5426\u4ed8\u6b3e:0-\u5f85\u4ed8\u6b3e\uff0c1-\u5df2\u4ed8\u6b3e\uff0c2-\u7b49\u5f85\u652f\u4ed8\u7ed3\u679c")
    private String payStatus;
    @ApiModelProperty("\u652f\u4ed8\u65f6\u95f4")
    private Date payTime;
    @ApiModelProperty("\u652f\u4ed8\u56de\u8c03\u65f6\u95f4")
    private Date payCallbackTime;
    
    public Long getId() {
        return this.id;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public String getFileUrl() {
        return this.fileUrl;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public String getFileHash() {
        return this.fileHash;
    }
    
    public String getCreator() {
        return this.creator;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public String getSceneType() {
        return this.sceneType;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public String getSourceType() {
        return this.sourceType;
    }
    
    public String getPlatform() {
        return this.platform;
    }
    
    public String getNoticeUrl() {
        return this.noticeUrl;
    }
    
    public String getHash() {
        return this.hash;
    }
    
    public String getSourceTag() {
        return this.sourceTag;
    }
    
    public String getCreatorName() {
        return this.creatorName;
    }
    
    public String getBeginTime() {
        return this.beginTime;
    }
    
    public String getEndTime() {
        return this.endTime;
    }
    
    public String getManagerName() {
        return this.managerName;
    }
    
    public String getManagerPhone() {
        return this.managerPhone;
    }
    
    public String getAuthUrl() {
        return this.authUrl;
    }
    
    public String getPayUrl() {
        return this.payUrl;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public String getStatuses() {
        return this.statuses;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }
    
    public Date getPublishTime() {
        return this.publishTime;
    }
    
    public Integer getViews() {
        return this.views;
    }
    
    public String getPayOrderId() {
        return this.payOrderId;
    }
    
    public String getPayType() {
        return this.payType;
    }
    
    public BigDecimal getPayAmount() {
        return this.payAmount;
    }
    
    public String getPayStatus() {
        return this.payStatus;
    }
    
    public Date getPayTime() {
        return this.payTime;
    }
    
    public Date getPayCallbackTime() {
        return this.payCallbackTime;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setUserId(final String userId) {
        this.userId = userId;
    }
    
    public void setUserName(final String userName) {
        this.userName = userName;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public void setRemark(final String remark) {
        this.remark = remark;
    }
    
    public void setFileUrl(final String fileUrl) {
        this.fileUrl = fileUrl;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileHash(final String fileHash) {
        this.fileHash = fileHash;
    }
    
    public void setCreator(final String creator) {
        this.creator = creator;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }
    
    public void setSceneType(final String sceneType) {
        this.sceneType = sceneType;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setSource(final String source) {
        this.source = source;
    }
    
    public void setSourceType(final String sourceType) {
        this.sourceType = sourceType;
    }
    
    public void setPlatform(final String platform) {
        this.platform = platform;
    }
    
    public void setNoticeUrl(final String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
    
    public void setHash(final String hash) {
        this.hash = hash;
    }
    
    public void setSourceTag(final String sourceTag) {
        this.sourceTag = sourceTag;
    }
    
    public void setCreatorName(final String creatorName) {
        this.creatorName = creatorName;
    }
    
    public void setBeginTime(final String beginTime) {
        this.beginTime = beginTime;
    }
    
    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }
    
    public void setManagerName(final String managerName) {
        this.managerName = managerName;
    }
    
    public void setManagerPhone(final String managerPhone) {
        this.managerPhone = managerPhone;
    }
    
    public void setAuthUrl(final String authUrl) {
        this.authUrl = authUrl;
    }
    
    public void setPayUrl(final String payUrl) {
        this.payUrl = payUrl;
    }
    
    public void setStatus(final String status) {
        this.status = status;
    }
    
    public void setStatuses(final String statuses) {
        this.statuses = statuses;
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    public void setPublishTime(final Date publishTime) {
        this.publishTime = publishTime;
    }
    
    public void setViews(final Integer views) {
        this.views = views;
    }
    
    public void setPayOrderId(final String payOrderId) {
        this.payOrderId = payOrderId;
    }
    
    public void setPayType(final String payType) {
        this.payType = payType;
    }
    
    public void setPayAmount(final BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
    
    public void setPayStatus(final String payStatus) {
        this.payStatus = payStatus;
    }
    
    public void setPayTime(final Date payTime) {
        this.payTime = payTime;
    }
    
    public void setPayCallbackTime(final Date payCallbackTime) {
        this.payCallbackTime = payCallbackTime;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoticePublish)) {
            return false;
        }
        final NoticePublish other = (NoticePublish)o;
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
        final Object this$views = this.getViews();
        final Object other$views = other.getViews();
        Label_0102: {
            if (this$views == null) {
                if (other$views == null) {
                    break Label_0102;
                }
            }
            else if (this$views.equals(other$views)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        Label_0139: {
            if (this$userId == null) {
                if (other$userId == null) {
                    break Label_0139;
                }
            }
            else if (this$userId.equals(other$userId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        Label_0176: {
            if (this$userName == null) {
                if (other$userName == null) {
                    break Label_0176;
                }
            }
            else if (this$userName.equals(other$userName)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0213: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0213;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0250: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0250;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$remark = this.getRemark();
        final Object other$remark = other.getRemark();
        Label_0287: {
            if (this$remark == null) {
                if (other$remark == null) {
                    break Label_0287;
                }
            }
            else if (this$remark.equals(other$remark)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$fileUrl = this.getFileUrl();
        final Object other$fileUrl = other.getFileUrl();
        Label_0324: {
            if (this$fileUrl == null) {
                if (other$fileUrl == null) {
                    break Label_0324;
                }
            }
            else if (this$fileUrl.equals(other$fileUrl)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$fileName = this.getFileName();
        final Object other$fileName = other.getFileName();
        Label_0361: {
            if (this$fileName == null) {
                if (other$fileName == null) {
                    break Label_0361;
                }
            }
            else if (this$fileName.equals(other$fileName)) {
                break Label_0361;
            }
            return false;
        }
        final Object this$fileHash = this.getFileHash();
        final Object other$fileHash = other.getFileHash();
        Label_0398: {
            if (this$fileHash == null) {
                if (other$fileHash == null) {
                    break Label_0398;
                }
            }
            else if (this$fileHash.equals(other$fileHash)) {
                break Label_0398;
            }
            return false;
        }
        final Object this$creator = this.getCreator();
        final Object other$creator = other.getCreator();
        Label_0435: {
            if (this$creator == null) {
                if (other$creator == null) {
                    break Label_0435;
                }
            }
            else if (this$creator.equals(other$creator)) {
                break Label_0435;
            }
            return false;
        }
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        Label_0472: {
            if (this$createTime == null) {
                if (other$createTime == null) {
                    break Label_0472;
                }
            }
            else if (this$createTime.equals(other$createTime)) {
                break Label_0472;
            }
            return false;
        }
        final Object this$sceneType = this.getSceneType();
        final Object other$sceneType = other.getSceneType();
        Label_0509: {
            if (this$sceneType == null) {
                if (other$sceneType == null) {
                    break Label_0509;
                }
            }
            else if (this$sceneType.equals(other$sceneType)) {
                break Label_0509;
            }
            return false;
        }
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        Label_0546: {
            if (this$code == null) {
                if (other$code == null) {
                    break Label_0546;
                }
            }
            else if (this$code.equals(other$code)) {
                break Label_0546;
            }
            return false;
        }
        final Object this$source = this.getSource();
        final Object other$source = other.getSource();
        Label_0583: {
            if (this$source == null) {
                if (other$source == null) {
                    break Label_0583;
                }
            }
            else if (this$source.equals(other$source)) {
                break Label_0583;
            }
            return false;
        }
        final Object this$sourceType = this.getSourceType();
        final Object other$sourceType = other.getSourceType();
        Label_0620: {
            if (this$sourceType == null) {
                if (other$sourceType == null) {
                    break Label_0620;
                }
            }
            else if (this$sourceType.equals(other$sourceType)) {
                break Label_0620;
            }
            return false;
        }
        final Object this$platform = this.getPlatform();
        final Object other$platform = other.getPlatform();
        Label_0657: {
            if (this$platform == null) {
                if (other$platform == null) {
                    break Label_0657;
                }
            }
            else if (this$platform.equals(other$platform)) {
                break Label_0657;
            }
            return false;
        }
        final Object this$noticeUrl = this.getNoticeUrl();
        final Object other$noticeUrl = other.getNoticeUrl();
        Label_0694: {
            if (this$noticeUrl == null) {
                if (other$noticeUrl == null) {
                    break Label_0694;
                }
            }
            else if (this$noticeUrl.equals(other$noticeUrl)) {
                break Label_0694;
            }
            return false;
        }
        final Object this$hash = this.getHash();
        final Object other$hash = other.getHash();
        Label_0731: {
            if (this$hash == null) {
                if (other$hash == null) {
                    break Label_0731;
                }
            }
            else if (this$hash.equals(other$hash)) {
                break Label_0731;
            }
            return false;
        }
        final Object this$sourceTag = this.getSourceTag();
        final Object other$sourceTag = other.getSourceTag();
        Label_0768: {
            if (this$sourceTag == null) {
                if (other$sourceTag == null) {
                    break Label_0768;
                }
            }
            else if (this$sourceTag.equals(other$sourceTag)) {
                break Label_0768;
            }
            return false;
        }
        final Object this$creatorName = this.getCreatorName();
        final Object other$creatorName = other.getCreatorName();
        Label_0805: {
            if (this$creatorName == null) {
                if (other$creatorName == null) {
                    break Label_0805;
                }
            }
            else if (this$creatorName.equals(other$creatorName)) {
                break Label_0805;
            }
            return false;
        }
        final Object this$beginTime = this.getBeginTime();
        final Object other$beginTime = other.getBeginTime();
        Label_0842: {
            if (this$beginTime == null) {
                if (other$beginTime == null) {
                    break Label_0842;
                }
            }
            else if (this$beginTime.equals(other$beginTime)) {
                break Label_0842;
            }
            return false;
        }
        final Object this$endTime = this.getEndTime();
        final Object other$endTime = other.getEndTime();
        Label_0879: {
            if (this$endTime == null) {
                if (other$endTime == null) {
                    break Label_0879;
                }
            }
            else if (this$endTime.equals(other$endTime)) {
                break Label_0879;
            }
            return false;
        }
        final Object this$managerName = this.getManagerName();
        final Object other$managerName = other.getManagerName();
        Label_0916: {
            if (this$managerName == null) {
                if (other$managerName == null) {
                    break Label_0916;
                }
            }
            else if (this$managerName.equals(other$managerName)) {
                break Label_0916;
            }
            return false;
        }
        final Object this$managerPhone = this.getManagerPhone();
        final Object other$managerPhone = other.getManagerPhone();
        Label_0953: {
            if (this$managerPhone == null) {
                if (other$managerPhone == null) {
                    break Label_0953;
                }
            }
            else if (this$managerPhone.equals(other$managerPhone)) {
                break Label_0953;
            }
            return false;
        }
        final Object this$authUrl = this.getAuthUrl();
        final Object other$authUrl = other.getAuthUrl();
        Label_0990: {
            if (this$authUrl == null) {
                if (other$authUrl == null) {
                    break Label_0990;
                }
            }
            else if (this$authUrl.equals(other$authUrl)) {
                break Label_0990;
            }
            return false;
        }
        final Object this$payUrl = this.getPayUrl();
        final Object other$payUrl = other.getPayUrl();
        Label_1027: {
            if (this$payUrl == null) {
                if (other$payUrl == null) {
                    break Label_1027;
                }
            }
            else if (this$payUrl.equals(other$payUrl)) {
                break Label_1027;
            }
            return false;
        }
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        Label_1064: {
            if (this$status == null) {
                if (other$status == null) {
                    break Label_1064;
                }
            }
            else if (this$status.equals(other$status)) {
                break Label_1064;
            }
            return false;
        }
        final Object this$statuses = this.getStatuses();
        final Object other$statuses = other.getStatuses();
        Label_1101: {
            if (this$statuses == null) {
                if (other$statuses == null) {
                    break Label_1101;
                }
            }
            else if (this$statuses.equals(other$statuses)) {
                break Label_1101;
            }
            return false;
        }
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        Label_1138: {
            if (this$reason == null) {
                if (other$reason == null) {
                    break Label_1138;
                }
            }
            else if (this$reason.equals(other$reason)) {
                break Label_1138;
            }
            return false;
        }
        final Object this$updateTime = this.getUpdateTime();
        final Object other$updateTime = other.getUpdateTime();
        Label_1175: {
            if (this$updateTime == null) {
                if (other$updateTime == null) {
                    break Label_1175;
                }
            }
            else if (this$updateTime.equals(other$updateTime)) {
                break Label_1175;
            }
            return false;
        }
        final Object this$publishTime = this.getPublishTime();
        final Object other$publishTime = other.getPublishTime();
        Label_1212: {
            if (this$publishTime == null) {
                if (other$publishTime == null) {
                    break Label_1212;
                }
            }
            else if (this$publishTime.equals(other$publishTime)) {
                break Label_1212;
            }
            return false;
        }
        final Object this$payOrderId = this.getPayOrderId();
        final Object other$payOrderId = other.getPayOrderId();
        Label_1249: {
            if (this$payOrderId == null) {
                if (other$payOrderId == null) {
                    break Label_1249;
                }
            }
            else if (this$payOrderId.equals(other$payOrderId)) {
                break Label_1249;
            }
            return false;
        }
        final Object this$payType = this.getPayType();
        final Object other$payType = other.getPayType();
        Label_1286: {
            if (this$payType == null) {
                if (other$payType == null) {
                    break Label_1286;
                }
            }
            else if (this$payType.equals(other$payType)) {
                break Label_1286;
            }
            return false;
        }
        final Object this$payAmount = this.getPayAmount();
        final Object other$payAmount = other.getPayAmount();
        Label_1323: {
            if (this$payAmount == null) {
                if (other$payAmount == null) {
                    break Label_1323;
                }
            }
            else if (this$payAmount.equals(other$payAmount)) {
                break Label_1323;
            }
            return false;
        }
        final Object this$payStatus = this.getPayStatus();
        final Object other$payStatus = other.getPayStatus();
        Label_1360: {
            if (this$payStatus == null) {
                if (other$payStatus == null) {
                    break Label_1360;
                }
            }
            else if (this$payStatus.equals(other$payStatus)) {
                break Label_1360;
            }
            return false;
        }
        final Object this$payTime = this.getPayTime();
        final Object other$payTime = other.getPayTime();
        Label_1397: {
            if (this$payTime == null) {
                if (other$payTime == null) {
                    break Label_1397;
                }
            }
            else if (this$payTime.equals(other$payTime)) {
                break Label_1397;
            }
            return false;
        }
        final Object this$payCallbackTime = this.getPayCallbackTime();
        final Object other$payCallbackTime = other.getPayCallbackTime();
        if (this$payCallbackTime == null) {
            if (other$payCallbackTime == null) {
                return true;
            }
        }
        else if (this$payCallbackTime.equals(other$payCallbackTime)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof NoticePublish;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $views = this.getViews();
        result = result * 59 + (($views == null) ? 43 : $views.hashCode());
        final Object $userId = this.getUserId();
        result = result * 59 + (($userId == null) ? 43 : $userId.hashCode());
        final Object $userName = this.getUserName();
        result = result * 59 + (($userName == null) ? 43 : $userName.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $remark = this.getRemark();
        result = result * 59 + (($remark == null) ? 43 : $remark.hashCode());
        final Object $fileUrl = this.getFileUrl();
        result = result * 59 + (($fileUrl == null) ? 43 : $fileUrl.hashCode());
        final Object $fileName = this.getFileName();
        result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
        final Object $fileHash = this.getFileHash();
        result = result * 59 + (($fileHash == null) ? 43 : $fileHash.hashCode());
        final Object $creator = this.getCreator();
        result = result * 59 + (($creator == null) ? 43 : $creator.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * 59 + (($createTime == null) ? 43 : $createTime.hashCode());
        final Object $sceneType = this.getSceneType();
        result = result * 59 + (($sceneType == null) ? 43 : $sceneType.hashCode());
        final Object $code = this.getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        final Object $source = this.getSource();
        result = result * 59 + (($source == null) ? 43 : $source.hashCode());
        final Object $sourceType = this.getSourceType();
        result = result * 59 + (($sourceType == null) ? 43 : $sourceType.hashCode());
        final Object $platform = this.getPlatform();
        result = result * 59 + (($platform == null) ? 43 : $platform.hashCode());
        final Object $noticeUrl = this.getNoticeUrl();
        result = result * 59 + (($noticeUrl == null) ? 43 : $noticeUrl.hashCode());
        final Object $hash = this.getHash();
        result = result * 59 + (($hash == null) ? 43 : $hash.hashCode());
        final Object $sourceTag = this.getSourceTag();
        result = result * 59 + (($sourceTag == null) ? 43 : $sourceTag.hashCode());
        final Object $creatorName = this.getCreatorName();
        result = result * 59 + (($creatorName == null) ? 43 : $creatorName.hashCode());
        final Object $beginTime = this.getBeginTime();
        result = result * 59 + (($beginTime == null) ? 43 : $beginTime.hashCode());
        final Object $endTime = this.getEndTime();
        result = result * 59 + (($endTime == null) ? 43 : $endTime.hashCode());
        final Object $managerName = this.getManagerName();
        result = result * 59 + (($managerName == null) ? 43 : $managerName.hashCode());
        final Object $managerPhone = this.getManagerPhone();
        result = result * 59 + (($managerPhone == null) ? 43 : $managerPhone.hashCode());
        final Object $authUrl = this.getAuthUrl();
        result = result * 59 + (($authUrl == null) ? 43 : $authUrl.hashCode());
        final Object $payUrl = this.getPayUrl();
        result = result * 59 + (($payUrl == null) ? 43 : $payUrl.hashCode());
        final Object $status = this.getStatus();
        result = result * 59 + (($status == null) ? 43 : $status.hashCode());
        final Object $statuses = this.getStatuses();
        result = result * 59 + (($statuses == null) ? 43 : $statuses.hashCode());
        final Object $reason = this.getReason();
        result = result * 59 + (($reason == null) ? 43 : $reason.hashCode());
        final Object $updateTime = this.getUpdateTime();
        result = result * 59 + (($updateTime == null) ? 43 : $updateTime.hashCode());
        final Object $publishTime = this.getPublishTime();
        result = result * 59 + (($publishTime == null) ? 43 : $publishTime.hashCode());
        final Object $payOrderId = this.getPayOrderId();
        result = result * 59 + (($payOrderId == null) ? 43 : $payOrderId.hashCode());
        final Object $payType = this.getPayType();
        result = result * 59 + (($payType == null) ? 43 : $payType.hashCode());
        final Object $payAmount = this.getPayAmount();
        result = result * 59 + (($payAmount == null) ? 43 : $payAmount.hashCode());
        final Object $payStatus = this.getPayStatus();
        result = result * 59 + (($payStatus == null) ? 43 : $payStatus.hashCode());
        final Object $payTime = this.getPayTime();
        result = result * 59 + (($payTime == null) ? 43 : $payTime.hashCode());
        final Object $payCallbackTime = this.getPayCallbackTime();
        result = result * 59 + (($payCallbackTime == null) ? 43 : $payCallbackTime.hashCode());
        return result;
    }
    
    public String toString() {
        return "NoticePublish(id=" + this.getId() + ", userId=" + this.getUserId() + ", userName=" + this.getUserName() + ", title=" + this.getTitle() + ", type=" + this.getType() + ", remark=" + this.getRemark() + ", fileUrl=" + this.getFileUrl() + ", fileName=" + this.getFileName() + ", fileHash=" + this.getFileHash() + ", creator=" + this.getCreator() + ", createTime=" + this.getCreateTime() + ", sceneType=" + this.getSceneType() + ", code=" + this.getCode() + ", source=" + this.getSource() + ", sourceType=" + this.getSourceType() + ", platform=" + this.getPlatform() + ", noticeUrl=" + this.getNoticeUrl() + ", hash=" + this.getHash() + ", sourceTag=" + this.getSourceTag() + ", creatorName=" + this.getCreatorName() + ", beginTime=" + this.getBeginTime() + ", endTime=" + this.getEndTime() + ", managerName=" + this.getManagerName() + ", managerPhone=" + this.getManagerPhone() + ", authUrl=" + this.getAuthUrl() + ", payUrl=" + this.getPayUrl() + ", status=" + this.getStatus() + ", statuses=" + this.getStatuses() + ", reason=" + this.getReason() + ", updateTime=" + this.getUpdateTime() + ", publishTime=" + this.getPublishTime() + ", views=" + this.getViews() + ", payOrderId=" + this.getPayOrderId() + ", payType=" + this.getPayType() + ", payAmount=" + this.getPayAmount() + ", payStatus=" + this.getPayStatus() + ", payTime=" + this.getPayTime() + ", payCallbackTime=" + this.getPayCallbackTime() + ")";
    }
}
