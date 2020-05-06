package com.example.demo.categoriesTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.categories.CategoriesRepository;
import com.example.demo.categories.CategoriesService;
import com.example.demo.entity.Categories;



@RunWith(MockitoJUnitRunner.class)
public class CategoriesServiceTest {

	
	@InjectMocks//creates instance of class and inject mocked dependencies(Mock annotation)
	private CategoriesService categoriesService;
	
	
	@Mock//creates model of class
	private CategoriesRepository categoriesRepository;
	
	
	//BeforeEach is from jupiter, Before from junit4 whicj is excluded
	@BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void getCategoryTest() {
		
		Categories cat = new Categories("test", "next");
		when(categoriesRepository.findByCategoryName(Mockito.any(String.class))).thenReturn(cat);
		
		Categories cat2 = categoriesService.getCategory("test");
		
		assertThat(cat.getCategoryName()).isEqualTo(cat2.getCategoryName());
		
	}
	
	
	
}
