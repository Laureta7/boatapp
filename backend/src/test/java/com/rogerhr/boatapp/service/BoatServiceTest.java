
package com.rogerhr.boatapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rogerhr.boatapp.dto.BoatRequestDTO;
import com.rogerhr.boatapp.dto.BoatResponseDTO;
import com.rogerhr.boatapp.exception.BoatNotFoundException;
import com.rogerhr.boatapp.mapper.BoatMapper;
import com.rogerhr.boatapp.model.Boat;
import com.rogerhr.boatapp.repository.BoatRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BoatServiceTest {

  @Mock
  private BoatRepository boatRepository;

  @Mock
  private BoatMapper boatMapper;

  @InjectMocks
  private BoatService boatService;

  private UUID boatId;
  private Boat boat;
  private BoatRequestDTO boatRequestDTO;
  private BoatResponseDTO boatResponseDTO;

  @BeforeEach
  void setUp() {
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

    boat = new Boat();
    boat.setId(boatId);
    boat.setName(boatRequestDTO.getName());
    boat.setDescription(boatRequestDTO.getDescription());
    boat.setYear(boatRequestDTO.getYear());
    boat.setLength(boatRequestDTO.getLength());
    boat.setOwnerName(boatRequestDTO.getOwnerName());
    boat.setPrice(boatRequestDTO.getPrice());
    boat.setRegistrationNumber(boatRequestDTO.getRegistrationNumber());

    boatResponseDTO = BoatResponseDTO.builder()
        .id(boatId)
        .name(boat.getName())
        .description(boat.getDescription())
        .year(boat.getYear())
        .length(boat.getLength())
        .ownerName(boat.getOwnerName())
        .price(boat.getPrice())
        .registrationNumber(boat.getRegistrationNumber())
        .build();
  }

  @Test
  void getAllBoats_ShouldReturnListOfBoats() {
    when(boatRepository.findAll()).thenReturn(List.of(boat));
    when(boatMapper.toResponseDTO(boat)).thenReturn(boatResponseDTO);

    List<BoatResponseDTO> result = boatService.getAllBoats();

    assertThat(result).hasSize(1);
    assertThat(result.get(0).getId()).isEqualTo(boatId);
    verify(boatRepository, times(1)).findAll();
  }

  @Test
  void getBoatById_ShouldReturnBoat() {
    when(boatRepository.findById(boatId)).thenReturn(Optional.of(boat));
    when(boatMapper.toResponseDTO(boat)).thenReturn(boatResponseDTO);

    BoatResponseDTO result = boatService.getBoatById(boatId);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(boatId);
    verify(boatRepository, times(1)).findById(boatId);
  }

  @Test
  void getBoatById_ShouldThrowException_WhenBoatNotFound() {
    when(boatRepository.findById(boatId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> boatService.getBoatById(boatId))
        .isInstanceOf(BoatNotFoundException.class)
        .hasMessageContaining(boatId.toString());

    verify(boatRepository, times(1)).findById(boatId);
  }

  @Test
  void updateBoat_ShouldReturnUpdatedBoat() {
    // Arrange: create the necessary objects

    // Renamed boatId to testBoatId
    UUID testBoatId = UUID.randomUUID();

    // Mock the existing boat entity
    // Renamed boat to existingBoat
    Boat existingBoat = new Boat();
    existingBoat.setId(testBoatId);
    existingBoat.setName("Old Boat");
    existingBoat.setDescription("Old description");
    existingBoat.setYear(2010);
    existingBoat.setLength(20.0);
    existingBoat.setOwnerName("Old Owner");
    existingBoat.setPrice(50000);
    existingBoat.setRegistrationNumber("OLD123");

    // Create a BoatRequestDTO with the updated values
    // Renamed boatRequestDTO to updatedBoatRequestDTO
    BoatRequestDTO updatedBoatRequestDTO = BoatRequestDTO.builder()
        .name("New Boat")
        .description("New description")
        .year(2022)
        .length(25.0)
        .ownerName("New Owner")
        .price(60000)
        .registrationNumber(null)
        .build();

    // Prepare the BoatResponseDTO expected result
    // Renamed boatResponseDTO to expectedBoatResponseDTO
    BoatResponseDTO expectedBoatResponseDTO = BoatResponseDTO.builder()
        .id(testBoatId)
        .name("New Boat")
        .description("New description")
        .year(2022)
        .length(25.0)
        .ownerName("New Owner")
        .price(60000)
        .registrationNumber("OLD123")
        .build();

    // Mock repository and mapper behavior
    when(boatRepository.findById(testBoatId)).thenReturn(Optional.of(existingBoat));
    when(boatRepository.save(any(Boat.class))).thenReturn(existingBoat);
    when(boatMapper.toResponseDTO(any(Boat.class))).thenReturn(expectedBoatResponseDTO);

    // Act: Call the method being tested
    BoatResponseDTO result = boatService.updateBoat(testBoatId, updatedBoatRequestDTO);

    // Assert: Check that the result is as expected
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(testBoatId);
    assertThat(result.getName()).isEqualTo("New Boat");
    assertThat(result.getDescription()).isEqualTo("New description");
    assertThat(result.getYear()).isEqualTo(2022);
    assertThat(result.getLength()).isEqualTo(25.0);
    assertThat(result.getOwnerName()).isEqualTo("New Owner");
    assertThat(result.getPrice()).isEqualTo(60000);
    assertThat(result.getRegistrationNumber()).isEqualTo("OLD123");

    // Verify that the correct repository methods were called
    verify(boatRepository, times(1)).findById(testBoatId);
    verify(boatRepository, times(1)).save(any(Boat.class));
    verify(boatMapper, times(1)).toResponseDTO(any(Boat.class));
  }

  @Test
  void updateBoat_ShouldThrowException_WhenBoatNotFound() {
    when(boatRepository.findById(boatId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> boatService.updateBoat(boatId, boatRequestDTO))
        .isInstanceOf(BoatNotFoundException.class)
        .hasMessageContaining(boatId.toString());

    verify(boatRepository, times(1)).findById(boatId);
    verify(boatRepository, never()).save(any(Boat.class));
  }

  @Test
  void deleteBoat_ShouldDeleteBoat() {
    when(boatRepository.existsById(boatId)).thenReturn(true);
    doNothing().when(boatRepository).deleteById(boatId);

    boatService.deleteBoat(boatId);

    verify(boatRepository, times(1)).existsById(boatId);
    verify(boatRepository, times(1)).deleteById(boatId);
  }

  @Test
  void deleteBoat_ShouldThrowException_WhenBoatNotFound() {
    when(boatRepository.existsById(boatId)).thenReturn(false);

    assertThatThrownBy(() -> boatService.deleteBoat(boatId))
        .isInstanceOf(BoatNotFoundException.class)
        .hasMessageContaining(boatId.toString());

    verify(boatRepository, times(1)).existsById(boatId);
    verify(boatRepository, never()).deleteById(any(UUID.class));
  }
}
