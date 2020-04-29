package com.example.demo.interfacesImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.BikeElement;
import com.example.demo.interfaces.Bike;

public class BikeElementDecorator extends BikeDecorator implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BikeElement bikeElement;
	
	public BikeElementDecorator(Bike bike, BikeElement bikeElement) {
		super(bike);
		this.bikeElement=bikeElement;
		description();
		price();
		addToMap(bikeElement);
		
	}

	@Override
	public String description() {
		//System.out.println(bikeElement.getModel());
		
		if(super.description().contains(bikeElement.getModel())) {
			return super.description().replace(bikeElement.getModel(), "");
		}
		return super.description()+bikeElement.getModel();
	}

	@Override
	public Double price() {
		return super.price()+bikeElement.getPrice();
	}

	@Override
	public Map<Long, BikeElement> bikeElements() {
		return super.bikeElements();
	}
	
	@Override
	public void addToMap(BikeElement bikeElement) {
		super.addToMap(bikeElement);
	}
	
	@Override
	public void removeFromMap(long elementId) {
		super.removeFromMap(elementId);
	}

	@Override
	public void removeFromDescription(String model) {
		super.removeFromDescription(model);
	}

	@Override
	public void removeFromPrice(Double price) {
		super.removeFromPrice(price);
	}
}
