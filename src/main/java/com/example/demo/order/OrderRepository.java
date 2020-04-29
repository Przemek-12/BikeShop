package com.example.demo.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Query("from Order where userId=:userId")
	List<Order> getOrderList(@Param("userId") long userId);
	
}