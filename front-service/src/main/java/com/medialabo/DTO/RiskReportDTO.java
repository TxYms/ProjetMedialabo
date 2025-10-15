package com.medialabo.DTO;

public record RiskReportDTO(
		  String patientId,
		  String firstName,
		  String lastName,
		  int age,
		  String gender,
		  String riskLevel
		) {}