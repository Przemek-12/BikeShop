package com.example.demo.appControllerTests;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.appController.RegisterPageController;
import com.example.demo.entity.User;
import com.example.demo.user.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(RegisterPageController.class)
public class RegisterPageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void pageLoadTest() throws Exception {
		mockMvc.perform(get("/registerPage"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("registerPage"))
		.andExpect(model().attribute("userIsTaken", false))
		.andExpect(model().attribute("emailIsTaken", false));
	}
	
	

	@Test
	public void CorrectUserTest() throws Exception {
		
		mockMvc.perform(post("/registerPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "Jimmy")
			.param("password", "Arrow")
			.param("email", "Arrow@qw.com")
			)
			.andDo(print())
			.andExpect(redirectedUrl("/loginPage"));
		
	}
	
	@Test
	public void UsernameEmailAlreadyTakenTest() throws Exception {
		
		mockMvc.perform(post("/registerPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "John")
			.param("password", "a")
			.param("email", "pece@gmail.com")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("registerPage"))
			.andExpect(model().attribute("userIsTaken", true))
			.andExpect(model().attribute("emailIsTaken", true));
		
	}
	
	@Test
	public void UsernameEmailPasswordErrorsTest() throws Exception {
		
		mockMvc.perform(post("/loginPage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "a")
			.param("password", "a")
			.param("email", "a")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("loginPage"))
			.andExpect(model().attributeHasFieldErrors("loginUser", "username"))
            .andExpect(model().attributeHasFieldErrors("loginUser", "password"))
            .andExpect(model().attributeHasFieldErrors("loginUser", "email"));
		
	}
	
}
