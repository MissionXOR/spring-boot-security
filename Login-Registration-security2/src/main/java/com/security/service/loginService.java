package com.security.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.security.entity.loginEntity;
import com.security.repository.loginRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class loginService {

	@Autowired
	loginRepository repository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	JavaMailSender mailSender;
	
	public loginEntity saveuser(loginEntity entity,String url) throws UnsupportedEncodingException, MessagingException {
		
		String pass  = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(pass);
		entity.setRole("ROLE_USER");
		
		entity.setEnable(false);
		entity.setVerfiyCode(UUID.randomUUID().toString());
		
		entity.setAccountNonLocked(true);
		entity.setFailedAttempt(0);
		entity.setLockTime(null);
		
		
		loginEntity user=repository.save(entity);
		
		if(user!=null)
		{
			sendEmail(user, url);
		}

		return user;
	}
	
	public void sendEmail(loginEntity entity, String url) throws UnsupportedEncodingException, MessagingException 
	{
		
	    String fromAddress = "19202103334@cse.bubt.edu.bd";
	    String toAddress = entity.getEmail();
	    
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Thank you for signing up with Team-XOR! <br>"
	    		+"To complete your registration, please verify your email address by clicking the link below:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
		    +"If you did not create an account with us, please ignore this email.<br>"
	            + "Thank you "
		    +"<h5>Team-XOR</h5>";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, "jovan");
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	     
	    content = content.replace("[[name]]", entity.getName());
	    String verifyURL = url + "/verify?code=" + entity.getVerfiyCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
	
	
	public boolean verifyAccount(String verificationCode) 
	{
		loginEntity en=repository.findByVerfiyCode(verificationCode);
		
		if(en==null)
		{
			return false;
		}else {
			en.setEnable(true);
			en.setVerfiyCode(null);
			
			repository.save(en);
			return true;
		}
		
		
	}
	
	public void increaseFailedAttempt(loginEntity entity) {
		
		  int attempt=entity.getFailedAttempt()+1;
		  
		  repository.updateFailedAttempt(attempt, entity.getEmail());
		
	}
	
	
	public void resetAttempt(String email) {
		repository.updateFailedAttempt(0, email);
	}
	
	
	public void lock(loginEntity entity) {
		
		entity.setAccountNonLocked(false);
		entity.setLockTime(new Date());
		repository.save(entity);
	}
	
	private static final long lock_duration=3000;
	public static final long attemptTime=3;
	
	public boolean unlockAccountTime(loginEntity entity) {
		
		  long locktime = entity.getLockTime().getTime();
		  long currenttime= System.currentTimeMillis();
		  
		  if(locktime+lock_duration <currenttime)
		  {
			  entity.setAccountNonLocked(true);
			  entity.setLockTime(null);
			  entity.setFailedAttempt(0);
			  repository.save(entity);
			  return true;
		  }
		  
		
		return false;
		
	}
	
	
	
	
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}
}
