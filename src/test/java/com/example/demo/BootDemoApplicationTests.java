package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //points on mechanism used to run tests
@SpringBootTest //runs SpringApplication.run() in main method
class BootDemoApplicationTests {

	//checks if spring application context was loaded
	//Spring contexts are also called Spring IoC containers, 
	//which are responsible for instantiating, configuring, 
	//and assembling beans by reading configuration metadata from XML, 
	//Java annotations, and/or Java code in the configuration files.
	@Test
	void contextLoads() {
	}

}



