package com.rogerhr.boatapp.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rogerhr.boatapp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
  // Add custom queries if needed, for now JpaRepository provides basic
  // CRUD operations
  Users findByUsername(String username);

  boolean existsByUsername(String username);
}
