package com.rogerhr.boatapp.service;

import com.rogerhr.boatapp.model.UserPrincipal;
import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

  @Mock
  private UserRepository userRepository; // Mock the UserRepository

  @InjectMocks
  private MyUserDetailsService myUserDetailsService; // Inject it into the service class

  @Test
  void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
    // Arrange
    String username = "testUser";
    Users mockUser = new Users(); // Create a mock User object
    mockUser.setUsername(username);
    // More properties of the mockUser can be set here as necessary...

    // Mock the repository response
    when(userRepository.findByUsername(username)).thenReturn(mockUser);

    // Act
    UserPrincipal userDetails = (UserPrincipal) myUserDetailsService.loadUserByUsername(username);

    // Assert
    assertNotNull(userDetails);
    assertEquals(mockUser.getUsername(), userDetails.getUsername());
    // Add additional assertions from UserPrincipal as necessary
  }

  @Test
  void loadUserByUsername_ShouldThrowException_WhenUserDoesNotExist() {
    // Arrange
    String username = "nonExistentUser";

    // Mock the repository response to return null
    when(userRepository.findByUsername(username)).thenReturn(null);

    // Act & Assert
    assertThrows(UsernameNotFoundException.class,
        () -> myUserDetailsService.loadUserByUsername(username),
        "User not found");
  }
}
