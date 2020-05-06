package com.example.demo.orderItemsTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.entity.OrderItems;
import com.example.demo.orderItems.OrderItemsRepository;
import com.example.demo.orderItems.OrderItemsService;


@RunWith(MockitoJUnitRunner.class)
public class OrderItemsServiceTest {

	
	@InjectMocks
	private OrderItemsService orderItemsService;
	
	@Mock
	private OrderItemsRepository orderItemsRepository;
	
	@BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void saveItemTest() {
		
		OrderItems item = new OrderItems(0, 0);
		when(orderItemsRepository.save(Mockito.any(OrderItems.class))).thenReturn(item);
		
		orderItemsService.saveItem(item);
		
		verify(orderItemsRepository, times(1)).save(Mockito.any(OrderItems.class));
		
	}
	
	
	@Test
	public void getOrderItemsListTest() {
		
		OrderItems item = new OrderItems(0, 0);
		OrderItems item2 = new OrderItems(0, 1);
		
		List<OrderItems> list1 = new ArrayList<>();
		list1.add(item);
		list1.add(item2);
		
		when(orderItemsRepository.findByOrderId(Mockito.any(Long.class))).thenReturn(list1);
		
		List<OrderItems> list2 = orderItemsService.getOrderItemsList(0);
		
		assertThat(list2).hasSize(2);
		
	}
	
	
	
}














