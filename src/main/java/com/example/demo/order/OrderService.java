package com.example.demo.order;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;


@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Transactional
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}
	
	@Transactional
	public List<Order> getOrderList(int userId){
		return orderDao.getOrderList(userId);
	}
	
}
