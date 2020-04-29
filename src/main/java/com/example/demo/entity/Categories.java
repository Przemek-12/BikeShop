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
@Table(name="categories")
public class Categories implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private long categoryId;
	
	@NonNull
	@Column(name="category_name")
	private String categoryName;
	
	@NonNull
	@Column(name="next_category")
	private String nextCategory;


	
	
}
