// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.entity.hs;

public class HSResponseBase
{
    protected String code;
    protected String result;
    
    public String getCode() {
        return this.code;
    }
    
    public String getResult() {
        return this.result;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setResult(final String result) {
        this.result = result;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HSResponseBase)) {
            return false;
        }
        final HSResponseBase other = (HSResponseBase)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        Label_0065: {
            if (this$code == null) {
                if (other$code == null) {
                    break Label_0065;
                }
            }
            else if (this$code.equals(other$code)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$result = this.getResult();
        final Object other$result = other.getResult();
        if (this$result == null) {
            if (other$result == null) {
                return true;
            }
        }
        else if (this$result.equals(other$result)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HSResponseBase;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $code = this.getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        final Object $result = this.getResult();
        result = result * 59 + (($result == null) ? 43 : $result.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HSResponseBase(code=" + this.getCode() + ", result=" + this.getResult() + ")";
    }
}
