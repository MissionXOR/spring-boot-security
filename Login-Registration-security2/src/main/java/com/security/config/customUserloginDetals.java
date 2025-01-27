package com.security.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.entity.loginEntity;

public class customUserloginDetals implements UserDetails {

	
	private loginEntity entity;
	
	

	public customUserloginDetals() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public customUserloginDetals(loginEntity entity) {
		super();
		this.entity = entity;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(entity.getRole());
		
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return entity.getPassword();
	}

	@Override
	public String getUsername() {
		
		return entity.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return entity.isAccountNonLocked();
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	
	
	@Override
	public boolean isEnabled() {
		
		return entity.isEnable();
	}


	public loginEntity getEntity() {
		return entity;
	}


	public void setEntity(loginEntity entity) {
		this.entity = entity;
	}
	
	
	


}
