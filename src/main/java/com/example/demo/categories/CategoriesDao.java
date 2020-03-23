package com.example.demo.categories;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Categories;

@Repository
public class CategoriesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Categories getCategory(String element) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Categories where categoryName='"+element+"'");
		Categories category = (Categories)query.uniqueResult();
		return category;
	}
}
