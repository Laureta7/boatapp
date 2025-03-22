package com.rogerhr.boatapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.rogerhr.boatapp.model.Users;
import com.rogerhr.boatapp.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Users register(Users user) {
    return userRepository.save(user);
  }
}
