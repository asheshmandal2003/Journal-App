package com.ashesh.journalApp.controllers;

import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.services.UserService;
import com.ashesh.journalApp.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ResponseData> addUser(@RequestBody User user) {
		try {
			User newUser = userService.saveUser(user);
			return new ResponseEntity<>(
					new ResponseData("User added!", newUser),
					HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error while adding user: {}", e.getMessage());
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseData> login(@RequestBody User user) {
		try {
			return new ResponseEntity<>(
					new ResponseData("User logged in!", userService.login(user)),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<ResponseData> getUser(@PathVariable String username) {
		try {
			User user = userService.getUserByUsername(username);
			return new ResponseEntity<>(
					new ResponseData("User fetched!", user),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{username}")
	public ResponseEntity<ResponseData> updateJournalEntry(
			@PathVariable String username,
			@RequestBody User newUser) {
		try {
			userService.updateUser(username, newUser);
			return new ResponseEntity<>(
					new ResponseData("User updated!", newUser),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ResponseData(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
