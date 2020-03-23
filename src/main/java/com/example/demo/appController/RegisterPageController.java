package com.example.demo.appController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.user.UserService;

@Controller
@RequestMapping("/registerPage")
public class RegisterPageController {

	@Autowired
	private UserService userService;
	

	
	@ModelAttribute(name="registerUser")
	public User registerUser() {
		return new User();
	}
	
	@GetMapping
	public String registerPage(Model model) {
		model.addAttribute("userIsTaken", false);
		model.addAttribute("emailIsTaken", false);
		return "registerPage";
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute("registerUser") @Valid  User user, Errors errors, Model model) {
		
		if(userService.getUserUsername(user.getUsername()) && userService.getUserEmail(user.getEmail())) {
			model.addAttribute("userIsTaken", true);
			model.addAttribute("emailIsTaken", true);
			return "registerPage";
		}
		
		else if(userService.getUserUsername(user.getUsername())) {
			model.addAttribute("userIsTaken", true);
			return "registerPage";
		}
		
		else if(userService.getUserEmail(user.getEmail())) {
			model.addAttribute("emailIsTaken", true);
			return "registerPage";
		}
		
		else if(errors.hasErrors()) {
			return "registerPage";
		}
		
		else if(!userService.getUserUsername(user.getUsername()) && !userService.getUserEmail(user.getEmail())){
			userService.saveUser(user);
			return "redirect:/loginPage";
		}
		
		
		
		return null;
	}
	
	
}
