package com.example.demo.appController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogOutController {
	
	@GetMapping("/logout")
	public String logOutSession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/mainpage";
	}
	

}
