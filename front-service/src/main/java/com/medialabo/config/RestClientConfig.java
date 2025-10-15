package com.medialabo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
	@Bean
    RestClient apiRestClient(@Value("${custom.api-base-url}") String apiBaseUrl) {
        return RestClient.builder()
                .baseUrl(apiBaseUrl) // <- clé: plus aucun "http://localhost:8081" en dur
                .build();
    }
}