package com.bnb.common;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.bnb.portal.**.dao", sqlSessionFactoryRef="sqlSessionFactory")
@EnableTransactionManagement
public class MainDBConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.maindb.datasource")
	public PoolProperties getPoolProperties(){
		return new PoolProperties();
	}

	@Bean
	public DataSource getDataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(getPoolProperties());
		return dataSource;
	}
  
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getDataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:config/mysql/*.xml"));
		
//        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:config/mysql/mybatis-config.xml");
//        sqlSessionFactoryBean.setConfigLocation(myBatisConfig);
        
		return sqlSessionFactoryBean.getObject();
	}
    
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager manager = new DataSourceTransactionManager(getDataSource());
		return manager;
	} 
    
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

}
