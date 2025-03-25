package com.rogerhr.boatapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoatRequestDTO {
  @NotNull(message = "Boat name cannot be null")
  @Size(min = 1, max = 255, message = "Boat name must be between 1 and 255 characters")
  private String name;

  @NotNull(message = "Boat description cannot be null")
  @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
  private String description;

  @NotNull
  @Min(value = 1900, message = "Year must be at least 1900")
  @Max(value = 2100, message = "Year cannot exceed 2100")
  private int year;

  @NotNull
  @Min(value = 0, message = "Length must be a positive number")
  private double length;

  @NotNull(message = "Owner name cannot be null")
  @Size(min = 2, max = 255, message = "Owner name must be between 2 and 255 characters")
  private String ownerName;

  @NotNull
  @Min(value = 0, message = "Price must be a positive number")
  private double price;

  @Size(min = 5, max = 20, message = "Registration number must be between 5 and 20 characters")
  private String registrationNumber;
}
