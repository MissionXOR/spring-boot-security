package com.security.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.security.entity.loginEntity;
import com.security.service.loginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class customSuccessHandaler implements AuthenticationSuccessHandler {

	@Autowired
	private loginService service;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		  
		  Set<String> roles=AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		  
		    customUserloginDetals user  = (customUserloginDetals)authentication.getPrincipal();
		     loginEntity u = user.getEntity();
		     
		     if(u!=null)
		     {
		    	 service.resetAttempt(u.getEmail());
		     }
		  
		  if(roles.contains("ROLE_ADMIN"))
		  {
			  response.sendRedirect("/admin/profile");
		  }else {
			  response.sendRedirect("/user/profile");
		}
		
	}

}
