package com.prachi.user.service.config.intercepter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
	
	@Autowired
	private OAuth2AuthorizedClientManager auth2AuthorizedClientManager;

	public RestTemplateInterceptor(OAuth2AuthorizedClientManager auth2AuthorizedClientManager) {
		//super();
		this.auth2AuthorizedClientManager = auth2AuthorizedClientManager;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		// TODO Auto-generated method stub
		String token = auth2AuthorizedClientManager.authorize(
				OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())
				.getAccessToken().getTokenValue();
		
		request.getHeaders().add("Authorization", "Bearer "+token);
		return execution.execute(request, body);
	}

}
