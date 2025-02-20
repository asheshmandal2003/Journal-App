package com.ashesh.journalApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

	@GetMapping("/api/v1/health-check")
	public String healthCheck() {
		return "Healthy";
	}
}
