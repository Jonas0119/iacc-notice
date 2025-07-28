// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import com.tjfae.notice.entity.EvidenceHistory;
import java.util.List;

public class EvidenceDetailQueryResp
{
    private String evidenceId;
    private String description;
    private String startDate;
    private String latestDate;
    private Integer totalEvAmount;
    private List<EvidenceTypeResp> evTypeList;
    
    public void update(final EvidenceHistory evidenceHistory) {
        this.evidenceId = evidenceHistory.getEvidenceId();
        this.description = evidenceHistory.getDescription();
        this.startDate = evidenceHistory.getStartDate();
        this.latestDate = evidenceHistory.getLatestDate();
        int notFileAmount = 0;
        final Map<String, EvidenceTypeResp> evTypeMap = new HashMap<String, EvidenceTypeResp>();
        for (final EvidenceHistory.EvidenceInfo one : evidenceHistory.getHistoryDataObj()) {
            if (!one.getType().equals("file")) {
                ++notFileAmount;
            }
            else {
                final String fileEvType = one.getFileEvidenceData().getType();
                final FileEvidenceResp fileEvidenceResp = new FileEvidenceResp();
                fileEvidenceResp.update(one.getFileEvidenceData(), one.getDate());
                EvidenceTypeResp evidenceTypeResp = evTypeMap.get(fileEvType);
                if (null == evidenceTypeResp) {
                    evidenceTypeResp = new EvidenceTypeResp();
                    evTypeMap.put(fileEvType, evidenceTypeResp);
                }
                evidenceTypeResp.update(fileEvType, fileEvidenceResp);
            }
        }
        for (final EvidenceTypeResp one2 : evTypeMap.values()) {
            this.evTypeList.add(one2);
            this.totalEvAmount += one2.getAmount();
        }
        this.totalEvAmount += notFileAmount;
    }
    
