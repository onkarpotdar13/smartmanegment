package com.scm.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

  @NotBlank(message = "Name is required")
  private String userName;

  @NotBlank(message = "Email is required")
  @Email(message = "Please provide a valid email address")
   @Pattern(
    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", 
    message = "Email must follow the pattern: example@domain.com"
  )
  private String userEmail;

  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String userPassword;

  @NotBlank(message = "Phone number is required")
  @Size(min = 8, max = 12, message = "Phone number must be between 8 and 12 characters")
  private String userPhoneNumber;
  
  @NotBlank(message = "Must not be blank")
  @Size(max = 50, message = "About section should not exceed 50 characters")
  private String about;

  @NotBlank(message = "Role is required")
  private String role; 

}
