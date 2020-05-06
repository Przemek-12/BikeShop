package com.example.demo.orderItemsTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.entity.OrderItems;
import com.example.demo.orderItems.OrderItemsRepository;

@DataJpaTest
public class OrderItemsRepositoryTest {

	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private OrderItemsRepository orderItemsRepo;
	
	
	@Test
	public void findByOrderIdTest() {
		
		entityManager.persist(new OrderItems(0, 0));
		entityManager.persist(new OrderItems(0, 1));
		entityManager.flush();
		
		List<OrderItems> list = orderItemsRepo.findByOrderId(0);
		
		assertThat(list).hasSize(2);
		
	}
	
	
}
