package com.example.demo.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.User;
import com.example.demo.interfaces.Bike;
import com.example.demo.orderItems.OrderItemsService;


@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	
	@Autowired
	private BikeElementService bikeElementService;
	
	
	
	@Transactional
	public void saveOrder(HttpServletRequest request) {
		
		//user id is taken from currently logged user
		long userId = (Long)((User)request.getSession().getAttribute("LOGGED_USER")).getUserId();
				
		//time of order submission
		LocalDateTime ldt = LocalDateTime.now();
				
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
				
		//order constructor: id of current user, description of bike, price of bike, current time
		Order order = new Order(userId, bike.description(), bike.price(), ldt);
				
		//order is saved in orders table, save returns id, id is created after save method
		orderRepository.save(order);
		
		//iteration through map keys, id of elements are added one by one
		for(long elementId: bike.bikeElements().keySet()) {
			OrderItems orderItem = new OrderItems(order.getOrderId(), elementId);
			orderItemsService.saveItem(orderItem);
		}
				
		//bike object is removed from session
		request.getSession().removeAttribute("BikeSessionObject");
		
	}
	
	
	@Transactional
	public List<Order> getOrderList(HttpServletRequest request){
		
		User user = (User)request.getSession().getAttribute("LOGGED_USER");
		
		//list of all order submitted by logged user
		List<Order> listOfOrders = orderRepository.findByUserId(user.getUserId());
				
		for(Order order: listOfOrders) {
					
			//list of OrderItems objects for particular order
			List<OrderItems> orderItemsObjects = orderItemsService.getOrderItemsList(order.getOrderId());
					
			//list of elements that will be set to order object
			List<BikeElement> listOfElements = new ArrayList<>();
					
			for(OrderItems item: orderItemsObjects) {
						
				//orderItems object contain elemntId, so those elements must be taken from db using those ids
				listOfElements.add(bikeElementService.getElement(item.getElementId()));
			}
			order.setListOfElements(listOfElements);
					
		}
		
		return listOfOrders;
	}
	
}












