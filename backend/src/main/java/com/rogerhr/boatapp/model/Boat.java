package com.rogerhr.boatapp.model;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String name;
  private String description;
  private int year;
  private double length;
  private String ownerName;
  private double price;
  private String registrationNumber;

  // Timestamps
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
