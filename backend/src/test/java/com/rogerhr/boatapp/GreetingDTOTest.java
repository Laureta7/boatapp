
package com.rogerhr.boatapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GreetingDTOTest {

  @Test
  void testDefaultConstructor() {
    GreetingDTO greetingDTO = new GreetingDTO();

    assertThat(greetingDTO.getContent()).isNull();
    assertThat(greetingDTO.getUsername()).isNull();
  }

  @Test
  void testParameterizedConstructor() {
    GreetingDTO greetingDTO = new GreetingDTO("Hello", "User123");

    assertThat(greetingDTO.getContent()).isEqualTo("Hello");
    assertThat(greetingDTO.getUsername()).isEqualTo("User123");
  }

  @Test
  void testSettersAndGetters() {
    GreetingDTO greetingDTO = new GreetingDTO();

    greetingDTO.setContent("Welcome!");
    greetingDTO.setUsername("TestUser");

    assertThat(greetingDTO.getContent()).isEqualTo("Welcome!");
    assertThat(greetingDTO.getUsername()).isEqualTo("TestUser");
  }
}
