// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.enums;

public enum SceneTypeEnum
{
    AUCTION("1", "\u62cd\u5356"), 
    PMWW("01", "\u62cd\u5356\u6587\u7269"), 
    PMBLZC("02", "\u62cd\u5356\u4e0d\u826f\u8d44\u4ea7"), 
    GYCQJY("03", "\u56fd\u6709\u4ea7\u6743\u4ea4\u6613"), 
    BLJRZC("04", "\u4e0d\u826f\u91d1\u878d\u8d44\u4ea7"), 
    PPP("05", "PPP\u8d44\u4ea7\u4ea4\u6613"), 
    PCQS("06", "\u7834\u4ea7\u6e05\u7b97"), 
    ZFCG("07", "\u653f\u5e9c\u91c7\u8d2d"), 
    QHT("11", "\u4f01\u6052\u901a"), 
    QXINT("12", "\u4f01\u4fe1\u901a"), 
    QXIANGT("13", "\u4f01\u4eab\u901a"), 
    QZT("14", "\u4f01\u8f6c\u901a");
    
    private String value;
    private String label;
    
    private SceneTypeEnum(final String value, final String label) {
        this.value = value;
        this.label = label;
    }
    
    public static String getLableByValue(final String value) {
        if (value == null) {
            return "";
        }
        for (final SceneTypeEnum us : values()) {
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
