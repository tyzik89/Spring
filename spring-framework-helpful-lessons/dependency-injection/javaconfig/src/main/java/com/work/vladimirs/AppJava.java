package com.work.vladimirs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppJava {

    public static void main(final String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        DependentService dependent = context.getBean(DependentService.class);

        System.out.println(dependent.process());
    }

}
