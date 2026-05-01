package com.sabtok.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	  @Bean
	    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	        return http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF for JWT-based auth
	            .authorizeExchange(exchanges -> exchanges
	                .pathMatchers("/auth/**").permitAll()
	                .pathMatchers("/user/**").permitAll()// Allow login/register without token
	                .anyExchange().authenticated()        // Protect everything else
	            )
	            .build();
	    }
	
}
