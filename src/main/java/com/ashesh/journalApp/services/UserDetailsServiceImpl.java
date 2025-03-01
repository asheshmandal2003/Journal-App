package com.ashesh.journalApp.services;

import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.repositories.UserRepository;
import com.ashesh.journalApp.entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()){
			return new UserInfo(user.get());
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
}
