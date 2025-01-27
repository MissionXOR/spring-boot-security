package com.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.security.entity.loginEntity;
import com.security.repository.loginRepository;
import com.security.service.loginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class customFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	loginService service;
	
	@Autowired
	loginRepository repository;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		    
			 String email =request.getParameter("username");
			 loginEntity entity  = repository.findByEmail(email);
			 
			 if(entity!=null)
			 {
				 if(entity.isEnable())
				 {
					 if(entity.isAccountNonLocked())
					 {
						 if(entity.getFailedAttempt()<service.attemptTime-1)
						 {
							 service.increaseFailedAttempt(entity);
						 }else {
							service.lock(entity);
							exception=new LockedException("Your account is locked ...Failed attempt 3 times");
						}
						 
						 
					 }else if (!entity.isAccountNonLocked()) {
						if(service.unlockAccountTime(entity))
						{
							exception=new LockedException("Your Account is unlocked..Please try to login");
						}else {
							exception=new LockedException("Your Account is locked..Please try after 24 hours ");
						}
					}
					 
					 
				 }else {
					exception=new LockedException("Account is in active...verify your account");
				}
			 }
		super.setDefaultFailureUrl("/signin?error");
		super.onAuthenticationFailure(request, response, exception);
	}

	
}
