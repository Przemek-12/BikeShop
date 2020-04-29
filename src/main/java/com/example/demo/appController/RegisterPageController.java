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
@RequestMapping("/registerpage")
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
		
		boolean getUserUsername = userService.getUserUsername(user.getUsername());
		boolean getUserEmail = userService.getUserEmail(user.getEmail());
		
		if(getUserUsername && getUserEmail) {
			model.addAttribute("userIsTaken", true);
			model.addAttribute("emailIsTaken", true);
			return "registerPage";
		}
		
		else if(getUserUsername) {
			model.addAttribute("userIsTaken", true);
			return "registerPage";
		}
		
		else if(getUserEmail) {
			model.addAttribute("emailIsTaken", true);
			return "registerPage";
		}
		
		else if(errors.hasErrors()) {
			return "registerPage";
		}
		
		else if(!getUserUsername && !getUserEmail){
			userService.saveUser(user);
			return "redirect:/loginpage";
		}
		
		
		return null;
	}
	
	
}
