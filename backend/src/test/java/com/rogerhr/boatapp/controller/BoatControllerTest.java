
package com.rogerhr.boatapp.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogerhr.boatapp.dto.BoatRequestDTO;
import com.rogerhr.boatapp.dto.BoatResponseDTO;
import com.rogerhr.boatapp.service.BoatService;
import com.rogerhr.boatapp.service.JWTService;

import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class BoatControllerTest {
  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @MockitoBean
  private BoatService boatService;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private JWTService jwtService;

  private String token;

  private UUID boatId;
  private BoatResponseDTO boatResponseDTO;
  private BoatRequestDTO boatRequestDTO;

  @BeforeEach
  void setUp() {

    mockMvc = MockMvcBuilders
        .webAppContextSetup(context)
        .apply(springSecurity())
        .build();
    boatId = UUID.randomUUID();

    boatRequestDTO = BoatRequestDTO.builder()
        .name("Test Boat")
        .description("A great boat")
        .year(2024)
        .length(10.5)
        .ownerName("John Doe")
        .price(25000)
        .registrationNumber("ABC12345")
        .build();

    boatResponseDTO = BoatResponseDTO.builder()
        .id(boatId)
        .name(boatRequestDTO.getName())
        .description(boatRequestDTO.getDescription())
        .year(boatRequestDTO.getYear())
        .length(boatRequestDTO.getLength())
        .ownerName(boatRequestDTO.getOwnerName())
        .price(boatRequestDTO.getPrice())
        .registrationNumber(boatRequestDTO.getRegistrationNumber())
        .build();

    // Generate a token for the test
    token = jwtService.generateToken("Terence");
    System.out.println("Token: " + token);
  }

  @Test
  void getAllBoats_ShouldReturnListOfBoats() throws Exception {
    List<BoatResponseDTO> boats = Collections.singletonList(boatResponseDTO);
    when(boatService.getAllBoats()).thenReturn(boats);
    System.out.println("Boats: " + boats.size());
    System.out.println("Boat: " + boatResponseDTO.getName());
    mockMvc.perform(get("/api/boats")
        .cookie(new Cookie("token", token)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(1)))
        .andExpect(jsonPath("$[0].id").value(boatId.toString()))
        .andExpect(jsonPath("$[0].name").value(boatRequestDTO.getName()));

    verify(boatService, times(1)).getAllBoats();
  }

  @Test
  void getBoatById_ShouldReturnBoat() throws Exception {
    when(boatService.getBoatById(boatId)).thenReturn(boatResponseDTO);

    mockMvc.perform(get("/api/boats/{id}", boatId)
        .cookie(new Cookie("token", token)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(boatId.toString()))
        .andExpect(jsonPath("$.name").value(boatRequestDTO.getName()));

    verify(boatService, times(1)).getBoatById(boatId);
  }

  @Test
  void createBoat_ShouldReturnCreatedBoat() throws Exception {
    when(boatService.createBoat(any(BoatRequestDTO.class))).thenReturn(boatResponseDTO);

    mockMvc.perform(post("/api/boats")
        .cookie(new Cookie("token", token))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(boatRequestDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(boatId.toString()))
        .andExpect(jsonPath("$.name").value(boatRequestDTO.getName()));

    verify(boatService, times(1)).createBoat(any(BoatRequestDTO.class));

  }

  @Test
  void updateBoat_ShouldReturnUpdatedBoat() throws Exception {
    when(boatService.updateBoat(eq(boatId), any(BoatRequestDTO.class))).thenReturn(boatResponseDTO);

    mockMvc.perform(put("/api/boats/{id}", boatId)
        .cookie(new Cookie("token", token))
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(boatRequestDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(boatId.toString()))
        .andExpect(jsonPath("$.name").value(boatRequestDTO.getName()));

    verify(boatService, times(1)).updateBoat(eq(boatId), any(BoatRequestDTO.class));

  }

  @Test
  void deleteBoat_ShouldReturnNoContent() throws Exception {
    doNothing().when(boatService).deleteBoat(boatId);

    mockMvc.perform(delete("/api/boats/{id}", boatId)
        .cookie(new Cookie("token", token)))
        .andExpect(status().isNoContent());

    verify(boatService, times(1)).deleteBoat(boatId);

  }
}
