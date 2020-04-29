package com.example.demo.orderItems;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderItems;

@Service
public class OrderItemsService {

	@Autowired
	private OrderItemsRepository orderItemsRepository;
	
	@Transactional
	public void saveItem(OrderItems item) {
		orderItemsRepository.save(item);
	}
	
	@Transactional
	public List<OrderItems> getOrderItemsList(long orderId){
		return orderItemsRepository.findByOrderId(orderId);
	}
}
