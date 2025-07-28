// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.common.enumeration;

public enum ErrorEnum
{
    E_500(Integer.valueOf(500), "\u8bf7\u6c42\u65b9\u5f0f\u6709\u8bef,\u8bf7\u68c0\u67e5 GET/POST"), 
    E_900(Integer.valueOf(900), "Excel\u6587\u4ef6\u6216\u6570\u636e\u6709\u8bef\uff01\u8bf7\u68c0\u67e5\u540e\u518d\u8bd5\uff01"), 
    E_901(Integer.valueOf(901), "Excel\u6587\u4ef6\u6216\u6570\u636e\u6709\u8bef\uff01\u8bfb\u53d6\u7684\u6570\u636e\u4e3a\u7a7a\uff01"), 
    E_902(Integer.valueOf(902), "Excel\u6587\u4ef6\u4e2d\u5b58\u5728\u4e0d\u540c\u7684\u65e5\u671f\u6216\u65e5\u671f\u672a\u586b\u5199\uff01"), 
    E_10012(Integer.valueOf(10012), "\u624b\u673a\u53f7\u7801\u6709\u8bef!");
    
    private Integer code;
    private String msg;
    
    private ErrorEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer getCode() {
        return this.code;
    }
    
    public String getMsg() {
        return this.msg;
    }
}
