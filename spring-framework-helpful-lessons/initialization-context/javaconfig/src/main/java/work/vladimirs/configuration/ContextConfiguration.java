package work.vladimirs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;


/**
 * Spring context configuration descriptor.
 */
@Configuration
/*Сканирует папку на наличие аннотаций типа @Service*/
@ComponentScan("work.vladimirs")
public class ContextConfiguration {

    /**
     * "Random" service bean.
     *  @return Java's built-in random generator.
     */
    @Bean
    public Random random() {
        return new Random();
    }
}
