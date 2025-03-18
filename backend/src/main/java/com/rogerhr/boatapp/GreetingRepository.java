package com.rogerhr.boatapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
  // You can add custom queries if needed, for now JpaRepository provides basic
  // CRUD operations
}
