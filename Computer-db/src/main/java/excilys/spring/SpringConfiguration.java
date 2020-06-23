package main.java.excilys.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan({"main.java.excilys.service","main.java.excilys.persistence","main.java.excilys.servlet"})

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
	@Scope("singleton")
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
}