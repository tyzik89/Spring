package com.work.vladimirs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class AppAnnotation {

    public static void main(final String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.work.vladimirs");

        DependentService dependent = context.getBean(DependentService.class);

        System.out.println(dependent.process());
    }

}
