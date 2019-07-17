package work.vladimirs;

/*
Когда все компоненты приложения готовы, можно проверить, как они взаимодействуют друг с другом.
Spring включает в себя небольшой инструментарий для упрощения тестирования и, в частности,
поддержку загрузки контекста в JUnit тестах.
Используя специальный runner  SpringJUnit4ClassRunner, мы инициализируем Spring контест автоматически при запуске теста,
а аннотация  @ContextConfiguration указывает, как именно мы хотим сконфигурировать контекст.

Класс интеграционного теста имеет суффикс *IT, а не *Test.
По соглашению все классы имеющие суффикс *IT признаются maven’ом интеграционными тестами и запускаются отдельно от юнит-тестов
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import work.vladimirs.entities.greeter.Greeter;

import javax.inject.Inject;
import static org.junit.Assert.*;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppIT {

    @Inject
    private ApplicationContext applicationContext;


    /*Код интеграционного теста придётся сильно изменить.
    Дело в том, что в настоящий момент @ConfigurationContext не поддерживает annotation-only конфигурацию,
    поэтому приходится использовать костыль:*/
    @Configuration
    @ComponentScan("work.vladimirs")
    public static class SpringConfig { }

    @Test
    public void testSpring() {
        Greeter greeter = applicationContext.getBean(Greeter.class);
        assertTrue(greeter.greet().startsWith("Hello"));
    }
}
