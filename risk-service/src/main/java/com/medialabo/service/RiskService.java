package com.medialabo.service;


import com.medialabo.DTO.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RiskService {

  private final RestClient api;

  private static final Set<String> TRIGGERS = Set.of(
    "hemoglobine a1c",
    "microalbumine",
    "taille",
    "poids",
    "fumeur",
    "fumeuse",
    "anormal",
    "cholesterol",
    "vertiges",
    "rechute",
    "reaction",
    "anticorps"
  );

  public RiskService(RestClient api) {
    this.api = api;
  }

  public RiskReport assessRisk(String patientId) {
    // 1) Patient
    PatientDTO p = api.get()
      .uri("/api/patients/{id}", patientId)
      .retrieve()
      .body(PatientDTO.class);

    if (p == null) throw new RuntimeException("Patient introuvable: " + patientId);

    // 2) Notes
    List<MedicalNoteDTO> notes = api.get()
      .uri("/api/patients/{id}/notes", patientId)
      .retrieve()
      .body(new ParameterizedTypeReference<List<MedicalNoteDTO>>() {});

    List<String> contents = (notes == null ? List.<MedicalNoteDTO>of() : notes)
      .stream().map(MedicalNoteDTO::content).filter(Objects::nonNull).toList();

    // 3) Compter les triggers (insensible à la casse + accents)
    int triggerCount = countTriggers(contents);

    // 4) Age / Sexe
    int age = computeAge(p.birthDate());
    String gender = safe(p.gender()); // "M" ou "F" attendu

    // 5) Appliquer les règles
    String level = computeLevel(age, gender, triggerCount);

    return new RiskReport(p.id(), p.firstName(), p.lastName(), age, gender, level);
  }

  private String computeLevel(int age, String gender, int n) {
    // None
    if (n == 0) return "None";

    // Borderline : 2..5 triggers ET age > 30
    if (age > 30 && n >= 2 && n <= 5) return "Borderline";

    // In Danger :
    //  - homme < 30 : n >= 3
    //  - femme < 30 : n >= 4
    //  - age > 30 : n == 6 || n == 7
    if (age < 30) {
      if ("M".equalsIgnoreCase(gender) && n >= 3) return "In Danger";
      if ("F".equalsIgnoreCase(gender) && n >= 4) return "In Danger";
    } else {
      if (n == 6 || n == 7) return "In Danger";
    }

    // Early onset :
    //  - homme < 30 : n >= 5
    //  - femme < 30 : n >= 7
    //  - age > 30 : n >= 8
    if (age < 30) {
      if ("M".equalsIgnoreCase(gender) && n >= 5) return "Early onset";
      if ("F".equalsIgnoreCase(gender) && n >= 7) return "Early onset";
    } else {
      if (n >= 8) return "Early onset";
    }

    // Si non classé par les règles ci-dessus, reviens à None (sécurité)
    return "None";
  }

  private int computeAge(String birthDate) {
    // birthDate attendu "yyyy-MM-dd"
    LocalDate dob = LocalDate.parse(birthDate);
    return Period.between(dob, LocalDate.now()).getYears();
  }

  private int countTriggers(List<String> notes) {
    String joined = normalize(notes.stream().collect(Collectors.joining("\n")));
    int count = 0;
    for (String t : TRIGGERS) {
      if (joined.contains(t)) count++;
    }
    return count;
  }

  private static String normalize(String s) {
    if (s == null) return "";
    String lower = s.toLowerCase(Locale.ROOT);
    // retirer les accents
    String decomposed = Normalizer.normalize(lower, Normalizer.Form.NFD);
    return decomposed.replaceAll("\\p{M}", ""); // retire diacritiques
  }

  private static String safe(String s) { return (s == null) ? "" : s; }
}
