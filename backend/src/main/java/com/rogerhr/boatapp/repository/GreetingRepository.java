package com.rogerhr.boatapp.repository;

import com.rogerhr.boatapp.model.Greeting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
  // You can add custom queries if needed, for now JpaRepository provides basic
  // CRUD operations
}
