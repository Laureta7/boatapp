package com.rogerhr.boatapp.mapper;

import com.rogerhr.boatapp.dto.BoatRequestDTO;
import com.rogerhr.boatapp.dto.BoatResponseDTO;
import com.rogerhr.boatapp.model.Boat;

import org.springframework.stereotype.Component;

@Component
public class BoatMapper {

  // Convertir un BoatRequestDTO en Boat (pour la création)
  public Boat toEntity(BoatRequestDTO boatRequestDTO) {
    if (boatRequestDTO == null) {
      return null;
    }

    Boat boat = new Boat();
    boat.setName(boatRequestDTO.getName());
    boat.setDescription(boatRequestDTO.getDescription());
    boat.setYear(boatRequestDTO.getYear());
    boat.setLength(boatRequestDTO.getLength());
    boat.setOwnerName(boatRequestDTO.getOwnerName());
    boat.setPrice(boatRequestDTO.getPrice());
    boat.setRegistrationNumber(boatRequestDTO.getRegistrationNumber());

    return boat;
  }

  // Convertir un Boat en BoatResponseDTO
  public BoatResponseDTO toResponseDTO(Boat boat) {
    if (boat == null) {
      return null;
    }

    BoatResponseDTO responseDTO = new BoatResponseDTO();
    responseDTO.setId(boat.getId());
    responseDTO.setName(boat.getName());
    responseDTO.setDescription(boat.getDescription());
    responseDTO.setYear(boat.getYear());
    responseDTO.setLength(boat.getLength());
    responseDTO.setOwnerName(boat.getOwnerName());
    responseDTO.setPrice(boat.getPrice());
    responseDTO.setRegistrationNumber(boat.getRegistrationNumber());
    responseDTO.setCreatedAt(boat.getCreatedAt());
    responseDTO.setUpdatedAt(boat.getUpdatedAt());

    return responseDTO;
  }
}
