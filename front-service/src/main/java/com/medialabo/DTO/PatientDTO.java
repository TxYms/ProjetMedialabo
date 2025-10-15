package com.medialabo.DTO;

import java.time.LocalDate;
public class PatientDTO {
  private String id, lastName, firstName, phone;
  private LocalDate birthDate;
  public String getId(){return id;} public void setId(String v){id=v;}
  public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
  public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
  public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
  public LocalDate getBirthDate(){return birthDate;} public void setBirthDate(LocalDate v){birthDate=v;}
}