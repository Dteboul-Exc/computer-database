package com.excilys.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan({"com.excilys.service","com.excilys.persistence","com.excilys.servlet","com.excilys.spring"})
public class SpringConfiguration {
		
	private static AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext (SpringConfiguration.class);
	
	public static AnnotationConfigApplicationContext getContext() {
		return appContext;
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
	
	
	@Bean
	@Scope("singleton")
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
}