package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);
	
	User findByEmail(String email); 
	
}
