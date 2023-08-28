package com.work.vladimirs.rocketscloud.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

   // @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(
//                new User(
//                        "bill",
//                        passwordEncoder.encode("kerman"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        userDetailsList.add(
//                new User(
//                        "bob",
//                        passwordEncoder.encode("kerman"),
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }
}
