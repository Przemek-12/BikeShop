package com.example.demo.orderTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;

import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.User;
import com.example.demo.interfaces.Bike;
import com.example.demo.interfacesImpl.BasicBike;
import com.example.demo.interfacesImpl.BikeElementDecorator;
import com.example.demo.order.OrderRepository;
import com.example.demo.order.OrderService;
import com.example.demo.orderItems.OrderItemsService;



@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	
	@InjectMocks
	private OrderService orderService;
	
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private OrderItemsService orderItemsService;
	
	@Mock
	private BikeElementService bikeElementService;
	
	
	@BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void saveOrderTest() {
		
		MockHttpSession session = new MockHttpSession();
		
		Bike bike = new BasicBike();
		bike = new BikeElementDecorator(bike, new BikeElement(1, "a", 1.1, "a", "a"));
		session.setAttribute("BikeSessionObject", bike);
		
		User user = new User();
		user.setUserId(1);
		user.setUsername("John");
		user.setPassword("Wick");
		session.setAttribute("LOGGED_USER", user);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		
		when(orderRepository.save(Mockito.any(Order.class))).thenReturn(new Order());
		doNothing().when(orderItemsService).saveItem(Mockito.any(OrderItems.class));
		
		orderService.saveOrder(request);
			
		//verifies how many times method was invoked
		verify(orderRepository, times(1)).save(Mockito.any(Order.class));
		verify(orderItemsService, times(1)).saveItem(Mockito.any(OrderItems.class));
			
		
		
	}
	
	
	@Test
	public void getorderListTest() {
		
		MockHttpSession session = new MockHttpSession();
		
		User user = new User();
		user.setUserId(1);
		user.setUsername("John");
		user.setPassword("Wick");
		session.setAttribute("LOGGED_USER", user);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setSession(session);
		
		
		List<Order> listOfOrders = new ArrayList<>();
		listOfOrders.add(new Order(1,"desc", 1.1, LocalDateTime.now()));
		listOfOrders.add(new Order(1,"desc", 1.1, LocalDateTime.now()));
		
		
		List<OrderItems> orderItemsObjects = new ArrayList<>();
		orderItemsObjects.add(new OrderItems(1,1));
		orderItemsObjects.add(new OrderItems(1,2));
		
		
		when(orderRepository.findByUserId(Mockito.any(Long.class))).thenReturn(listOfOrders);
		when(orderItemsService.getOrderItemsList(Mockito.any(Long.class))).thenReturn(orderItemsObjects);
		when(bikeElementService.getElement(Mockito.any(Long.class))).thenReturn(new BikeElement());
		
		List<Order> returnedList = orderService.getOrderList(request);
		
		verify(orderRepository, times(1)).findByUserId(Mockito.any(Long.class));
		verify(orderItemsService, times(2)).getOrderItemsList(Mockito.any(Long.class));
		verify(bikeElementService, times(4)).getElement(Mockito.any(Long.class));
		
		assertThat(returnedList.size()).isEqualTo(2);
		
	}
	
	
	
	

	
	
}















