package com.rogerhr.boatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoatRequestDTO {
  private String name;
  private String description;
  private int year;
  private double length;
  private String ownerName;
  private double price;
  private String registrationNumber;
}
