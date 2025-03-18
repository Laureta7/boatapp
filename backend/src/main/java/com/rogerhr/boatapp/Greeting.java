package com.rogerhr.boatapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Greeting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates a unique id
  private long id;

  private String content;
  private String username;

  // Default constructor
  public Greeting() {
  }

  // Constructor with fields
  public Greeting(String content, String username) {
    this.content = content;
    this.username = username;
  }

  // Getters and setters
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  @Override
  public String toString() {
    return "Greeting{id=" + id + ", content='" + content + "', username='" + username + "'}";
  }
}
