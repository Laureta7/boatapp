package com.rogerhr.boatapp.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String username;
  private String password;
  private String role;

}
