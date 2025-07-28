// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.util;

import java.io.Serializable;

public class ResultJson<T> implements Serializable
{
    private static final long serialVersionUID = 783015033603078674L;
    private String code;
    private String msg;
    private T data;
    
    public static ResultJson ok() {
        return ok(null);
    }
    
    public static ResultJson ok(final Object o) {
        return new ResultJson(ResultCode.SUCCESS, o);
    }
    
    public static ResultJson failure(final ResultCode code) {
        return failure(code, null);
    }
    
    public static ResultJson failure(final ResultCode code, final Object o) {
        return new ResultJson(code, o);
    }
    
    public ResultJson(final ResultCode resultCode) {
        this.setResultCode(resultCode);
    }
    
    public ResultJson(final ResultCode resultCode, final T data) {
        this.setResultCode(resultCode);
        this.data = data;
    }
    
    public void setResultCode(final ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
    
    @Override
    public String toString() {
        return "{\"code\":\"" + this.code + "\", \"msg\":\"" + this.msg + '\"' + ", \"data\":\"" + this.data + '\"' + '}';
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public T getData() {
        return this.data;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public void setMsg(final String msg) {
        this.msg = msg;
    }
    
    public void setData(final T data) {
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
        final ResultJson<?> other = (ResultJson<?>)o;
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
        final Object $data = this.getData();
        result = result * 59 + (($data == null) ? 43 : $data.hashCode());
        return result;
    }
}
