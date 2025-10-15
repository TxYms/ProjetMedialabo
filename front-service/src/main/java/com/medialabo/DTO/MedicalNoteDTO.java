package com.medialabo.DTO;

import java.time.LocalDateTime;
public class MedicalNoteDTO {
  private String id, patientId, authorUsername, content;
  private LocalDateTime createdAt, updatedAt;
  public String getId(){return id;} public void setId(String v){id=v;}
  public String getPatientId(){return patientId;} public void setPatientId(String v){patientId=v;}
  public String getAuthorUsername(){return authorUsername;} public void setAuthorUsername(String v){authorUsername=v;}
  public String getContent(){return content;} public void setContent(String v){content=v;}
  public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
  public LocalDateTime getUpdatedAt(){return updatedAt;} public void setUpdatedAt(LocalDateTime v){updatedAt=v;}
}
