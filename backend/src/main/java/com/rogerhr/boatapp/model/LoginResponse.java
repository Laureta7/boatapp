package com.rogerhr.boatapp.model;

public class LoginResponse {
  private String message; // Changed from token to message

  public LoginResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
