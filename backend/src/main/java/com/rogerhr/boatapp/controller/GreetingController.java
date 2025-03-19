package com.rogerhr.boatapp.controller;

import java.util.List;

import com.rogerhr.boatapp.dto.GreetingDTO;
import com.rogerhr.boatapp.model.Greeting;
import com.rogerhr.boatapp.repository.GreetingRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greeting")
public class GreetingController {
  private final GreetingRepository greetingRepository;

  // Constructor for dependency injection
  public GreetingController(GreetingRepository greetingRepository) {
    this.greetingRepository = greetingRepository;
  }

  // Endpoint to get all greetings
  @GetMapping
  public List<Greeting> getAllGreetings() {
    return greetingRepository.findAll();
  }

  @PostMapping
  public Greeting createGreeting(@RequestBody GreetingDTO greetingDTO) {
    // Convert the DTO to an entity
    Greeting greeting = new Greeting(greetingDTO.getContent(), greetingDTO.getUsername());

    // Save the entity and return the saved Greeting (with auto-generated id)
    return greetingRepository.save(greeting);
  }
}
