
package com.rogerhr.boatapp.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class BoatTest {

  @Test
  void testBoatGettersAndSetters() {
    Boat boat = new Boat();

    UUID id = UUID.randomUUID();
    boat.setId(id);
    boat.setName("Titanic");
    boat.setDescription("A large ship");
    boat.setYear(2022);
    boat.setLength(300.5);
    boat.setOwnerName("John Doe");
    boat.setPrice(5000000);
    boat.setRegistrationNumber("REG123");

    assertThat(boat.getId()).isEqualTo(id);
    assertThat(boat.getName()).isEqualTo("Titanic");
    assertThat(boat.getDescription()).isEqualTo("A large ship");
    assertThat(boat.getYear()).isEqualTo(2022);
    assertThat(boat.getLength()).isEqualTo(300.5);
    assertThat(boat.getOwnerName()).isEqualTo("John Doe");
    assertThat(boat.getPrice()).isEqualTo(5000000);
    assertThat(boat.getRegistrationNumber()).isEqualTo("REG123");
  }
}
