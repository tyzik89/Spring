package com.work.vladimirs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc //добавление этой аннотации к классу импортирует конфигурацию Spring MVC из WebMvcConfigurationSupport.
@ComponentScan("com.work.vladimirs")
public class WebConfig implements WebMvcConfigurer {

   public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/pages/", ".jsp");
    }

    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
