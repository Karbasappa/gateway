package com.sabtok.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	/*
	  @Bean
	    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	        return http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF for JWT-based auth
	            .authorizeExchange(exchanges -> exchanges
	            	.pathMatchers("/app/**").permitAll()
	                //.pathMatchers("/auth/**").permitAll()// Allow login/register without token
	                .pathMatchers("/user/**").permitAll()
	                .anyExchange().authenticated()        // Protect everything else
	            )
	            .httpBasic(Customizer.withDefaults()) 
	            .build();
	    }
	   */ 
	
	  @Bean
	  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	      return http
	          .csrf(csrf -> csrf.disable()) 
	          .authorizeExchange(exchanges -> exchanges
	              // 1. Secure the /auth/ endpoints (Requires login/Basic Auth)
	              .pathMatchers("/auth/**").authenticated()
	              
	              // 2. Allow everything else (app/**, user/**, etc.)
	              .anyExchange().permitAll()
	          )
	          .httpBasic(Customizer.withDefaults()) 
	          .build();
	  }
	  
}
