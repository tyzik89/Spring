package com.work.vladimirs.rocketscloud.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Конфигурация пользовательского хранилища
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //In-memory хранилище пользователей
        auth
            .inMemoryAuthentication()
                .withUser("user_1")
                    //NoOpPasswordEncoder  {noop} - считается устарешим
                    //.password("{noop}pass_1")
                    .password(passwordEncoder().encode("pass_1"))
                    .authorities("ROLE_USER")
                .and()
                .withUser("user_2")
                    //NoOpPasswordEncoder  {noop} - считается устарешим
                    //.password("{noop}pass_2")
                    .password(passwordEncoder().encode("pass_2"))
                    .authorities("ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
