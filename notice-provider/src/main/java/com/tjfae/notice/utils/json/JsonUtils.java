// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.json;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Iterator;
import org.springframework.cglib.beans.BeanMap;
import com.google.common.collect.Maps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import java.util.ArrayList;
import com.tjfae.notice.utils.string.StringUtils;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.core.JsonParseException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils
{
    private static ObjectMapper JSON;
    
    public static List convertJson2List(final String json) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<LinkedHashMap<String, Object>> list = null;
        try {
            list = (List)objectMapper.readValue(json, (Class)List.class);
        }
        catch (final JsonParseException e) {
            throw e;
        }
        catch (final JsonMappingException e2) {
            throw e2;
        }
        catch (final IOException e3) {
            throw e3;
        }
        return list;
    }
    
    public static Map<String, Object> convertJson2Map(final String json) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = (Map)objectMapper.readValue(json, (Class)Map.class);
        }
        catch (final JsonParseException e) {
            throw e;
        }
        catch (final JsonMappingException e2) {
            throw e2;
        }
        catch (final IOException e3) {
            throw e3;
        }
        return map;
    }
    
    public static String writeEntityJSON(final Object obj) {
        final ObjectMapper objectMapper = new ObjectMapper();
        String res = null;
        try {
            res = objectMapper.writeValueAsString(obj);
        }
        catch (final IOException e) {
            return "json\u5b57\u7b26\u4e32\u8f6c\u6362\u5931\u8d25";
        }
        return res;
    }
    
    public static Map<String, String> convertEntity2Map(final Object obj) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        Map<String, String> map = null;
        try {
            json = objectMapper.writeValueAsString(obj);
            map = (Map)objectMapper.readValue(json, (Class)Map.class);
        }
        catch (final IOException e) {
            throw e;
        }
        return map;
    }
    
    public static <T, E> T jsonStr2Object(final String content, final Class cls) throws IOException {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        final ObjectMapper objectMapper = new ObjectMapper();
        final T obj = (T)objectMapper.readValue(content, cls);
        return obj;
    }
    
    public static <T> List<T> json2List(final String json, final Class<T> tClass) throws Exception {
        if (json == null || json.equals("")) {
            return null;
        }
        final ObjectMapper objectMapper = new ObjectMapper();
        final JavaType javaType = objectMapper.getTypeFactory().constructParametricType((Class)ArrayList.class, new Class[] { tClass });
        final List<T> list = (List<T>)objectMapper.readValue(json, javaType);
        return list;
    }
    
    public static String toJson(final Object obj) {
        try {
            return JsonUtils.JSON.writeValueAsString(obj);
        }
        catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static <T> Map<String, Object> beanToMap(final T bean) {
        final Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            final BeanMap beanMap = BeanMap.create((Object)bean);
            for (final Object key : beanMap.keySet()) {
                map.put(key.toString(), beanMap.get(key));
            }
        }
        return map;
    }
    
    static {
        (JsonUtils.JSON = new ObjectMapper()).setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JsonUtils.JSON.configure(SerializationFeature.INDENT_OUTPUT, (boolean)Boolean.TRUE);
    }
}
