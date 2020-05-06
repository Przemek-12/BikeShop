package com.example.demo.bikeElementTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.bikeElement.BikeElementRepository;
import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.entity.BikeElement;


@RunWith(MockitoJUnitRunner.class)
public class BikeElementServiceTest {

	
	@InjectMocks//creates instance of class and inject mocked dependencies(Mock annotation)
	private BikeElementService bikeElementService;
	
	
	@Mock//creates model of class
	private BikeElementRepository bikeElementRepository;
	
	
	//BeforeEach is from jupiter, Before from junit4 whicj is excluded
	@BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void getElementTest() {
		
		BikeElement bikeElement = new BikeElement(1, "model", 1.1, "spec", "img");
		when(bikeElementRepository.findByElementId(Mockito.any(Long.class))).thenReturn(bikeElement);
		
		BikeElement be = bikeElementService.getElement(bikeElement.getElementId());
		
		assertThat(be.getElementId()).isEqualTo(bikeElement.getElementId());
	
		
	}
	
	
	@Test
	public void getListTest() {
		
		BikeElement bikeElement1 = new BikeElement(0, "model", 1.1, "spec", "img");
		BikeElement bikeElement2 = new BikeElement(0, "model2", 1.2, "spec2", "img2");
		
		List<BikeElement> givenList = new ArrayList<>();
		givenList.add(bikeElement1);
		givenList.add(bikeElement2);
		
		when(bikeElementRepository.findByCategoryId(Mockito.any(Long.class))).thenReturn(givenList);
		
		List<BikeElement> takenList = bikeElementService.getList(0);
		
		assertThat(takenList.size()).isEqualTo(2);
		
	}
	
	
	
}












