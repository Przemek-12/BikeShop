package com.example.demo.bikeElement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BikeElement;


@Repository
public interface BikeElementRepository extends JpaRepository<BikeElement, Long>{

	BikeElement findByElementId(long elementId); 
	
	List<BikeElement> findByCategoryId(long categoryId);
	
	
}
