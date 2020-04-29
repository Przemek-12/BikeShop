package com.example.demo.bikeElement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BikeElement;


@Repository
public interface BikeElementRepository extends JpaRepository<BikeElement, Long>{

	
	@Query("from BikeElement where elementId=:elementId")
	BikeElement getElement(@Param("elementId") long elementId); 
	
	
	@Query("from BikeElement where categoryId=:categoryId")
	List<BikeElement> getList(@Param("categoryId") long categoryId);
	
	
}
