package com.rogerhr.boatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
  public Users register(@Validated @RequestBody Users user) {

    return service.register(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody @Valid Users user) {
    // Log the request path and method

    // If this point is reached, it means that validation has succeeded
    return service.verifyUser(user);
  }

}
