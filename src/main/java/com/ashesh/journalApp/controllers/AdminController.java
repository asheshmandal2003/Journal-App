package com.ashesh.journalApp.controllers;

import com.ashesh.journalApp.entities.User;
import com.ashesh.journalApp.services.UserService;
import com.ashesh.journalApp.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<ResponseData> getAllUsers() {
		try {
			List<User> users = userService.getAllUsers();
			return new ResponseEntity<>(new ResponseData("Users fetched!", users), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseData(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
