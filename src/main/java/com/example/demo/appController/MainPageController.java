package com.example.demo.appController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.services.MainPageService;


@Controller
@RequestMapping("/mainpage")
public class MainPageController {

	
	@Autowired
	 MainPageService mainPageService;
	
	
	@GetMapping
	public String mainPage(Model model, HttpServletRequest request) {
		
		model = mainPageService.getModel(model, request);
		
		return "mainPage";
	}
	
	
	
	
}
