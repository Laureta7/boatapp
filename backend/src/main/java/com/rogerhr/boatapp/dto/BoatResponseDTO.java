package com.rogerhr.boatapp.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoatResponseDTO {
  private UUID id;
  private String name;
  private String description;
  private int year;
  private double length;
  private String ownerName;
  private double price;
  private String registrationNumber;
}
