package com.example.demo.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.example.demo.interfaces.Bike;

@Service
public class OrderPageService {

	
	public void removeFromOrder(String elementIdS, HttpServletRequest request) {
		
		System.out.println(elementIdS);
		
		long elementId = Long.parseLong(elementIdS);
		
		System.out.println(elementId);
		
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		//request.getSession().removeAttribute("BikeSessionObject");
		
		bike.removeFromDescription(bike.bikeElements().get(elementId).getModel());	
		bike.removeFromPrice(bike.bikeElements().get(elementId).getPrice());
		
		//removes element from map of elements
		bike.removeFromMap(elementId);
	
		request.getSession().setAttribute("BikeSessionObject", bike);
		
	}
	
	
}
