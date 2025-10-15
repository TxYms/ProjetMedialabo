package com.medialabo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "patient.api")
public record PatientApiProperties(
    String baseUrl,
    String username,
    String password
) {}