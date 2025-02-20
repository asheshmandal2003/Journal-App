package com.ashesh.journalApp.services;

import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;


	public void saveUser(User user) throws Exception {
		userRepository.save(user);
	}


	public List<User> getAllUsers() throws Exception {
		return userRepository.findAll();
	}


	public User getUserByUsername(String username) throws Exception{
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
		if(user.isEmpty()){
			throw new Exception("User not found!");
		}
		return user.get();
	}


	public void updateUser(String username, User newUser){
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));

		if (user.isPresent()){
			String _username = newUser.getUsername().trim();

			if (!_username.isEmpty() && !_username.equals(user.get().getUsername())){
				user.get().setUsername(_username);
				userRepository.save(user.get());
			}
		}
	}
}
