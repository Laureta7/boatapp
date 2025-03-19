
package com.rogerhr.boatapp.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class BoatLifecycleTest {

  @Test
  void testPrePersist_ShouldSetCreatedAt() {
    Boat boat = new Boat();
    boat.onCreate(); // Simulate JPA pre-persist event

    assertThat(boat.getCreatedAt()).isNotNull();
    assertThat(boat.getCreatedAt()).isBeforeOrEqualTo(LocalDateTime.now());
  }

  @Test
  void testPreUpdate_ShouldSetUpdatedAt() {
    Boat boat = new Boat();
    boat.onCreate();
    LocalDateTime createdTime = boat.getCreatedAt();

    boat.onUpdate();

    assertThat(boat.getUpdatedAt()).isNotNull();
    assertThat(boat.getUpdatedAt()).isAfter(createdTime);
  }
}
