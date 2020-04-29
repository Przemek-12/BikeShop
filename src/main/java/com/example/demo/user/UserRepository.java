package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	@Query("from User where username=:username and password=:password")
	User getUserLogin(@Param("username") String username, @Param("password") String password);
	
	
	@Query("from User where username=:username")
	User getUserUsername(@Param("username") String username);
	
	
	@Query("from User where email=:email")
	User getUserEmail(@Param("email") String email); 
	
}
