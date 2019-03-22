package com.work.vladimirs.java_config;

import com.work.vladimirs.java_config.config.MyConfig;
import com.work.vladimirs.java_config.entities.WeekDay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        /*Либо вот так
    ApplicationContext context =
            new AnnotationConfigApplicationContext("com.work.vladimirs.java_config");*/
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        WeekDay weekDay = context.getBean(WeekDay.class);
        System.out.println("This is " + weekDay.getWeekDayName() + " today!");
    }
}
