// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

public class EvidencePageQueryByUserReq extends CommonReq
{
    private String startTxId;
    private String pageSize;
    
    public EvidencePageQueryByUserReq() {
        this.pageSize = "10";
    }
    
    public String getStartTxId() {
        return this.startTxId;
    }
    
    public String getPageSize() {
        return this.pageSize;
    }
    
    public void setStartTxId(final String startTxId) {
        this.startTxId = startTxId;
    }
    
    public void setPageSize(final String pageSize) {
        this.pageSize = pageSize;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidencePageQueryByUserReq)) {
            return false;
        }
        final EvidencePageQueryByUserReq other = (EvidencePageQueryByUserReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$startTxId = this.getStartTxId();
        final Object other$startTxId = other.getStartTxId();
        Label_0065: {
            if (this$startTxId == null) {
                if (other$startTxId == null) {
                    break Label_0065;
                }
            }
            else if (this$startTxId.equals(other$startTxId)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$pageSize = this.getPageSize();
        final Object other$pageSize = other.getPageSize();
        if (this$pageSize == null) {
            if (other$pageSize == null) {
                return true;
            }
        }
        else if (this$pageSize.equals(other$pageSize)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidencePageQueryByUserReq;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $startTxId = this.getStartTxId();
        result = result * 59 + (($startTxId == null) ? 43 : $startTxId.hashCode());
        final Object $pageSize = this.getPageSize();
        result = result * 59 + (($pageSize == null) ? 43 : $pageSize.hashCode());
        return result;
    }
    
    public String toString() {
        return "EvidencePageQueryByUserReq(startTxId=" + this.getStartTxId() + ", pageSize=" + this.getPageSize() + ")";
    }
}
