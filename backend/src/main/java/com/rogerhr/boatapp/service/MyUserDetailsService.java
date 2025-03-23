package com.rogerhr.boatapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rogerhr.boatapp.model.UserPrincipal;
import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  // Constructor injection
  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Users user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return new UserPrincipal(user);

  }

}