    public EvidenceDetailQueryResp() {
        this.totalEvAmount = 0;
        this.evTypeList = new ArrayList();
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
    
    public Integer getTotalEvAmount() {
        return this.totalEvAmount;
    }
    
    public List<EvidenceTypeResp> getEvTypeList() {
        return this.evTypeList;
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
    
    public void setTotalEvAmount(final Integer totalEvAmount) {
        this.totalEvAmount = totalEvAmount;
    }
    
    public void setEvTypeList(final List<EvidenceTypeResp> evTypeList) {
        this.evTypeList = evTypeList;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceDetailQueryResp)) {
            return false;
        }
        final EvidenceDetailQueryResp other = (EvidenceDetailQueryResp)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$totalEvAmount = this.getTotalEvAmount();
        final Object other$totalEvAmount = other.getTotalEvAmount();
        Label_0065: {
            if (this$totalEvAmount == null) {
                if (other$totalEvAmount == null) {
                    break Label_0065;
                }
            }
            else if (this$totalEvAmount.equals(other$totalEvAmount)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$evidenceId = this.getEvidenceId();
        final Object other$evidenceId = other.getEvidenceId();
        Label_0102: {
            if (this$evidenceId == null) {
                if (other$evidenceId == null) {
                    break Label_0102;
                }
            }
            else if (this$evidenceId.equals(other$evidenceId)) {
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
        final Object this$startDate = this.getStartDate();
        final Object other$startDate = other.getStartDate();
        Label_0176: {
            if (this$startDate == null) {
                if (other$startDate == null) {
                    break Label_0176;
                }
            }
            else if (this$startDate.equals(other$startDate)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$latestDate = this.getLatestDate();
        final Object other$latestDate = other.getLatestDate();
        Label_0213: {
            if (this$latestDate == null) {
                if (other$latestDate == null) {
                    break Label_0213;
                }
            }
            else if (this$latestDate.equals(other$latestDate)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$evTypeList = this.getEvTypeList();
        final Object other$evTypeList = other.getEvTypeList();
        if (this$evTypeList == null) {
            if (other$evTypeList == null) {
                return true;
            }
        }
        else if (this$evTypeList.equals(other$evTypeList)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceDetailQueryResp;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $totalEvAmount = this.getTotalEvAmount();
        result = result * 59 + (($totalEvAmount == null) ? 43 : $totalEvAmount.hashCode());
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $description = this.getDescription();
        result = result * 59 + (($description == null) ? 43 : $description.hashCode());
        final Object $startDate = this.getStartDate();
        result = result * 59 + (($startDate == null) ? 43 : $startDate.hashCode());
        final Object $latestDate = this.getLatestDate();
        result = result * 59 + (($latestDate == null) ? 43 : $latestDate.hashCode());
        final Object $evTypeList = this.getEvTypeList();
        result = result * 59 + (($evTypeList == null) ? 43 : $evTypeList.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceDetailQueryResp(evidenceId=" + this.getEvidenceId() + ", description=" + this.getDescription() + ", startDate=" + this.getStartDate() + ", latestDate=" + this.getLatestDate() + ", totalEvAmount=" + this.getTotalEvAmount() + ", evTypeList=" + this.getEvTypeList() + ")";
    }
    
    public static class FileEvidenceResp
    {
        private String type;
        private String hash;
        private String url;
        private String name;
        private String sn;
        private String description;
        private String date;
        
        public void update(final EvidenceHistory.FileEvidenceData fileEvidenceData, final String dateStr) {
            this.type = fileEvidenceData.getType();
            this.hash = fileEvidenceData.getHash();
            this.url = fileEvidenceData.getUrl();
            this.name = fileEvidenceData.getName();
            this.sn = fileEvidenceData.getSn();
            this.description = fileEvidenceData.getDescription();
            this.date = dateStr;
        }
        
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
        
        public String getDate() {
            return this.date;
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
        
        public void setDate(final String date) {
            this.date = date;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FileEvidenceResp)) {
                return false;
            }
            final FileEvidenceResp other = (FileEvidenceResp)o;
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
            Label_0250: {
                if (this$description == null) {
                    if (other$description == null) {
                        break Label_0250;
                    }
                }
                else if (this$description.equals(other$description)) {
                    break Label_0250;
                }
                return false;
            }
            final Object this$date = this.getDate();
            final Object other$date = other.getDate();
            if (this$date == null) {
                if (other$date == null) {
                    return true;
                }
            }
            else if (this$date.equals(other$date)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof FileEvidenceResp;
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
            final Object $date = this.getDate();
            result = result * 59 + (($date == null) ? 43 : $date.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "EvidenceDetailQueryResp.FileEvidenceResp(type=" + this.getType() + ", hash=" + this.getHash() + ", url=" + this.getUrl() + ", name=" + this.getName() + ", sn=" + this.getSn() + ", description=" + this.getDescription() + ", date=" + this.getDate() + ")";
        }
    }
    
    public static class EvidenceTypeResp
    {
        private String evTypeDesc;
        private Integer amount;
        private List<FileEvidenceResp> evList;
        
        public void update(final String evTypeDesc, final FileEvidenceResp fileEvidenceResp) {
            this.evTypeDesc = evTypeDesc;
            final Integer amount = this.amount;
            ++this.amount;
            this.addFileEvidence(fileEvidenceResp);
        }
        
        private void addFileEvidence(final FileEvidenceResp fileEvidenceResp) {
            this.evList.add(fileEvidenceResp);
        }
        
        public EvidenceTypeResp() {
            this.amount = 0;
            this.evList = new ArrayList();
        }
        
        public String getEvTypeDesc() {
            return this.evTypeDesc;
        }
        
        public Integer getAmount() {
            return this.amount;
        }
        
        public List<FileEvidenceResp> getEvList() {
            return this.evList;
        }
        
        public void setEvTypeDesc(final String evTypeDesc) {
            this.evTypeDesc = evTypeDesc;
        }
        
        public void setAmount(final Integer amount) {
            this.amount = amount;
        }
        
        public void setEvList(final List<FileEvidenceResp> evList) {
            this.evList = evList;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof EvidenceTypeResp)) {
                return false;
            }
            final EvidenceTypeResp other = (EvidenceTypeResp)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$amount = this.getAmount();
            final Object other$amount = other.getAmount();
            Label_0065: {
                if (this$amount == null) {
                    if (other$amount == null) {
                        break Label_0065;
                    }
                }
                else if (this$amount.equals(other$amount)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$evTypeDesc = this.getEvTypeDesc();
            final Object other$evTypeDesc = other.getEvTypeDesc();
            Label_0102: {
                if (this$evTypeDesc == null) {
                    if (other$evTypeDesc == null) {
                        break Label_0102;
                    }
                }
                else if (this$evTypeDesc.equals(other$evTypeDesc)) {
                    break Label_0102;
                }
                return false;
            }
            final Object this$evList = this.getEvList();
            final Object other$evList = other.getEvList();
            if (this$evList == null) {
                if (other$evList == null) {
                    return true;
                }
            }
            else if (this$evList.equals(other$evList)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof EvidenceTypeResp;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $amount = this.getAmount();
            result = result * 59 + (($amount == null) ? 43 : $amount.hashCode());
            final Object $evTypeDesc = this.getEvTypeDesc();
            result = result * 59 + (($evTypeDesc == null) ? 43 : $evTypeDesc.hashCode());
            final Object $evList = this.getEvList();
            result = result * 59 + (($evList == null) ? 43 : $evList.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "EvidenceDetailQueryResp.EvidenceTypeResp(evTypeDesc=" + this.getEvTypeDesc() + ", amount=" + this.getAmount() + ", evList=" + this.getEvList() + ")";
        }
    }
}
