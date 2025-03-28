
package com.rogerhr.boatapp.service;

import java.util.List;
import java.util.UUID;

import com.rogerhr.boatapp.dto.BoatRequestDTO;
import com.rogerhr.boatapp.dto.BoatResponseDTO;
import com.rogerhr.boatapp.exception.BoatNotFoundException;
import com.rogerhr.boatapp.mapper.BoatMapper;
import com.rogerhr.boatapp.model.Boat;
import com.rogerhr.boatapp.repository.BoatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoatService {

  private final BoatRepository boatRepository;

  private final BoatMapper boatMapper;

  @Autowired
  public BoatService(BoatRepository boatRepository, BoatMapper boatMapper) {
    this.boatRepository = boatRepository;
    this.boatMapper = boatMapper;
  }

  // Get all boats
  public List<BoatResponseDTO> getAllBoats() {
    return boatRepository.findAll()
        .stream()
        .map(boatMapper::toResponseDTO)
        .toList();
  }

  // Get a boat by ID
  public BoatResponseDTO getBoatById(UUID id) {
    Boat boat = boatRepository.findById(id)
        .orElseThrow(() -> new BoatNotFoundException(id));
    return boatMapper.toResponseDTO(boat);
  }

  // Create a boat
  @Transactional
  public BoatResponseDTO createBoat(BoatRequestDTO boatRequestDTO) {
    Boat boat = boatMapper.toEntity(boatRequestDTO);
    Boat savedBoat = boatRepository.save(boat);
    return boatMapper.toResponseDTO(savedBoat);
  }

  @Transactional
  public BoatResponseDTO updateBoat(UUID id, BoatRequestDTO boatRequestDTO) {
    Boat existingBoat = boatRepository.findById(id)
        .orElseThrow(() -> new BoatNotFoundException(id));
    // Update fields with values from boatRequestDTO if they are not null or meet
    // certain criteria

    existingBoat.setName(boatRequestDTO.getName());
    existingBoat.setDescription(boatRequestDTO.getDescription());
    existingBoat.setYear(boatRequestDTO.getYear());
    existingBoat.setLength(boatRequestDTO.getLength());
    existingBoat.setOwnerName(boatRequestDTO.getOwnerName());
    existingBoat.setPrice(boatRequestDTO.getPrice());
    existingBoat.setRegistrationNumber(boatRequestDTO.getRegistrationNumber());

    Boat updatedBoat = boatRepository.save(existingBoat);
    return boatMapper.toResponseDTO(updatedBoat);
  }

  // Delete a boat
  @Transactional
  public void deleteBoat(UUID id) {
    if (!boatRepository.existsById(id)) {
      throw new BoatNotFoundException(id);
    }
    boatRepository.deleteById(id);
  }
}
