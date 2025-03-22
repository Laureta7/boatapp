package com.rogerhr.boatapp.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoatRequestDTOValidationTest {

  private Validator validator;

  @BeforeEach
  void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testBoatRequestDTOValidation_ShouldFailForInvalidData() {
    BoatRequestDTO dto = BoatRequestDTO.builder()
        .name("") // Too short, should trigger validation error
        .description(null) // Should not be null
        .year(-1) // Should be >= 0
        .length(0) // Should be > 0
        .ownerName(null) // Should not be null
        .price(-500) // Should be >= 0
        .registrationNumber("123") // Too short, should trigger validation error
        .build();

    // Validate DTO
    Set<ConstraintViolation<BoatRequestDTO>> violations = validator.validate(dto);

    // Assertions
    assertThat(violations)
        .isNotEmpty()
        .hasSize(6); // Expecting six validation errors
  }
}
