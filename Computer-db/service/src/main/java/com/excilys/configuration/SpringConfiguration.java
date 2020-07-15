package com.excilys.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
@ComponentScan({ "com.excilys.service", "com.excilys.CrudRepository", "com.excilys.controller",
		"com.excilys.configuration", "com.excilys.model","com.excilys.mapper" })
@EnableTransactionManagement


public class SpringConfiguration {

	private static AnnotationConfigWebApplicationContext context;

	public static AnnotationConfigWebApplicationContext getContext() {
		if (context == null) {
			context = new AnnotationConfigWebApplicationContext();
			context.refresh();
		}
		return context;
	}



}