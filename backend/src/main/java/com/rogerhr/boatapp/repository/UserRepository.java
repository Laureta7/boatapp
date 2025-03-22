package com.rogerhr.boatapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rogerhr.boatapp.model.Users;

public interface UserRepository extends JpaRepository<Users, UUID> {
  // Add custom queries if needed, for now JpaRepository provides basic
  // CRUD operations
  Users findByUsername(String username);
}
