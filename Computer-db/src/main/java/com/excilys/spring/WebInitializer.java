package com.excilys.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

public final class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {

		AnnotationConfigWebApplicationContext dispatcher = new AnnotationConfigWebApplicationContext();
		dispatcher.register(SpringConfiguration.class, MvcConfiguraiton.class);
		dispatcher.setServletContext(context);
		DispatcherServlet dv = new DispatcherServlet(dispatcher);
		ServletRegistration.Dynamic servlet = context.addServlet("Dashboard",dv );
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry
	          .addResourceHandler("/resources/**")
	          .addResourceLocations("/resources/"); 
	    }
}
