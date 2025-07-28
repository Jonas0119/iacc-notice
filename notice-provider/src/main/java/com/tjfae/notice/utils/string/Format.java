// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.string;

import java.text.ParsePosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format
{
    public static String formatCode(final long code, final int num) {
        String result = "";
        result = String.format("%0" + num + "d", code);
        return result;
    }
    
    public static String formatCode(final int code, final int num) {
        String result = "";
        result = String.format("%0" + num + "d", code);
        return result;
    }
    
    public static String formatDate(final Date date) {
        if (date == null) {
            return "";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    
    public static Date formatDateTime(final String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date o = new Date();
        try {
            o = sdf.parse(date);
        }
        catch (final ParseException e) {
            System.out.print("Date error" + e);
        }
        return o;
    }
    
    public static Date getNowDate() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        final ParsePosition pos = new ParsePosition(0);
        final Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }
    
    public static String getNowDateString() {
        final Date currentTime = new Date();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static Date formatDate(final String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date o;
        try {
            o = sdf.parse(date);
        }
        catch (final ParseException e) {
            System.out.print("Date not yyyy-MM-dd format" + e);
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                o = sdf.parse(date);
            }
            catch (final Exception ee) {
                System.out.print("Date not yyyy-MM-dd HH:mm:ss format" + e);
                return null;
            }
        }
        return o;
    }
    
    public static String formatDay(final Date date) {
        if (date == null) {
            return "";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    public static String diffTimme(final Date date) {
        if (date == null) {
            return "\u65f6\u95f4\u4e22\u5931";
        }
        final Date currentTime = new Date();
        long diff = (currentTime.getTime() - date.getTime()) / 60000L;
        if (diff < 5L) {
            return "\u521a\u521a";
        }
        if (diff < 60L) {
            return diff + "\u5206\u949f\u524d";
        }
        if (diff < 600L) {
            diff = (currentTime.getTime() - date.getTime()) / 3600000L;
            return diff + "\u5c0f\u65f6\u524d";
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
