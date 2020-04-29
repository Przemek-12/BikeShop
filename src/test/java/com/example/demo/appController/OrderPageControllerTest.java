package com.example.demo.appController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.BikeElement;
import com.example.demo.entity.User;
import com.example.demo.interfaces.Bike;
import com.example.demo.interfacesImpl.BasicBike;
import com.example.demo.interfacesImpl.BikeElementDecorator;



@RunWith(SpringRunner.class)
@WebMvcTest(OrderPageController.class)
public class OrderPageControllerTest {

//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	
//	@Test
//	public void pageLoadTest() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		Bike bike = new BasicBike();
//		bike = new BikeElementDecorator(bike, new BikeElement(1,1, "a", 1.1, "a", "a"));
//		
//		session.setAttribute("BikeSessionObject", bike);
//		
//		mockMvc.perform(get("/orderPage").session(session))
//		.andDo(print())
//		.andExpect(status().isOk())
//		.andExpect(view().name("orderPage"))
//		.andExpect(model().attribute("bikeDescription", "a"))
//		.andExpect(model().attribute("bikePrice", 1.1))
//		.andExpect(model().attribute("orderList", bike.bikeElements().values()));
//	}
//	
//	
//	@Test
//	@Transactional
//	public void submitOrderTest() throws Exception {
//		
//		MockHttpSession session = new MockHttpSession();
//		User user = new User();
//		user.setUserId(1);
//		user.setUsername("John");
//		user.setPassword("Wick");
//		
//		Bike bike = new BasicBike();
//		bike = new BikeElementDecorator(bike, new BikeElement(1,1, "a", 1.1, "a", "a"));
//		
//		session.setAttribute("BikeSessionObject", bike);
//		session.setAttribute("LOGGED_USER", user);
//		
//		mockMvc.perform(post("/orderPage").session(session))
//			.andDo(print())
//			.andExpect(redirectedUrl("/userPage"));
//	}
//	
//	
//	@Test
//	public void removeFromOrderTest() throws Exception{
//		
//		MockHttpSession session = new MockHttpSession();
//		session.removeAttribute("BikeSessionObject");
//		
//		Bike bike = new BasicBike();
//		BikeElement bikeEl1 = new BikeElement(1,1, "frame", 100.0, "a", "a");
//		BikeElement bikeEl2 = new BikeElement(2,2, "break", 100.0, "a", "a");
//		bike = new BikeElementDecorator(bike, bikeEl1);
//		bike = new BikeElementDecorator(bike, bikeEl2);
//		
//		System.out.println(bike.description());
//		
//		session.setAttribute("BikeSessionObject", bike);
//
//		mockMvc.perform(post("/orderPage/remove").session(session)
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//				.param("elementId", "2")
//				)
//				.andDo(print())
//				.andExpect(redirectedUrl("/orderPage"));
//		
//		mockMvc	.perform(get("/orderPage").session(session))
//				.andExpect(model().attribute("bikeDescription", "frame"))
//				.andExpect(model().attribute("bikePrice", 100.0))
//				.andExpect(model().attribute("orderList", bike.bikeElements().values()));
//		
//	}
}






















