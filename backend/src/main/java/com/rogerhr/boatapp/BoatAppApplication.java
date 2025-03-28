package com.rogerhr.boatapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rogerhr.boatapp.repository")
@EntityScan(basePackages = "com.rogerhr.boatapp")
public class BoatAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(BoatAppApplication.class, args);
  }

}
