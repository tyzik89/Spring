package com.work.vladimirs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class AppGroovy {

    public static void main(final String[] args) {
        ApplicationContext context = new GenericGroovyApplicationContext("/applicationContext.groovy");

        DependentService dependent = context.getBean(DependentService.class);

        System.out.println(dependent.process());
    }

}
