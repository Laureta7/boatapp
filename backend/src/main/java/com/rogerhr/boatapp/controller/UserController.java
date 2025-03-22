package com.rogerhr.boatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService service;

  @PostMapping("/register")
  public Users register(@Valid @RequestBody Users user) {

    return service.register(user);
  }

}
