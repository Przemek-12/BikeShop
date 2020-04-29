package com.example.demo.appController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.categories.CategoriesService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Categories;
import com.example.demo.interfaces.Bike;
import com.example.demo.interfacesImpl.BasicBike;
import com.example.demo.interfacesImpl.BikeElementDecorator;
import com.example.demo.services.BikeElementPageService;

@Controller
@RequestMapping("/{element}")
public class BikeElementPageController {
	
	
	@Autowired
	private BikeElementPageService bikeElementPageService;
	
	
	@GetMapping
	public String bikeElementPage(@PathVariable("element") String element, Model model, HttpServletRequest request) {
		
		model = bikeElementPageService.bikeElementPageGetModel(element, model, request);
		
		//page will be loaded if user is logged
		if(model!=null) {
			
			return "bikeElementPage";
		}
		
		return "redirect:/mainpage";
	}
	
	
	
	@PostMapping
	public String bikeElementPage(@PathVariable("element") String element, BikeElement bikeElement, HttpServletRequest request) {	

		return "redirect:/"+bikeElementPageService.bikeElementPagePost(element, bikeElement, request);
	}
	
}
