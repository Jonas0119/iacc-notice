// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5Algs
{
    public static String getMD5(final String str) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            return bytesToHex1(md.digest());
        }
        catch (final Exception e) {
            System.out.println(e.toString());
            return "";
        }
    }
    
    public static String getMD5(final byte[] bytes) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            return bytesToHex1(md.digest());
        }
        catch (final Exception e) {
            System.out.println(e.toString());
            return "";
        }
    }
    
    private static String bytesToHex1(final byte[] md5Array) {
        final StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < md5Array.length; ++i) {
            final int temp = 0xFF & md5Array[i];
            final String hexString = Integer.toHexString(temp);
            if (hexString.length() == 1) {
                strBuilder.append("0").append(hexString);
            }
            else {
                strBuilder.append(hexString);
            }
        }
        return strBuilder.toString();
    }
}
