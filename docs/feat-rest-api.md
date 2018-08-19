# 支持REST API

* Spring MVC
* Jackson JSON

## MvcConfig

```java
@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		// 添加 Jackson JSON的支持
		converters.add(new MappingJackson2HttpMessageConverter());
	}
}
```

## MvcConfig 纳入到应用的配置

```java
@Configuration
@ComponentScan(basePackages = { "com.waylau.lite" })  
@Import({MvcConfig.class})
public class AppConfig {

}
```

## 编写REST API

```java
@RestController
@RequestMapping("/lite")
public class LiteController {
	
	@GetMapping("/hi")
	public Lite sayHi() {
		return new Lite("waylau.com", "1.0.0");
	}

}
```

## 运行

访问

<http://localhost:8080/lite/hi>

返回

```json
{"author":"waylau.com","version":"1.0.0"}
```
