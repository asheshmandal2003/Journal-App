package com.ashesh.journalApp.services;

import com.ashesh.journalApp.entities.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;

	@ParameterizedTest
	@ValueSource(strings = {
			"ashesh001",
			"ashesh007",
	})
	public void testGetUserByUsername(String username) throws Exception {
		User user = userService.getUserByUsername(username);
		assertNotNull(user);
	}
}
