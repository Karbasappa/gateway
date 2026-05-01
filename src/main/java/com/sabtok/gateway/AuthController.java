package com.sabtok.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

	 @Autowired
	    private JwtProvider jwtProvider;

	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
	        // 1. Authenticate user against database (omitted for brevity)
	        // 2. If valid, generate token
	        String token = jwtProvider.generateToken(loginRequest.getUsername());
	        
	        return ResponseEntity.ok(token);
	    }
	
}
