package com.medialabo.controller;

import com.medialabo.DTO.RiskReport;
import com.medialabo.service.RiskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/risk")
public class RiskController {

  private final RiskService service;
  public RiskController(RiskService service) { this.service = service; }

  @GetMapping("/{patientId}")
  public RiskReport assess(@PathVariable String patientId) {
    return service.assessRisk(patientId);
  }
}