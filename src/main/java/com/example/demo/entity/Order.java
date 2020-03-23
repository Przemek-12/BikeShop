package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "orderId_generator")
	@SequenceGenerator(name="orderId_generator", sequenceName = "orderId_seq", allocationSize=50)
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="bike_description")
	private String description;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="orderSubmitTime")
	private LocalDateTime orderSubmitTime;
	
	@Transient
	//field has no representation as column in database
	private List<BikeElement> listOfElements;
	

	
	
	
	public Order() {}


	public Order(int userId, String description, Double price, LocalDateTime orderSubmitTime) {
		super();
		this.userId = userId;
		this.description = description;
		this.price = price;
		this.orderSubmitTime = orderSubmitTime;
	}
	
	
	
	

	public List<BikeElement> getListOfElements() {
		return listOfElements;
	}


	public void setListOfElements(List<BikeElement> listOfElements) {
		this.listOfElements = listOfElements;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getOrderSubmitTime() {
		return orderSubmitTime;
	}

	public void setOrderSubmitTime(LocalDateTime orderSubmitTime) {
		this.orderSubmitTime = orderSubmitTime;
	}


	
}
