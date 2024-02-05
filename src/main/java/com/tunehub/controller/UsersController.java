package com.tunehub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entity.Users;
import com.tunehub.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class UsersController {
	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute Users user) {
		boolean userStatus = service.emailExists(user.getEmail());
		if(userStatus  == false) {
			service.addUsers(user);
			System.out.println("user added");
		}
		else {
			System.out.println("user already exists");
		}
		
		
		return "login";
	}
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email,
			@RequestParam("password") String password,
			HttpSession session) {
		
		if(service.validateUser(email,password) == true) {
			String role = service.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equals("admin")) {
				return "adminHome";
			}
			else {
				return "customerHome";
			}
		}
		else {
			return "login";
		}
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "login";
	}
	
	@GetMapping("/forgot")
	public String forgotPassword() {
		return "forgot";
	}
	
	@PostMapping("/updatePassword")
	public String  updatePassword(@RequestParam("email")String email,
			@RequestParam("password") String password,Users user,String conformPassword) {
		
		Users existUser = service.userObject(user.getEmail());
		if(password.equals(conformPassword)) {
			existUser.setPassword(password);
			service.addUsers(existUser);
			
			return "login";
			
		}
		
		return "forgot";
		
	}
	
	
	
	
	
	
}