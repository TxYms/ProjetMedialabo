package com.medialabo.service;

import com.medialabo.DTO.MedicalNoteDTO;
import com.medialabo.model.MedicalNote;
import com.medialabo.repository.MedicalNoteRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicalNoteService {

    private final MedicalNoteRepository repo;

    public MedicalNoteService(MedicalNoteRepository repo) {
        this.repo = repo;
    }

    private MedicalNoteDTO toDto(MedicalNote n) {
        return new MedicalNoteDTO(
            n.getId(),
            n.getPatientId(),
            n.getContent(),
            n.getCreatedAt()
        );
    }

    private MedicalNote fromCreate(String patientId, String content) {
        MedicalNote n = new MedicalNote();
        n.setPatientId(patientId);
        n.setContent(content);
        n.setCreatedAt(Instant.now());           // ← on fixe nous-mêmes la date
        return n;
    }

    public List<MedicalNoteDTO> findByPatientId(String patientId) {
        return repo.findByPatientId(patientId).stream().map(this::toDto).toList();
    }

    public MedicalNoteDTO addNote(String patientId, String content) {
        MedicalNote saved = repo.save(fromCreate(patientId, content));
        return toDto(saved);
    }

    public Set<String> findPatientIdsByContent(String q) {
        return repo.findPatientIdsByContentRegex(q).stream()
                .map(com.medialabo.repository.MedicalNoteRepository.NotePatientId::getPatientId)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}