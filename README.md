## Demo project to demonstrate Spring Boot + Mybatis

### Dependencies
- PostgresSQL database
- `mybatis-spring-boot-starter` maven dependency (see pom file)
- JDK 17

### Key Takeaways
1. Configuring `mybatis` mapper with spring boot to expose via `@MapperScan` annotation as Beans
   
```java
@Configuration
@MapperScan("com.tapusd.springmybaties")
public class AppConfig {
}
```

2. Using `Mybatis` apis in `ArticleMapper.java` file to do `CRUD` operation
