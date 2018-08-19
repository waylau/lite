/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.waylau.lite.mvc.MvcConfig;

/**
 * App Configuration.
 * 
 * @since 1.0.0 2018年8月18日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Configuration
@ComponentScan(basePackages = { "com.waylau.lite" })  
@Import({MvcConfig.class})
public class AppConfig {

}
