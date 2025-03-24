package com.rogerhr.boatapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rogerhr.boatapp.model.LoginResponse;
import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

  private UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @Operation(summary = "Register user", description = "No authentication required")
  @PostMapping("/register")
  public Users register(@Validated @RequestBody Users user) {

    return service.register(user);
  }

  @Operation(summary = "Login user", description = "No authentication required")
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid Users user, HttpServletResponse response) {

    return service.verifyUser(user, response);
  }

  @GetMapping("/verify-token")
  public ResponseEntity<LoginResponse> verifyToken() {

    return ResponseEntity.ok(new LoginResponse("Valid token"));

  }

}
