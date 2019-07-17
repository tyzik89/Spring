package work.vladimirs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import work.vladimirs.configuration.ContextConfiguration;
import work.vladimirs.entities.greeter.Greeter;

/**
 * Application main class
 */
public final class App {

    /**
     * Нельзя конструировать
     */
    private App() {};

    /**
     * Application entry point.
     * @param args Array of command line arguments.
     */
    public static void main(String[] args) {
        /*Создаёт Spring context используя аннотации и Spring beans из  ContextConfiguration.*/
        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /*Запрашивает из контекста bean типа Greeter.
        Стоит отметить, что класс к этому времени уже сконструирован, классы, от которых он зависит,
         тоже уже сконструированы и getBean только возвращает ссылку на существующий экземпляр.*/
        Greeter greeter = context.getBean(Greeter.class);

        System.out.println(greeter.greet());
    }
}
