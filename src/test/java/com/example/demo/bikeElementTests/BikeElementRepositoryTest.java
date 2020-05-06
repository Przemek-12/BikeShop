package com.example.demo.bikeElementTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.bikeElement.BikeElementRepository;
import com.example.demo.entity.BikeElement;


//@RunWith(SpringRunner.class)
@DataJpaTest 
		//setting Hibernate, Spring Data, and the DataSource
		//scans for @Entity classes and configures Spring Data JPA repositories annotated with @Repository annotation so classes with @Repository can be autowired, others has to be MockBean
		//turning on SQL logging
public class BikeElementRepositoryTest {

	
	@Autowired
	//The TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests.
    private TestEntityManager entityManager;
	
	
	@Autowired 
	private BikeElementRepository bikeElRepo;
	
	
	@Test
	public void findByElementIdTest() {
		
		BikeElement bikeElement = new BikeElement(1, "model", 1.1, "spec", "img");
		//bikeElement.setElementId(1);
		
		entityManager.persist(bikeElement);
		entityManager.flush();
		
		BikeElement be = bikeElRepo.findByElementId(bikeElement.getElementId());
		
		assertThat(be.getElementId()).isEqualTo(bikeElement.getElementId());
		
		
	}
	
	
	@Test
	public void findByCategoryIdTest() {
		
		BikeElement bikeElement1 = new BikeElement(0, "model", 1.1, "spec", "img");
		BikeElement bikeElement2 = new BikeElement(0, "model2", 1.2, "spec2", "img2");
		//bikeElement.setElementId(1);
		
		entityManager.persist(bikeElement1);
		entityManager.persist(bikeElement2);
		entityManager.flush();
		
		List<BikeElement> be = bikeElRepo.findByCategoryId(0);
		
		assertThat(be.size()).isEqualTo(2);
		
		
	}
	
	
	
	
}
