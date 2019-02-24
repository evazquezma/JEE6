package es.pruebas.sisifo.spring4.negocio;

import java.util.Properties;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@Configuration
public class PropertiesConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(final Properties customProperties) {
    	LOGGER.info("Inicializando ficheros de configuración");
        final PropertySourcesPlaceholderConfigurer placeHolder = new PropertySourcesPlaceholderConfigurer();
        placeHolder.setProperties(customProperties);
        return placeHolder;
    }

    @Bean(name = "customProperties")
    public PropertiesFactoryBean customProperties() throws NamingException {
        final PropertiesFactoryBean factory = new PropertiesFactoryBean();
        factory.setLocations(propertiesResources());
        return factory;
    }

    @Bean(name = "propertiesResources")
    public Resource[] propertiesResources() throws NamingException {
    	return new Resource[] { new ClassPathResource("conf.properties") };
    	/*
    	try {
    		final Resource resourceConfProperties = new ClassPathResource("conf.properties");
    		final Resource resourceExterno = new FileSystemResource((String) new JndiTemplate().lookup("java:/ic2/ic2-services-properties"));
    		return new Resource[] {resourceConfProperties, resourceExterno};
        }
        catch(final Exception e) {
            LOGGER.warn("Error al cargar los ficheros de configuración de la aplicación. Se requiere un conf.properties interno y una clave JNDI 'java:/ic2/ic2-services-properties'", e);
            throw new RuntimeException("Error al cargar ficheros de configuración", e);
        }
        */
    }

}
