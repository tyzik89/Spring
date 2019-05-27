package com.work.vladimirs.shawermacloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Хранение юзеров в памяти - такой подход не позволяет измениять данные о пользователях "на лету"
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("buzz")
                // When you store the users in memory, you are providing the passwords in plain text and when trying to
                // retrieve the encoder from the DelegatingPasswordEncoder to validate the password it can't find one that matches
                // the way in which these passwords were stored.
                // need to use NoOpPasswordEncoder for inMemoryAuthentication {noop} macros
                    .password("{noop}buzz")
                    .authorities("ROLE_USER")
                .and()
                .withUser("woody")
                    .password("{noop}woody")
                    .authorities("ROLE_USER");
    }
}
