package com.bnb.common;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@PropertySource("classpath:application.properties")
public class MainPersistanceJPAConfig {

//	@Value("${spring.datasource.driverClassName}")
//	private String driverClassName;
//
//	@Value("${spring.datasource.url}")
//	private String url;
//	
//	@Value("${spring.datasource.username}")
//	private String userName;
//	
//	@Value("${spring.datasource.password}")
//	private String password;
//
//	@Bean
//	public DataSource dataSource(){
//	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
//	    dataSource.setDriverClassName(driverClassName);
//	    dataSource.setUrl(url);
//	    dataSource.setUsername(userName);
//	    dataSource.setPassword(password);
//	    return dataSource;
//	}

	@Bean
	@ConfigurationProperties(prefix="spring.mainjpa.datasource")
	public PoolProperties getJPAPoolProperties(){
		return new PoolProperties();
	}

	@Bean
	public DataSource getJPADataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(getJPAPoolProperties());
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	   LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	   em.setDataSource(getJPADataSource());
	   em.setPackagesToScan(new String[] {"com.bnb.portal.adm.entity"});
	   JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	   em.setJpaVendorAdapter(vendorAdapter);
	   em.setJpaProperties(additionalProperties());
	   return em;
	}
	
	@Bean
	public PlatformTransactionManager transactionJPAManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	    return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    properties.setProperty("hibernate.show_sql", "true");
	    properties.setProperty("hibernate.format_sql", "true");
	    return properties;
	}

}
