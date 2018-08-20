# 数据访问层的实现


* 数据库链接池
* 基于 AOP 的事务管理
* 基于 MyBatis的数据库映射、操作


## 涉及技术点

* Spring JDBC 5.0.8.RELEASE
* Spring Aspects 5.0.8.RELEASE
* Mybatis 3.4.5
* Mybatis Spring 1.3.2
* MySQL Connector/J 8.0.12
* Apache Commons DBCP 2.5.0
* MySQL 8.0.12

## 启用AOP

```java
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

}
```

## 数据库链接池


```xml
<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	    <property name="locations" value="classpath:lite.properties"/>
	</bean>

<!-- 数据原 -->
<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
    <property name="driverClassName" value="${lite.jdbc.driverClassName}" />
    <property name="url" value="${lite.jdbc.url}" />
    <property name="username" value="${lite.jdbc.username}" />
    <property name="password" value="${lite.jdbc.password}" />
</bean>
```

## 基于 AOP 的事务管理


```xml
<!-- 事务管理 -->
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 事务dvice -->
<tx:advice id="txAdvice" transaction-manager="txManager">
    <tx:attributes>
		<!--get,find,list开头的方法设置为“只读” -->
        <tx:method name="get*" read-only="true" propagation="NOT_SUPPORTED" />
        <tx:method name="find*" read-only="true" propagation="NOT_SUPPORTED" />
        <tx:method name="list*" read-only="true" propagation="NOT_SUPPORTED" />
        
<!--其余方法使用默认事务 -->
<tx:method name="*" propagation="REQUIRED"/>
    </tx:attributes>
</tx:advice>

<!--配置 事务dvice -->
<aop:config>
    <aop:pointcut id="serviceOperation" expression="execution(* com..service.*.*(..)) || execution(* org..service.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceOperation"/>
</aop:config>
```

## 集成 MyBatis

```xml
<!-- MyBatis 工厂 -->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource" />
</bean>
```

使用时，在用户程序扫描要注入的 Mapper 接口：


```xml
<!-- 扫描 MyBatis Mapper 接口所在的包 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
  <property name="basePackage" value="com.waylau.lite.mall.mapper" />
</bean>
```

