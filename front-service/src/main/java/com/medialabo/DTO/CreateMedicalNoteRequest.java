package com.medialabo.DTO;

import jakarta.validation.constraints.NotBlank;

public class CreateMedicalNoteRequest {
  @NotBlank private String content;
  public String getContent(){return content;}
  public void setContent(String c){this.content=c;}
}