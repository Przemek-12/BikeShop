package com.example.demo.appController;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.User;
import com.example.demo.interfaces.Bike;
import com.example.demo.order.OrderService;
import com.example.demo.orderItems.OrderItemsService;

@Controller
@RequestMapping("orderPage")
public class OrderPageController {

	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	
	
	@GetMapping
	public String orderPage(Model model, HttpServletRequest request) {
		
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		
		model.addAttribute("bikeDescription", bike.description());
		model.addAttribute("bikePrice", bike.price());
		model.addAttribute("orderList", bike.bikeElements().values());
		
		return "orderPage";
	}
	
	
	
	@PostMapping
	public String submitOrder(HttpServletRequest request) {
		
		//user id is taken from currently logged user
		int userId = (Integer)((User)request.getSession().getAttribute("LOGGED_USER")).getUserId();
		
		//time of order submission
		LocalDateTime ldt = LocalDateTime.now();
		
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		
		//order constructor: id of current user, description of bike, price of bike, current time
		Order order = new Order(userId, bike.description(), bike.price(), ldt);
		
		//order is saved in orders table, save returns id, id is created after save method
		orderService.saveOrder(order);
		
		//iteration through map keys, id of elements are added one by one
		for(int elementId: bike.bikeElements().keySet()) {
			OrderItems orderItem = new OrderItems(order.getOrderId(), elementId);
			orderItemsService.saveItem(orderItem);
		}
		
		//bike object is removed from session
		request.getSession().removeAttribute("BikeSessionObject");
		
		return "redirect:/userPage";
	}
	
	
	
	@PostMapping("/remove")
	public String removeFromOrder(int elementId, HttpServletRequest request) {
		
		Bike bike = (Bike)request.getSession().getAttribute("BikeSessionObject");
		//request.getSession().removeAttribute("BikeSessionObject");
		
		bike.removeFromDescription(bike.bikeElements().get(elementId).getModel());	
		bike.removeFromPrice(bike.bikeElements().get(elementId).getPrice());
		
		//removes element from map of elements
		bike.removeFromMap(elementId);
	
		request.getSession().setAttribute("BikeSessionObject", bike);
		
		
		return "redirect:/orderPage";
	}
	
}
