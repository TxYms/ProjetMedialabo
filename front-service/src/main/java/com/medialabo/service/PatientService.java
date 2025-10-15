package com.medialabo.service;

import com.medialabo.DTO.PatientDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class PatientService {

  private final RestTemplate rest = new RestTemplate();
  private final String baseUrl = "http://localhost:8082"; // back-service
  private final HttpHeaders headers;

  public PatientService() {
    // ── Basic Auth : organizer:changeit ──
    String basic = "Basic " + Base64.getEncoder().encodeToString("organizer:changeit".getBytes());
    headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, basic);
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
  }

  public List<PatientDTO> findAll() {
    HttpEntity<Void> entity = new HttpEntity<>(headers);
    ResponseEntity<PatientDTO[]> resp = rest.exchange(
        baseUrl + "/api/patients", HttpMethod.GET, entity, PatientDTO[].class);
    PatientDTO[] body = resp.getBody();
    return Arrays.asList(body != null ? body : new PatientDTO[0]);
  }
}                                                    