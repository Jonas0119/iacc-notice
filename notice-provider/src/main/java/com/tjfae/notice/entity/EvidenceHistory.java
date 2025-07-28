// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.slf4j.LoggerFactory;
import java.util.Iterator;
import com.tjfae.notice.utils.json.JsonUtils;
import java.util.ArrayList;
import org.springframework.data.annotation.Transient;
import java.util.List;
import org.slf4j.Logger;

public class EvidenceHistory
{
    private static Logger logger;
    private String evidenceId;
    private String description;
    private String startDate;
    private String latestDate;
    private Object historyData;
    @Transient
    private List<EvidenceInfo> historyDataObj;
    
    public EvidenceHistory() {
        this.historyDataObj = new ArrayList();
    }
    
    public void deserialization() {
        if (this.historyData != null) {
            try {
                this.historyDataObj = JsonUtils.json2List(JsonUtils.writeEntityJSON(this.historyData), (Class)EvidenceInfo.class);
                for (final EvidenceInfo one : this.historyDataObj) {
                    one.deserialization();
                }
            }
            catch (final Exception e) {
                e.printStackTrace();
                EvidenceHistory.logger.error(e.getMessage());
            }
        }
    }
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getStartDate() {
        return this.startDate;
    }
    
    public String getLatestDate() {
        return this.latestDate;
    }
    
    public Object getHistoryData() {
        return this.historyData;
    }
    
