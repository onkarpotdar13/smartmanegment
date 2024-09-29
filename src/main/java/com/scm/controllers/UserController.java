package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  private Logger logger = LoggerFactory.getLogger(UserController.class);

  // user dashbaord page

  @RequestMapping(value = "/dashboard")
  public String userDashboard() {
      System.out.println("User dashboard");
      return "user/dashboard";
  }

  // user profile page

  @RequestMapping(value = "/profile")
  public String userProfile() {

      return "user/profile";
  }


}
