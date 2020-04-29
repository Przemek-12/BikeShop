package com.example.demo.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.categories.CategoriesService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Categories;
import com.example.demo.interfaces.Bike;
import com.example.demo.interfacesImpl.BasicBike;
import com.example.demo.interfacesImpl.BikeElementDecorator;

@Service
public class BikeElementPageService {

	
	@Autowired
	//bike elements database methods
	private BikeElementService bikeElementService;
	
	
	@Autowired
	//elements categories database methods
	private CategoriesService categoriesService;
	
	
	
	//returns model that is created when user is logged or null when user is not logged
	public Model bikeElementPageGetModel(String element, Model model, HttpServletRequest request) {
		
		//page will be loaded if user is logged
		if(request.getSession().getAttribute("LOGGED_USER")!=null) {
		
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
			model.addAttribute("elementList", bikeElementService.getList(category.getCategoryId()));
			
			//folder with element graphics, elementString is used as variable in path to jpg location
			model.addAttribute("elementString", element);
			
			//puts text on next location button
			model.addAttribute("nextLocationText", category.getNextCategory().toUpperCase());
			
			//object that will be submitted by form
			model.addAttribute("bikeElement", new BikeElement());
			
			return model;
		}
		
		 return null;
	}

	
	
	//returns next category String
	public String bikeElementPagePost(String element, BikeElement bikeElement, HttpServletRequest request) {
		
		
		//gets category object from categories table to set redirect string variable
		Categories category = categoriesService.getCategory(element);
		
		//bike object is taken from session and removed
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		request.getSession().removeAttribute("BikeSessionObject");
			
		//bikeElement object is taken from database because bikeElement from form has no all data
		bikeElement = (BikeElement) bikeElementService.getElement(bikeElement.getElementId());
	
		//bike object is decorated
		bike = new BikeElementDecorator(bike, bikeElement);
				
		//new decorated bike object is set into session
		request.getSession().setAttribute("BikeSessionObject", bike);
		
		return category.getNextCategory();
	}
	
	
}
