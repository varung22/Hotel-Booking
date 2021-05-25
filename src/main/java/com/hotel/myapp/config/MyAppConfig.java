package com.hotel.myapp.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * It enables Transaction Management
 * @author Vidhusha
 * 
 *
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.hotel.myapp")
@PropertySource(value = { "classpath:persistence-mysql.properties" })
@PropertySource(value = { "classpath:application.properties" })
public class MyAppConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;
	
	
	/**
	 * @return Page Views
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * @return DataSource
	 * It is a connection given to Database
	 */

	@Bean
	public DataSource myDataSource() {

		// create connection pool
		BasicDataSource myDataSource = new BasicDataSource();
		// set database connection props
		myDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		myDataSource.setUrl(env.getProperty("jdbc.url"));
		myDataSource.setUsername(env.getProperty("jdbc.user"));
		myDataSource.setPassword(env.getProperty("jdbc.password"));

		return myDataSource;
	}
	
//	@Bean
//	public DataSource myDataSource() {
//
//		// create connection pool
//		ComboPooledDataSource myDataSource = new ComboPooledDataSource();
//
//		// set the jdbc driver
//		try {
//			myDataSource.setDriverClass("com.mysql.jdbc.Driver");
//		} catch (PropertyVetoException exc) {
//			throw new RuntimeException(exc);
//		}
//
//		// set database connection props
//		myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
//		myDataSource.setUser(env.getProperty("jdbc.user"));
//		myDataSource.setPassword(env.getProperty("jdbc.password"));
//
//		// set connection pool props
//		myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
//		myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
//		myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
//		myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
//
//		return myDataSource;
//	}

	/**
	 * @return Hibernate Properties
	 
	 */
	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl"));
		return props;
	}

	// need a helper method
	// read environment property and convert to int

	/**
	 * @param propName
	 * @return propValue
	 */
	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}

	/**
	 * @return sessionFactory
	 */
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {

		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		// set the properties
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());

		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 * @return Transaction Manager
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}

}