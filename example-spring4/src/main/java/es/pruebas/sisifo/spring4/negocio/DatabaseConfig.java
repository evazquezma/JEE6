package es.pruebas.sisifo.spring4.negocio;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.engine.transaction.jta.platform.internal.JBossAppServerJtaPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@Bean
	public DataSource datasource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup("java:jboss/datasources/ExampleDS");
	}

	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(final DataSource datasource) {
		final LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(datasource);

		final Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.HSQLDialect.class);

		props.put("hibernate.show_sql", true);
		props.put("hibernate.format_sql", true);
		props.put("hibernate.hbm2ddl.auto", "create");

		props.put("hibernate.transaction.coordinator_class", "jta");
		props.put("hibernate.transaction.jta.platform", JBossAppServerJtaPlatform.class);


		factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedPackages("es.pruebas.sisifo.spring4.persistencia.entity");
		return factoryBean;
	}

	@Bean
	public PlatformTransactionManager getTransactionManager() {
		return new JtaTransactionManager();
	}
}
