package com.example.demo.appController;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.User;
import com.example.demo.interfaces.Bike;
import com.example.demo.order.OrderService;
import com.example.demo.orderItems.OrderItemsService;
import com.example.demo.services.OrderPageService;

@Controller
@RequestMapping("/orderpage")
public class OrderPageController {

	
	@Autowired
	private OrderService orderService;
	
	
	@Autowired
	private OrderPageService orderPageService;
	
	
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
				
		orderService.saveOrder(request);

		return "redirect:/userpage";
	}
	
	
	
	@DeleteMapping
	public String removeFromOrder(@RequestBody String elementIdS, HttpServletRequest request) {
		
		orderPageService.removeFromOrder(elementIdS, request);
		
		return "orderpage";
	}
	
	
	
	
}










