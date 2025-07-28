// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.response;

import java.io.Serializable;

public class ResultJson implements Serializable
{
    private static final long serialVersionUID = 783015033603078674L;
    private String code;
    private String msg;
    private String sign;
    private Object data;
    
    @Override
    public String toString() {
        return "{\"code\":\"" + this.code + "\", \"msg\":\"" + this.msg + '\"' + ", \"sign\":\"" + this.sign + '\"' + ", \"data\":\"" + this.data + '\"' + '}';
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public String getSign() {
        return this.sign;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setMsg(final String msg) {
        this.msg = msg;
    }
    
    public void setSign(final String sign) {
        this.sign = sign;
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResultJson)) {
            return false;
        }
        final ResultJson other = (ResultJson)o;
        if (!other.canEqual(this)) {
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
        final Object this$msg = this.getMsg();
        final Object other$msg = other.getMsg();
        Label_0102: {
            if (this$msg == null) {
                if (other$msg == null) {
                    break Label_0102;
                }
            }
            else if (this$msg.equals(other$msg)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$sign = this.getSign();
        final Object other$sign = other.getSign();
        Label_0139: {
            if (this$sign == null) {
                if (other$sign == null) {
                    break Label_0139;
                }
            }
            else if (this$sign.equals(other$sign)) {
                break Label_0139;
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
        return other instanceof ResultJson;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $code = this.getCode();
        result = result * 59 + (($code == null) ? 43 : $code.hashCode());
        final Object $msg = this.getMsg();
        result = result * 59 + (($msg == null) ? 43 : $msg.hashCode());
        final Object $sign = this.getSign();
        result = result * 59 + (($sign == null) ? 43 : $sign.hashCode());
        final Object $data = this.getData();
        result = result * 59 + (($data == null) ? 43 : $data.hashCode());
        return result;
    }
}
