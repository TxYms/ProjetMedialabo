package com.medialabo.DTO;

public record MedicalNoteDTO(
		  String id,
		  String patientId,
		  String content,
		  String createdAt // ISO-8601 en String -> pas besoin d'Instant ici
		) {}