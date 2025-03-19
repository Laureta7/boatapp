
package com.rogerhr.boatapp.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Boat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotNull(message = "Boat name cannot be null")
  @Size(min = 1, max = 255, message = "Boat name must be between 1 and 255 characters")
  private String name;

  @NotNull(message = "Boat description cannot be null")
  @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000 characters")
  private String description;

  @Min(value = 0, message = "Year must be a positive number")
  private int year;

  @Min(value = 0, message = "Length must be a positive number")
  private double length;

  // Champs sensibles
  @NotNull(message = "Owner name cannot be null")
  private String ownerName;

  @Min(value = 0, message = "Price must be a positive number")
  private double price;

  @NotNull(message = "Registration number cannot be null")
  @Size(min = 5, max = 20, message = "Registration number must be between 5 and 20 characters")
  private String registrationNumber;

  // Timestamps
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // Constructeur par défaut qui génère un UUID
  public Boat() {
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  // Getters et Setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
