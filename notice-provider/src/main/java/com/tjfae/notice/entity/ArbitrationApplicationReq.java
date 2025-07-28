// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity;

public class ArbitrationApplicationReq
{
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArbitrationApplicationReq)) {
            return false;
        }
        final ArbitrationApplicationReq other = (ArbitrationApplicationReq)o;
        return other.canEqual(this);
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ArbitrationApplicationReq;
    }
    
    @Override
    public int hashCode() {
        final int result = 1;
        return 1;
    }
    
    @Override
    public String toString() {
        return "ArbitrationApplicationReq()";
    }
}
