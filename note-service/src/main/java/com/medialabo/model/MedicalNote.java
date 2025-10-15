package com.medialabo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "medicalnote")
public class MedicalNote {

  @Id
  private String id;

  @Field("patientId")
  private Long patientId;           // <-- Long (NumberLong en base)

  @Field("patient")
  private String patient;           // <-- présent dans tes docs

  @Field("note")
  private String note;              // <-- correspond au champ "note" en base

  @CreatedDate
  private Instant createdAt;

  // GETTERS/SETTERS
  public String getId() { return id; }
  public Long getPatientId() { return patientId; }
  public String getPatient() { return patient; }
  public String getNote() { return note; }
  public Instant getCreatedAt() { return createdAt; }

  public void setPatientId(Long patientId) { this.patientId = patientId; }
  public void setPatient(String patient) { this.patient = patient; }
  public void setNote(String note) { this.note = note; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}