package com.medialabo.DTO;

public record RiskReport(
		  String patientId,
		  String firstName,
		  String lastName,
		  int age,
		  String gender,     // "M" / "F"
		  String riskLevel   // "None" | "Borderline" | "In Danger" | "Early onset"
		) {}