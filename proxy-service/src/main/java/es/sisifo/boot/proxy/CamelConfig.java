package es.sisifo.boot.proxy;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

	@Bean
	public RouteBuilder routeBuilder() {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("cxf:bean:servicePublisher?dataFormat=POJO")
				.log(LoggingLevel.INFO,"Doing some stuff")
				.to("cxf:bean:serviceConsumer?dataFormat=POJO");
			}
		};
	}
}
