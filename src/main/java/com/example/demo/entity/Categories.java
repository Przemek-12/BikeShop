package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categories implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	@Column(name="next_category")
	private String nextCategory;

	
	
	public Categories() {
		super();
	}

	public Categories(int categoryId, String categoryName, String nextCategory) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.nextCategory = nextCategory;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getNextCategory() {
		return nextCategory;
	}

	public void setNextCategory(String nextCategory) {
		this.nextCategory = nextCategory;
	}
	
	
	
	
	
}
