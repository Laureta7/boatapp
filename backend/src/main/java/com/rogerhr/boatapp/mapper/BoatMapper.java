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
    // Utilisation du Builder pour créer un BoatDTO
    return new BoatDTO.Builder()
        .setId(boat.getId())
        .setName(boat.getName())
        .setDescription(boat.getDescription())
        .setYear(boat.getYear())
        .setLength(boat.getLength())
        .setOwnerName(boat.getOwnerName())
        .setPrice(boat.getPrice())
        .setRegistrationNumber(boat.getRegistrationNumber())
        .setCreatedAt(boat.getCreatedAt())
        .setUpdatedAt(boat.getUpdatedAt())
        .build();
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
