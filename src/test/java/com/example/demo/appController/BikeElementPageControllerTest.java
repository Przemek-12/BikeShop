package com.example.demo.appController;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.User;
import com.example.demo.interfacesImpl.BasicBike;


@RunWith(SpringRunner.class)
@WebMvcTest(BikeElementPageController.class)
public class BikeElementPageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void bikeElementPageNotLogged() throws Exception {
		mockMvc.perform(get("/frames"))
		.andDo(print())
		.andExpect(redirectedUrl("/mainPage"))//instead of status.isOk, redirection should return status 302
		.andExpect(view().name("redirect:/mainPage"));
	}
	
	
	@Test
	@Transactional
	public void bikeElementPageLogged() throws Exception{
		
		MockHttpSession session = new MockHttpSession();
		User user = new User();
		user.setUsername("John");
		user.setPassword("Wick");
		
		session.setAttribute("LOGGED_USER", user);
		session.setAttribute("BikeSessionObject", new BasicBike());
		
		List<String> list = new ArrayList<>(Arrays.asList("frames", "brakes", "deraiileurs", "pedals", "wheels", "saddles", "handlebars"));
		
		list.forEach((item)->{
			try {
				mockMvc.perform(get("/"+item).session(session))
				.andDo(print())
				.andExpect(status().isOk()) 
				.andExpect(view().name("bikeElementPage"))
				.andExpect(model().attribute("elementString", item));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	
}
