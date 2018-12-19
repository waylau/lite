/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.mvc.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Configuration.
 * 
 * @since 1.0.0 2018年12月12日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@EnableWebSecurity // 启用Spring Security功能
public class WebSecurityConfig 
	extends WebSecurityConfigurerAdapter {

	/**
	 * 自定义配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		
		// 允许所有人访问
		http.authorizeRequests().anyRequest().anonymous();
	}

}