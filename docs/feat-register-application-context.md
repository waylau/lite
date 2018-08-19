# 支持传入外部上下文

## AnnotationConfigWebApplicationContext.register 方法

```java
private Class<?> annotatedClass;

public LiteJettyServer() {
}

public LiteJettyServer(Class<?> annotatedClass) {
	this.annotatedClass = annotatedClass;
}

private WebApplicationContext webApplicationContext() {
	AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	context.register(LiteConfig.class);
	
	if (this.annotatedClass != null) {
		context.register(this.annotatedClass); // 支持外部上下文
	}

	return context;
}
```

## 使用

AppConfig 就是一个上下文配置类：

```
public class App {
	public static void main(String[] args) {
		new LiteJettyServer(AppConfig.class).run(args);
	}
}
```