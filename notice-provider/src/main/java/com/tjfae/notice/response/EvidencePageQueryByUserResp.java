// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

import java.util.ArrayList;
import java.util.Iterator;
import com.tjfae.notice.entity.EvidenceHistory;
import com.tjfae.notice.entity.EvidencePageUser;
import java.util.List;

public class EvidencePageQueryByUserResp
{
    private String startTxId;
    private String count;
    private List<EvidenceDetailQueryResp> data;
    
    public void update(final EvidencePageUser evidencePageUser) {
        this.startTxId = evidencePageUser.getStartTxId();
        this.count = evidencePageUser.getCount();
        for (final EvidenceHistory oneHistory : evidencePageUser.getData()) {
            final EvidenceDetailQueryResp resp = new EvidenceDetailQueryResp();
            resp.update(oneHistory);
            this.data.add(resp);
        }
    }
    
    public EvidencePageQueryByUserResp() {
        this.data = new ArrayList<EvidenceDetailQueryResp>();
    }
    
    public String getStartTxId() {
        return this.startTxId;
    }
    
    public String getCount() {
        return this.count;
    }
    
    public List<EvidenceDetailQueryResp> getData() {
        return this.data;
    }
    
    public void setStartTxId(final String startTxId) {
        this.startTxId = startTxId;
    }
    
    public void setCount(final String count) {
        this.count = count;
    }
    
    public void setData(final List<EvidenceDetailQueryResp> data) {
        this.data = data;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EvidencePageQueryByUserResp)) {
            return false;
        }
        final EvidencePageQueryByUserResp other = (EvidencePageQueryByUserResp)o;
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
        final Object this$count = this.getCount();
        final Object other$count = other.getCount();
        Label_0102: {
            if (this$count == null) {
                if (other$count == null) {
                    break Label_0102;
                }
            }
            else if (this$count.equals(other$count)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null) {
            if (other$data == null) {
                return true;
            }
        }
        else if (this$data.equals(other$data)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EvidencePageQueryByUserResp;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $startTxId = this.getStartTxId();
        result = result * 59 + (($startTxId == null) ? 43 : $startTxId.hashCode());
        final Object $count = this.getCount();
        result = result * 59 + (($count == null) ? 43 : $count.hashCode());
        final Object $data = this.getData();
        result = result * 59 + (($data == null) ? 43 : $data.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "EvidencePageQueryByUserResp(startTxId=" + this.getStartTxId() + ", count=" + this.getCount() + ", data=" + this.getData() + ")";
    }
}
