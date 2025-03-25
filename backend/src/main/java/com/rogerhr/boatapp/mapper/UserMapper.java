package com.rogerhr.boatapp.mapper;

import com.rogerhr.boatapp.dto.UserCreateDTO;
import com.rogerhr.boatapp.dto.UserDTO;
import com.rogerhr.boatapp.model.Users;

public class UserMapper {
  // Private constructor to hide the implicit public one
  private UserMapper() {
    // Prevent instantiation
  }

  // Convert a Users entity to UserDTO
  public static UserDTO toUserDTO(Users user) {
    if (user == null) {
      return null;
    }
    return new UserDTO(user.getId(), user.getUsername());
  }

  // Convert a UserCreateDTO to Users (without ID as it is generated
  // automatically)
  public static Users toUser(UserCreateDTO userCreateDTO) {
    if (userCreateDTO == null) {
      return null;
    }
    Users user = new Users();
    user.setUsername(userCreateDTO.getUsername());
    user.setPassword(userCreateDTO.getPassword());
    return user;
  }
}
