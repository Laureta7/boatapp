
package com.rogerhr.boatapp.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import com.rogerhr.boatapp.dto.BoatDTO;
import com.rogerhr.boatapp.service.BoatService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boats")
public class BoatController {

  private final BoatService boatService;

  public BoatController(BoatService boatService) {
    this.boatService = boatService;
  }

  // Get all boats
  @GetMapping
  public ResponseEntity<List<BoatDTO>> getAllBoats() {
    return ResponseEntity.ok(boatService.getAllBoats());
  }

  // Get a boat by ID
  @GetMapping("/{id}")
  public ResponseEntity<BoatDTO> getBoatById(@PathVariable UUID id) {
    return ResponseEntity.ok(boatService.getBoatById(id));
  }

  // Create a boat
  @PostMapping
  public ResponseEntity<BoatDTO> createBoat(@Valid @RequestBody BoatDTO boatDTO) {
    return ResponseEntity.ok(boatService.createBoat(boatDTO));
  }

  // Update a boat
  @PutMapping("/{id}")
  public ResponseEntity<BoatDTO> updateBoat(@PathVariable UUID id, @Valid @RequestBody BoatDTO boatDTO) {
    return ResponseEntity.ok(boatService.updateBoat(id, boatDTO));
  }

  // Delete a boat
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBoat(@PathVariable UUID id) {
    boatService.deleteBoat(id);
    return ResponseEntity.noContent().build();
  }
}
