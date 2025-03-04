package com.ashesh.journalApp.controllers;

import com.ashesh.journalApp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	public String getPost(){
		try {
			return postService.getPost().getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping
	public String createPost(){
		try {
			return postService.createPost().getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
