package com.tjfae.notice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security配置类
 * 开发环境配置：禁用CSRF，允许所有请求访问
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs", "/webjars/**").permitAll()
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/api/**", "/arbitrate/**", "/arbitrates/**", "/notify/**").permitAll()
            .anyRequest().permitAll()
            .and()
            .logout().permitAll();
    }
}
