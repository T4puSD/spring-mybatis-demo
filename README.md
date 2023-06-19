## Demo project to demonstrate Spring Boot + Mybatis

### Dependencies
- PostgresSQL database
- `mybatis-spring-boot-starter` maven dependency (see pom file)
- JDK 17

### Key Takeaways
1. Configuring `mybatis` mapper with spring boot to expose via `@MapperScan` annotation as Beans   
   https://github.com/T4puSD/spring-mybatis-demo/blob/908fdb97f69df41ed7878195f35528bb3759f3d5/src/main/java/com/tapusd/springmybaties/config/AppConfig.java#LL6C1-L9C2

2. Using `Mybatis` apis in `ArticleMapper.java` file to do `CRUD` operation