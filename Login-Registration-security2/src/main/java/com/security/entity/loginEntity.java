package com.security.entity;

import java.util.Date;

import jakarta.persistence.*;


@Entity
public class loginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String mobileNo;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private boolean enable;
	
	private String verfiyCode;
	
	private boolean isAccountNonLocked;
	
	private int failedAttempt;
	
	private Date lockTime;

	

	public loginEntity(int id, String name, String mobileNo, String password, String email, String role, boolean enable,
			String verfiyCode, boolean isAccountNonLocked, int failedAttempt, Date lockTime) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enable = enable;
		this.verfiyCode = verfiyCode;
		this.isAccountNonLocked = isAccountNonLocked;
		this.failedAttempt = failedAttempt;
		this.lockTime = lockTime;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getVerfiyCode() {
		return verfiyCode;
	}

	public void setVerfiyCode(String verfiyCode) {
		this.verfiyCode = verfiyCode;
	}
	

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public loginEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
