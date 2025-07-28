// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.string;

import java.util.UUID;
import java.util.Random;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.LinkedList;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
    public static Long[] parseLongArray(String input) {
        if (input == null || input.length() == 0) {
            return null;
        }
        input = input.trim();
        input = input.replace("\"", "");
        input = input.replace("'", "");
        input = input.replace("\uff0c", ",");
        final String[] idstr = input.split(",");
        final Long[] idlongs = new Long[idstr.length];
        for (int i = 0; i < idlongs.length; ++i) {
            idlongs[i] = Long.parseLong(idstr[i]);
        }
        return idlongs;
    }
    
    public static List<Long> StringToLongList(String inputs) {
        if (isBlank(inputs)) {
            return new ArrayList<Long>();
        }
        inputs = inputs.trim();
        inputs = inputs.replace("\"", "");
        inputs = inputs.replace("'", "");
        if (inputs.startsWith(",")) {
            inputs = inputs.substring(1, inputs.length());
        }
        if (inputs.endsWith(",")) {
            inputs = inputs.substring(0, inputs.length() - 1);
        }
        final String[] idstr = inputs.split(",");
        final Set<Long> longList = new LinkedHashSet<Long>();
        for (int i = 0; i < idstr.length; ++i) {
            longList.add(Long.parseLong(idstr[i]));
        }
        return new LinkedList<Long>(longList);
    }
    
    public static List<String> StringToStringList(String inputs) {
        if (isBlank(inputs)) {
            return new ArrayList<String>();
        }
        inputs = inputs.trim();
        inputs = inputs.replace("\"", "");
        inputs = inputs.replace("'", "");
        final String[] str = inputs.split(",");
        final List<String> StringList = new ArrayList<String>();
        for (int i = 0; i < str.length; ++i) {
            StringList.add(str[i]);
        }
        return StringList;
    }
    
    public static List<String> StringToStringListByEnter(String inputs) {
        if (isBlank(inputs)) {
            return new ArrayList<String>();
        }
        inputs = inputs.trim();
        final String[] str = inputs.split("\n");
        final List<String> StringList = new ArrayList<String>();
        for (int i = 0; i < str.length; ++i) {
            StringList.add(str[i]);
        }
        return StringList;
    }
    
    public static String StringListToStringWithEnter(final List<String> input) {
        if (input == null || input.size() == 0) {
            return "";
        }
        return join((List)input, '\n');
    }
    
    public static boolean isEmpty(final Collection<?> coll) {
        return isNull((Object)coll) || coll.isEmpty();
    }
    
    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty((Collection)coll);
    }
    
    public static boolean isEmpty(final Object[] objects) {
        return isNull((Object)objects) || objects.length == 0;
    }
    
    public static boolean isNotEmpty(final Object[] objects) {
        return !isEmpty(objects);
    }
    
    public static boolean isEmpty(final Map<?, ?> map) {
        return isNull((Object)map) || map.isEmpty();
    }
    
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty((Map)map);
    }
    
    public static boolean isEmpty(final String str) {
        return isNull((Object)str) || "".equals(str.trim());
    }
    
    public static boolean isNotEmpty(final String str) {
        return !isEmpty(str);
    }
    
    public static boolean isNull(final Object object) {
        return object == null;
    }
    
    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }
    
    public static boolean isNotBlank(final String input) {
        return input != null && !input.isEmpty();
    }
    
    public static boolean isBlank(final String input) {
        return input == null || input.isEmpty();
    }
    
    public static String join(final List<String> input, final char port) {
        return org.apache.tomcat.util.buf.StringUtils.join((Collection)input, port);
    }
    
    public static String joinLong(final List<Long> inputs, final char port) {
        if (inputs == null || inputs.size() == 0) {
            return "";
        }
        final List<String> newInput = new ArrayList<String>();
        for (final Long input : inputs) {
            newInput.add("" + input);
        }
        return org.apache.tomcat.util.buf.StringUtils.join((Collection)newInput, port);
    }
    
    public static Long StringToLong(final String id) {
        try {
            return Long.valueOf(id);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
    
    public static String stripHtml(String input) {
        if (isBlank(input)) {
            return null;
        }
        input = input.replaceAll("<p .*?>", "\r\n");
        input = input.replaceAll("<br\\s*/?>", "\r\n");
        input = input.replaceAll("\\<.*?>", "");
        return input;
    }
    
    public static String randomCode(int length) {
        Random random;
        String code;
        for (random = new Random(), code = ""; length > 0; --length, code += random.nextInt(10)) {}
        return code;
    }
    
    public static String hideMiddle(final String input, final int length) {
        if (length <= 0) {
            return input;
        }
        if (isBlank(input)) {
            return "****";
        }
        if (input.length() <= length) {
            return "****";
        }
        final String start = input.substring(0, (input.length() - length) / 2);
        final String end = input.substring(input.length() - (input.length() - length) / 2, input.length());
        final StringBuffer str = new StringBuffer();
        for (int i = 0; i < length - 1; ++i) {
            str.append("*");
        }
        return start + (Object)str + end;
    }
    
    public static String cut(final String input, final int index) {
        if (isBlank(input)) {
            return input;
        }
        if (index < 0) {
            return input;
        }
        if (input.length() < index + 1) {
            return input;
        }
        return input.substring(0, index);
    }
    
    public static Integer stringToInteger(final String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        }
        catch (final Exception e) {
            return null;
        }
    }
    
    public static final String uuid() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid;
    }
}
