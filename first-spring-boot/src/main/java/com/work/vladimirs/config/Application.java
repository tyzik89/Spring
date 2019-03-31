package com.work.vladimirs.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication //аннотация которая обозначает, что это spring boot приложение
//а значит, что программа должна быть запущена как spring boot
@EnableAutoConfiguration//включает автоконфигурацю и тем самым избавляет от написания
//дополнительных классов конфигураций
//по желанию можно написать свою конфигурацию. приложение выберет именно нашу кастомноую конфигурацию
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
