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

@Controller
@RequestMapping("/{element}")
public class BikeElementPageController {

	@Autowired
	//bike elements database methods
	private BikeElementService bikeService;
	
	@Autowired
	//elements categories database methods
	private CategoriesService categoriesService;
	
	
	@GetMapping
	public String bikeElementPage(@PathVariable("element") String element, Model model, HttpServletRequest request) {
		
		//bike object is object that is decorated
		//request.getSession() Returns the current session associated with this request, or if the request does not have a session, creates one.
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		
		//if bike object is null it will be created and set as session attribute
		if(bike==null) {
			request.getSession().setAttribute("BikeSessionObject", new BasicBike());
		}
		
		//gets category object from categories table
		Categories category = categoriesService.getCategory(element);
		
		//sets elementList attribute, list of objects is acquired based on catergoryId variable, which is id of specific category  
		model.addAttribute("elementList", bikeService.getList(category.getCategoryId()));
		
		//folder with element graphics, elementString is used as variable in path to jpg location
		model.addAttribute("elementString", element);
		
		//puts text on next location button
		model.addAttribute("nextLocationText", category.getNextCategory().toUpperCase());
		
		//object that will be submitted by form
		model.addAttribute("bikeElement", new BikeElement());
		
		return "bikeElementPage";
	}
	
	
	@PostMapping
	public String bikeElementPage(@PathVariable("element") String element, BikeElement bikeElement, HttpServletRequest request) {
		
		//gets category object from categories table to set redirect string variable
		Categories category = categoriesService.getCategory(element);
		
		//bike object is taken from session and removed
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		request.getSession().removeAttribute("BikeSessionObject");
			
		//bikeElement object is taken from database because bikeElement from form has no all data
		bikeElement = (BikeElement) bikeService.getElement(bikeElement.getElementId());
	
		//bike object is decorated
		bike = new BikeElementDecorator(bike, bikeElement);
				
		//new decorated bike object is set into session
		request.getSession().setAttribute("BikeSessionObject", bike);
		

		return "redirect:/"+category.getNextCategory();
	}
	
}
