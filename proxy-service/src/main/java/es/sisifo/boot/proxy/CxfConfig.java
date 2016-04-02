package es.sisifo.boot.proxy;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.common.gzip.GZIPInInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.javacodegeeks.ws.product_service.Product;
import com.javacodegeeks.ws.product_service.ProductPortImpl;

@Configuration
@ImportResource("classpath:/spring/conduit-config.xml")
public class CxfConfig {

	@Value("${service.publish.localEndpoint}")
	private String localEndPoint;

	@Value("${service.client.remoteEndpoint}")
	private String remoteEndPoint;


	@Bean
	public LoggingInInterceptor loggingInInterceptor() {
		final LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
		loggingInInterceptor.setPrettyLogging(true);
		return loggingInInterceptor;
	}


	@Bean
	public LoggingOutInterceptor loggingOutInterceptor() {
		final LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
		loggingOutInterceptor.setPrettyLogging(true);
		return loggingOutInterceptor;
	}


	@Bean
	public GZIPInInterceptor gzipInInterceptor() {
		return new GZIPInInterceptor();
	}


	@Bean
	public CxfEndpoint servicePublisher(final CamelContext camelContext) {
		 final CxfComponent cxfComponent = new CxfComponent(camelContext);

		 final CxfEndpoint serviceEndpoint = new CxfEndpoint("", cxfComponent);
		 serviceEndpoint.setServiceClass(ProductPortImpl.class);
		 serviceEndpoint.setAddress(localEndPoint);
		 serviceEndpoint.setWsdlURL("/wsdl/service/productService.wsdl");

		 serviceEndpoint.setServiceNameString("{http://ws.javacodegeeks.com/product-service}ProductService");
		 serviceEndpoint.setEndpointNameString("ProductPort");

		 serviceEndpoint.setDataFormat(DataFormat.POJO);

		 serviceEndpoint.setLoggingFeatureEnabled(true);
		 serviceEndpoint.getInInterceptors().add(gzipInInterceptor());
		 serviceEndpoint.getInInterceptors().add(loggingInInterceptor());
		 serviceEndpoint.getOutInterceptors().add(loggingOutInterceptor());

		 return serviceEndpoint;
	}


	@Bean
	public CxfEndpoint serviceConsumer(final CamelContext camelContext) {
		 final CxfComponent cxfComponent = new CxfComponent(camelContext);

		 final CxfEndpoint serviceEndpoint = new CxfEndpoint("", cxfComponent);
		 serviceEndpoint.setServiceClass(Product.class);
		 serviceEndpoint.setAddress(remoteEndPoint);
		 serviceEndpoint.setWsdlURL("/wsdl/service/productService.wsdl");

		 serviceEndpoint.setServiceNameString("{http://ws.javacodegeeks.com/product-service}ProductService");
		 serviceEndpoint.setEndpointNameString("ProductPort");

		 serviceEndpoint.setDataFormat(DataFormat.POJO);

		 serviceEndpoint.setLoggingFeatureEnabled(true);
		 serviceEndpoint.getInInterceptors().add(gzipInInterceptor());
		 serviceEndpoint.getInInterceptors().add(loggingInInterceptor());
		 serviceEndpoint.getOutInterceptors().add(loggingOutInterceptor());

		 return serviceEndpoint;
	}
}
