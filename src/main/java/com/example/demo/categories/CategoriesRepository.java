package com.example.demo.categories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Categories;


@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long>{

	@Query("from Categories where categoryName=:element")
	Categories getCategory(@Param("element") String element); 
	
}
