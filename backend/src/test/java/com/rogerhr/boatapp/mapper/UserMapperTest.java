package com.rogerhr.boatapp.mapper;

import com.rogerhr.boatapp.dto.UserCreateDTO;
import com.rogerhr.boatapp.dto.UserDTO;
import com.rogerhr.boatapp.model.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;

class UserMapperTest {
  private UUID userId;

  @BeforeEach
  void setUp() {
    userId = UUID.randomUUID();
  }

  @Test
  void toUserDTO_ShouldReturnUserDTO_WhenUserIsNotNull() {
    // Arrange
    Users user = new Users();
    user.setId(userId);
    user.setUsername("testUser");

    // Act
    UserDTO userDTO = UserMapper.toUserDTO(user);

    // Assert
    assertNotNull(userDTO);
    assertEquals(userId, userDTO.getId());
    assertEquals("testUser", userDTO.getUsername());
  }

  @Test
  void toUserDTO_ShouldReturnNull_WhenUserIsNull() {
    // Act
    UserDTO userDTO = UserMapper.toUserDTO(null);

    // Assert
    assertNull(userDTO);
  }

  @Test
  void toUser_ShouldReturnUser_WhenUserCreateDTOIsNotNull() {
    // Arrange
    UserCreateDTO userCreateDTO = new UserCreateDTO();
    userCreateDTO.setUsername("testUser");
    userCreateDTO.setPassword("testPassword");

    // Act
    Users user = UserMapper.toUser(userCreateDTO);

    // Assert
    assertNotNull(user);
    assertEquals("testUser", user.getUsername());
    assertEquals("testPassword", user.getPassword());
  }

  @Test
  void toUser_ShouldReturnNull_WhenUserCreateDTOIsNull() {
    // Act
    Users user = UserMapper.toUser(null);

    // Assert
    assertNull(user);
  }
}
