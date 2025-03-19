
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
  void createBoat_ShouldReturnCreatedBoat() {
    when(boatMapper.toEntity(any(BoatRequestDTO.class))).thenReturn(boat);
    when(boatRepository.save(any(Boat.class))).thenReturn(boat);
    when(boatMapper.toResponseDTO(any(Boat.class))).thenReturn(boatResponseDTO);

    BoatResponseDTO result = boatService.createBoat(boatRequestDTO);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(boatId);
    verify(boatRepository, times(1)).save(any(Boat.class));
  }

  @Test
  void updateBoat_ShouldReturnUpdatedBoat() {
    when(boatRepository.findById(boatId)).thenReturn(Optional.of(boat));
    when(boatRepository.save(any(Boat.class))).thenReturn(boat);
    when(boatMapper.toResponseDTO(any(Boat.class))).thenReturn(boatResponseDTO);

    BoatResponseDTO result = boatService.updateBoat(boatId, boatRequestDTO);

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(boatId);
    verify(boatRepository, times(1)).findById(boatId);
    verify(boatRepository, times(1)).save(boat);
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
  void updateBoat_ShouldUpdateOnlyProvidedFields() {
    // Mock existing boat
    Boat existingBoat = new Boat();
    existingBoat.setId(boatId);
    existingBoat.setName("Old Boat");
    existingBoat.setDescription("Old description");
    existingBoat.setYear(2010);
    existingBoat.setLength(20.0);
    existingBoat.setOwnerName("Old Owner");
    existingBoat.setPrice(50000);
    existingBoat.setRegistrationNumber("OLD123");

    // Partial update DTO (some fields null/zero)
    BoatRequestDTO partialUpdateDTO = BoatRequestDTO.builder()
        .name("New Boat") // Should be updated
        .description(null) // Should remain unchanged
        .year(0) // Should remain unchanged
        .length(0) // Should remain unchanged
        .ownerName(null) // Should remain unchanged
        .price(0) // Should remain unchanged
        .registrationNumber(null) // Should remain unchanged
        .build();

    when(boatRepository.findById(boatId)).thenReturn(Optional.of(existingBoat));
    when(boatRepository.save(any(Boat.class))).thenAnswer(invocation -> invocation.getArgument(0));
    when(boatMapper.toResponseDTO(any(Boat.class))).thenAnswer(invocation -> {
      Boat updatedBoat = invocation.getArgument(0);
      return BoatResponseDTO.builder()
          .id(updatedBoat.getId())
          .name(updatedBoat.getName())
          .description(updatedBoat.getDescription())
          .year(updatedBoat.getYear())
          .length(updatedBoat.getLength())
          .ownerName(updatedBoat.getOwnerName())
          .price(updatedBoat.getPrice())
          .registrationNumber(updatedBoat.getRegistrationNumber())
          .build();
    });

    // Call service method
    BoatResponseDTO result = boatService.updateBoat(boatId, partialUpdateDTO);

    // Assertions
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(existingBoat.getId());
    assertThat(result.getName()).isEqualTo("New Boat"); // Updated
    assertThat(result.getDescription()).isEqualTo("Old description"); // Unchanged
    assertThat(result.getYear()).isEqualTo(2010); // Unchanged
    assertThat(result.getLength()).isEqualTo(20.0); // Should NOT be updated (length=0 in DTO)
    assertThat(result.getOwnerName()).isEqualTo("Old Owner"); // Unchanged
    assertThat(result.getPrice()).isEqualTo(50000); // Unchanged
    assertThat(result.getRegistrationNumber()).isEqualTo("OLD123"); // Should NOT be updated (null in DTO)

    verify(boatRepository, times(1)).findById(boatId);
    verify(boatRepository, times(1)).save(existingBoat);
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
