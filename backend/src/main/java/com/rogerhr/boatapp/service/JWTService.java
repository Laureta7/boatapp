package com.rogerhr.boatapp.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
  @Value("${jwt.secret}")
  private String secretKey; // Fetch from application.properties

  @Value("${jwt.expiration}")
  private long expirationTime;

  public String generateToken(String username) {

    Map<String, Object> claims = new HashMap<>();

    return Jwts.builder()
        .claims()
        .add(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000 * expirationTime))
        .and()
        .signWith(getSecretKey())
        .compact();

  }

  private Key getSecretKey() {
    // Convert secret key string to Key
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);

  }

}
