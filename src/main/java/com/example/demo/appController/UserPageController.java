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
@RequestMapping("/userpage")
public class UserPageController {
	
	
	@Autowired
	private OrderService orderService;
	
	

	@GetMapping
	public String userPage(Model model, HttpServletRequest request) {
				
		model.addAttribute("listOfOrders", orderService.getOrderList(request));
		
		return "userPage";
	}

}
