package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class securityConfig {

	@Autowired
	private customSuccessHandaler handaler;
	
	@Autowired
	private customFailureHandler faileHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new customUserloginDetailsService();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider d=new DaoAuthenticationProvider();
		d.setUserDetailsService(userDetailsService());
		d.setPasswordEncoder(passwordEncoder());
		
		return d;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		/*http.csrf().disable()
		.authorizeHttpRequests().requestMatchers("/","/register","/signin","/saveUser").permitAll()
		.requestMatchers("/user/**").authenticated().and().formLogin()
		.loginPage("/signin").loginProcessingUrl("/userlogin")
		.defaultSuccessUrl("/user/profile").permitAll();
		*/
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/user/**").hasRole("USER")
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.requestMatchers("/**").permitAll()
		.and().formLogin().loginPage("/signin").loginProcessingUrl("/userlogin")
		.failureHandler(faileHandler)
		.successHandler(handaler)
		.permitAll();
		
		return http.build();
	}
	
	
}
