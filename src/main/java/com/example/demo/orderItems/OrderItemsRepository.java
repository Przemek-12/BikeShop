package com.example.demo.orderItems;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrderItems;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{

	@Query("from OrderItems where orderId=:orderId")
	List<OrderItems> getOrderItemsList(@Param("orderId")long orderId);
	
}
