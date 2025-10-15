package com.medialabo.repository;

import com.medialabo.model.MedicalNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MedicalNoteRepository extends MongoRepository<MedicalNote, String> {

    List<MedicalNote> findByPatientId(String patientId);

    // Recherche simple par regex insensible à la casse
    @Query("{ 'content': { $regex: ?0, $options: 'i' } }")
    List<MedicalNote> findByContentLikeIgnoreCase(String query);

    // Projection minimaliste pour récupérer uniquement les patientId
    interface NotePatientId {
        String getPatientId();
    }

    @Query(value = "{ 'content': { $regex: ?0, $options: 'i' } }",
           fields = "{ 'patientId': 1, '_id': 0 }")
    List<NotePatientId> findPatientIdsByContentRegex(String query);
}