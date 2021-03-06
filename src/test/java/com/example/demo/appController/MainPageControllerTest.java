package com.example.demo.appController;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.User;


@RunWith(SpringRunner.class)
@WebMvcTest(MainPageController.class) //for tests in spring mvc, there's no need to run server
public class MainPageControllerTest {

	
	//Main entry point for server-side Spring MVC test support.
	@Autowired
	private MockMvc mockMvc;
	
	
	//content of page when user is not logged
	@Test
	public void mainPageNotLoggedTest() throws Exception{
		mockMvc.perform(get("/mainPage"))
			.andDo(print())
			.andExpect(status().isOk()) //http 200 code
			.andExpect(view().name("mainPage"))
			.andExpect(content().string(containsString("Login to Create Bike")))
			.andExpect(content().string(containsString("Login")))
			.andExpect(model().attribute("btnDisabled", "disabled"));	
	}
	
	
	//content of page when user is logged
	@Test
	public void mainPageLogged() throws Exception{
		
		MockHttpSession session = new MockHttpSession();
		User user = new User();
		user.setUsername("John");
		user.setPassword("Wick");
		
		session.setAttribute("LOGGED_USER", user);
		
		mockMvc.perform(get("/mainPage").session(session))
		.andDo(print())
		.andExpect(status().isOk()) //http 200 code
		.andExpect(view().name("mainPage"))
		.andExpect(content().string(containsString("Create Bike")))
		.andExpect(content().string(containsString("John")))
		.andExpect(model().attribute("btnDisabled", nullValue()));
		
	}
	
	
}
