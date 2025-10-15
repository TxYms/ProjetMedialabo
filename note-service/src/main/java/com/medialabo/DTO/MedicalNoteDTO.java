package com.medialabo.DTO;

import java.time.Instant;

public record MedicalNoteDTO(
    String id,
    Long patientId,
    String content,
    Instant createdAt
) {}