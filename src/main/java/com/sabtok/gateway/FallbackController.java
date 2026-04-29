package com.sabtok.gateway;

import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class FallbackController {

	@RequestMapping("/serviceUnavailableFallback")
	public Mono<String> fallback() {
        return Mono.just("The user service is taking too long or is down. Please try again later.");
    }
	
}
