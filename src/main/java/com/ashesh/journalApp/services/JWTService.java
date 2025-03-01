package com.ashesh.journalApp.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Service
public class JWTService {

	private String secretKey = "";

	public JWTService() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
		SecretKey secretKey = keyGenerator.generateKey();
		this.secretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();

		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.and()
				.signWith(getKey())
				.compact();
	}

	private Key getKey() {
		byte[] keyInBytes = Decoders.BASE64.decode(this.secretKey);
		return Keys.hmacShaKeyFor(keyInBytes);
	}
}
