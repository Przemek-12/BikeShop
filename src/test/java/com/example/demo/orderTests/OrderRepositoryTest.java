package com.example.demo.orderTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.entity.Order;
import com.example.demo.order.OrderRepository;



@DataJpaTest 
public class OrderRepositoryTest {

	
	@Autowired
	//The TestEntityManager provided by Spring Boot is an alternative to the standard JPA EntityManager that provides methods commonly used when writing tests.
    private TestEntityManager entityManager;
	
	@Autowired
	private OrderRepository orderRepo;
	
	
	@Test
	public void finbyUserIdTest() {
		
		entityManager.persist(new Order(0,"desc", 1.1, LocalDateTime.now()));
		entityManager.persist(new Order(0,"desc", 1.1, LocalDateTime.now()));
		entityManager.flush();
		
		List<Order> list = orderRepo.findByUserId(0);
		
		assertThat(list).hasSize(2);
		
	}
	
	
	
}
