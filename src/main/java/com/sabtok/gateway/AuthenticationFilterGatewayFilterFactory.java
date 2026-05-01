package com.sabtok.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationFilterGatewayFilterFactory.Config> {

    @Autowired
    private JwtUtil jwtUtil;
	    


	    public AuthenticationFilterGatewayFilterFactory() {
	        super(Config.class);
	    }

	    @Override
	    public GatewayFilter apply(Config config) {
	        return (exchange, chain) -> {
	            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
	                throw new RuntimeException("Missing Authorization Header");
	            }

	            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
	            if (authHeader != null && authHeader.startsWith("Bearer ")) {
	                authHeader = authHeader.substring(7);
	            }

	            try {
	                jwtUtil.validateToken(authHeader);
	            } catch (Exception e) {
	            	 e.printStackTrace(); // This will show why validation failed in the console
	                throw new RuntimeException("Unauthorized access to application");
	            }
	            return chain.filter(exchange);
	        };
	    }

	    public static class Config {}
	
}
