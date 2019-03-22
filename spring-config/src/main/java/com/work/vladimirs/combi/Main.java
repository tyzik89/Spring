package com.work.vladimirs.combi;

import com.work.vladimirs.auto_config.entities.Cat;
import com.work.vladimirs.auto_config.entities.Dog;
import com.work.vladimirs.auto_config.entities.Parrot;
import com.work.vladimirs.java_config.entities.WeekDay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MyCombiConfig.class);

        WeekDay weekDay = context.getBean(WeekDay.class);
        System.out.println("This is " + weekDay.getWeekDayName() + " today!");

        Cat cat = context.getBean(Cat.class);
        Dog dog = (Dog) context.getBean("dog");
        Parrot parrot = context.getBean("parrot-kesha", Parrot.class);

        System.out.println(cat.getName());
        System.out.println(dog.getName());
        System.out.println(parrot.getName());
    }
}
