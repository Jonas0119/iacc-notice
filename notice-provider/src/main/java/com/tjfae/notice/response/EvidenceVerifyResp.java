// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

public class EvidenceVerifyResp
{
    private String blockHash;
    private String evHash;
    private String evType;
    private String evTime;
    private String blockHeight;
    
    public String getBlockHash() {
        return this.blockHash;
    }
    
    public String getEvHash() {
        return this.evHash;
    }
    
    public String getEvType() {
        return this.evType;
    }
    
    public String getEvTime() {
        return this.evTime;
    }
    
    public String getBlockHeight() {
        return this.blockHeight;
    }
    
    public void setBlockHash(final String blockHash) {
        this.blockHash = blockHash;
    }
    
    public void setEvHash(final String evHash) {
        this.evHash = evHash;
    }
    
    public void setEvType(final String evType) {
        this.evType = evType;
    }
    
    public void setEvTime(final String evTime) {
        this.evTime = evTime;
    }
    
    public void setBlockHeight(final String blockHeight) {
        this.blockHeight = blockHeight;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidenceVerifyResp)) {
            return false;
        }
        final EvidenceVerifyResp other = (EvidenceVerifyResp)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$blockHash = this.getBlockHash();
        final Object other$blockHash = other.getBlockHash();
        Label_0065: {
            if (this$blockHash == null) {
                if (other$blockHash == null) {
                    break Label_0065;
                }
            }
            else if (this$blockHash.equals(other$blockHash)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$evHash = this.getEvHash();
        final Object other$evHash = other.getEvHash();
        Label_0102: {
            if (this$evHash == null) {
                if (other$evHash == null) {
                    break Label_0102;
                }
            }
            else if (this$evHash.equals(other$evHash)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$evType = this.getEvType();
        final Object other$evType = other.getEvType();
        Label_0139: {
            if (this$evType == null) {
                if (other$evType == null) {
                    break Label_0139;
                }
            }
            else if (this$evType.equals(other$evType)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$evTime = this.getEvTime();
        final Object other$evTime = other.getEvTime();
        Label_0176: {
            if (this$evTime == null) {
                if (other$evTime == null) {
                    break Label_0176;
                }
            }
            else if (this$evTime.equals(other$evTime)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$blockHeight = this.getBlockHeight();
        final Object other$blockHeight = other.getBlockHeight();
        if (this$blockHeight == null) {
            if (other$blockHeight == null) {
                return true;
            }
        }
        else if (this$blockHeight.equals(other$blockHeight)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidenceVerifyResp;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $blockHash = this.getBlockHash();
        result = result * 59 + (($blockHash == null) ? 43 : $blockHash.hashCode());
        final Object $evHash = this.getEvHash();
        result = result * 59 + (($evHash == null) ? 43 : $evHash.hashCode());
        final Object $evType = this.getEvType();
        result = result * 59 + (($evType == null) ? 43 : $evType.hashCode());
        final Object $evTime = this.getEvTime();
        result = result * 59 + (($evTime == null) ? 43 : $evTime.hashCode());
        final Object $blockHeight = this.getBlockHeight();
        result = result * 59 + (($blockHeight == null) ? 43 : $blockHeight.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidenceVerifyResp(blockHash=" + this.getBlockHash() + ", evHash=" + this.getEvHash() + ", evType=" + this.getEvType() + ", evTime=" + this.getEvTime() + ", blockHeight=" + this.getBlockHeight() + ")";
    }
}
