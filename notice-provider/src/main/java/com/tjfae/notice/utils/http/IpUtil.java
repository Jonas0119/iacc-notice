// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils.http;

import java.net.UnknownHostException;
import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class IpUtil
{
    private static Log logger;
    
    public static String getIpAddr(final HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    }
                    catch (final UnknownHostException e) {
                        IpUtil.logger.error((Object)e.getMessage(), (Throwable)e);
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            if (ipAddress != null && ipAddress.length() > 15 && ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        catch (final Exception e2) {
            ipAddress = "";
        }
        return ipAddress;
    }
    
    static {
        IpUtil.logger = LogFactory.getLog((Class)IpUtil.class);
    }
}
