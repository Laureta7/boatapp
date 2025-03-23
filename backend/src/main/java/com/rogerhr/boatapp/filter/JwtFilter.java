package com.rogerhr.boatapp.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rogerhr.boatapp.service.JWTService;
import com.rogerhr.boatapp.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private JWTService jwtService;

  @Autowired
  private ApplicationContext applicationContext;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // From the request, get the Authorization header (Bearer token)
    String authorizationHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      token = authorizationHeader.substring(7);
      username = jwtService.extractUserName(token);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = applicationContext.getBean(MyUserDetailsService.class).loadUserByUsername(username);
        if (jwtService.validateToken(token, userDetails)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
              userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource()
              .buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }

    }
    filterChain.doFilter(request, response);

  }

}
