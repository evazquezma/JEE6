package es.sisifo.boot.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	if (args.length == 0) {
    		System.out.println("Usage: --propertyFile=/home/xxx/envConfig.properties");
    		System.exit(-1);
    	}


        SpringApplication.run(Application.class, args);
        try {
			Thread.currentThread().join();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
    }

}