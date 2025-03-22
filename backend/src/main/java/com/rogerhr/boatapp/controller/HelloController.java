package com.rogerhr.boatapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
  @GetMapping("/api/")
  public String hello(HttpServletRequest request) {
    return "Hello World! I am running on port " + request.getSession().getId();
  }
}
