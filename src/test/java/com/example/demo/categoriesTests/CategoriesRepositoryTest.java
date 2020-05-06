package com.example.demo.categoriesTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.categories.CategoriesRepository;
import com.example.demo.entity.Categories;

@DataJpaTest
public class CategoriesRepositoryTest {

	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Autowired
	private CategoriesRepository catRepo; 
	
	
	
	@Test
	public void findByCategoryNameTest() {
		
		Categories cat = new Categories("testCatName", "testnextCat");
		
		entityManager.persist(cat);
		entityManager.flush();
		
		Categories cat2 = catRepo.findByCategoryName("testCatname");
		
		assertThat(cat.getCategoryName()).isEqualTo(cat2.getCategoryName());
		
	}
	
	
	
	
}
