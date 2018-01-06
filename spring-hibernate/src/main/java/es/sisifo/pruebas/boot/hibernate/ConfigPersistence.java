package es.sisifo.pruebas.boot.hibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class ConfigPersistence {


	@Bean
	// @ConfigurationProperties("spring.datasource")
	public DataSource dataSource() {
		// return
		// DataSourceBuilder.create().type(HikariDataSource.class).build();
		final HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(100);
		ds.setDataSourceClassName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
		ds.addDataSourceProperty("url", "jdbc:sqlserver://windowsServer-R2:1433;databaseName=Firma");
		ds.addDataSourceProperty("user", "firma");
		ds.addDataSourceProperty("password", "frm");
		return ds;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory(final DataSource datasource) {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setPackagesToScan(new String[] { "es.sisifo.pruebas.boot.hibernate.persistencia.entity" });
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	private Properties getHibernateProperties() {
		final Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.SQLServer2008Dialect");
		properties.put(AvailableSettings.SHOW_SQL, "true");
		properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(final SessionFactory sessionFactory) {
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
