package com.example.demo.userTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.entity.User;
import com.example.demo.user.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	public void findByUsernameAndPasswordTest() {
		
		entityManager.persist(new User("name", "pass", "mail@mail"));
		entityManager.flush();
		
		User user = userRepository.findByUsernameAndPassword("name", "pass");
		
		assertThat(user).isNotNull();
		
	}
	
	@Test
	public void findByUsernameTest() {
		
		entityManager.persist(new User("name", "pass", "mail@mail"));
		entityManager.flush();
		
		User user = userRepository.findByUsername("name");
		
		assertThat(user).isNotNull();
		
	}
	
	@Test
	public void findByEmailTest() {
		
		entityManager.persist(new User("name", "pass", "mail@mail"));
		entityManager.flush();
		
		User user = userRepository.findByEmail("mail@mail");
		
		assertThat(user).isNotNull();
		
	}
	
}
