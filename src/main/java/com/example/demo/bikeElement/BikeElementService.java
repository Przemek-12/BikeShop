package com.example.demo.bikeElement;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.BikeElement;

@Service
public class BikeElementService {

	@Autowired
	private BikeElementDao bikeElementDao;
	
	@Transactional
	public BikeElement getElement(int id) {
		return bikeElementDao.getElement(id);
	}
	
	@Transactional
	public List<BikeElement> getList(int categoryId){
		return bikeElementDao.getList(categoryId);
	}
	
}
