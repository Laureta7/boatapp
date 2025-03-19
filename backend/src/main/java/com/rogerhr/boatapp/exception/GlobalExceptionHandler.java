
package com.rogerhr.boatapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BoatNotFoundException.class)
  public ResponseEntity<String> handleBoatNotFoundException(BoatNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(GenericServiceException.class)
  public ResponseEntity<String> handleGenericServiceException(GenericServiceException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // You can add more handlers for different exceptions if needed.
}
