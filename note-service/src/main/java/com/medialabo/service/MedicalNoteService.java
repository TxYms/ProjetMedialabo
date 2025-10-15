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
            n.getPatientId(),      // Long
            n.getNote(),           // mappe vers DTO.content
            n.getCreatedAt()
        );
    }

    private MedicalNote fromCreate(Long patientId, String content) {
        MedicalNote n = new MedicalNote();
        n.setPatientId(patientId);   // Long
        n.setNote(content);          // champ "note" en base
        n.setCreatedAt(Instant.now());
        return n;
    }

    public List<MedicalNoteDTO> findByPatientId(Long patientId) {
        return repo.findByPatientId(patientId)
                   .stream()
                   .map(this::toDto)
                   .toList();
    }

    public MedicalNoteDTO addNote(Long patientId, String content) {
        MedicalNote saved = repo.save(fromCreate(patientId, content));
        return toDto(saved);
    }

    // Si possible, préfère renvoyer Set<Long>.
    public Set<Long> findPatientIdsByContent(String q) {
        return repo.findPatientIdsByContentRegex(q).stream()
                .map(com.medialabo.repository.MedicalNoteRepository.NotePatientId::getPatientId) // Long
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}