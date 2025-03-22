package com.rogerhr.boatapp.model;

import java.util.UUID;
import jakarta.persistence.Entity; // Ensure the class is marked as an entity
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Add this annotation to mark it as a JPA entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String username;
  private String password;
  private String role;

  // Getters and Setters are auto-generated with Lombok
}
