
package com.rogerhr.boatapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BoatCreateDTO {

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

  // Constructeur sans les champs createdAt, updatedAt, id
  public BoatCreateDTO() {
  }

  public BoatCreateDTO(String name, String description, int year, double length, String ownerName, double price,
      String registrationNumber) {
    this.name = name;
    this.description = description;
    this.year = year;
    this.length = length;
    this.ownerName = ownerName;
    this.price = price;
    this.registrationNumber = registrationNumber;
  }

  // Getters et Setters
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
}
