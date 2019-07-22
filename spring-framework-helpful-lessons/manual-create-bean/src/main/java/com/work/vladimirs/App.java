package com.work.vladimirs;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.work.vladimirs");

        // Класс GreeterService не имеет аннотаций.
        // Чтобы создать бин GreeterService, нужно попопросить фабрику бинов создать его.
        // Однако этот бин существует только в виде объекта, который вернула createBean()
        // и он не может быть запрошен из контекста и его жизненный цикл управляется вами, а не Srping’ом.
        AutowireCapableBeanFactory bf = context.getAutowireCapableBeanFactory();

        GreeterService greeterServiceBean_One = bf.createBean(GreeterService.class);
        System.out.println(greeterServiceBean_One.greet());

        // Чтобы создать «настоящий» бин, которым будет управлять Spring и который можно запросить из контекста,
        // необходимо не инстанциировать класс напрямую, а описать будущий бин и попросить Spring построить его по этому описанию:
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(GreeterService.class);
        gbd.setAutowireCandidate(true);
        gbd.setScope("singleton");
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) bf;
        registry.registerBeanDefinition("greeter", gbd);


        GreeterService greeterServiceBean_Two = (GreeterService) context.getBean("greeter");
        System.out.println(greeterServiceBean_Two.greet());
    }
}
