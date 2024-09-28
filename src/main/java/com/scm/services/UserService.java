package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.enitities.User;

public interface UserService {

  User saveUser(User user);

  Optional<User> getUserById(String userId);

  Optional<User> updateUserById(User user);

  void  deleteUserById(String userId);

  boolean isUserExistById(String userId);

  boolean isUserExistByEmail(String userEmail);

  List<User> getAllusers();

}
