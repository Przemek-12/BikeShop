package com.example.demo.interfacesImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.BikeElement;
import com.example.demo.interfaces.Bike;

public abstract class BikeDecorator implements Bike, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Bike bike;
	
	public BikeDecorator(Bike bike) {
		this.bike=bike;
	}
	
	
	@Override
	public String description() {
		return bike.description();
	}

	@Override
	public Double price() {
		return bike.price();
	}

	@Override
	public Map<Long,BikeElement> bikeElements() {
		return bike.bikeElements();
	}
	
	@Override
	public void addToMap(BikeElement bikeElement) {
		bike.addToMap(bikeElement);
	}

	@Override
	public void removeFromMap(long elementId) {
		bike.removeFromMap(elementId);
	}
	
	@Override
	public void removeFromDescription(String model) {
		bike.removeFromDescription(model);
	}

	@Override
	public void removeFromPrice(Double price) {
		bike.removeFromPrice(price);
	}
	
}
