// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.enums;

public enum PlatformTypeEnum
{
    ZF("01", "\u4e2d\u56fd\u53d1\u5c55\u7f51"), 
    ZB("02", "\u4e2d\u56fd\u62a5\u4e1a\u7f51");
    
    private String value;
    private String label;
    
    private PlatformTypeEnum(final String value, final String label) {
        this.value = value;
        this.label = label;
    }
    
    public static String getLableByValue(final String value) {
        if (value == null) {
            return "";
        }
        for (final PlatformTypeEnum us : values()) {
            if (us.getValue().equals(value)) {
                return us.getLabel();
            }
        }
        return "";
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getLabel() {
        return this.label;
    }
}
