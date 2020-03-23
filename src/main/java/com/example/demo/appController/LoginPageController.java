package com.example.demo.appController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.User;
import com.example.demo.user.UserService;

@Controller
@RequestMapping("/loginPage")
public class LoginPageController {

	@Autowired
	private UserService userService;
	
	
	@ModelAttribute(name="loginUser")
	public User loginUser() {
		return new User();
	}
		
	
	@GetMapping
	public String loginPage(Model model) {
		model.addAttribute("wrongData", false);
		return "loginPage";
	}
	
	
	//@modelAttribute behind User indicates that object parameters should come from model and Spring will not attach to it request parameters, 
	//in order to use @modelAttribute before method argument, @ModelAtribute method must be created
	//@Valid - user object will be validated before sending further
	//all errors will be stored in errors object
	@PostMapping
	public String logUser(@ModelAttribute("loginUser") @Valid  User user, Errors errors, HttpSession httpSession, Model model) {
				
			//if getUserLogin method returns user, session is created
			User user2 = userService.getUserLogin(user.getUsername(), user.getPassword());
			if(user2!=null) {
				httpSession.setAttribute("LOGGED_USER", user2);
				model.addAttribute("wrongData", false);
				return "redirect:/mainPage";
			}
			
			if(errors.hasErrors()) {
				model.addAttribute("wrongData", true);
				return "loginPage";
			}
			
			return null;
			
	}
	
	
}
