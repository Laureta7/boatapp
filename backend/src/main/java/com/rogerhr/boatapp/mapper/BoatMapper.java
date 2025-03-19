package com.rogerhr.boatapp.mapper;

import com.rogerhr.boatapp.dto.BoatCreateDTO;
import com.rogerhr.boatapp.dto.BoatDTO;
import com.rogerhr.boatapp.model.Boat;

import org.springframework.stereotype.Component;

@Component
public class BoatMapper {

  // Convertir un Boat en BoatDTO (pour la réponse)
  public BoatDTO toDTO(Boat boat) {
    if (boat == null) {
      return null;
    }
    return new BoatDTO(
        boat.getId(),
        boat.getName(),
        boat.getDescription(),
        boat.getYear(),
        boat.getLength(),
        boat.getOwnerName(),
        boat.getPrice(),
        boat.getRegistrationNumber(),
        boat.getCreatedAt(),
        boat.getUpdatedAt());
  }

  // Convertir un BoatCreateDTO en Boat (pour la création)
  public Boat toEntity(BoatCreateDTO boatCreateDTO) {
    if (boatCreateDTO == null) {
      return null;
    }
    Boat boat = new Boat();
    boat.setName(boatCreateDTO.getName());
    boat.setDescription(boatCreateDTO.getDescription());
    boat.setYear(boatCreateDTO.getYear());
    boat.setLength(boatCreateDTO.getLength());
    boat.setOwnerName(boatCreateDTO.getOwnerName());
    boat.setPrice(boatCreateDTO.getPrice());
    boat.setRegistrationNumber(boatCreateDTO.getRegistrationNumber());
    return boat;
  }

  // Convertir un BoatDTO en Boat (si nécessaire pour d'autres cas)
  public Boat toEntity(BoatDTO boatDTO) {
    if (boatDTO == null) {
      return null;
    }
    Boat boat = new Boat();
    boat.setId(boatDTO.getId()); // Tu peux utiliser ça si tu veux récupérer un bateau existant
    boat.setName(boatDTO.getName());
    boat.setDescription(boatDTO.getDescription());
    boat.setYear(boatDTO.getYear());
    boat.setLength(boatDTO.getLength());
    boat.setOwnerName(boatDTO.getOwnerName());
    boat.setPrice(boatDTO.getPrice());
    boat.setRegistrationNumber(boatDTO.getRegistrationNumber());
    return boat;
  }
}
