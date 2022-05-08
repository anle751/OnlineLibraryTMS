# library
Этот проект представляет собой онлайн библиотеку
В которой есть возможность читать и делится своими книгами в процессе написания (При регистрации как автор)

Используемые в проекте технологии:
1. Spring Boot
2. Spring Boot MVC
3. Spring Security
4. Spring Data JPA
5. Hibernate
6. Hibernate Validation
7. DB H2 (Используется для удобства запуска приложения, есть возможность использования базы данных MySQL)
8. HTML + Bootstrap + thymeleaf
9. Lombok
10. Logging log4j2

Note 1:
Для подключения вместо H2 mySQL необходимо внести следующие изменения
1. в файл pom.xml заменить эти строки:
   `<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <scope>runtime</scope>
   </dependency>`
2.
на эти:
`        <dependency>
<groupId>mysql</groupId>
<artifactId>mysql-connector-java</artifactId>
<scope>runtime</scope>
</dependency>`
2. в файле /src/main/resources/application.properties заменить строки
   `spring.h2.console.enabled=true`

на эти:

`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/testLib
spring.datasource.username=root
spring.datasource.password=12345678Root
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true`

указав верный путь к базе данных, пользователя и пароль

**_Внимание_!**
База данных должна быть предварительно создана!