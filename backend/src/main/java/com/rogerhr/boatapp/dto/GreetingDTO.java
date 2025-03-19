
package com.rogerhr.boatapp.dto;

public class GreetingDTO {

  private String content;
  private String username;

  // Default constructor
  public GreetingDTO() {
  }

  // Constructor with fields
  public GreetingDTO(String content, String username) {
    this.content = content;
    this.username = username;
  }

  // Getters and setters
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
