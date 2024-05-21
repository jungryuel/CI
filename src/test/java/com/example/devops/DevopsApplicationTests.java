package com.example.devops;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Value("${spring.datasource.username}")
	private String name;

	@Test
	void dbUrlIsCorrect() {
		String userName = "sa";
		assertEquals(userName, name);
	}
}
