package com.deloitte.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * Application Web Security
 * 
 * @author Alan Kavanagh
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("configuring http security for application");
		/* 
		 * Enables HTTP access to login/logout without authorisation
		 * Enables HTTP access to all Task Controller with authorisation
		 * Enables HTTP access to Swagger UI with authorisation
		 * Enables HTTP access to H2-Console with authorisation 
		 * */
		http.authorizeRequests().antMatchers("/todo", "/all", "/active", "/completed", "/registration", "/resources/**").permitAll().anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll().and().logout().permitAll()
			.and().authorizeRequests().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagger-ui.html").permitAll().anyRequest().authenticated()
			.and().authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * Applies password encoder to userDetailsService
		 * ToDo: Apply ROLE enforcement
		 */
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}