package com.rogerhr.boatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final JWTService jwtService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AuthenticationManager authManager;

  @Autowired
  public UserService(UserRepository userRepository,
      JWTService jwtService,
      AuthenticationManager authManager) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
    this.authManager = authManager;
  }

  public Users register(Users user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public String verifyUser(@Valid Users user) {
    Authentication authentication = authManager
        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    if (authentication.isAuthenticated())
      return jwtService.generateToken(user.getUsername());
    return "User is not authenticated";
  }
}
