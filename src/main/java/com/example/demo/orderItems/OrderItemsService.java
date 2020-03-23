package com.example.demo.orderItems;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderItems;

@Service
public class OrderItemsService {

	@Autowired
	private OrderItemsDao orderItemsDao;
	
	@Transactional
	public void saveItem(OrderItems item) {
		orderItemsDao.saveItem(item);
	}
	
	@Transactional
	public List<OrderItems> getOrderItemsList(int orderId){
		return orderItemsDao.getOrderItemsList(orderId);
	}
}
