// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.utils;

import org.springframework.core.io.Resource;
import java.io.IOException;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.ClassPathResource;
import java.util.Properties;

public class SystemConfig
{
    private static Properties props;
    
    public SystemConfig() {
        try {
            Resource resource = (Resource)new ClassPathResource("/application.yml");
            EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
            SystemConfig.props = PropertiesLoaderUtils.loadProperties(encodedResource);
            final String truePath = "/application-" + SystemConfig.props.getProperty("spring.profiles.active") + ".properties";
            resource = (Resource)new ClassPathResource(truePath);
            encodedResource = new EncodedResource(resource, "utf-8");
            SystemConfig.props = PropertiesLoaderUtils.loadProperties(encodedResource);
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(final String key) {
        if (SystemConfig.props == null) {
            new SystemConfig();
        }
        return (SystemConfig.props == null) ? null : SystemConfig.props.getProperty(key);
    }
    
    public static String getProperty(final String key, final String defaultValue) {
        return (SystemConfig.props == null) ? null : SystemConfig.props.getProperty(key, defaultValue);
    }
    
    public static Properties getProperties() {
        return SystemConfig.props;
    }
}
