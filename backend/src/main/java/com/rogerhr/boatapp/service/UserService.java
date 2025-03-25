package com.rogerhr.boatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rogerhr.boatapp.dto.UserCreateDTO;
import com.rogerhr.boatapp.dto.UserDTO;
import com.rogerhr.boatapp.exception.UsernameAlreadyExistsException;
import com.rogerhr.boatapp.mapper.UserMapper;
import com.rogerhr.boatapp.model.LoginResponse;
import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

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

  public UserDTO register(UserCreateDTO userCreateDTO) {
    // Check if username already exists
    if (userRepository.existsByUsername(userCreateDTO.getUsername())) {
      throw new UsernameAlreadyExistsException("Username is already taken");
    }

    // Mapper
    Users newUser = UserMapper.toUser(userCreateDTO);
    newUser.setPassword(bCryptPasswordEncoder.encode(userCreateDTO.getPassword())); // Hash du password

    Users savedUser = userRepository.save(newUser);

    // Return without password
    return UserMapper.toUserDTO(savedUser);
  }

  public ResponseEntity<LoginResponse> verifyUser(UserCreateDTO userCreateDTO, HttpServletResponse response) {
    try {
      Authentication authentication = authManager.authenticate(
          new UsernamePasswordAuthenticationToken(userCreateDTO.getUsername(), userCreateDTO.getPassword()));

      if (authentication.isAuthenticated()) {
        String token = jwtService.generateToken(userCreateDTO.getUsername());

        // Create a cookie
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); // No javascript access
        cookie.setSecure(true); // HTTPS Only
        cookie.setPath("/"); // Allow all paths
        cookie.setMaxAge(3600); // (1h) = token

        response.addCookie(cookie); // Ajoute le cookie à la réponse HTTP
        return ResponseEntity.ok(new LoginResponse("Login successful" + token));
      } else {
        return ResponseEntity.status(403).body(new LoginResponse("User is not authenticated"));
      }
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new LoginResponse("Error: " + e.getMessage()));
    }
  }

  public ResponseEntity<LoginResponse> logout(HttpServletResponse response) {
    Cookie cookie = new Cookie("token", null);
    cookie.setHttpOnly(true);
    cookie.setSecure(true);
    cookie.setPath("/");
    cookie.setMaxAge(0); // Set the max age to 0 to delete the cookie
    response.addCookie(cookie);

    return ResponseEntity.ok(new LoginResponse("Logout successful"));
  }

}
