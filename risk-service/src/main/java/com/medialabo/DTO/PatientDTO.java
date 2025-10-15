package com.medialabo.DTO;



public record PatientDTO(
  String id,
  String firstName,
  String lastName,
  String birthDate, // "yyyy-MM-dd"
  String gender
) {}