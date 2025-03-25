
package com.rogerhr.boatapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogerhr.boatapp.dto.UserCreateDTO;
import com.rogerhr.boatapp.dto.UserDTO;
import com.rogerhr.boatapp.model.LoginResponse;
import com.rogerhr.boatapp.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private ObjectMapper objectMapper = new ObjectMapper();

  private UUID userId;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    userId = UUID.randomUUID();
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void testRegister() throws Exception {
    UserCreateDTO userCreateDTO = new UserCreateDTO();

    userCreateDTO.setUsername("testuser");
    userCreateDTO.setPassword("password123");

    UserDTO userDTO = new UserDTO();
    userDTO.setId(userId);
    userDTO.setUsername("testuser");

    when(userService.register(any(UserCreateDTO.class))).thenReturn(userDTO);

    mockMvc.perform(post("/api/users/register")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(userCreateDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.username").value("testuser"));
  }

  @Test
  void testLogin() throws Exception {
    UserCreateDTO userCreateDTO = new UserCreateDTO();
    userCreateDTO.setUsername("testuser");
    userCreateDTO.setPassword("password123");

    LoginResponse loginResponse = new LoginResponse("Login successful");

    when(userService.verifyUser(any(UserCreateDTO.class), any(HttpServletResponse.class)))
        .thenReturn(ResponseEntity.ok(loginResponse));

    mockMvc.perform(post("/api/users/login")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(userCreateDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Login successful"));
  }

  @Test
  void testVerifyToken() throws Exception {
    mockMvc.perform(get("/api/users/verify-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Valid token"));
  }

  @Test
  void testLogout() throws Exception {
    LoginResponse logoutResponse = new LoginResponse("Logged out");

    when(userService.logout(any(HttpServletResponse.class)))
        .thenReturn(ResponseEntity.ok(logoutResponse));

    mockMvc.perform(post("/api/users/logout"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Logged out"));
  }
}
