package com.example.demo.appController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(MainPageController.class) 
public class MainPageControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHomePage() throws Exception{
		mockMvc.perform(get("/mainPage"))
			.andExpect(status().isOk()) //http 200 code
			.andExpect(view().name("mainPage"));
			
	}
}
