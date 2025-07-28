// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

import java.util.List;
import javax.validation.constraints.NotBlank;

public class ArbitrateApplyReq extends CommonReq
{
    @NotBlank(message = "\u4e1a\u52a1\u5b58\u8bc1ID\u4e0d\u80fd\u4e3a\u7a7a")
    private String evidenceId;
    @NotBlank(message = "\u4ef2\u88c1\u673a\u6784ID\u4e0d\u80fd\u4e3a\u7a7a")
    private String arbitralAgencyId;
    @NotBlank(message = "\u4ef2\u88c1\u673a\u6784\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private String arbitralAgencyName;
    @NotBlank(message = "\u4e89\u8bae\u7c7b\u578bID\u4e0d\u80fd\u4e3a\u7a7a")
    private String arbitrateTypeId;
    @NotBlank(message = "\u4e89\u8bae\u7c7b\u578b\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private String arbitrateTypeName;
    @NotBlank(message = "\u6807\u9898\u4e0d\u80fd\u4e3a\u7a7a")
    private String title;
    @NotBlank(message = "\u8bc1\u636e\u5217\u8868\u4e0d\u80fd\u4e3a\u7a7a")
    private List<ProofInfo> proofList;
    @NotBlank(message = "\u4ef2\u88c1\u7533\u8bf7\u4e66\u5217\u8868\u4e0d\u80fd\u4e3a\u7a7a")
    private List<ApplicationInfo> applicationList;
    
    public String getEvidenceId() {
        return this.evidenceId;
    }
    
    public String getArbitralAgencyId() {
        return this.arbitralAgencyId;
    }
    
    public String getArbitralAgencyName() {
        return this.arbitralAgencyName;
    }
    
    public String getArbitrateTypeId() {
        return this.arbitrateTypeId;
    }
    
    public String getArbitrateTypeName() {
        return this.arbitrateTypeName;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public List<ProofInfo> getProofList() {
        return this.proofList;
    }
    
    public List<ApplicationInfo> getApplicationList() {
        return this.applicationList;
    }
    
    public void setEvidenceId(final String evidenceId) {
        this.evidenceId = evidenceId;
    }
    
    public void setArbitralAgencyId(final String arbitralAgencyId) {
        this.arbitralAgencyId = arbitralAgencyId;
    }
    
    public void setArbitralAgencyName(final String arbitralAgencyName) {
        this.arbitralAgencyName = arbitralAgencyName;
    }
    
    public void setArbitrateTypeId(final String arbitrateTypeId) {
        this.arbitrateTypeId = arbitrateTypeId;
    }
    
    public void setArbitrateTypeName(final String arbitrateTypeName) {
        this.arbitrateTypeName = arbitrateTypeName;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public void setProofList(final List<ProofInfo> proofList) {
        this.proofList = proofList;
    }
    
    public void setApplicationList(final List<ApplicationInfo> applicationList) {
        this.applicationList = applicationList;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateApplyReq)) {
            return false;
        }
        final ArbitrateApplyReq other = (ArbitrateApplyReq)o;
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
        final Object this$arbitralAgencyId = this.getArbitralAgencyId();
        final Object other$arbitralAgencyId = other.getArbitralAgencyId();
        Label_0102: {
            if (this$arbitralAgencyId == null) {
                if (other$arbitralAgencyId == null) {
                    break Label_0102;
                }
            }
            else if (this$arbitralAgencyId.equals(other$arbitralAgencyId)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$arbitralAgencyName = this.getArbitralAgencyName();
        final Object other$arbitralAgencyName = other.getArbitralAgencyName();
        Label_0139: {
            if (this$arbitralAgencyName == null) {
                if (other$arbitralAgencyName == null) {
                    break Label_0139;
                }
            }
            else if (this$arbitralAgencyName.equals(other$arbitralAgencyName)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$arbitrateTypeId = this.getArbitrateTypeId();
        final Object other$arbitrateTypeId = other.getArbitrateTypeId();
        Label_0176: {
            if (this$arbitrateTypeId == null) {
                if (other$arbitrateTypeId == null) {
                    break Label_0176;
                }
            }
            else if (this$arbitrateTypeId.equals(other$arbitrateTypeId)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$arbitrateTypeName = this.getArbitrateTypeName();
        final Object other$arbitrateTypeName = other.getArbitrateTypeName();
        Label_0213: {
            if (this$arbitrateTypeName == null) {
                if (other$arbitrateTypeName == null) {
                    break Label_0213;
                }
            }
            else if (this$arbitrateTypeName.equals(other$arbitrateTypeName)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        Label_0250: {
            if (this$title == null) {
                if (other$title == null) {
                    break Label_0250;
                }
            }
            else if (this$title.equals(other$title)) {
                break Label_0250;
            }
            return false;
        }
        final Object this$proofList = this.getProofList();
        final Object other$proofList = other.getProofList();
        Label_0287: {
            if (this$proofList == null) {
                if (other$proofList == null) {
                    break Label_0287;
                }
            }
            else if (this$proofList.equals(other$proofList)) {
                break Label_0287;
            }
            return false;
        }
        final Object this$applicationList = this.getApplicationList();
        final Object other$applicationList = other.getApplicationList();
        if (this$applicationList == null) {
            if (other$applicationList == null) {
                return true;
            }
        }
        else if (this$applicationList.equals(other$applicationList)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrateApplyReq;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $evidenceId = this.getEvidenceId();
        result = result * 59 + (($evidenceId == null) ? 43 : $evidenceId.hashCode());
        final Object $arbitralAgencyId = this.getArbitralAgencyId();
        result = result * 59 + (($arbitralAgencyId == null) ? 43 : $arbitralAgencyId.hashCode());
        final Object $arbitralAgencyName = this.getArbitralAgencyName();
        result = result * 59 + (($arbitralAgencyName == null) ? 43 : $arbitralAgencyName.hashCode());
        final Object $arbitrateTypeId = this.getArbitrateTypeId();
        result = result * 59 + (($arbitrateTypeId == null) ? 43 : $arbitrateTypeId.hashCode());
        final Object $arbitrateTypeName = this.getArbitrateTypeName();
        result = result * 59 + (($arbitrateTypeName == null) ? 43 : $arbitrateTypeName.hashCode());
        final Object $title = this.getTitle();
        result = result * 59 + (($title == null) ? 43 : $title.hashCode());
        final Object $proofList = this.getProofList();
        result = result * 59 + (($proofList == null) ? 43 : $proofList.hashCode());
        final Object $applicationList = this.getApplicationList();
        result = result * 59 + (($applicationList == null) ? 43 : $applicationList.hashCode());
        return result;
    }
    
    public String toString() {
        return "ArbitrateApplyReq(evidenceId=" + this.getEvidenceId() + ", arbitralAgencyId=" + this.getArbitralAgencyId() + ", arbitralAgencyName=" + this.getArbitralAgencyName() + ", arbitrateTypeId=" + this.getArbitrateTypeId() + ", arbitrateTypeName=" + this.getArbitrateTypeName() + ", title=" + this.getTitle() + ", proofList=" + this.getProofList() + ", applicationList=" + this.getApplicationList() + ")";
    }
    
    public static class ProofInfo
    {
        private String name;
        private String hash;
        private String type;
        private String sn;
        private String description;
        
        public String getName() {
            return this.name;
        }
        
        public String getHash() {
            return this.hash;
        }
        
        public String getType() {
            return this.type;
        }
        
        public String getSn() {
            return this.sn;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public void setName(final String name) {
            this.name = name;
        }
        
        public void setHash(final String hash) {
            this.hash = hash;
        }
        
        public void setType(final String type) {
            this.type = type;
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
            if (!(o instanceof ProofInfo)) {
                return false;
            }
            final ProofInfo other = (ProofInfo)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            Label_0065: {
                if (this$name == null) {
                    if (other$name == null) {
                        break Label_0065;
                    }
                }
                else if (this$name.equals(other$name)) {
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
            final Object this$sn = this.getSn();
            final Object other$sn = other.getSn();
            Label_0176: {
                if (this$sn == null) {
                    if (other$sn == null) {
                        break Label_0176;
                    }
                }
                else if (this$sn.equals(other$sn)) {
                    break Label_0176;
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
            return other instanceof ProofInfo;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $name = this.getName();
            result = result * 59 + (($name == null) ? 43 : $name.hashCode());
            final Object $hash = this.getHash();
            result = result * 59 + (($hash == null) ? 43 : $hash.hashCode());
            final Object $type = this.getType();
            result = result * 59 + (($type == null) ? 43 : $type.hashCode());
            final Object $sn = this.getSn();
            result = result * 59 + (($sn == null) ? 43 : $sn.hashCode());
            final Object $description = this.getDescription();
            result = result * 59 + (($description == null) ? 43 : $description.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "ArbitrateApplyReq.ProofInfo(name=" + this.getName() + ", hash=" + this.getHash() + ", type=" + this.getType() + ", sn=" + this.getSn() + ", description=" + this.getDescription() + ")";
        }
    }
    
    public static class ApplicationInfo
    {
        private String imageName;
        private String imageUrl;
        
        public String getImageName() {
            return this.imageName;
        }
        
        public String getImageUrl() {
            return this.imageUrl;
        }
        
        public void setImageName(final String imageName) {
            this.imageName = imageName;
        }
        
        public void setImageUrl(final String imageUrl) {
            this.imageUrl = imageUrl;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof ApplicationInfo)) {
                return false;
            }
            final ApplicationInfo other = (ApplicationInfo)o;
            if (!other.canEqual((Object)this)) {
                return false;
            }
            final Object this$imageName = this.getImageName();
            final Object other$imageName = other.getImageName();
            Label_0065: {
                if (this$imageName == null) {
                    if (other$imageName == null) {
                        break Label_0065;
                    }
                }
                else if (this$imageName.equals(other$imageName)) {
                    break Label_0065;
                }
                return false;
            }
            final Object this$imageUrl = this.getImageUrl();
            final Object other$imageUrl = other.getImageUrl();
            if (this$imageUrl == null) {
                if (other$imageUrl == null) {
                    return true;
                }
            }
            else if (this$imageUrl.equals(other$imageUrl)) {
                return true;
            }
            return false;
        }
        
        protected boolean canEqual(final Object other) {
            return other instanceof ApplicationInfo;
        }
        
        @Override
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $imageName = this.getImageName();
            result = result * 59 + (($imageName == null) ? 43 : $imageName.hashCode());
            final Object $imageUrl = this.getImageUrl();
            result = result * 59 + (($imageUrl == null) ? 43 : $imageUrl.hashCode());
            return result;
        }
        
        @Override
        public String toString() {
            return "ArbitrateApplyReq.ApplicationInfo(imageName=" + this.getImageName() + ", imageUrl=" + this.getImageUrl() + ")";
        }
    }
}
