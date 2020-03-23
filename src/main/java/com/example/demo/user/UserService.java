package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Service //creates bean so autowired can be used 
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
	@Transactional
	public void saveUser(User user) {
		userDao.saveUser(user);
	}
	
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	@Transactional
	public User getUserLogin(String username, String password) {
		User user = userDao.getUserLogin(username, password);
		if(user!=null) {
			return user;
		}
		return null;
	}
	
	@Transactional
	//returns true if username exists in db
	public boolean getUserUsername(String username) {
		User user = userDao.getUserUsername(username);
		if(user==null) {
			return false;
		}
		return true;
	}
	
	@Transactional
	//returns true if email exists in db
	public boolean getUserEmail(String email) {
		User user = userDao.getUserEmail(email);
		if(user==null) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}
}
