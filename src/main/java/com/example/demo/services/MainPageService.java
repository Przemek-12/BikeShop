package com.example.demo.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.User;


@Service
public class MainPageService {

	
	
	public Model getModel(Model model, HttpServletRequest request) {
		
		User loggedUser = (User)request.getSession().getAttribute("LOGGED_USER");
		
		if(request.getSession().getAttribute("BikeSessionObject")!=null) {
			request.getSession().removeAttribute("BikeSessionObject");
		}
		
		if(loggedUser!=null) {
			model.addAttribute("textOnHref", loggedUser.getUsername());
			model.addAttribute("location", "userpage");
			model.addAttribute("textOnButton", "Create Bike");
		}
		else{
			model.addAttribute("textOnHref", "Login");
			model.addAttribute("location", "loginpage");
			model.addAttribute("textOnButton", "Login to Create Bike");
			model.addAttribute("btnDisabled", "disabled");
		}
		
		return model;
	}

	
	
}
