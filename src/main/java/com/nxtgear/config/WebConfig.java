package com.nxtgear.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.nxtgear.pojo.Student;

/**
* @author: Patrick F
* @Date:Apr 17, 2017
* Spring configuration for all web elements and Hibernate
**/

@ComponentScan(basePackages = { "com.nxtgear" })
@Configuration
@EnableTransactionManagement
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
   /**
    * Bean for view resolver - web pages stored in Web-INF/views
    * 
    */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    /**
     * Configured to allow static resources through
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    /**
     * Database information required by hibernate
     * 
     */
    @Bean
    public DataSource dataSource() {//config for DB
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:patsdb");
        dataSource.setUsername("C##PATRICK");
        dataSource.setPassword("madcat");
         
        return dataSource;
    }
 
    /**
     * Hibernate configuration, sessionFactory bean
     * 
     */
    @Bean 
    public LocalSessionFactoryBean sessionFactory(){//Hibernate Bootstrap, uses datasource
     Properties hibernateProperties = new Properties();
     hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
     hibernateProperties.put("hibernate.show_sql", "true");
     hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
     
     LocalSessionFactoryBean bean=new LocalSessionFactoryBean();
     bean.setDataSource(dataSource());
     bean.setHibernateProperties(hibernateProperties);
     bean.setAnnotatedClasses(new Class<?>[] { Student.class});
     //bean.setAnnotatedPackages("com.spring.pojo");
     
     return bean;
    }
    /**
     * Spring transaction manager for hibernate
     * 
     */
	@Bean
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager manager=new HibernateTransactionManager();
		manager.setSessionFactory(sessionFactory().getObject());
		  
		return manager;
	}
}