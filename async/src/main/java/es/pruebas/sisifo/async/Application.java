package es.pruebas.sisifo.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@Configuration
@ComponentScan(basePackages = { "es.pruebas.sisifo.async" })
@EnableAutoConfiguration
// The @SpringBootApplication annotation is equivalent to using @Configuration,
// @EnableAutoConfiguration and @ComponentScan with their default attributes:
public class Application {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    
    }

}