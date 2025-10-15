package com.medialabo.DTO;

import java.time.LocalDate;

public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public PatientDTO() {}

    public PatientDTO(Long long1, String firstName, String lastName, LocalDate birthDate) {
        this.id = long1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // --- getters/setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}