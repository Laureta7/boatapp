
package com.rogerhr.boatapp.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.rogerhr.boatapp.dto.BoatCreateDTO;
import com.rogerhr.boatapp.dto.BoatDTO;
import com.rogerhr.boatapp.mapper.BoatMapper;
import com.rogerhr.boatapp.model.Boat;
import com.rogerhr.boatapp.repository.BoatRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoatService {

  private final BoatRepository boatRepository;
  private final BoatMapper boatMapper;

  public BoatService(BoatRepository boatRepository, BoatMapper boatMapper) {
    this.boatRepository = boatRepository;
    this.boatMapper = boatMapper;
  }

  // Get all boats
  public List<BoatDTO> getAllBoats() {
    List<Boat> boats = boatRepository.findAll();
    return boats.stream()
        .map(boatMapper::toDTO)
        .collect(Collectors.toList());
  }

  // Get a boat by ID
  public BoatDTO getBoatById(UUID id) {
    Boat boat = boatRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Boat not found"));
    return boatMapper.toDTO(boat);
  }

  // Create a boat
  @Transactional
  public BoatDTO createBoat(BoatCreateDTO boatDTO) {
    Boat boat = boatMapper.toEntity(boatDTO);
    Boat savedBoat = boatRepository.save(boat);
    return boatMapper.toDTO(savedBoat);
  }

  // Update a boat
  @Transactional
  public BoatDTO updateBoat(UUID id, BoatDTO boatDTO) {
    Boat existingBoat = boatRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Boat not found"));
    existingBoat.setName(boatDTO.getName());
    existingBoat.setDescription(boatDTO.getDescription());
    existingBoat.setYear(boatDTO.getYear());
    existingBoat.setLength(boatDTO.getLength());
    existingBoat.setOwnerName(boatDTO.getOwnerName());
    existingBoat.setPrice(boatDTO.getPrice());
    existingBoat.setRegistrationNumber(boatDTO.getRegistrationNumber());

    Boat updatedBoat = boatRepository.save(existingBoat);
    return boatMapper.toDTO(updatedBoat);
  }

  // Delete a boat
  @Transactional
  public void deleteBoat(UUID id) {
    if (!boatRepository.existsById(id)) {
      throw new RuntimeException("Boat not found");
    }
    boatRepository.deleteById(id);
  }
}
