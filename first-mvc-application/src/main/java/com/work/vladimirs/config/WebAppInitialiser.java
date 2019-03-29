package com.work.vladimirs.config;

/*
* Этот класс нужен нам вместо web.xml - дескриптора развёртывания
 */
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitialiser extends AbstractAnnotationConfigDispatcherServletInitializer{

    // Этот метод должен содержать конфигурации которые инициализируют Beans
    // для инициализации бинов у нас использовалась аннотация @Bean
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                WebConfig.class
        };
    }

    // Тут добавляем конфигурацию, в которой инициализируем ViewResolver
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebConfig.class
        };
    }

    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
