// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.http;

import com.tjfae.notice.utils.string.StringUtils;
import com.tjfae.notice.utils.string.Format;
import java.math.BigDecimal;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;

public class HttpUtils
{
    public static <T> T FormatRequest(final HttpServletRequest request, final Class Clazz) {
        T dtoObj = null;
        if (Clazz == null || request == null) {
            return dtoObj;
        }
        try {
            dtoObj = (T) Clazz.newInstance();
            setDTOValue(request, dtoObj);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return dtoObj;
    }
    
    public static <T> T FormatRequest(final HttpServletRequest request, final T object) {
        if (object == null || request == null) {
            return object;
        }
        try {
            setDTOValue(request, object);
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    public static void setDTOValue(final HttpServletRequest request, final Object dto) throws Exception {
        if (dto == null || request == null) {
            return;
        }
        final Method[] methods = dto.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            try {
                final String methodName = methods[i].getName();
                final Class[] type = methods[i].getParameterTypes();
                if (methodName.length() > 3 && methodName.startsWith("set") && type.length == 1) {
                    final String name = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    final Object objValue = getBindValue(request, name, type[0]);
                    if (objValue != null) {
                        final Object[] value = { objValue };
                        invokeMothod(dto, methodName, type, value);
                    }
                }
            }
            catch (final Exception ex) {
                throw ex;
            }
        }
    }
    
    public static Object getBindValue(final HttpServletRequest request, final String bindName, final Class bindType) {
        String value = request.getParameter(bindName);
        if (value != null) {
            value = value.trim();
        }
        return getBindValue(value, bindType);
    }
    
    public static Object invokeMothod(final Object classObject, final String strMethodName, final Class[] argsType, final Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Method concatMethod = classObject.getClass().getMethod(strMethodName, (Class<?>[])argsType);
        return concatMethod.invoke(classObject, args);
    }
    
    public static Object getBindValue(String value, final Class bindType) {
        if (value == null) {
            return null;
        }
        final String typeName = bindType.getName();
        if (value.trim().length() == 0) {
            if (typeName.equals("java.lang.String")) {
                return value;
            }
            return null;
        }
        else {
            if (typeName.equals("java.lang.String")) {
                return value;
            }
            if (typeName.equals("int")) {
                value = value.replaceAll(",", "");
                value = value.replaceAll("\uff0c", "");
                return Integer.valueOf(value);
            }
            if (typeName.equals("long")) {
                value = value.replaceAll(",", "");
                value = value.replaceAll("\uff0c", "");
                return Long.valueOf(value);
            }
            if (typeName.equals("boolean")) {
                value = value.replaceAll(",", "");
                value = value.replaceAll("\uff0c", "");
                return Boolean.valueOf(value);
            }
            if (typeName.equals("float")) {
                value = value.replaceAll(",", "");
                value = value.replaceAll("\uff0c", "");
                return Float.valueOf(value);
            }
            if (typeName.equals("double")) {
                value = value.replaceAll(",", "");
                value = value.replaceAll("\uff0c", "");
                return Double.valueOf(value);
            }
            if (typeName.equals("java.math.BigDecimal")) {
                if ("NaN.00".equals(value)) {
                    return new BigDecimal("0");
                }
                return new BigDecimal(value.trim());
            }
            else {
                if (typeName.equals("java.util.Date")) {
                    return Format.formatDate(value);
                }
                if (typeName.equals("java.lang.Integer")) {
                    value = value.replaceAll(",", "");
                    value = value.replaceAll("\uff0c", "");
                    return Integer.valueOf(value);
                }
                if (typeName.equals("java.lang.Long")) {
                    value = value.replaceAll(",", "");
                    value = value.replaceAll("\uff0c", "");
                    return Long.valueOf(value);
                }
                if (typeName.equals("java.lang.Boolean")) {
                    return Boolean.valueOf(value);
                }
                if (typeName.equals("java.lang.Float")) {
                    value = value.replaceAll(",", "");
                    value = value.replaceAll("\uff0c", "");
                    return Float.valueOf(value);
                }
                if (typeName.equals("java.lang.Double")) {
                    value = value.replaceAll(",", "");
                    value = value.replaceAll("\uff0c", "");
                    return Double.valueOf(value);
                }
                return value;
            }
        }
    }
    
    public static String getClientIP(final HttpServletRequest request) {
        String ipstr = request.getRemoteHost().toString();
        String proIp = null;
        try {
            if (request.getHeader("x-forwarded-for") != null) {}
            proIp = request.getHeader("x-forwarded-for").toString().split(",")[0];
            if (StringUtils.isNotBlank(proIp)) {
                ipstr = proIp;
            }
            return ipstr;
        }
        catch (final Exception e) {
            return ipstr;
        }
    }
}
