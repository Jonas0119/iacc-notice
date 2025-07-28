// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.enums;

public enum SourceTypeEnum
{
    WEB("web", "\u7ba1\u7406\u540e\u7ba1", "1"), 
    PC("zssj", "PC\u5b98\u7f51", "1"), 
    AUCTION("auction", "\u62cd\u5356\u5927\u5385", "1"), 
    ALLAUC("allauc", "\u5168\u62cd\u7f51", "2"), 
    GUOPAI("guopai", "\u56fd\u62cd", "2");
    
    private String value;
    private String label;
    private String type;
    
    private SourceTypeEnum(final String value, final String label, final String type) {
        this.value = value;
        this.label = label;
        this.type = type;
    }
    
    public static String getLableByValue(final String s) {
        if (s != null) {
            for (final SourceTypeEnum sourceTypeEnum : values()) {
                if (sourceTypeEnum.getValue().equals(s)) {
                    return sourceTypeEnum.getLabel();
                }
            }
        }
        return "";
    }
    
    public static SourceTypeEnum getByValue(final String s) {
        if (s != null) {
            for (final SourceTypeEnum sourceTypeEnum : values()) {
                if (sourceTypeEnum.getValue().equals(s)) {
                    return sourceTypeEnum;
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
    
    public String getType() {
        return this.type;
    }
}
