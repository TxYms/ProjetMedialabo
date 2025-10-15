package com.medialabo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "medicalnote")
public class MedicalNote {

  @Id
  private String id;

  @Indexed
  private String patientId;

  private String content;

  @CreatedDate            // optionnel (si auditing activé)
  private Instant createdAt;

  // GETTERS
  public String getId() { return id; }
  public String getPatientId() { return patientId; }
  public String getContent() { return content; }
  public Instant getCreatedAt() { return createdAt; }

  // SETTERS
  public void setPatientId(String patientId) { this.patientId = patientId; }
  public void setContent(String content) { this.content = content; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; } // utile si tu le fixes côté service
}