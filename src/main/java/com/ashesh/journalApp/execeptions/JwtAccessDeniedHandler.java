package com.ashesh.journalApp.execeptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden

		Map<String, Object> errorDetails = new HashMap<>();
		errorDetails.put("status", HttpServletResponse.SC_FORBIDDEN);
		errorDetails.put("error", "Forbidden");
		errorDetails.put("message", "You do not have permission to access this resource!");
		errorDetails.put("path", request.getRequestURI());

		ObjectMapper objectMapper = new ObjectMapper();
		response.getWriter().write(objectMapper.writeValueAsString(errorDetails));
	}
}
