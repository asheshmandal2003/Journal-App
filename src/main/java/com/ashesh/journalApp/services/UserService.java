package com.ashesh.journalApp.services;

import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);


	public User saveUser(User user) throws Exception {
		user.setPassword(encoder.encode(user.getPassword().trim()));
		return userRepository.save(user);
	}

	public Map<String, Object> login(User user) throws Exception {
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								user.getUsername(), user.getPassword()));

		if (authentication.isAuthenticated()){
			String token = jwtService.generateToken(user.getUsername());
			User loggedUser = getUserByUsername(authentication.getName());

			return Map.of("token", token, "user", loggedUser);
		}
		throw new Exception("Invalid credentials!");
	}

	public List<User> getAllUsers() throws Exception {
		return userRepository.findAll();
	}

	public User getUserByUsername(String username) throws Exception{
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isEmpty()){
			throw new Exception("User not found!");
		}
		return user.get();
	}

	public void updateUser(String username, User newUser){
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isPresent()){
			String _username = newUser.getUsername().trim();

			if (!_username.isEmpty() && !_username.equals(user.get().getUsername())){
				user.get().setUsername(_username);
				userRepository.save(user.get());
			}
		}
	}
}
