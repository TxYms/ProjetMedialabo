package com.medialabo.controller;


import com.medialabo.model.Patient;
import com.medialabo.service.PatientService;
import jakarta.validation.Valid;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	

  private final PatientService service;
  

  
  public PatientController(PatientService service) {
      this.service = service;
  }

  @GetMapping public List<Patient> all() { return service.findAll(); }

  @GetMapping("/{id}")
  public ResponseEntity<Patient> one(@PathVariable String id){
    var p = service.findById(id);
    return (p==null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
  }

  @PostMapping
  public ResponseEntity<Patient> create(@Valid @RequestBody Patient p){
    var saved = service.create(p);
    return ResponseEntity.created(URI.create("/api/patients/"+saved.getId())).body(saved);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Patient> update(@PathVariable String id, @Valid @RequestBody Patient p){
    if (service.findById(id)==null) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(service.update(id, p));
  }
}