package com.example.demo.user;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;


@Repository
public class UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	
	public void saveUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	
	// method used when user is logging in, checks if password and username are in same row in db
	public User getUserLogin(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username=:username and password=:password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		return (User)query.uniqueResult();
	}
	
	public User getUserUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where username=:username");
		query.setParameter("username", username);
		return (User)query.uniqueResult();
	}
	
	public User getUserEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where email=:email");
		query.setParameter("email", email);
		return (User)query.uniqueResult();
	}
	
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}
	
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}
}
