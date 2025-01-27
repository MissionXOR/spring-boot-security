package com.security.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.entity.loginEntity;
import com.security.repository.loginRepository;
import com.security.service.loginService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	loginService service;
	
	@Autowired
	loginRepository repository;
	
	@ModelAttribute
	public void commonUser(Principal principal,Model model) {
		
		if(principal!=null)
		{
			    String email	=principal.getName();
			    loginEntity entity =  repository.findByEmail(email);
			    model.addAttribute("user", entity);
		}

	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	/*@GetMapping("/user/profile")
	public String profile(Principal principal, Model model) {
		
	    String email	=principal.getName();
	    loginEntity entity =  repository.findByEmail(email);
	    model.addAttribute("user", entity);
		return "profile";
	}
	*/
	
	
	@PostMapping("/saveUser")
	public String saveuser(@ModelAttribute loginEntity entity,HttpSession session,HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		
		String url=request.getRequestURL().toString();
		url=url.replace(request.getServletPath(), "");
		
		 loginEntity u =service.saveuser(entity,url);
		 if (u != null) {
				
				session.setAttribute("msg", "Register successfully Now verify your Email");

			} else {
				
				session.setAttribute("msg", "Something wrong server");
			}
		return "redirect:/register";
	}
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code,Model model) {
		
		boolean f  = service.verifyAccount(code);
		
		if(f)
		{
			model.addAttribute("msg", "Successfully your Account is verified Now you can login ");
		}else {
			model.addAttribute("msg", "verification code is incorrect or you are already verified");
		}
		
		return "message";
	}
	
}
