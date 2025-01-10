package com.work.vladimirs.kafka;

import com.work.vladimirs.kafka.producer.KafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        KafkaProducer producer = context.getBean(KafkaProducer.class);
        producer.sendMessages();
    }
}
