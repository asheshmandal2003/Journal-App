package com.ashesh.journalApp.services;

import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserDetailsServiceTests {

	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLoadUserByUsername() {
		when(userRepository.findByUsername(ArgumentMatchers.anyString()))
				.thenReturn(Optional.ofNullable(User
						.builder()
						.username("ashesh001")
						.password("Ashesh@123")
						.roles(new ArrayList<>())
						.build()));

		UserDetails userDetails = userDetailsService.loadUserByUsername("ashesh001");
		Assertions.assertNotNull(userDetails, "UserDetails object is null");
	}
}
