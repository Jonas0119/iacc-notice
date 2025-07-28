// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.enums;

public enum StatusEnum
{
    SUBMIT("0", "\u63d0\u4ea4"), 
    PASS("1", "\u5ba1\u6838\u901a\u8fc7"), 
    BACK("2", "\u9a73\u56de"), 
    PUBLISH("3", "\u53d1\u5e03"), 
    HIDDEN("4", "\u9690\u85cf"), 
    HISTORY("5", "\u5386\u53f2\u516c\u544a"), 
    OUTCANCEL("6", "\u5916\u90e8\u53d6\u6d88\u516c\u544a");
    
    private String value;
    private String label;
    
    private StatusEnum(final String value, final String label) {
        this.value = value;
        this.label = label;
    }
    
    public static String getLableByValue(final String value) {
        if (value == null) {
            return "";
        }
        for (final StatusEnum us : values()) {
            if (us.getValue().equals(value)) {
                return us.getLabel();
            }
        }
        return "";
    }
    
    public static StatusEnum getByValue(final String value) {
        if (value != null) {
            for (final StatusEnum statusEnum : values()) {
                if (statusEnum.getValue().equals(value)) {
                    return statusEnum;
                }
            }
        }
        return null;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getLabel() {
        return this.label;
    }
}
