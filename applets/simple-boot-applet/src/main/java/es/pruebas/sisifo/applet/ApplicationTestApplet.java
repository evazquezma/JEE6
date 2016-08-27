package es.pruebas.sisifo.applet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class ApplicationTestApplet  {
	private static Class<ApplicationTestApplet> applicationClass = ApplicationTestApplet.class;

    public static void main(final String[] args) {
        final ApplicationContext ctx = SpringApplication.run(applicationClass, args);

    }
}