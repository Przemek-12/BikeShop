package com.example.demo.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.BikeElement;

public interface Bike {

	String description();
	Double price();
	Map<Long,BikeElement> bikeElements();
	void addToMap(BikeElement bikeElement);
	void removeFromMap(long elementId);
	void removeFromDescription(String model);
	void removeFromPrice(Double price);
}
