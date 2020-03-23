package com.example.demo.bikeElement;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.categories.CategoriesService;
import com.example.demo.entity.BikeElement;
import com.example.demo.entity.Categories;

@Repository
public class BikeElementDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	public BikeElement getElement(int id) {
		return (BikeElement)sessionFactory.getCurrentSession().createQuery("from BikeElement where elementId="+String.valueOf(id)).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<BikeElement> getList(int categoryId){
		return sessionFactory.getCurrentSession().createQuery("from BikeElement where categoryId="+String.valueOf(categoryId)).list();
	}
}
