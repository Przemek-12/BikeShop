package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;



@Data
@NoArgsConstructor
@Entity
@Table(name="bike_elements")
public class BikeElement implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="element_id")
	private long elementId;
	
	@NonNull
	@Column(name="category_id")
	private long categoryId;
	
	@NonNull
	@Column(name="model")
	private String model;
	
	@NonNull
	@Column(name="price")
	private Double price;
	
	@NonNull
	@Column(name="specification")
	private String specification;
	
	@NonNull
	@Column(name="imgPath")
	private String imgPath;

	
	

	@Override
	public String toString(){
		return model;
	}
	
	

}
