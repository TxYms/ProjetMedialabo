package com.medialabo.api;


import com.medialabo.DTO.PatientDTO;
import com.medialabo.model.Patient;

public final class PatientMapper {
    private PatientMapper() {}

    public static PatientDTO toDTO(Patient p) {
        if (p == null) return null;
        return new PatientDTO(
            p.getId(),
            p.getFirstName(),
            p.getLastName(),
            p.getBirthDate()
        );
    }
}