
package com.rogerhr.boatapp.controller;

import java.util.List;
import java.util.UUID;

import com.rogerhr.boatapp.dto.BoatRequestDTO;
import com.rogerhr.boatapp.dto.BoatResponseDTO;
import com.rogerhr.boatapp.service.BoatService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
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

  // Get all boats (Retourne une liste de BoatResponseDTO)
  @GetMapping
  @Operation(summary = "Get all boats", description = "Authentication required")
  public ResponseEntity<List<BoatResponseDTO>> getAllBoats() {
    List<BoatResponseDTO> boats = boatService.getAllBoats();
    return ResponseEntity.ok(boats);
  }

  // Get a boat by ID (Retourne un BoatResponseDTO)
  @Operation(summary = "Get a boat by ID", description = "Authentication required")
  @GetMapping("/{id}")
  public ResponseEntity<BoatResponseDTO> getBoatById(@PathVariable UUID id) {
    BoatResponseDTO boat = boatService.getBoatById(id);
    return ResponseEntity.ok(boat);
  }

  @Operation(summary = "Get CSRF token", description = "Authentication required")
  @GetMapping("/csrf-token")
  public CsrfToken getCsrfToken(CsrfToken token) {
    return token;
  }

  // Create a boat (Prend un BoatRequestDTO et retourne un BoatResponseDTO)
  @Operation(summary = "Create a boat", description = "Authentication required")
  @PostMapping
  public ResponseEntity<BoatResponseDTO> createBoat(@RequestBody BoatRequestDTO boatRequestDTO) {
    BoatResponseDTO createdBoat = boatService.createBoat(boatRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBoat);
  }

  // Update a boat
  @Operation(summary = "Update a boat", description = "Authentication required")
  @PutMapping("/{id}")
  public ResponseEntity<BoatResponseDTO> updateBoat(
      @PathVariable UUID id,
      @RequestBody BoatRequestDTO boatRequestDTO) {
    BoatResponseDTO updatedBoat = boatService.updateBoat(id, boatRequestDTO);
    return ResponseEntity.ok(updatedBoat);
  }

  // Delete a boat
  @Operation(summary = "Delete a boat", description = "Authentication required")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBoat(@PathVariable UUID id) {
    boatService.deleteBoat(id);
    return ResponseEntity.noContent().build();
  }
}
