package com.sabtok.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

	
	@Value("${env: none}")
	private String environment;
	
	@GetMapping("/info")
	public String getEnvironment() {
		return environment;
	}
	
}
