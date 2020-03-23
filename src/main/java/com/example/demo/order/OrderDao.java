package com.example.demo.order;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

@Repository
public class OrderDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveOrder(Order order) {
		sessionFactory.getCurrentSession().save(order);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getOrderList(int userId){
		return sessionFactory.getCurrentSession().createQuery("from Order where userId="+String.valueOf(userId)).list();
	}

	
}
