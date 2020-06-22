package main.java.exc.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan(basePackages = "main.java.exc")
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
	@Scope("singleton")
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
}