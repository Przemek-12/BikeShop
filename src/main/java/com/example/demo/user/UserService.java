package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Service //creates bean so autowired can be used 
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	
	@Transactional
	public User getUserLogin(String username, String password) {
		User user = userRepository.getUserLogin(username, password);
		if(user!=null) {
			return user;
		}
		return null;
	}
	
	@Transactional
	//returns true if username exists in db
	public boolean getUserUsername(String username) {
		User user = userRepository.getUserUsername(username);
		if(user==null) {
			return false;
		}
		return true;
	}
	
	@Transactional
	//returns true if email exists in db
	public boolean getUserEmail(String email) {
		User user = userRepository.getUserEmail(email);
		if(user==null) {
			return false;
		}
		return true;
	}
	
	
}
