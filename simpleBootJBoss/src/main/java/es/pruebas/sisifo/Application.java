package es.pruebas.sisifo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



//@Configuration
//@ComponentScan(basePackages = { "es.pruebas.sisifo" })
//@EnableAutoConfiguration
@SpringBootApplication
// The @SpringBootApplication annotation is equivalent to using @Configuration,
// @EnableAutoConfiguration and @ComponentScan with their default attributes:
public class Application  {
	private static Class<Application> applicationClass = Application.class;
	
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(applicationClass, args);
    
    }
}