package com.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.security.entity.loginEntity;
import com.security.repository.loginRepository;

@Controller
@RequestMapping("/admin")
public class adminController {

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
	
	@GetMapping("/profile")
	public String profile() {
		return "adminprofile";
	}
	@GetMapping("/content")
	public String content() {
		return "admincontent";
	}
	
}
