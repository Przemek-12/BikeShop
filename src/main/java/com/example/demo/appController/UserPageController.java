package com.example.demo.appController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bikeElement.BikeElementService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.User;
import com.example.demo.order.OrderService;
import com.example.demo.orderItems.OrderItemsService;

@Controller
@RequestMapping("/userPage")
public class UserPageController {
	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	@Autowired
	private BikeElementService bikeElementService;
	
	
	@GetMapping
	public String userPage(Model model, HttpServletRequest request) {
		
		User user = (User)request.getSession().getAttribute("LOGGED_USER");
		
		//list of all order submitted by logged user
		List<Order> listOfOrders = orderService.getOrderList(user.getUserId());
		
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
		
		
		model.addAttribute("listOfOrders", listOfOrders);
		

		
		
		return "userPage";
	}

}
