package com.example.demo.appControllerTests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareConcurrentModel;
import org.springframework.validation.support.BindingAwareModelMap;

import com.example.demo.appController.BikeElementPageController;
import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.categories.CategoriesService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.User;
import com.example.demo.interfacesImpl.BasicBike;
import com.example.demo.services.BikeElementPageService;


@RunWith(SpringRunner.class)
@WebMvcTest(BikeElementPageController.class)
public class BikeElementPageControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BikeElementPageService bikeElementPageService;

	@MockBean
	//bike elements database methods
	private BikeElementService bikeElementService;
	
	@MockBean
	//elements categories database methods
	private CategoriesService categoriesService;
	
	
	@Test
	public void bikeElementPageNotLoggedTest() throws Exception {
		mockMvc.perform(get("/frames"))
		.andDo(print())
		.andExpect(redirectedUrl("/mainpage"))//instead of status.isOk, redirection should return status 302
		.andExpect(view().name("redirect:/mainpage"));
	}
	
	
	
	@Test
	public void bikeElementPageLoggedTest() throws Exception{
		
		MockHttpSession session = new MockHttpSession();
		User user = new User();
		user.setUsername("John");
		user.setPassword("Wick");
		
		session.setAttribute("LOGGED_USER", user);
		session.setAttribute("BikeSessionObject", new BasicBike());
		
		List<String> list = new ArrayList<>(Arrays.asList("frames", "brakes", "derailleurs", "pedals", "wheels", "saddles", "handlebars"));
		
		
		list.forEach((item)->{

			try {
				Model model = new  ConcurrentModel();
				
				when(bikeElementPageService.bikeElementPageGetModel(Mockito.any(),  Mockito.any(), Mockito.any())).thenReturn(model);
		
				mockMvc.perform(get("/"+item).session(session))
				.andDo(print())
				.andExpect(status().isOk()) 
				.andExpect(view().name("bikeElementPage"));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
	}
	
	
	
	@Test
	public void bikeElementPagePostTest() throws Exception {
		
		MockHttpSession session = new MockHttpSession();
		User user = new User();
		user.setUsername("John");
		user.setPassword("Wick");
		
		when(bikeElementPageService.bikeElementPagePost(Mockito.any(),  Mockito.any(), Mockito.any())).thenReturn("nextCategory");
		
		mockMvc.perform(post("/"+"frames").session(session))
				.andDo(print())
				.andExpect(view().name("redirect:/nextCategory"));
		
		
		
		
	}
	
	
	
	
}










