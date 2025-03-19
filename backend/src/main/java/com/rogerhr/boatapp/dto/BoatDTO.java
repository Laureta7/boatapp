
package com.rogerhr.boatapp.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BoatDTO {

  private UUID id;

  @NotNull(message = "Boat name cannot be null")
  @Size(min = 1, max = 255, message = "Boat name must be between 1 and 255 characters")
  private String name;

  @NotNull(message = "Boat description cannot be null")
  @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
  private String description;

  @Min(value = 0, message = "Year must be a positive number")
  private int year;

  @Min(value = 1, message = "Length must be a positive number")
  private double length;

  @NotNull(message = "Owner name cannot be null")
  private String ownerName;

  @Min(value = 0, message = "Price must be a positive number")
  private double price;

  @NotNull(message = "Registration number cannot be null")
  @Size(min = 5, max = 20, message = "Registration number must be between 5 and 20 characters")
  private String registrationNumber;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public BoatDTO() {
    /*
     * Default constructor required for JSON deserialization by Spring Boot.
     * It's intentionally empty as Spring initializes properties via setters.
     * If specific logic was needed, it could be added here.
     */
  }

  private BoatDTO(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
    this.year = builder.year;
    this.length = builder.length;
    this.ownerName = builder.ownerName;
    this.price = builder.price;
    this.registrationNumber = builder.registrationNumber;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
  }

  // Getter pour chaque attribut
  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getYear() {
    return year;
  }

  public double getLength() {
    return length;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public double getPrice() {
    return price;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  // Builder interne
  public static class Builder {
    private UUID id;
    private String name;
    private String description;
    private int year;
    private double length;
    private String ownerName;
    private double price;
    private String registrationNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Méthodes de type setter pour chaque attribut
    public Builder setId(UUID id) {
      this.id = id;
      return this;
    }

    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setYear(int year) {
      this.year = year;
      return this;
    }

    public Builder setLength(double length) {
      this.length = length;
      return this;
    }

    public Builder setOwnerName(String ownerName) {
      this.ownerName = ownerName;
      return this;
    }

    public Builder setPrice(double price) {
      this.price = price;
      return this;
    }

    public Builder setRegistrationNumber(String registrationNumber) {
      this.registrationNumber = registrationNumber;
      return this;
    }

    public Builder setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder setUpdatedAt(LocalDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    // Méthode build qui retourne l'objet final
    public BoatDTO build() {
      return new BoatDTO(this);
    }
  }
}
