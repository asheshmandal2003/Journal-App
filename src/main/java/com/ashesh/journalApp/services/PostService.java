package com.ashesh.journalApp.services;

import com.ashesh.journalApp.apiResponse.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {

	@Autowired
	private RestTemplate restTemplate;

	public PostResponse getPost() {
		ResponseEntity<PostResponse> response = restTemplate
				.exchange(
						"https://jsonplaceholder.typicode.com/posts/1",
						HttpMethod.GET,
						null,
						PostResponse.class
				);

		return response.getBody();
	}

	public PostResponse createPost() {

		PostResponse newPost = PostResponse.builder().id(101).title("foo").body("bar").userId(1).build();
		HttpEntity<PostResponse> httpEntity = new HttpEntity<>(newPost);

		ResponseEntity<PostResponse> response = restTemplate
				.exchange(
						"https://jsonplaceholder.typicode.com/posts",
						HttpMethod.POST,
						httpEntity,
						PostResponse.class
				);

		return response.getBody();
	}
}
