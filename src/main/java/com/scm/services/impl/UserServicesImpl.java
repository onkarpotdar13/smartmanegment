package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.enitities.User;
import com.scm.helper.AppConstant;
import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;

@Service
public class UserServicesImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public User saveUser(User user) {
    // user id : generate id
    String userId = UUID.randomUUID().toString();
    user.setUserId(userId);

    //password encode
    user.setUserPassword(passwordEncoder.encode(user.getPassword()));

    //set role
    user.setRoleList(List.of(AppConstant.ROLE_USER));
    return userRepository.save(user);
  }

  @Override
  public Optional<User> getUserById(String userId) {
    return userRepository.findById(userId);
  }

  @Override
  public Optional<User> updateUserById(User user) {
    User user1=userRepository.findById(user.getUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("USER NOT FOUND..."));
    user1.setUserName(user.getUserName()); 
    user1.setUserEmail(user.getUserEmail());
    user1.setUserPassword(user.getPassword());  // UserDetails method
    user1.setUserPhoneNumber(user.getUserPhoneNumber());
    user1.setAbout(user.getAbout());
    user1.setUserProfilePic(user.getUserProfilePic());
    user1.setEnabled(user.isEnabled());
    user1.setEmailVerified(user.isEmailVerified());
    user1.setPhoneVerified(user.isPhoneVerified());
    user1.setProvider(user.getProvider());
    user1.setProviderUserId(user.getProviderUserId());

    // save user in data base

    User save = userRepository.save(user1);
    return Optional.ofNullable(save);
  }

  @Override
  public void deleteUserById(String userId) {
    User user2 = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("USER NOT FOUND : " + userId));
    userRepository.delete(user2);
  }

  @Override
  public boolean isUserExistById(String userId) {
    User user2 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("USER ID NOT FOUND : "+userId));
    return user2 != null ? true : false;
  }

  @Override
  public boolean isUserExistByEmail(String userEmail) {
    User user2 = userRepository.findByUserEmail(userEmail).orElse(null);
    return user2 != null ? true : false;
  }

  @Override
  public List<User> getAllusers() {
    return userRepository.findAll();

  }

}
