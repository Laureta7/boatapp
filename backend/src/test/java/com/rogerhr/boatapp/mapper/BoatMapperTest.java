
package com.rogerhr.boatapp.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.rogerhr.boatapp.dto.BoatRequestDTO;
import com.rogerhr.boatapp.dto.BoatResponseDTO;
import com.rogerhr.boatapp.model.Boat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoatMapperTest {

  private BoatMapper boatMapper;

  @BeforeEach
  void setUp() {
    boatMapper = new BoatMapper();
  }

  @Test
  void testToEntity_ShouldMapBoatRequestDTOToBoat() {
    // GIVEN
    BoatRequestDTO dto = BoatRequestDTO.builder()
        .name("Test Boat")
        .description("A beautiful boat")
        .year(2024)
        .length(10.5)
        .ownerName("John Doe")
        .price(50000)
        .registrationNumber("ABC123")
        .build();

    // WHEN
    Boat boat = boatMapper.toEntity(dto);

    // THEN
    assertThat(boat).isNotNull();
    assertThat(boat.getName()).isEqualTo(dto.getName());
    assertThat(boat.getDescription()).isEqualTo(dto.getDescription());
    assertThat(boat.getYear()).isEqualTo(dto.getYear());
    assertThat(boat.getLength()).isEqualTo(dto.getLength());
    assertThat(boat.getOwnerName()).isEqualTo(dto.getOwnerName());
    assertThat(boat.getPrice()).isEqualTo(dto.getPrice());
    assertThat(boat.getRegistrationNumber()).isEqualTo(dto.getRegistrationNumber());
  }

  @Test
  void testToEntity_WithNull_ShouldReturnNull() {
    // WHEN
    Boat boat = boatMapper.toEntity(null);

    // THEN
    assertThat(boat).isNull();
  }

  @Test
  void testToResponseDTO_ShouldMapBoatToBoatResponseDTO() {
    // GIVEN
    Boat boat = new Boat();
    boat.setName("Luxury Boat");
    boat.setDescription("A luxury yacht");
    boat.setYear(2023);
    boat.setLength(15.5);
    boat.setOwnerName("Alice Smith");
    boat.setPrice(200000);
    boat.setRegistrationNumber("XYZ789");

    // WHEN
    BoatResponseDTO responseDTO = boatMapper.toResponseDTO(boat);

    // THEN
    assertThat(responseDTO).isNotNull();
    assertThat(responseDTO.getId()).isEqualTo(boat.getId());
    assertThat(responseDTO.getName()).isEqualTo(boat.getName());
    assertThat(responseDTO.getDescription()).isEqualTo(boat.getDescription());
    assertThat(responseDTO.getYear()).isEqualTo(boat.getYear());
    assertThat(responseDTO.getLength()).isEqualTo(boat.getLength());
    assertThat(responseDTO.getOwnerName()).isEqualTo(boat.getOwnerName());
    assertThat(responseDTO.getPrice()).isEqualTo(boat.getPrice());
    assertThat(responseDTO.getRegistrationNumber()).isEqualTo(boat.getRegistrationNumber());
  }

  @Test
  void testToResponseDTO_WithNull_ShouldReturnNull() {
    // WHEN
    BoatResponseDTO responseDTO = boatMapper.toResponseDTO(null);

    // THEN
    assertThat(responseDTO).isNull();
  }
}
