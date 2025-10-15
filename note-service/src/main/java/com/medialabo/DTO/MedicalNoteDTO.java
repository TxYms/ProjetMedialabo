package com.medialabo.DTO;

import java.time.Instant;

public record MedicalNoteDTO(
    String id,
    String patientId,
    String content,
    Instant createdAt
) {}