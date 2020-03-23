package com.example.demo.categories;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Categories;


@Service
public class CategoriesService {

	@Autowired
	private CategoriesDao categoriesDao;
	
	
	@Transactional
	public Categories getCategory(String element) {
		Categories category = categoriesDao.getCategory(element);
		return category;
	}
	
}
