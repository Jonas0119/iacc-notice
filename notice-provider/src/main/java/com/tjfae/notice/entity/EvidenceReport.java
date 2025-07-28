// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.ArrayList;
import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.utils.string.Format;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;

public class EvidenceReport
{
    private static Logger logger;
    private String description;
    private String evidenceId;
    private String buildTime;
    private Integer totalCount;
    private String startDate;
    private String latestDate;
    private Map<String, List<Info>> infoMap;
    private String guide;
    private String evidenceStruct;
    
    public EvidenceReport() {
        this.description = "";
        this.evidenceId = "";
        this.buildTime = "";
        this.totalCount = 0;
        this.startDate = "";
        this.latestDate = "";
        this.infoMap = new HashMap();
        this.guide = "\u7edf\u4e00\u6838\u9a8c\u6307\u5357";
        this.evidenceStruct = "";
    }
    
    public EvidenceReport(final EvidenceHistory evidenceHistory) {
        this.description = "";
        this.evidenceId = "";
        this.buildTime = "";
        this.totalCount = 0;
        this.startDate = "";
        this.latestDate = "";
        this.infoMap = new HashMap();
        this.guide = "\u7edf\u4e00\u6838\u9a8c\u6307\u5357";
        this.evidenceStruct = "";
        this.description = evidenceHistory.getDescription();
        this.evidenceId = evidenceHistory.getEvidenceId();
        this.buildTime = Format.formatDate(new Date());
        this.startDate = evidenceHistory.getStartDate();
        this.latestDate = evidenceHistory.getLatestDate();
        final List<EvidenceHistory.EvidenceInfo> evidenceInfos = evidenceHistory.getHistoryDataObj();
        for (final EvidenceHistory.EvidenceInfo e : evidenceInfos) {
            if ("file".equals(e.getType())) {
                final String key = e.getFileEvidenceData().getType();
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                final Info info = new Info(e);
                if (this.infoMap.containsKey(key)) {
                    this.infoMap.get(key).add(info);
                }
                else {
                    this.evidenceStruct = this.evidenceStruct + key + "+";
                    final List<Info> infos = new ArrayList<Info>();
                    infos.add(info);
                    this.infoMap.put(key, infos);
                }
            }
        }
        EvidenceReport.logger.info("\u5b58\u8bc1\u62a5\u544a\u6587\u4ef6\u5185\u5bb9\uff1a{}" + this.evidenceStruct);
        if (StringUtils.isNotBlank(this.evidenceStruct)) {
            this.evidenceStruct = this.evidenceStruct.substring(0, this.evidenceStruct.length() - 1);
        }
        for (final Map.Entry<String, List<Info>> entry : this.infoMap.entrySet()) {
            this.totalCount += entry.getValue().size();
        }
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getBuildTime() {
        return this.buildTime;
    }
    
    public Integer getTotalCount() {
        return this.totalCount;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public String getLatestDate() {
        return this.latestDate;
    }
    
    public Map<String, List<Info>> getInfoMap() {
        return this.infoMap;
    }
    
    public String getGuide() {
        return this.guide;
    }
    
    public String getEvidenceStruct() {
        return this.evidenceStruct;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setBuildTime(final String buildTime) {
        this.buildTime = buildTime;
    }
    
    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }
    
    public void setLatestDate(final String latestDate) {
        this.latestDate = latestDate;
    }
    
    public void setInfoMap(final Map<String, List<Info>> infoMap) {
        this.infoMap = infoMap;
    }
    
    public void setGuide(final String guide) {
        this.guide = guide;
    }
    
    public void setEvidenceStruct(final String evidenceStruct) {
        this.evidenceStruct = evidenceStruct;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceReport)) {
            return false;
        }
        final EvidenceReport other = (EvidenceReport)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$totalCount = this.getTotalCount();
        final Object other$totalCount = other.getTotalCount();
        Label_0065: {
            if (this$totalCount == null) {
                if (other$totalCount == null) {
                    break Label_0065;
                }
            }
            else if (this$totalCount.equals(other$totalCount)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        Label_0102: {
            if (this$description == null) {
                if (other$description == null) {
                    break Label_0102;
                }
            }
            else if (this$description.equals(other$description)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0139: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0139;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$buildTime = this.getBuildTime();
        final Object other$buildTime = other.getBuildTime();
        Label_0176: {
            if (this$buildTime == null) {
                if (other$buildTime == null) {
                    break Label_0176;
                }
            }
            else if (this$buildTime.equals(other$buildTime)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        Label_0213: {
            if (this$startDate == null) {
                if (other$startDate == null) {
                    break Label_0213;
                }
            }
            else if (this$startDate.equals(other$startDate)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$latestDate = this.getLatestDate();
        final Object other$latestDate = other.getLatestDate();
        Label_0250: {
            if (this$latestDate == null) {
                if (other$latestDate == null) {
                    break Label_0250;
                }
            }
            else if (this$latestDate.equals(other$latestDate)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$infoMap = this.getInfoMap();
        final Object other$infoMap = other.getInfoMap();
        Label_0287: {
            if (this$infoMap == null) {
                if (other$infoMap == null) {
                    break Label_0287;
                }
            }
            else if (this$infoMap.equals(other$infoMap)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$guide = this.getGuide();
        final Object other$guide = other.getGuide();
        Label_0324: {
            if (this$guide == null) {
                if (other$guide == null) {
                    break Label_0324;
                }
            }
            else if (this$guide.equals(other$guide)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$evidenceStruct = this.getEvidenceStruct();
        final Object other$evidenceStruct = other.getEvidenceStruct();
        if (this$evidenceStruct == null) {
            if (other$evidenceStruct == null) {
                return true;
            }
        }
        else if (this$evidenceStruct.equals(other$evidenceStruct)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceReport;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $totalCount = this.getTotalCount();
        result = result * 59 + (($totalCount == null) ? 43 : $totalCount.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $buildTime = this.getBuildTime();
        result = result * 59 + (($buildTime == null) ? 43 : $buildTime.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * 59 + (($startDate == null) ? 43 : $startDate.hashCode());
        final Object $latestDate = this.getLatestDate();
        result = result * 59 + (($latestDate == null) ? 43 : $latestDate.hashCode());
        final Object $infoMap = this.getInfoMap();
        result = result * 59 + (($infoMap == null) ? 43 : $infoMap.hashCode());
        final Object $guide = this.getGuide();
        result = result * 59 + (($guide == null) ? 43 : $guide.hashCode());
        final Object $evidenceStruct = this.getEvidenceStruct();
        result = result * 59 + (($evidenceStruct == null) ? 43 : $evidenceStruct.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceReport(description=" + this.getDescription() + ", evidenceId=" + this.getEvidenceId() + ", buildTime=" + this.getBuildTime() + ", totalCount=" + this.getTotalCount() + ", startDate=" + this.getStartDate() + ", latestDate=" + this.getLatestDate() + ", infoMap=" + this.getInfoMap() + ", guide=" + this.getGuide() + ", evidenceStruct=" + this.getEvidenceStruct() + ")";
    }
    
    static {
        EvidenceReport.logger = LoggerFactory.getLogger((Class)EvidenceReport.class);
    }
    
    public static class Info
    {
        private String txId;
        private String date;
        private String description;
        private String type;
        private String fileHash;
        private String url;
        private String fileName;
        private String sn;
        private String fileDescription;
        
        public Info() {
            this.type = "";
            this.fileHash = "";
            this.url = "";
            this.fileName = "";
            this.sn = "";
            this.fileDescription = "";
        }
        
        public Info(final EvidenceHistory.EvidenceInfo e) {
            this.type = "";
            this.fileHash = "";
            this.url = "";
            this.fileName = "";
            this.sn = "";
            this.fileDescription = "";
            this.description = e.getDescription();
            this.txId = e.getTxId();
            this.date = e.getDate();
            this.type = e.getFileEvidenceData().getType();
            this.fileHash = e.getFileEvidenceData().getHash();
            this.url = e.getFileEvidenceData().getUrl();
            this.fileName = e.getFileEvidenceData().getName();
            this.sn = e.getFileEvidenceData().getSn();
            this.fileDescription = e.getFileEvidenceData().getDescription();
        }
        
        public String getTxId() {
            return this.txId;
        }
        
        public String getDate() {
            return this.date;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public String getType() {
            return this.type;
        }
        
        public String getFileHash() {
            return this.fileHash;
        }
        
        public String getUrl() {
            return this.url;
        }
        
        public String getFileName() {
            return this.fileName;
        }
        
        public String getSn() {
            return this.sn;
        }
        
        public String getFileDescription() {
            return this.fileDescription;
        }
        
        public void setTxId(final String txId) {
            this.txId = txId;
        }
        
        public void setDate(final String date) {
            this.date = date;
        }
        
        public void setDescription(final String description) {
            this.description = description;
        }
        
        public void setType(final String type) {
            this.type = type;
        }
        
        public void setFileHash(final String fileHash) {
            this.fileHash = fileHash;
        }
        
        public void setUrl(final String url) {
            this.url = url;
        }
        
        public void setFileName(final String fileName) {
            this.fileName = fileName;
        }
        
        public void setSn(final String sn) {
            this.sn = sn;
        }
        
        public void setFileDescription(final String fileDescription) {
            this.fileDescription = fileDescription;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Info)) {
                return false;
            }
            final Info other = (Info)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$txId = this.getTxId();
            final Object other$txId = other.getTxId();
            Label_0065: {
                if (this$txId == null) {
                    if (other$txId == null) {
                        break Label_0065;
                    }
                }
                else if (this$txId.equals(other$txId)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$date = this.getDate();
            final Object other$date = other.getDate();
            Label_0102: {
                if (this$date == null) {
                    if (other$date == null) {
                        break Label_0102;
                    }
                }
                else if (this$date.equals(other$date)) {
                    break Label_0102;
                }
                return false;
            }
            final Object this$description = this.getDescription();
            final Object other$description = other.getDescription();
            Label_0139: {
                if (this$description == null) {
                    if (other$description == null) {
                        break Label_0139;
                    }
                }
                else if (this$description.equals(other$description)) {
                    break Label_0139;
                }
                return false;
            }
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            Label_0176: {
                if (this$type == null) {
                    if (other$type == null) {
                        break Label_0176;
                    }
                }
                else if (this$type.equals(other$type)) {
                    break Label_0176;
                }
                return false;
            }
            final Object this$fileHash = this.getFileHash();
            final Object other$fileHash = other.getFileHash();
            Label_0213: {
                if (this$fileHash == null) {
                    if (other$fileHash == null) {
                        break Label_0213;
                    }
                }
                else if (this$fileHash.equals(other$fileHash)) {
                    break Label_0213;
                }
                return false;
            }
            final Object this$url = this.getUrl();
            final Object other$url = other.getUrl();
            Label_0250: {
                if (this$url == null) {
                    if (other$url == null) {
                        break Label_0250;
                    }
                }
                else if (this$url.equals(other$url)) {
                    break Label_0250;
                }
                return false;
            }
            final Object this$fileName = this.getFileName();
            final Object other$fileName = other.getFileName();
            Label_0287: {
                if (this$fileName == null) {
                    if (other$fileName == null) {
                        break Label_0287;
                    }
                }
                else if (this$fileName.equals(other$fileName)) {
                    break Label_0287;
                }
                return false;
            }
            final Object this$sn = this.getSn();
            final Object other$sn = other.getSn();
            Label_0324: {
                if (this$sn == null) {
                    if (other$sn == null) {
                        break Label_0324;
                    }
                }
                else if (this$sn.equals(other$sn)) {
                    break Label_0324;
                }
                return false;
            }
            final Object this$fileDescription = this.getFileDescription();
            final Object other$fileDescription = other.getFileDescription();
            if (this$fileDescription == null) {
                if (other$fileDescription == null) {
                    return true;
                }
            }
            else if (this$fileDescription.equals(other$fileDescription)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof Info;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $txId = this.getTxId();
            result = result * 59 + (($txId == null) ? 43 : $txId.hashCode());
            final Object $date = this.getDate();
            result = result * 59 + (($date == null) ? 43 : $date.hashCode());
            final Object $description = this.getDescription();
            result = result * 59 + (($description == null) ? 43 : $description.hashCode());
            final Object $type = this.getType();
            result = result * 59 + (($type == null) ? 43 : $type.hashCode());
            final Object $fileHash = this.getFileHash();
            result = result * 59 + (($fileHash == null) ? 43 : $fileHash.hashCode());
            final Object $url = this.getUrl();
            result = result * 59 + (($url == null) ? 43 : $url.hashCode());
            final Object $fileName = this.getFileName();
            result = result * 59 + (($fileName == null) ? 43 : $fileName.hashCode());
            final Object $sn = this.getSn();
            result = result * 59 + (($sn == null) ? 43 : $sn.hashCode());
            final Object $fileDescription = this.getFileDescription();
            result = result * 59 + (($fileDescription == null) ? 43 : $fileDescription.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "EvidenceReport.Info(txId=" + this.getTxId() + ", date=" + this.getDate() + ", description=" + this.getDescription() + ", type=" + this.getType() + ", fileHash=" + this.getFileHash() + ", url=" + this.getUrl() + ", fileName=" + this.getFileName() + ", sn=" + this.getSn() + ", fileDescription=" + this.getFileDescription() + ")";
        }
    }
}
