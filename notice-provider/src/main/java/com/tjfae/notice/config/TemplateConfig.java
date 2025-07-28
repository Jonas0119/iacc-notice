// 
// Decompiled by Procyon v0.6.0
// 

package com.tjfae.notice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateConfig
{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
