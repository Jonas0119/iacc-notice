// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

import org.slf4j.LoggerFactory;
import com.tjfae.notice.utils.json.JsonUtils;
import org.springframework.data.annotation.Transient;
import org.slf4j.Logger;

public class EvidenceFile
{
    private static Logger logger;
    private Integer height;
    private Integer number;
    private Object payload;
    private String dataHash;
    private String blockDataHash;
    private String previousHash;
    private String date;
    private String createMSPID;
    @Transient
    private EvidenceInfo evidenceInfo;
    
    public void deserialization() {
        if (this.payload != null) {
            try {
                (this.evidenceInfo = (EvidenceInfo)JsonUtils.jsonStr2Object((String)this.payload, (Class)EvidenceInfo.class)).deserialization();
            }
            catch (final Exception e) {
                e.printStackTrace();
                EvidenceFile.logger.error(e.getMessage());
            }
        }
    }
    
    public Integer getHeight() {
        return this.height;
    }
    
    public Integer getNumber() {
        return this.number;
    }
    
    public Object getPayload() {
        return this.payload;
    }
    
    public String getDataHash() {
        return this.dataHash;
    }
    
    public String getBlockDataHash() {
        return this.blockDataHash;
    }
    
    public String getPreviousHash() {
        return this.previousHash;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getCreateMSPID() {
        return this.createMSPID;
    }
    
    public EvidenceInfo getEvidenceInfo() {
        return this.evidenceInfo;
    }
    
    public void setHeight(final Integer height) {
        this.height = height;
    }
    
    public void setNumber(final Integer number) {
        this.number = number;
    }
    
    public void setPayload(final Object payload) {
        this.payload = payload;
    }
    
    public void setDataHash(final String dataHash) {
        this.dataHash = dataHash;
    }
    
    public void setBlockDataHash(final String blockDataHash) {
        this.blockDataHash = blockDataHash;
    }
    
    public void setPreviousHash(final String previousHash) {
        this.previousHash = previousHash;
    }
    
    public void setDate(final String date) {
        this.date = date;
    }
    
    public void setCreateMSPID(final String createMSPID) {
        this.createMSPID = createMSPID;
    }
    
    public void setEvidenceInfo(final EvidenceInfo evidenceInfo) {
        this.evidenceInfo = evidenceInfo;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceFile)) {
            return false;
        }
        final EvidenceFile other = (EvidenceFile)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$height = this.getHeight();
        final Object other$height = other.getHeight();
        Label_0065: {
            if (this$height == null) {
                if (other$height == null) {
                    break Label_0065;
                }
            }
            else if (this$height.equals(other$height)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$number = this.getNumber();
        final Object other$number = other.getNumber();
        Label_0102: {
            if (this$number == null) {
                if (other$number == null) {
                    break Label_0102;
                }
            }
            else if (this$number.equals(other$number)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$payload = this.getPayload();
        final Object other$payload = other.getPayload();
        Label_0139: {
            if (this$payload == null) {
                if (other$payload == null) {
                    break Label_0139;
                }
            }
            else if (this$payload.equals(other$payload)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$dataHash = this.getDataHash();
        final Object other$dataHash = other.getDataHash();
        Label_0176: {
            if (this$dataHash == null) {
                if (other$dataHash == null) {
                    break Label_0176;
                }
            }
            else if (this$dataHash.equals(other$dataHash)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$blockDataHash = this.getBlockDataHash();
        final Object other$blockDataHash = other.getBlockDataHash();
        Label_0213: {
            if (this$blockDataHash == null) {
                if (other$blockDataHash == null) {
                    break Label_0213;
                }
            }
            else if (this$blockDataHash.equals(other$blockDataHash)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$previousHash = this.getPreviousHash();
        final Object other$previousHash = other.getPreviousHash();
        Label_0250: {
            if (this$previousHash == null) {
                if (other$previousHash == null) {
                    break Label_0250;
                }
            }
            else if (this$previousHash.equals(other$previousHash)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        Label_0287: {
            if (this$date == null) {
                if (other$date == null) {
                    break Label_0287;
                }
            }
            else if (this$date.equals(other$date)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$createMSPID = this.getCreateMSPID();
        final Object other$createMSPID = other.getCreateMSPID();
        Label_0324: {
            if (this$createMSPID == null) {
                if (other$createMSPID == null) {
                    break Label_0324;
                }
            }
            else if (this$createMSPID.equals(other$createMSPID)) {
                break Label_0324;
            }
            return false;
        }
        final Object this$evidenceInfo = this.getEvidenceInfo();
        final Object other$evidenceInfo = other.getEvidenceInfo();
        if (this$evidenceInfo == null) {
            if (other$evidenceInfo == null) {
                return true;
            }
        }
        else if (this$evidenceInfo.equals(other$evidenceInfo)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceFile;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $height = this.getHeight();
        result = result * 59 + (($height == null) ? 43 : $height.hashCode());
        final Object $number = this.getNumber();
        result = result * 59 + (($number == null) ? 43 : $number.hashCode());
        final Object $payload = this.getPayload();
        result = result * 59 + (($payload == null) ? 43 : $payload.hashCode());
        final Object $dataHash = this.getDataHash();
        result = result * 59 + (($dataHash == null) ? 43 : $dataHash.hashCode());
        final Object $blockDataHash = this.getBlockDataHash();
        result = result * 59 + (($blockDataHash == null) ? 43 : $blockDataHash.hashCode());
        final Object $previousHash = this.getPreviousHash();
        result = result * 59 + (($previousHash == null) ? 43 : $previousHash.hashCode());
        final Object $date = this.getDate();
        result = result * 59 + (($date == null) ? 43 : $date.hashCode());
        final Object $createMSPID = this.getCreateMSPID();
        result = result * 59 + (($createMSPID == null) ? 43 : $createMSPID.hashCode());
        final Object $evidenceInfo = this.getEvidenceInfo();
        result = result * 59 + (($evidenceInfo == null) ? 43 : $evidenceInfo.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceFile(height=" + this.getHeight() + ", number=" + this.getNumber() + ", payload=" + this.getPayload() + ", dataHash=" + this.getDataHash() + ", blockDataHash=" + this.getBlockDataHash() + ", previousHash=" + this.getPreviousHash() + ", date=" + this.getDate() + ", createMSPID=" + this.getCreateMSPID() + ", evidenceInfo=" + this.getEvidenceInfo() + ")";
    }
    
    static {
        EvidenceFile.logger = LoggerFactory.getLogger((Class)EvidenceFile.class);
    }
    
    public static class EvidenceInfo
    {
        private String app;
        private String owner;
        private String type;
        private String evidenceId;
        private Object data;
        private String description;
        @Transient
        private FileEvidenceData fileEvidenceData;
        
        public void deserialization() {
            if ("file".equals(this.type) && this.data != null) {
                try {
                    this.fileEvidenceData = (FileEvidenceData)JsonUtils.jsonStr2Object((String)this.data, (Class)FileEvidenceData.class);
                    String temp = (String)this.data;
                    while (this.fileEvidenceData == null) {
                        temp = (String)JsonUtils.jsonStr2Object(temp, (Class)String.class);
                        this.fileEvidenceData = (FileEvidenceData)JsonUtils.jsonStr2Object(temp, (Class)FileEvidenceData.class);
                    }
                }
                catch (final Exception e) {
                    e.printStackTrace();
                    EvidenceFile.logger.error(e.getMessage());
                }
            }
            else if ("common".equals(this.type) && this.data != null) {
                try {
                    this.fileEvidenceData = (FileEvidenceData)JsonUtils.jsonStr2Object((String)this.data, (Class)FileEvidenceData.class);
                    String temp = (String)this.data;
                    while (this.fileEvidenceData == null) {
                        temp = (String)JsonUtils.jsonStr2Object(temp, (Class)String.class);
                        this.fileEvidenceData = (FileEvidenceData)JsonUtils.jsonStr2Object(temp, (Class)FileEvidenceData.class);
                    }
                }
                catch (final Exception e) {
                    e.printStackTrace();
                    EvidenceFile.logger.error(e.getMessage());
                }
            }
        }
        
        public String getApp() {
            return this.app;
        }
        
        public String getOwner() {
            return this.owner;
        }
        
        public String getType() {
            return this.type;
        }
        
        public String getEvidenceId() {
            return this.evidenceId;
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
        
        public void setApp(final String app) {
            this.app = app;
        }
        
        public void setOwner(final String owner) {
            this.owner = owner;
        }
        
        public void setType(final String type) {
            this.type = type;
        }
        
        public void setEvidenceId(final String evidenceId) {
            this.evidenceId = evidenceId;
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
            final Object this$app = this.getApp();
            final Object other$app = other.getApp();
            Label_0065: {
                if (this$app == null) {
                    if (other$app == null) {
                        break Label_0065;
                    }
                }
                else if (this$app.equals(other$app)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$owner = this.getOwner();
            final Object other$owner = other.getOwner();
            Label_0102: {
                if (this$owner == null) {
                    if (other$owner == null) {
                        break Label_0102;
                    }
                }
                else if (this$owner.equals(other$owner)) {
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
            final Object this$evidenceId = this.getEvidenceId();
            final Object other$evidenceId = other.getEvidenceId();
            Label_0176: {
                if (this$evidenceId == null) {
                    if (other$evidenceId == null) {
                        break Label_0176;
                    }
                }
                else if (this$evidenceId.equals(other$evidenceId)) {
                    break Label_0176;
                }
                return false;
            }
            final Object this$data = this.getData();
            final Object other$data = other.getData();
            Label_0213: {
                if (this$data == null) {
                    if (other$data == null) {
                        break Label_0213;
                    }
                }
                else if (this$data.equals(other$data)) {
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
            final Object $app = this.getApp();
            result = result * 59 + (($app == null) ? 43 : $app.hashCode());
            final Object $owner = this.getOwner();
            result = result * 59 + (($owner == null) ? 43 : $owner.hashCode());
            final Object $type = this.getType();
            result = result * 59 + (($type == null) ? 43 : $type.hashCode());
            final Object $evidenceId = this.getEvidenceId();
            result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
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
            return "EvidenceFile.EvidenceInfo(app=" + this.getApp() + ", owner=" + this.getOwner() + ", type=" + this.getType() + ", evidenceId=" + this.getEvidenceId() + ", data=" + this.getData() + ", description=" + this.getDescription() + ", fileEvidenceData=" + this.getFileEvidenceData() + ")";
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
            return "EvidenceFile.FileEvidenceData(type=" + this.getType() + ", hash=" + this.getHash() + ", url=" + this.getUrl() + ", name=" + this.getName() + ", sn=" + this.getSn() + ", description=" + this.getDescription() + ")";
        }
    }
}
