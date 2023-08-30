package com.work.vladimirs.rocketscloud.security.configuration;

import com.work.vladimirs.rocketscloud.repositories.UserRepository;
import com.work.vladimirs.rocketscloud.security.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создание и хранение пользователей в памяти
     */
/*    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(
                new User(
                        "bill",
                        passwordEncoder.encode("kerman"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        userDetailsList.add(
                new User(
                        "bob",
                        passwordEncoder.encode("kerman"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    /**
     * Настройка системы безопасности на веб-уровне
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
         httpSecurity
                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design", true) // Страница после успешного входа в систему
                    .loginProcessingUrl("/auth") // Переопределения стандартного URL
//                    .usernameParameter("user") // стандартного параметра (в поле) username на user
//                    .passwordParameter("pswd") // стандартного параметра (в поле) password на pswd
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
//                    .csrf()
//                    .disable()
                 .headers(headers -> headers.frameOptions().disable())
                 .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**"));
//                    .headers()
//                    .frameOptions()
//                    .disable()
        return httpSecurity.build();
    }
}
