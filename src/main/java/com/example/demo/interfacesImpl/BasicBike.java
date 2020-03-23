package com.example.demo.interfacesImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.BikeElement;
import com.example.demo.interfaces.Bike;

public class BasicBike implements Bike, Serializable {

	private static final long serialVersionUID = 1L;

	//LinkedHashMap remembers order of adding 
	private Map<Integer,BikeElement> bikeElements = new LinkedHashMap<>();
	
	private String description="";
	
	private Double price=0.0;
	
	
	@Override
	public String description() {
		return description;
	}

	@Override
	public Double price() {
		return price;
	}

	@Override
	public Map<Integer,BikeElement> bikeElements() {
		return bikeElements;
	}

	@Override
	public void addToMap(BikeElement bikeElement) {
		bikeElements.put(bikeElement.getElementId(), bikeElement);
	}
	
	@Override
	public void removeFromMap(int elementId) {
		bikeElements.remove(elementId);
	}

	@Override
	public void removeFromDescription(String model) {
		this.description=this.description+model;
	}

	@Override
	public void removeFromPrice(Double price) {
		this.price=this.price-price;
	}
	
}
