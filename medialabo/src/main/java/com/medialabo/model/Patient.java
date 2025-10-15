package com.medialabo.model;

import jakarta.validation.constraints.*;
import lombok.*;



import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @NotBlank
  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Past
  @Column(name = "birth_date")
  private LocalDate birthDate;

  @NotBlank
  @Column(name = "gender", length = 10)
  private String gender;

  @NotBlank
  @Column(name = "address", length = 255)
  private String address;

  @NotBlank
  @Column(name = "phone", length = 30)
  private String phone;


  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
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
