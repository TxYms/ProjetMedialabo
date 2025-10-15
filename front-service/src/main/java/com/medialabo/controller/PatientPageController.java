package com.medialabo.controller;

import com.medialabo.DTO.CreateMedicalNoteRequest;
import com.medialabo.DTO.MedicalNoteDTO;
import com.medialabo.DTO.PatientDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientPageController {

  private final RestClient api;

  // ✅ Utilise le Builder auto-configuré par Spring Boot
  public PatientPageController(RestClient api) {
    this.api = api;
  }
        

  @GetMapping
  public String list(Model model){
    List<PatientDTO> patients = api.get()
        .uri("/api/patients")
        .retrieve()
        .body(new ParameterizedTypeReference<List<PatientDTO>>() {});
    model.addAttribute("patients", patients == null ? List.of() : patients);
    return "patients/list";
  }

  @GetMapping("/{id}/notes")
  public String notes(@PathVariable String id, Model model){
    List<MedicalNoteDTO> notes = api.get()
        .uri("/api/patients/{id}/notes", id)
        .retrieve()
        .body(new ParameterizedTypeReference<List<MedicalNoteDTO>>() {});
    model.addAttribute("notes", notes == null ? List.of() : notes);
    model.addAttribute("patientId", id);
    model.addAttribute("createRequest", new CreateMedicalNoteRequest());
    return "patients/notes";
  }

  @PostMapping("/{id}/notes")
  public String add(@PathVariable String id,
                    @ModelAttribute("createRequest") CreateMedicalNoteRequest req){
    api.post()
        .uri("/api/patients/{id}/notes", id)
        .body(req)
        .retrieve()
        .toBodilessEntity();
    return "redirect:/patients/" + id + "/notes";
  }
  
  @GetMapping("/{id}/risk")
  public String risk(@PathVariable String id, Model model){
    var report = api.get()
      .uri("/api/risk/{id}", id) // via Gateway 8081
      .retrieve()
      .body(com.medialabo.DTO.RiskReportDTO.class);
    model.addAttribute("report", report);
    return "patients/risk";
  }
}