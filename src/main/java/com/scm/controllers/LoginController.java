package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/error")
public class LoginController {

  @GetMapping("/errlogin")
    public String showLoginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
        }
        return "login";  // Return the login.html page
    }

    @PostMapping("/perform-login")
    public String performLogin(@RequestParam String userEmail, @RequestParam String userPassword, Model model) {
        // Replace with actual user authentication logic
        if (!authenticateUser(userEmail, userPassword)) {
            // Redirect to the login page with error param
            return "redirect:/login?error=true";
        }
        // If login successful, redirect to home page or dashboard
        return "redirect:/home";
    }

    private boolean authenticateUser(String email, String password) {
        // Dummy logic, replace with actual authentication (database lookup, etc.)
        return email.equals("test@domain.com") && password.equals("password123");
    }

}
