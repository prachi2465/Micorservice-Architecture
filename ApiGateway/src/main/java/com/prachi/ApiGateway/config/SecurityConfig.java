package com.prachi.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity//spring cloud supports webflux
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
		.authorizeExchange()//authorize request
		.anyExchange() //any request
		.authenticated() //should be authenticated
		.and()
		.oauth2Client()//configure login
		.and()
		.oauth2ResourceServer()
		.jwt();
		
		return httpSecurity.build();
	}
}
