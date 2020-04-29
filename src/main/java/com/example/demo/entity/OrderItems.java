package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="order_items")
public class OrderItems implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_item_id")
	private long orderItemId;
	
	@NonNull
	@Column(name="order_id")
	private long orderId;
	
	@NonNull
	@Column(name="element_id")
	private long elementId;
	
	


	
}
