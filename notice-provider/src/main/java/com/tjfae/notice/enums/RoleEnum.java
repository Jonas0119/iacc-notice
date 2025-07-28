// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.enums;

public enum RoleEnum
{
    SUBMIT("1", "\u63d0\u4ea4\u4eba"), 
    AUDIT("2", "\u8fd0\u8425\u5ba1\u6279");
    
    private String value;
    private String label;
    
    private RoleEnum(final String value, final String label) {
        this.value = value;
        this.label = label;
    }
    
    public static String getLableByValue(final String value) {
        if (value == null) {
            return "";
        }
        for (final RoleEnum us : values()) {
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
