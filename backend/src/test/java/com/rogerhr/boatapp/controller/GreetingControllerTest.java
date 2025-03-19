package com.rogerhr.boatapp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rogerhr.boatapp.model.Greeting;
import com.rogerhr.boatapp.repository.GreetingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GreetingController.class)
class GreetingControllerTest {

  @MockitoBean // Injection correcte du mock
  private GreetingRepository greetingRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    // Aucun setup nécessaire pour ObjectMapper (Spring l'injecte automatiquement)
  }

  @Test
  void testGetAllGreetings() throws Exception {
    Greeting greeting1 = new Greeting("Hello", "World");
    greeting1.setId(1L);

    Greeting greeting2 = new Greeting("Hi", "Universe");
    greeting2.setId(2L);

    // Mock repository response
    when(greetingRepository.findAll()).thenReturn(List.of(greeting1, greeting2));

    // Perform GET request
    mockMvc.perform(get("/api/greeting"))
        .andExpect(status().isOk()) // Expect HTTP 200
        .andExpect(jsonPath("$[0].content").value("Hello"))
        .andExpect(jsonPath("$[0].username").value("World"))
        .andExpect(jsonPath("$[1].content").value("Hi"))
        .andExpect(jsonPath("$[1].username").value("Universe"));
  }

  // @Test
  // void testCreateGreeting() throws Exception {
  // Greeting greeting = new Greeting("Hello", "World_user");
  //
  // // Mock repository response
  // Greeting savedGreeting = new Greeting("Hello", "World_user");
  // savedGreeting.setId(1L);
  //
  // when(greetingRepository.save(ArgumentMatchers.any(Greeting.class))).thenReturn(savedGreeting);
  //
  // // Convert object to JSON dynamically
  // String greetingJson = objectMapper.writeValueAsString(greeting);
  // System.out.println(greetingJson);
  //
  // // Perform POST request
  // mockMvc.perform(post("/api/greeting")
  // .contentType("application/json")
  // .content(greetingJson))
  // .andExpect(status().isOk()) // Expect HTTP 200
  // // .andDo(print())
  // .andExpect(jsonPath("$.id").value(1))
  // .andExpect(jsonPath("$.content").value("Hello"))
  // .andExpect(jsonPath("$.username").value("World_user"));
  // }
}
