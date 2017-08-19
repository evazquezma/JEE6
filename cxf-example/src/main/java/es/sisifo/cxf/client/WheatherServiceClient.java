package es.sisifo.cxf.client;

import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import net.webservicex.GlobalWeatherSoap;

public class WheatherServiceClient {
	private GlobalWeatherSoap weatherService;


	public void setup() throws Exception {
		final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(GlobalWeatherSoap.class);
        factory.setAddress("http://www.webservicex.net/globalweather.asmx");
        factory.setUsername("test");
        factory.setPassword("testPass");
        weatherService = (GlobalWeatherSoap) factory.create();

        final Client client = ClientProxy.getClient(weatherService);
        configureConduit(client);
	}

	private void configureConduit(final Client client) throws Exception {
		final HTTPConduit conduit = (HTTPConduit) client.getConduit();

        final TLSClientParameters parameters = new TLSClientParameters();
        parameters.setSSLSocketFactory(createSSLContext().getSocketFactory());
        parameters.setDisableCNCheck(true);
        conduit.setTlsClientParameters(parameters);

        final HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(36000);
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(32000);
	}

	private SSLContext createSSLContext() throws Exception{
        final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(getClass().getResourceAsStream("src/main/resources/conduit/myTrustStore.jks"), "1234".toCharArray());

        final KeyStore identityStore = KeyStore.getInstance(KeyStore.getDefaultType());
        identityStore.load(getClass().getResourceAsStream("src/main/resources/conduit/myIdentityStore.jks"), "1234".toCharArray());


        final TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(trustStore);

        final KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(identityStore, "1234".toCharArray());

        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kmf.getKeyManagers() , tmf.getTrustManagers(), new SecureRandom());
        return sslContext;
    }



	public void run() {
		final String cities = weatherService.getCitiesByCountry("Germany");
		System.out.println(cities);
	}



	public static void main(final String[] args) throws Exception {
		final WheatherServiceClient wheatherService = new WheatherServiceClient();
		wheatherService.setup();

		wheatherService.run();
	}
}
