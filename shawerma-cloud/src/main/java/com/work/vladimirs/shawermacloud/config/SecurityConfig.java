package com.work.vladimirs.shawermacloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    //Хранение юзеров в памяти(user for tests) - такой подход не позволяет измениять данные о пользователях "на лету"
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
    }*/

    /*
    //LDAP authentication
    @Override
    protected void configure1(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .ldapAuthentication()
                //search will be done from the root of the LDAP hierarchy
               *//* .userSearchFilter("(uid={0})")
                .groupSearchFilter("member={0}");*//*
                //----------------------------------------------------------
                //change root search
                //The userSearchBase() method provides a base query for finding users.
                //This piece of code specifies that users be searched for where the organizational
                //unit is people.
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                //Groups should be searched for where the organizational
                //unit is groups.
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                //If we need authenticate by doing a password comparison, we can declare so
                //with the passwordCompare() method, else by default used LDAP Server
                .passwordCompare()
                .passwordEncoder(new BCryptPasswordEncoder())
                //By default, the password given in the login form will be compared with the value of the
                //userPassword attribute in the user’s LDAP entry. If the password is kept in a different
                //attribute, you can specify the password attribute’s name with passwordAttribute():
                .passwordAttribute("passcode")
                //exact location of LDAP files
                .contextSource()
                    .root("dc=tacocloud,dc=com")
                    .ldif("classpath:users.ldif");
    }*/

    private static final String SECRET = "53cr3t";

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource)
                //Changed standard queries for authentication and authorisation of Spring on our custom
                .usersByUsernameQuery("select username, password, enabled from Users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from UserAuthorities where username = ?")
                //Used for encoded password in DB
                .passwordEncoder(new StandardPasswordEncoder(SECRET));


    }
}
