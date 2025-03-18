package com.rogerhr.boatapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @Autowired
  private GreetingRepository greetingRepository;

  // Endpoint to get all greetings
  @GetMapping("/api/greetings")
  public List<Greeting> getAllGreetings() {
    System.out.println("Hello World");
    return greetingRepository.findAll();
  }

  // Endpoint to create a new greeting
  @PostMapping("/api/greetings")
  public Greeting createGreeting(@RequestBody Greeting greeting) {
    return greetingRepository.save(greeting);

  }
}
