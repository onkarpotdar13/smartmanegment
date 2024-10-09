package com.scm.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.scm.entities.User;
import com.scm.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  // that method use for --> LOAD USER 

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   
    // Get the current request object
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    // Extract the role from the form submission
        String roleAttempted = request.getParameter("role");

    Optional<User> userOption = userRepository.findByUserEmail(username);

    if(userOption.isEmpty()){
      throw new UsernameNotFoundException("USER NOT FOUND WITH EMAIL: " + username);
    }

    User user = userOption.get();

    boolean isRoleValid = user.getRoles()
    .stream()
    .anyMatch(role -> role.getRoleName().equals(roleAttempted));

    if(!isRoleValid){
      throw new UsernameNotFoundException("Invalid credentials or role mismatch for user: " + username);
    }

    return user;

    // return userRepository.findByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH EMAIL : " + username));
  }

}
