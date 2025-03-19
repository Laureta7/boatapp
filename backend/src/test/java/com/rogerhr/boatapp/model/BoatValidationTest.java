
package com.rogerhr.boatapp.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoatValidationTest {

  private Validator validator;

  @BeforeEach
  void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testBoatValidation_ShouldFailForInvalidData() {
    Boat boat = new Boat();
    boat.setName(""); // Too short
    boat.setDescription(null); // Should not be null
    boat.setYear(-1); // Should be >= 0
    boat.setLength(0); // Should be > 0
    boat.setOwnerName(null); // Should not be null
    boat.setPrice(-500); // Should be >= 0
    boat.setRegistrationNumber("123"); // Too short

    Set<ConstraintViolation<Boat>> violations = validator.validate(boat);

    assertThat(violations)
        .isNotEmpty()
        .hasSize(6);
  }
}