    public List<EvidenceInfo> getHistoryDataObj() {
        return this.historyDataObj;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setStartDate(final String startDate) {
        this.startDate = startDate;
    }
    
    public void setLatestDate(final String latestDate) {
        this.latestDate = latestDate;
    }
    
    public void setHistoryData(final Object historyData) {
        this.historyData = historyData;
    }
    
    public void setHistoryDataObj(final List<EvidenceInfo> historyDataObj) {
        this.historyDataObj = historyDataObj;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceHistory)) {
            return false;
        }
        final EvidenceHistory other = (EvidenceHistory)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0065: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0065;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
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
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        Label_0139: {
            if (this$startDate == null) {
                if (other$startDate == null) {
                    break Label_0139;
                }
            }
            else if (this$startDate.equals(other$startDate)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$latestDate = this.getLatestDate();
        final Object other$latestDate = other.getLatestDate();
        Label_0176: {
            if (this$latestDate == null) {
                if (other$latestDate == null) {
                    break Label_0176;
                }
            }
            else if (this$latestDate.equals(other$latestDate)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$historyData = this.getHistoryData();
        final Object other$historyData = other.getHistoryData();
        Label_0213: {
            if (this$historyData == null) {
                if (other$historyData == null) {
                    break Label_0213;
                }
            }
            else if (this$historyData.equals(other$historyData)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$historyDataObj = this.getHistoryDataObj();
        final Object other$historyDataObj = other.getHistoryDataObj();
        if (this$historyDataObj == null) {
            if (other$historyDataObj == null) {
                return true;
            }
        }
        else if (this$historyDataObj.equals(other$historyDataObj)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceHistory;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * 59 + (($startDate == null) ? 43 : $startDate.hashCode());
        final Object $latestDate = this.getLatestDate();
        result = result * 59 + (($latestDate == null) ? 43 : $latestDate.hashCode());
        final Object $historyData = this.getHistoryData();
        result = result * 59 + (($historyData == null) ? 43 : $historyData.hashCode());
        final Object $historyDataObj = this.getHistoryDataObj();
        result = result * 59 + (($historyDataObj == null) ? 43 : $historyDataObj.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceHistory(evidenceId=" + this.getEvidenceId() + ", description=" + this.getDescription() + ", startDate=" + this.getStartDate() + ", latestDate=" + this.getLatestDate() + ", historyData=" + this.getHistoryData() + ", historyDataObj=" + this.getHistoryDataObj() + ")";
    }
    
    static {
        EvidenceHistory.logger = LoggerFactory.getLogger((Class)EvidenceHistory.class);
    }
    
    public static class EvidenceInfo
    {
        private String txId;
        private String date;
        private String type;
        private Object data;
        private String description;
        @Transient
        private FileEvidenceData fileEvidenceData;
        
        public void deserialization() {
            if ("file".equals(this.type) && this.data != null) {
                try {
                    this.fileEvidenceData = (FileEvidenceData)JsonUtils.jsonStr2Object((String)this.data, (Class)FileEvidenceData.class);
                }
                catch (final Exception e) {
                    e.printStackTrace();
                    EvidenceHistory.logger.error(e.getMessage());
                }
            }
        }
        
        public String getTxId() {
            return this.txId;
        }
        
        public String getDate() {
            return this.date;
        }
        
        public String getType() {
            return this.type;
        }
        
        public Object getData() {
            return this.data;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public FileEvidenceData getFileEvidenceData() {
            return this.fileEvidenceData;
        }
        
        public void setTxId(final String txId) {
            this.txId = txId;
        }
        
        public void setDate(final String date) {
            this.date = date;
        }
        
        public void setType(final String type) {
            this.type = type;
        }
        
        public void setData(final Object data) {
            this.data = data;
        }
        
        public void setDescription(final String description) {
            this.description = description;
        }
        
        public void setFileEvidenceData(final FileEvidenceData fileEvidenceData) {
            this.fileEvidenceData = fileEvidenceData;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EvidenceInfo)) {
                return false;
            }
            final EvidenceInfo other = (EvidenceInfo)o;
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
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            Label_0139: {
                if (this$type == null) {
                    if (other$type == null) {
                        break Label_0139;
                    }
                }
                else if (this$type.equals(other$type)) {
                    break Label_0139;
                }
                return false;
            }
            final Object this$data = this.getData();
            final Object other$data = other.getData();
            Label_0176: {
                if (this$data == null) {
                    if (other$data == null) {
                        break Label_0176;
                    }
                }
                else if (this$data.equals(other$data)) {
                    break Label_0176;
                }
                return false;
            }
            final Object this$description = this.getDescription();
            final Object other$description = other.getDescription();
            Label_0213: {
                if (this$description == null) {
                    if (other$description == null) {
                        break Label_0213;
                    }
                }
                else if (this$description.equals(other$description)) {
                    break Label_0213;
                }
                return false;
            }
            final Object this$fileEvidenceData = this.getFileEvidenceData();
            final Object other$fileEvidenceData = other.getFileEvidenceData();
            if (this$fileEvidenceData == null) {
                if (other$fileEvidenceData == null) {
                    return true;
                }
            }
            else if (this$fileEvidenceData.equals(other$fileEvidenceData)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof EvidenceInfo;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $txId = this.getTxId();
            result = result * 59 + (($txId == null) ? 43 : $txId.hashCode());
            final Object $date = this.getDate();
            result = result * 59 + (($date == null) ? 43 : $date.hashCode());
            final Object $type = this.getType();
            result = result * 59 + (($type == null) ? 43 : $type.hashCode());
            final Object $data = this.getData();
            result = result * 59 + (($data == null) ? 43 : $data.hashCode());
            final Object $description = this.getDescription();
            result = result * 59 + (($description == null) ? 43 : $description.hashCode());
            final Object $fileEvidenceData = this.getFileEvidenceData();
            result = result * 59 + (($fileEvidenceData == null) ? 43 : $fileEvidenceData.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "EvidenceHistory.EvidenceInfo(txId=" + this.getTxId() + ", date=" + this.getDate() + ", type=" + this.getType() + ", data=" + this.getData() + ", description=" + this.getDescription() + ", fileEvidenceData=" + this.getFileEvidenceData() + ")";
        }
    }
    
    public static class FileEvidenceData
    {
        private String type;
        private String hash;
        private String url;
        private String name;
        private String sn;
        private String description;
        
        public String getType() {
            return this.type;
        }
        
        public String getHash() {
            return this.hash;
        }
        
        public String getUrl() {
            return this.url;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getSn() {
            return this.sn;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public void setType(final String type) {
            this.type = type;
        }
        
        public void setHash(final String hash) {
            this.hash = hash;
        }
        
        public void setUrl(final String url) {
            this.url = url;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        public void setSn(final String sn) {
            this.sn = sn;
        }
        
        public void setDescription(final String description) {
            this.description = description;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FileEvidenceData)) {
                return false;
            }
            final FileEvidenceData other = (FileEvidenceData)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$type = this.getType();
            final Object other$type = other.getType();
            Label_0065: {
                if (this$type == null) {
                    if (other$type == null) {
                        break Label_0065;
                    }
                }
                else if (this$type.equals(other$type)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$hash = this.getHash();
            final Object other$hash = other.getHash();
            Label_0102: {
                if (this$hash == null) {
                    if (other$hash == null) {
                        break Label_0102;
                    }
                }
                else if (this$hash.equals(other$hash)) {
                    break Label_0102;
                }
                return false;
            }
            final Object this$url = this.getUrl();
            final Object other$url = other.getUrl();
            Label_0139: {
                if (this$url == null) {
                    if (other$url == null) {
                        break Label_0139;
                    }
                }
                else if (this$url.equals(other$url)) {
                    break Label_0139;
                }
                return false;
            }
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            Label_0176: {
                if (this$name == null) {
                    if (other$name == null) {
                        break Label_0176;
                    }
                }
                else if (this$name.equals(other$name)) {
                    break Label_0176;
                }
                return false;
            }
            final Object this$sn = this.getSn();
            final Object other$sn = other.getSn();
            Label_0213: {
                if (this$sn == null) {
                    if (other$sn == null) {
                        break Label_0213;
                    }
                }
                else if (this$sn.equals(other$sn)) {
                    break Label_0213;
                }
                return false;
            }
            final Object this$description = this.getDescription();
            final Object other$description = other.getDescription();
            if (this$description == null) {
                if (other$description == null) {
                    return true;
                }
            }
            else if (this$description.equals(other$description)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof FileEvidenceData;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $type = this.getType();
            result = result * 59 + (($type == null) ? 43 : $type.hashCode());
            final Object $hash = this.getHash();
            result = result * 59 + (($hash == null) ? 43 : $hash.hashCode());
            final Object $url = this.getUrl();
            result = result * 59 + (($url == null) ? 43 : $url.hashCode());
            final Object $name = this.getName();
            result = result * 59 + (($name == null) ? 43 : $name.hashCode());
            final Object $sn = this.getSn();
            result = result * 59 + (($sn == null) ? 43 : $sn.hashCode());
            final Object $description = this.getDescription();
            result = result * 59 + (($description == null) ? 43 : $description.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "EvidenceHistory.FileEvidenceData(type=" + this.getType() + ", hash=" + this.getHash() + ", url=" + this.getUrl() + ", name=" + this.getName() + ", sn=" + this.getSn() + ", description=" + this.getDescription() + ")";
        }
    }
}
