package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.security.entity.loginEntity;

import jakarta.transaction.Transactional;

@Repository
public interface loginRepository extends JpaRepository<loginEntity, Integer> {

	public loginEntity findByEmail(String email);
	
	public loginEntity findByVerfiyCode(String verfiyCode);
	
	    @Transactional // Required for modifying queries
	    @Modifying
	    @Query("update loginEntity e set e.failedAttempt = ?1 where e.email = ?2")
	public void updateFailedAttempt(int attemp,String email);
	
}
