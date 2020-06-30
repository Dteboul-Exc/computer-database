package com.excilys.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan({"com.excilys.service","com.excilys.persistence","com.excilys.servlet","com.excilys.spring"})
@EnableTransactionManagement
@EnableJpaRepositories("sample.data.jpa.service")
public class SpringConfiguration {
	
	private  static AnnotationConfigWebApplicationContext context;
	public static AnnotationConfigWebApplicationContext getContext() {
		if (context == null)
		{
			context = new AnnotationConfigWebApplicationContext();
			context.register(SpringConfiguration.class);
			context.register(MvcConfiguraiton.class);
			context.refresh();
		}
		return context;
	}
	
	@Bean
	public HikariConfig hikariConfig() {
		return new HikariConfig("/db.properties");
	}
	
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		return new JdbcTemplate(getHikariDataSource());
	}
	
	
	@Bean
	public NamedParameterJdbcTemplate NamedParameterJdbcTemplate()
	{
		return new NamedParameterJdbcTemplate(getHikariDataSource());
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
	    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	    interceptor.setParamName("mylocale");
	    registry.addInterceptor(interceptor);
	} 
	
	@Bean
	@Scope("singleton")
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    factory.setPackagesToScan("com.acme.domain");
	    factory.setDataSource(getHikariDataSource());
	    return factory;
	  }

	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory);
	    return txManager;
	  }
}