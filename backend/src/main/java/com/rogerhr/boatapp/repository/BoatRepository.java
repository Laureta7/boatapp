package com.rogerhr.boatapp.repository;

import java.util.UUID;

import com.rogerhr.boatapp.model.Boat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, UUID> {
  // You can add custom queries if needed, for now JpaRepository provides basic
  // CRUD operations
}
