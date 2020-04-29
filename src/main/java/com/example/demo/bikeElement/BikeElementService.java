package com.example.demo.bikeElement;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BikeElement;

@Service
public class BikeElementService {

	
	@Autowired
	private BikeElementRepository bikeElementRepository;
	
	
	@Transactional
	public BikeElement getElement(long elementId) {
		return bikeElementRepository.getElement(elementId);
	}
	
	
	@Transactional
	public List<BikeElement> getList(long categoryId){
		return bikeElementRepository.getList(categoryId);
	}
	
}
