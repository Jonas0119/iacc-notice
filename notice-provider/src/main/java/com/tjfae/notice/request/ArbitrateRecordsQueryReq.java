// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.request;

public class ArbitrateRecordsQueryReq extends CommonReq
{
    private String pageIndex;
    private String pageSize;
    
    public ArbitrateRecordsQueryReq() {
        this.pageSize = "10";
    }
    
    public String getPageIndex() {
        return this.pageIndex;
    }
    
    public String getPageSize() {
        return this.pageSize;
    }
    
    public void setPageIndex(final String pageIndex) {
        this.pageIndex = pageIndex;
    }
    
    public void setPageSize(final String pageSize) {
        this.pageSize = pageSize;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrateRecordsQueryReq)) {
            return false;
        }
        final ArbitrateRecordsQueryReq other = (ArbitrateRecordsQueryReq)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$pageIndex = this.getPageIndex();
        final Object other$pageIndex = other.getPageIndex();
        Label_0065: {
            if (this$pageIndex == null) {
                if (other$pageIndex == null) {
                    break Label_0065;
                }
            }
            else if (this$pageIndex.equals(other$pageIndex)) {
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
        return other instanceof ArbitrateRecordsQueryReq;
    }
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $pageIndex = this.getPageIndex();
        result = result * 59 + (($pageIndex == null) ? 43 : $pageIndex.hashCode());
        final Object $pageSize = this.getPageSize();
        result = result * 59 + (($pageSize == null) ? 43 : $pageSize.hashCode());
        return result;
    }
    
    public String toString() {
        return "ArbitrateRecordsQueryReq(pageIndex=" + this.getPageIndex() + ", pageSize=" + this.getPageSize() + ")";
    }
}
