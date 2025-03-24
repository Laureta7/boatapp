package com.rogerhr.boatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerhr.boatapp.exception.UsernameAlreadyExistsException;
import com.rogerhr.boatapp.model.LoginResponse;
import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    // Check if the username already exists
    if (userRepository.existsByUsername(user.getUsername())) {
      throw new UsernameAlreadyExistsException("Username is already taken");
    }
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public ResponseEntity<LoginResponse> verifyUser(@Valid Users user, HttpServletResponse response) {
    try {
      Authentication authentication = authManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
      if (authentication.isAuthenticated()) {
        String token = jwtService.generateToken(user.getUsername());

        // Create a cookie for the JWT
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); // Prevents JavaScript access to the cookie
        cookie.setSecure(true); // Use HTTPS only
        cookie.setPath("/"); // Make cookie accessible on all routes
        cookie.setMaxAge(3600); // Set expiration time for the cookie (in seconds)

        response.addCookie(cookie); // Add the cookie to the response
        return ResponseEntity.ok(new LoginResponse("Login successful " + token));
      } else {
        return ResponseEntity.status(403).body(new LoginResponse("User is not authenticated"));

      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new LoginResponse("Error: " + e.getMessage())); // Handle errors
                                                                                              // appropriately
    }
  }

  public ResponseEntity<LoginResponse> logout(HttpServletResponse response) {
    Cookie cookie = new Cookie("token", null);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
    response.addCookie(cookie);

    return ResponseEntity.ok(new LoginResponse("Logout successful "));
  }

}
