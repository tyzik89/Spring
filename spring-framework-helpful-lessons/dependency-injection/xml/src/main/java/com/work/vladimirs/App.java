package com.work.vladimirs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(final String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/applicationContext.xml");

        DependentService dependent = context.getBean(DependentService.class);

        System.out.println(dependent.process());
    }

}
