package com.example.demo.orderItems;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderItems;

@Repository
public class OrderItemsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveItem(OrderItems item) {
		sessionFactory.getCurrentSession().save(item);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderItems> getOrderItemsList(int orderId){
		return sessionFactory.getCurrentSession().createQuery("from OrderItems where orderId="+String.valueOf(orderId)).list();
	}
}
