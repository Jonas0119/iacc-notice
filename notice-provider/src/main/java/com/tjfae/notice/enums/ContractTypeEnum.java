// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.enums;

public enum ContractTypeEnum
{
    SFCZ("01", "\u53f8\u6cd5\u5b58\u8bc1"), 
    ZSCQ("02", "\u77e5\u8bc6\u4ea7\u6743"), 
    SCCZ("03", "\u6eaf\u6e90\u5b58\u8bc1"), 
    SWCZ("04", "\u5546\u52a1\u5b58\u8bc1"), 
    JRCZ("05", "\u91d1\u878d\u5b58\u8bc1"), 
    DWZXCZ("06", "\u5355\u4f4d\u8d44\u4fe1\u5b58\u8bc1"), 
    GRZXCZ("07", "\u4e2a\u4eba\u8d44\u4fe1\u5b58\u8bc1"), 
    DWGZJL("08", "\u5355\u4f4d\u5de5\u4f5c\u8bb0\u5f55"), 
    QT("09", "\u5176\u4ed6\u5b58\u8bc1");
    
    private String value;
    private String label;
    
    private ContractTypeEnum(final String value, final String label) {
        this.value = value;
        this.label = label;
    }
    
    public static String getLableByValue(final String value) {
        if (value == null) {
            return "";
        }
        for (final ContractTypeEnum us : values()) {
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
