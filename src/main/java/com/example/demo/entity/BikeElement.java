package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bike_elements")
public class BikeElement implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="element_id")
	private int elementId;
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="model")
	private String model;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="specification")
	private String specification;
	
	@Column(name="imgPath")
	private String imgPath;

	public BikeElement() {
	
	}

	public BikeElement(int elementId, int categoryId, String model, Double price, String specification, String imgPath) {
		super();
		this.elementId=elementId;
		this.categoryId = categoryId;
		this.model = model;
		this.price = price;
		this.specification = specification;
		this.imgPath = imgPath;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Override
	public String toString(){
		return model;
	}
	
	

}
