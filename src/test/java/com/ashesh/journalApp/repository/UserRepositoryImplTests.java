package com.ashesh.journalApp.repository;

import com.ashesh.journalApp.repositories.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {

	@Autowired
	private UserRepositoryImpl userRepositoryImpl;

	@Test
	public void testGetUsersForSA(){
		Assertions.assertNotNull(userRepositoryImpl.getUsersForSA());
	}
}
