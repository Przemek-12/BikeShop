package com.example.demo.appControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.appController.UserPageController;
import com.example.demo.entity.User;



@RunWith(SpringRunner.class)
@WebMvcTest(UserPageController.class)
public class UserPageControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@Transactional
	public void pageLoadTest() throws Exception {
		
		MockHttpSession session = new MockHttpSession();
		User user = new User();
		user.setUsername("John");
		user.setPassword("Wick");
		
		session.setAttribute("LOGGED_USER", user);
		
		mockMvc.perform(get("/userPage").session(session))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("userPage"));
	}
	
	
	
	
}
