package com.example.demo.hibernateConfig;

import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value={"classpath:application.properties"})
public class HibernateConfiguration {

	@Value("${jdbc.driverClassname}")
	private String driverClass;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
    
	@Value("${hibernate.dialect}")
	private String dialect;
	
	
	//A factory for connections to the physical data source that this DataSource object represents. 
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url,username,password);
		dataSource.setDriverClassName(driverClass);
		return dataSource;
	}
	
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2dll.auto", "update");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}
	
	
	//FactoryBean that creates a Hibernate SessionFactory. 
	//This is the usual way to set up a shared Hibernate SessionFactory in a Spring 
	//application context; the SessionFactory can then be passed to data access objects via dependency injection.
	@Bean
	@Primary
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan(new String[] {""});
		return sessionFactory;
	}
	
	
	//Binds a Hibernate Session from the specified factory to the thread, potentially allowing for one thread-bound Session per factory. 
	//SessionFactory.getCurrentSession() is required for Hibernate access code that needs to support this transaction handling mechanism, 
	//with the SessionFactory being configured with SpringSessionContext.
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
	
	
	//entityManager can be used instead of sessionFactory
	//Spring container creates the objects, wire them together, configure them, and manage their complete life cycle from creation till destruction. 
	//to use entity class objects, EntityManager must be set up first
	//@Bean(name="entityManagerFactory")
	//public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
	//    LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
	//    lcemfb.setDataSource(getDataSource());
	//    lcemfb.setPackagesToScan("com.example.demo.entity");
	//    lcemfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
	//    lcemfb.setPersistenceUnitName("localEntity");
	//    return lcemfb;
	//}
	
	
	
	
	
	
	
	
}













