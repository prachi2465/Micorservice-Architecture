package com.prachi.user.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.prachi.user.service.config.intercepter.RestTemplateInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration
public class MyConfig {//configuration for rest template
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
	
	@Bean
	@LoadBalanced
	RestTemplate template() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> list = new ArrayList<>();
		list.add(new RestTemplateInterceptor(auth2AuthorizedClientManager(
				clientRegistrationRepository, auth2AuthorizedClientRepository)));
		
		restTemplate.setInterceptors(list);
		return restTemplate;
	}

	@Bean
	OAuth2AuthorizedClientManager auth2AuthorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository) {
		
		OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials()
				.build();

		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository, auth2AuthorizedClientRepository);
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
		return defaultOAuth2AuthorizedClientManager;
	}
}
