package com.work.vladimirs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.work.vladimirs");

        FirstConsumer firstConsumer = context.getBean(FirstConsumer.class);
        SecondConsumer secondConsumer = context.getBean(SecondConsumer.class);

        firstConsumer.processState();
        secondConsumer.processState();
        firstConsumer.processState();
    }
}
