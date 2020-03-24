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
@WebMvcTest(RegisterPageController.class)
public class RegisterPageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void pageLoadTest() throws Exception {
		mockMvc.perform(get("/registerPage"))
		.andDo(print())
		.andExpect(view().name("registerPage"))
		.andExpect(model().attribute("userIsTaken", false))
		.andExpect(model().attribute("emailIsTaken", false));
	}
	
	

	@Test
	@Transactional
	public void CorrectUserTest() throws Exception {
		
		mockMvc.perform(post("/registerPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "Jimmy")
			.param("password", "Arrow")
			.param("email", "Arrow@qw.com")
			.sessionAttr("loginUser", new User())	
			)
			.andDo(print())
			.andExpect(redirectedUrl("/loginPage"));
		
	}
	
	@Test
	@Transactional
	public void UsernameEmailAlreadyTakenTest() throws Exception {
		
		mockMvc.perform(post("/registerPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "John")
			.param("password", "a")
			.param("email", "pece@gmail.com")
			.sessionAttr("loginUser", new User())	
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("registerPage"))
			.andExpect(model().attribute("userIsTaken", true))
			.andExpect(model().attribute("emailIsTaken", true));
		
	}
	
	@Test
	@Transactional
	public void UsernameEmailPasswordErrorsTest() throws Exception {
		
		mockMvc.perform(post("/loginPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "a")
			.param("password", "a")
			.param("email", "a")
			.sessionAttr("loginUser", new User())	
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("loginPage"))
			.andExpect(model().attributeHasFieldErrors("loginUser", "username"))
            .andExpect(model().attributeHasFieldErrors("loginUser", "password"))
            .andExpect(model().attributeHasFieldErrors("loginUser", "email"));
		
	}
	
}
