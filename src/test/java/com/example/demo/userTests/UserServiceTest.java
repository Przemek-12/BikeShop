package com.example.demo.userTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.entity.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	public void saveUserTest() {
		
		when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());
		userService.saveUser(new User());
		
		verify(userRepository, times(1)).save(Mockito.any(User.class));
		
	}
	
	@Test
	public void getUserLoginValidTest() {
		when(userRepository.findByUsernameAndPassword(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(new User());
		assertThat(userService.getUserLogin("a", "a")).isNotNull();
	}
	
	@Test
	public void getUserLoginInvalidTest() {
		when(userRepository.findByUsernameAndPassword(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(null);
		assertThat(userService.getUserLogin("a", "a")).isNull();
	}
	
	@Test
	public void getUserUsernameValidTest() {
		when(userRepository.findByUsername(Mockito.any(String.class))).thenReturn(new User());
		assertThat(userService.getUserUsername("a")).isTrue();
	}
	
	@Test
	public void getUserUsernameInvalidTest() {
		when(userRepository.findByUsername(Mockito.any(String.class))).thenReturn(null);
		assertThat(userService.getUserUsername("a")).isFalse();
	}
	
	@Test
	public void getUserEmailValidTest() {
		when(userRepository.findByEmail(Mockito.any(String.class))).thenReturn(new User());
		assertThat(userService.getUserEmail("a")).isTrue();
	}
	
	@Test
	public void getUserEmailInvalidTest() {
		when(userRepository.findByEmail(Mockito.any(String.class))).thenReturn(null);
		assertThat(userService.getUserEmail("a")).isFalse();
	}
	
	
	
}
