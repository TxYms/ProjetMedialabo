package com.medialabo.repository;

import com.medialabo.model.MedicalNote;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MedicalNoteRepository extends MongoRepository<MedicalNote, String> {

  List<MedicalNote> findByPatientId(Long patientId);

  @Aggregation(pipeline = {
    "{ $match: { note: { $regex: ?0, $options: 'i' } } }",
    "{ $group: { _id: '$patientId' } }",
    "{ $project: { _id: 0, patientId: '$_id' } }"
  })
  List<NotePatientId> findPatientIdsByContentRegex(String regex);

  interface NotePatientId {
    Long getPatientId();
  }
}