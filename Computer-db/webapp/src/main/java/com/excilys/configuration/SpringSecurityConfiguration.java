package com.excilys.configuration;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.excilys.dto.UserDTO;
import com.excilys.service.ServiceUser;;

@EnableWebSecurity
@Configuration
@ComponentScan({"com.excilys.controller","com.excilys.configuration"})
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter implements AuthenticationSuccessHandler {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    ServiceUser ServiceUser;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	List<UserDTO>   users = ServiceUser.getAllCompany();
    	for (UserDTO u : users)
    	{
    		System.out.println("username :" + u.getUsername() +" role = " + u.getRole());
            auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder)
            .withUser(u.getUsername()).password(passwordEncoder.encode(u.getPassword())).roles(u.getRole());
    	}
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {  
    	http
		.csrf()
		.disable();
    	
        http
        .authorizeRequests().antMatchers("/","/login").permitAll();  

        
        http
        .authorizeRequests().antMatchers("/dashboard").access("hasAnyRole('USER','ADMIN')");
        
        http
        .authorizeRequests().antMatchers("/addComputer","/editComputer").access("hasAnyRole('ADMIN')");
        
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/dashboard")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        
    }

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	      Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
	        if (roles.contains("ADMIN"))
	        {
	            request.getSession(false).setMaxInactiveInterval(10);
	        }
	        else
	        {
	            request.getSession(false).setMaxInactiveInterval(120);
	        }
	        //Your login success url goes here, currently login success url="/"
	        response.sendRedirect(request.getContextPath());
		
	}  
    

    
    
}
