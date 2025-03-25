
package com.rogerhr.boatapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.rogerhr.boatapp.dto.UserCreateDTO;
import com.rogerhr.boatapp.dto.UserDTO;
import com.rogerhr.boatapp.exception.UsernameAlreadyExistsException;
import com.rogerhr.boatapp.model.LoginResponse;
import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private JWTService jwtService;

  @Mock
  private AuthenticationManager authManager;

  @Mock
  private HttpServletResponse response;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    userService = new UserService(userRepository, jwtService, authManager);
  }

  @Test
  void testRegister_Success() {
    UserCreateDTO userCreateDTO = new UserCreateDTO("testuser", "password123");
    Users savedUser = new Users();
    savedUser.setUsername("testuser");

    when(userRepository.existsByUsername("testuser")).thenReturn(false);
    when(userRepository.save(any(Users.class))).thenReturn(savedUser);

    UserDTO result = userService.register(userCreateDTO);

    assertNotNull(result);
    assertEquals("testuser", result.getUsername());
  }

  @Test
  void testRegister_UsernameAlreadyExists() {
    UserCreateDTO userCreateDTO = new UserCreateDTO("existingUser", "password123");

    when(userRepository.existsByUsername("existingUser")).thenReturn(true);

    assertThrows(UsernameAlreadyExistsException.class, () -> userService.register(userCreateDTO));
  }

  @Test
  void testVerifyUser_Success() {
    UserCreateDTO userCreateDTO = new UserCreateDTO("testuser", "password123");
    Authentication authentication = mock(Authentication.class);
    String token = "mockedToken123";

    when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
    when(authentication.isAuthenticated()).thenReturn(true);
    when(jwtService.generateToken("testuser")).thenReturn(token);

    ResponseEntity<LoginResponse> responseEntity = userService.verifyUser(userCreateDTO, response);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().message().contains("Login successful"));
  }

  @Test
  void testVerifyUser_Failure() {
    UserCreateDTO userCreateDTO = new UserCreateDTO("testuser", "wrongpassword");

    when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
        .thenThrow(new RuntimeException("Invalid credentials"));

    ResponseEntity<LoginResponse> responseEntity = userService.verifyUser(userCreateDTO, response);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().message().contains("Invalid credentials"));
  }

  @Test
  void testLogout() {
    ResponseEntity<LoginResponse> responseEntity = userService.logout(response);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().message().contains("Logout successful"));
    verify(response).addCookie(any(Cookie.class));
  }
}
