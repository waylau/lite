/**
 * Welcome to https://waylau.com
 */
package com.waylau.lite.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waylau.lite.Lite;

/**
 * Lite Controller
 * @since 1.0.0 2018年8月19日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@RestController
@RequestMapping("/lite")
public class LiteController {
	
	@GetMapping
	public Lite sayHi() {
		return new Lite("waylau.com", "1.0.2");
	}

}
