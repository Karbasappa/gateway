package com.sabtok.gateway;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	 // Generate a secure key for HS256
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expirationTime = 3600000; // 1 hour in milliseconds

    public static final String secret = "your-very-secure-secret-key-32-chars-long";

	  private Key getSigningKey() {
	        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }
    
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // You can add custom claims like roles here
        claims.put("role", "USER");

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Use the same key here!
                .compact();
}
}
