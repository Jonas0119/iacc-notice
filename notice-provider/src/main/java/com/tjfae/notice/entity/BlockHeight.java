// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

public class BlockHeight
{
    private Long id;
    private Long blocknum;
    private Long evidence_amount;
    private String channel_name;
    
    public Long getId() {
        return this.id;
    }
    
    public Long getBlocknum() {
        return this.blocknum;
    }
    
    public Long getEvidence_amount() {
        return this.evidence_amount;
    }
    
    public String getChannel_name() {
        return this.channel_name;
    }
    
    public void setId(final Long id) {
        this.id = id;
    }
    
    public void setBlocknum(final Long blocknum) {
        this.blocknum = blocknum;
    }
    
    public void setEvidence_amount(final Long evidence_amount) {
        this.evidence_amount = evidence_amount;
    }
    
    public void setChannel_name(final String channel_name) {
        this.channel_name = channel_name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BlockHeight)) {
            return false;
        }
        final BlockHeight other = (BlockHeight)o;
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
        final Object this$blocknum = this.getBlocknum();
        final Object other$blocknum = other.getBlocknum();
        Label_0102: {
            if (this$blocknum == null) {
                if (other$blocknum == null) {
                    break Label_0102;
                }
            }
            else if (this$blocknum.equals(other$blocknum)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$evidence_amount = this.getEvidence_amount();
        final Object other$evidence_amount = other.getEvidence_amount();
        Label_0139: {
            if (this$evidence_amount == null) {
                if (other$evidence_amount == null) {
                    break Label_0139;
                }
            }
            else if (this$evidence_amount.equals(other$evidence_amount)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$channel_name = this.getChannel_name();
        final Object other$channel_name = other.getChannel_name();
        if (this$channel_name == null) {
            if (other$channel_name == null) {
                return true;
            }
        }
        else if (this$channel_name.equals(other$channel_name)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof BlockHeight;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $blocknum = this.getBlocknum();
        result = result * 59 + (($blocknum == null) ? 43 : $blocknum.hashCode());
        final Object $evidence_amount = this.getEvidence_amount();
        result = result * 59 + (($evidence_amount == null) ? 43 : $evidence_amount.hashCode());
        final Object $channel_name = this.getChannel_name();
        result = result * 59 + (($channel_name == null) ? 43 : $channel_name.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "BlockHeight(id=" + this.getId() + ", blocknum=" + this.getBlocknum() + ", evidence_amount=" + this.getEvidence_amount() + ", channel_name=" + this.getChannel_name() + ")";
    }
}
