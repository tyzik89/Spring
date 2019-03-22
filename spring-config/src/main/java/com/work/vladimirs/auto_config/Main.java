package com.work.vladimirs.auto_config;

import com.work.vladimirs.auto_config.entities.Cat;
import com.work.vladimirs.auto_config.entities.Dog;
import com.work.vladimirs.auto_config.entities.Parrot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        // создаем пустой спринговый контекст, который будет искать свои бины по аннотациям в указанном пакете
        ApplicationContext context =
                new AnnotationConfigApplicationContext("com.work.vladimirs.auto_config");

        Cat cat = context.getBean(Cat.class);
        Dog dog = (Dog) context.getBean("dog");
        Parrot parrot = context.getBean("parrot-kesha", Parrot.class);

        System.out.println(cat.getName());
        System.out.println(dog.getName());
        System.out.println(parrot.getName());
    }
}
