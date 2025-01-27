package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.security.entity.loginEntity;
import com.security.repository.loginRepository;

@Component
public class customUserloginDetailsService implements UserDetailsService {
	
	@Autowired
	loginRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 loginEntity eLoginEntity	=repository.findByEmail(username);
	 
			 if(eLoginEntity==null)
			 {
				 throw new UsernameNotFoundException("user not found");
			 }else {
				
				return new customUserloginDetals(eLoginEntity);
			}
	 
	}

}
