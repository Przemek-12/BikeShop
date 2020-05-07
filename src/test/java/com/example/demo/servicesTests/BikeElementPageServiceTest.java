package com.example.demo.servicesTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.categories.CategoriesService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Categories;
import com.example.demo.entity.User;
import com.example.demo.interfaces.Bike;
import com.example.demo.interfacesImpl.BasicBike;
import com.example.demo.interfacesImpl.BikeElementDecorator;
import com.example.demo.services.BikeElementPageService;


@RunWith(MockitoJUnitRunner.class)
public class BikeElementPageServiceTest {
	
	@InjectMocks
	private BikeElementPageService bikeElementPageService;
	
	@Mock
	//bike elements database methods
	private BikeElementService bikeElementService;
	
	@Mock
	//elements categories database methods
	private CategoriesService categoriesService;
	
	
	@BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void bikeElementPageGetModelNotNullTest() {
		
		MockHttpSession session = new MockHttpSession();
		
		Bike bike = new BasicBike();
		bike = new BikeElementDecorator(bike, new BikeElement(1, "a", 1.1, "a", "a"));
		session.setAttribute("BikeSessionObject", bike);
		
		User user = new User();
		user.setUserId(1);
		user.setUsername("John");
		user.setPassword("Wick");
		session.setAttribute("LOGGED_USER", user);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		
		Categories category = new Categories("thisCat", "nextCat");
		
		when(categoriesService.getCategory(Mockito.any(String.class))).thenReturn(category);
		when(bikeElementService.getList(Mockito.any(Long.class))).thenReturn(new ArrayList<>());		
		
		Model model = bikeElementPageService.bikeElementPageGetModel("element", new ConcurrentModel(), request);
		
		assertThat(model).isNotNull();
		assertThat(model.getAttribute("elementString")).isEqualTo("element");
		
	}
	
	
	@Test
	public void bikeElementPageGetModelNullTest() {
		
		MockHttpSession session = new MockHttpSession();
		
		Bike bike = new BasicBike();
		bike = new BikeElementDecorator(bike, new BikeElement(1, "a", 1.1, "a", "a"));
		session.setAttribute("BikeSessionObject", bike);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
			
		Model model = bikeElementPageService.bikeElementPageGetModel("element", new ConcurrentModel(), request);
		
		assertThat(model).isNull();
		
	}
	
	
	@Test
	public void bikeElementPagePostTest() {
		
		MockHttpSession session = new MockHttpSession();
		
		Bike bike = new BasicBike();
		bike = new BikeElementDecorator(bike, new BikeElement(1, "a", 1.1, "a", "a"));
		session.setAttribute("BikeSessionObject", bike);
		
		User user = new User();
		user.setUserId(1);
		user.setUsername("John");
		user.setPassword("Wick");
		session.setAttribute("LOGGED_USER", user);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		
		Categories category = new Categories("thisCat", "nextCat");
		
		when(categoriesService.getCategory(Mockito.any(String.class))).thenReturn(category);
		when(bikeElementService.getElement(Mockito.any(Long.class))).thenReturn(new BikeElement(1, "a", 1.1, "a", "a"));
		
		String nextCat = bikeElementPageService.bikeElementPagePost("element", new BikeElement(1, "a", 1.1, "a", "a"), request);
		
		assertThat(nextCat).isEqualTo("nextCat");
		
	}
	

}













