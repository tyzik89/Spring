package com.work.vladimirs.rocketscloud.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Настройка простых контроллеров, для простых действий, по типу отображения страниц.
 * Например используется вместо HomeController
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/login");
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
