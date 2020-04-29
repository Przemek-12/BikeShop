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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "orderId_generator")
	@SequenceGenerator(name="orderId_generator", sequenceName = "orderId_seq", allocationSize=50)
	@Column(name="order_id")
	private long orderId;
	
	@NonNull
	@Column(name="user_id")
	private long userId;
	
	@NonNull
	@Column(name="bike_description")
	private String description;
	
	@NonNull
	@Column(name="price")
	private Double price;
	
	@NonNull
	@Column(name="orderSubmitTime")
	private LocalDateTime orderSubmitTime;
	
	@Transient
	//field has no representation as column in database
	private List<BikeElement> listOfElements;
	


	
}
