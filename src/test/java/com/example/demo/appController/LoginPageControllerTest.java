package com.example.demo.appController;

import static org.mockito.Mockito.when;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.User;
import com.example.demo.order.OrderRepository;
import com.example.demo.orderItems.OrderItemsRepository;
import com.example.demo.user.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoginPageController.class)
public class LoginPageControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private UserService userService;
	

	@Test
	public void pageLoadTest() throws Exception {
		mockMvc.perform(get("/loginpage"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("loginPage"))
		.andExpect(model().attribute("wrongData", false));
	}
	
	

	@Test
	//checks if user was logged properly, redirection to mainPage, status 302
	public void CorrectUserTest() throws Exception {
		
		when(userService.getUserLogin(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(new User());
		
		mockMvc.perform(post("/loginpage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "John")
			.param("password", "Wick")
			)
			.andDo(print())
			.andExpect(redirectedUrl("/mainpage"));
		
	}
	
	
	@Test
	public void UncorrectUserTest() throws Exception {
		
		when(userService.getUserLogin(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(null);
		
		mockMvc.perform(post("/loginpage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "John")
			.param("password", "Wayne")
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("loginPage"))
			.andExpect(model().attribute("wrongData", true));
		
	}
	
	
	@Test
	public void UsernameAndPasswordErrorsTest() throws Exception {
		
		mockMvc.perform(post("/loginpage")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("username", "a")
			.param("password", "a")	
			)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("loginPage"))
			.andExpect(model().attributeHasFieldErrors("loginUser", "username"))
            .andExpect(model().attributeHasFieldErrors("loginUser", "password"))
			.andExpect(model().attribute("wrongData", true));
		
	}
	
}















