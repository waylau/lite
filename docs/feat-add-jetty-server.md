# 添加Servlet容器

* 使用内嵌 Jetty 的方式。
* Spring 与  Jetty 整合。
* 整合 Logback
* 解析命令行参数
* 三种启动服务器的方式

## LiteServer

## LiteRuntimeException

## CommandLineArgs 及 CommandLineArgsParser

用于解析命令行参数。

## 三种启动方式

### 1. 不指定端口

```java
new LiteJettyServer().run(); // 默认端口8080
```

### 2. 编程方式指定端口

```java
new LiteJettyServer().run(8081);
```

### 3. 在命令行指定端口号

```java
new LiteJettyServer().run(args);
```

在命令行指定端口号

```
--port=8081
```