package com.work.vladimirs.shawermacloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    //Вместо контроллера, который просто делает перенаправление на старничку, можно использовать
    //этот конфиг и этот метод. В даннос случае метод отображает home страничку при обращении к "/"
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
