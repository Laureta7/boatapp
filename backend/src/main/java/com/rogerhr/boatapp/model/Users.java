package com.rogerhr.boatapp.model;

import java.util.UUID;
import jakarta.persistence.Entity; // Ensure the class is marked as an entity
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

  @NotNull(message = "Username cannot be null")
  private String username;

  @NotNull(message = "Password cannot be null")
  @Size(min = 2, message = "Password must be at least 8 characters long")
  private String password;

  private String role;

  // Getters and Setters are auto-generated with Lombok
}
