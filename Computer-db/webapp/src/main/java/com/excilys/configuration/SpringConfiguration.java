package com.excilys.configuration;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
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
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({ "com.excilys.service", "com.excilys.CrudRepository", "com.excilys.controller",
		"com.excilys.configuration", "com.excilys.model","com.excilys.mapper" })
@EnableTransactionManagement
@EnableJpaRepositories("com.excilys.DAO")

public class SpringConfiguration {

	private static AnnotationConfigWebApplicationContext context;

	public static AnnotationConfigWebApplicationContext getContext() {
		if (context == null) {
			context = new AnnotationConfigWebApplicationContext();
			context.register(JdbcSpringConfiguration.class);
			context.register(SpringConfiguration.class);
			context.register(MvcConfiguraiton.class);
			context.register(SecurityWebApplicationInitializer.class);
			context.register(SpringSecurityConfiguration.class);
			context.refresh();
		}
		return context;
	}



}