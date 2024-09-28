package com.scm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.repositories.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  // that method use for --> LOAD USER 

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   
    return userRepository.findByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH EMAIL : " + username));
  }

}
