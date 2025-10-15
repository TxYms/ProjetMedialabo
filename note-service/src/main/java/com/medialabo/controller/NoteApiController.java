package com.medialabo.controller;

import com.medialabo.DTO.MedicalNoteDTO;

import com.medialabo.service.MedicalNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class NoteApiController {

  private final MedicalNoteService service;
  public NoteApiController(MedicalNoteService service){ this.service = service; }

  // Liste des notes d'un patient
  @GetMapping("/patients/{id}/notes")
  public List<MedicalNoteDTO> notes(@PathVariable Long id){
    return service.findByPatientId(id);
  }

  // Ajout d'une note
  public static class CreateNote { public String content; }
  @PostMapping("/patients/{id}/notes")
  public ResponseEntity<Void> add(@PathVariable Long id, @RequestBody CreateNote req){
    service.addNote(id, req.content);
    return ResponseEntity.ok().build();
  }

  // Recherche patients par contenu
  @GetMapping("/notes/search")
  public Set<Long> search(@RequestParam("q") String q){
    return service.findPatientIdsByContent(q);
  }
}