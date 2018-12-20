/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.mvc;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC Configuration.
 * 
 * @since 1.0.0 2018年8月19日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@EnableWebMvc
@Configuration
public class LiteMvcConfig implements WebMvcConfigurer {

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		// 添加 Jackson JSON的支持
		converters.add(new MappingJackson2HttpMessageConverter());
	}
}
