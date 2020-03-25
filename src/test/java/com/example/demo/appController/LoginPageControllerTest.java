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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.User;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginPageController.class)
public class LoginPageControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void pageLoadTest() throws Exception {
		mockMvc.perform(get("/loginPage"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("loginPage"))
		.andExpect(model().attribute("wrongData", false));
	}
	
	

	@Test
	@Transactional
	//checks if user was logged properly, redirection to mainPage, status 302
	public void CorrectUserTest() throws Exception {
		
		mockMvc.perform(post("/loginPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "John")
			.param("password", "Wick")
			//.sessionAttr("loginUser", new User())	
			)
			.andDo(print())
			.andExpect(redirectedUrl("/mainPage"));
		
	}
	
	@Test
	@Transactional
	public void UncorrectUserTest() throws Exception {
		
		mockMvc.perform(post("/loginPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "John")
			.param("password", "Wayne")
			//.sessionAttr("loginUser", new User())	
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("loginPage"))
			.andExpect(model().attribute("wrongData", true));
		
	}
	
	@Test
	@Transactional
	public void UsernameAndPasswordErrorsTest() throws Exception {
		
		mockMvc.perform(post("/loginPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "a")
			.param("password", "a")
			//.sessionAttr("loginUser", new User())	
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("loginPage"))
			.andExpect(model().attributeHasFieldErrors("loginUser", "username"))
            .andExpect(model().attributeHasFieldErrors("loginUser", "password"))
			.andExpect(model().attribute("wrongData", true));
		
	}
	
}















