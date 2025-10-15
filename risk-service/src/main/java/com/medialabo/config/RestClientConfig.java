package com.medialabo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

  // ✅ passe par la gateway (elle ajoute déjà l’Authorization Basic)
  private static final String GATEWAY_BASE_URL = "http://api-gateway:8081";

  @Bean
   RestClient restClient() {
    return RestClient.builder()
        .baseUrl(GATEWAY_BASE_URL)
        .build();
  }
}