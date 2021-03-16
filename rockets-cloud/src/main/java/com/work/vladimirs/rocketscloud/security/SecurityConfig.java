package com.work.vladimirs.rocketscloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Qualifier("userRepositoryUserDetailsService")
   @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Метод используется для конфигурации работы секьюрити на веб уровне.
     * HttpSecurity позволяет в частности:
     * 1. Соблюдение условий безопасности, прежде чем обрабатывать запрос
     * 2. Настройка пользовательской страницы входа
     * 3. Предоставление выхода из приложения пользователем
     * 4. Настройка защиты от подделки межсайтовых запросов
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/design", "/orders")
                    .hasRole("USER")
//                    .access("hasRole('USER')") // Выражение SpEL Spring Expression Language
                .antMatchers("/", "/**")
                    .permitAll()
//                    .access("permitAll");   // Выражение SpEL Spring Expression Language
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design")    // дефолтная страница при успешном логине

            .and()
                .logout()
                    .logoutSuccessUrl("/")      //дефолтная страница при выходе из сессии
        ;

//                .loginProcessingUrl("/authenticate")      //Кастомизация пути и имён полей
//                .usernameParameter("user")
//                .passwordParameter("pwd");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    /*// User fro JDBC-based configuration
    @Autowired
    DataSource dataSource;

    // Конфигурация пользовательского хранилища
    // JBDC хранилище пользователей
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource)
                // Конфигурация собственных query для секьюрити путём переопределения стандартных.
                // Отключает использование стандартных таблиц users, authorities, groups , group_members, group_authorities
                .usersByUsernameQuery(
                        "select username, password, enabled from Users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities where username = ?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }*/


    /*// Конфигурация пользовательского хранилища
    // In-memory хранилище пользователей
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user_1")
                    // NoOpPasswordEncoder  {noop} - считается устарешим
                    // .password("{noop}pass_1")
                    .password(passwordEncoder().encode("pass_1"))
                    .authorities("ROLE_USER")
                .and()
                .withUser("user_2")
                    // NoOpPasswordEncoder  {noop} - считается устарешим
                    // .password("{noop}pass_2")
                    .password(passwordEncoder().encode("pass_2"))
                    .authorities("ROLE_USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

}
