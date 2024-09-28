package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfiguration {

  @Autowired
  private SecurityCustomUserDetailService securityCustomUserDetailService;

  @Bean
  public DaoAuthenticationProvider authenticationProvider(){

    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

    //user details object
    daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailService);

    //password encoder object
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

    return daoAuthenticationProvider;
  }


   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // configuration

        // urls configure kiay hai ki koun se public rangenge aur koun se private
        // rangenge
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form default login
        // agar hame kuch bhi change karna hua to hama yaha ayenge: form login se
        // related
        httpSecurity.formLogin(formLogin -> {

            //
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            // formLogin.successForwardUrl("/user/contact");
            
            formLogin.defaultSuccessUrl("/home");
            // formLogin.failureForwardUrl("/login?error=true");

            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

           

        });

       

        return httpSecurity.build();

    }

  // password encoder method
   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
