package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
	//contains:
	//@SpringBootConfiguration - configuration class, specialized form of Configuration annotation
	//@EnableAutoConfiguration - automatically switches on Spring Boot configuration
	//@ComponentScan - scan for components, classes with annotations @Component, @Controller, @Service
	//@EnableWebMvc: Flags the application as a web application and activates key behaviors, such as setting up a DispatcherServlet. 
	//				Spring Boot adds it automatically when it sees spring-webmvc on the classpath.
	//DispatcherServlet is responsible for correctly coordinating the HttpRequests to their right handlers.
@ComponentScan(basePackages="com.example.demo")
@EntityScan(basePackages="com.example.demo.user")
public class BootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}

}
