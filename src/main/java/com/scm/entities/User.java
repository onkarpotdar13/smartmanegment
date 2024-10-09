package com.scm.entities;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{

  @Id
  private String userId;

  @Column(name = "user_name", nullable = false)
  private String userName;
  
  public String getUserName() {
    return userName;
  }

  @Column(unique = true, nullable = false)
  private String userEmail;

  @Getter(AccessLevel.NONE)
  private String userPassword;

  private String userPhoneNumber;
  @Column(length = 1000)
  private String userProfilePic;
  @Column(length = 1000)
  private String about;

  // information
    @Getter(value =  AccessLevel.NONE)
    private boolean enabled = false;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    

     // user details methods

   


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // list of roles[USER,ADMIN]
        // Collection of SimpGrantedAuthority[roles{ADMIN,USER}]
        return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }   

   // Return the email as the username for authentication purposes --> PROJECT
    @Override
    public String getUsername() {
      return this.userEmail;        // in that project we use   USERNAME AS userEmail
    }
    @Override
    public boolean isAccountNonExpired() {
      return true;
    }
    @Override
    public boolean isAccountNonLocked() {
      return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }

    // Password getter (return encrypted password if needed) --> PROJECT
    @Override
    public String getPassword() {
      return this.userPassword;
    }

   

}
