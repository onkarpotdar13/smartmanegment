package com.scm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Role;
import com.scm.entities.User;
import com.scm.form.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.repositories.RoleRepository;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class PageController {

  @Autowired
  private UserService userService;

  @Autowired
  private RoleRepository roleRepository;

  @RequestMapping("/home")
  public String home(Model model)
  {
    System.out.println("Home Page Handler");
    model.addAttribute("Title", "WELCOME TO HOME PAGE");
    model.addAttribute("Name", "Onkar Hanmant Potdar");
    model.addAttribute("Class", "Automobile Engineering");
    model.addAttribute("Address", "Uran-Islampur, Sangli");
    model.addAttribute("Url", "https://www.opencart.com/index.php?route=cms/demo");
      return "home";
  }

  @RequestMapping("/about")
  public String aboutPage(){
    System.out.println("about page loading");
    return "about";
  }

  @RequestMapping("/services")
  public String servicesPage(){
    System.out.println("service page loading");
    return "services";
  }

  @GetMapping("/contact")
  public String getMethodName() {
      return "contact";
  }
  
  @GetMapping("/login")
  public String loginPage(){
    return "login";
  }

  @GetMapping("/register")
  public String registerPage(Model model){

    UserForm userForm = new UserForm();
    // userForm.setUserName("Kavita Patil");
    // userForm.setAbout("I am java developer");
    model.addAttribute("userForm", userForm);
    return "register";
  }

  // processing registration

  @RequestMapping(value = "/do-register", method = RequestMethod.POST)
  public String processRegisteration(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session){
    System.out.println("Process Register");
    // fetch form data
    // user form
    System.out.println(userForm);
    // validationform data

    if(bindingResult.hasErrors()){
      return "register";
    }

    if(bindingResult.hasErrors()){
      return "login";
    }

    // save to database

    // userservice and take data form user form and save in user
    // User user = User.builder()
    // .userName(userForm.getUserName())
    // .userEmail(userForm.getUserEmail())
    // .userPassword(userForm.getUserPassword())
    // .userPhoneNumber(userForm.getUserPhoneNumber())
    // .userProfilePic("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?size=338&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid")
    // .about(userForm.getAbout())
    // .build();

    User user = new User();
    user.setUserName(userForm.getUserName());
    user.setUserEmail(userForm.getUserEmail());
    user.setUserPassword(userForm.getUserPassword());
    user.setUserPhoneNumber(userForm.getUserPhoneNumber());
    user.setUserProfilePic("https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?size=338&ext=jpg&ga=GA1.1.2008272138.1726963200&semt=ais_hybrid");
    user.setAbout(userForm.getAbout());

    // Fetch the selected role
    String selectedRoleName = userForm.getRole(); // e.g., "ROLE_PATIENT"
    Role selectedRole = roleRepository.findByRoleName(selectedRoleName)
            .orElseThrow(() -> new RuntimeException("Selected Role not found: " + selectedRoleName));

    user.setRoles(List.of(selectedRole));

    User savedUser = userService.saveUser(user);

    System.out.println("USER SAVED SUCCESSFULLY...");

    // message = registration successfull

    // add message

    // Message for registration success
    if (savedUser != null) {
        Message successMessage = Message.builder()
                .content("Registration Successful!")
                .type(MessageType.GREEN)
                .build();
        session.setAttribute("message", successMessage);
    } else {
        Message errorMessage = Message.builder()
                .content("Registration Failed! Please try again.")
                .type(MessageType.RED)
                .build();
        session.setAttribute("message", errorMessage);
    }



    // session.setAttribute("message", "Register Successful...");
    // redirect login page
    return "redirect:/register";
  }


}
