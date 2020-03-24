package com.example.demo.appController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;


@Controller
@RequestMapping("/mainPage")
public class MainPageController {

	
	
	@GetMapping
	public String mainPage(Model model, HttpServletRequest request) {
		
		User loggedUser = (User)request.getSession().getAttribute("LOGGED_USER");
		
		if(request.getSession().getAttribute("BikeSessionObject")!=null) {
			request.getSession().removeAttribute("BikeSessionObject");
		}
		
		if(loggedUser!=null) {
			model.addAttribute("textOnHref", loggedUser.getUsername());
			model.addAttribute("location", "userPage");
			model.addAttribute("textOnButton", "Create Bike");
		}
		else{
			model.addAttribute("textOnHref", "Login");
			model.addAttribute("location", "loginPage");
			model.addAttribute("textOnButton", "Login to Create Bike");
			model.addAttribute("btnDisabled", "disabled");
		}
		
		return "mainPage";
	}
	
	
	
	
}
