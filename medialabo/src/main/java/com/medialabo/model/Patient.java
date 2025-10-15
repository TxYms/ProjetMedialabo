package com.medialabo.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "patients")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {

  @Id
  private String id;                  // <-- String pour Mongo (ObjectId)

  @NotBlank @Size(max = 80)
  private String firstName;

  @NotBlank @Size(max = 80)
  private String lastName;

  @NotNull
  private LocalDate birthDate;

  @NotBlank
  @Pattern(regexp = "^(M|F)$")        // <-- correspond à tes données importées
  private String gender;

  @Size(max = 255)
  private String address;

  @Pattern(regexp = "^$|^[+0-9() .-]{6,20}$")
  private String phone;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public LocalDate getBirthDate() { return birthDate; }
  public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
  public String getGender() { return gender; }
  public void setGender(String gender) { this.gender = gender; }
  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }
  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }

}
