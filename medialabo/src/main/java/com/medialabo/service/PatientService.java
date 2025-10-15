package com.medialabo.service;

import com.medialabo.model.Patient;
import com.medialabo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

  private final PatientRepository repo;

  public PatientService(PatientRepository repo) {
    this.repo = repo;
  }

  public List<Patient> findAll() { return repo.findAll(); }

  public Patient findById(Long id) { return repo.findById(id).orElse(null); }

  public Patient create(Patient p) { p.setId(null); return repo.save(p); }

  public Patient update(Long id, Patient p) {
    p.setId(id);
    return repo.save(p);
  }
}