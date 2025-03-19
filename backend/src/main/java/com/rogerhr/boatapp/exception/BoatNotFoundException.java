package com.rogerhr.boatapp.exception;

import java.util.UUID;

public class BoatNotFoundException extends RuntimeException {

  public BoatNotFoundException(UUID boatId) {
    super("Boat with ID " + boatId + " not found.");
  }
}
