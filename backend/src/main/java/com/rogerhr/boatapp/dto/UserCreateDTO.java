package com.rogerhr.boatapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

  @NotNull(message = "Username cannot be null")
  @Column(unique = true)
  private String username;

  @NotNull(message = "Password cannot be null")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String password;
}
