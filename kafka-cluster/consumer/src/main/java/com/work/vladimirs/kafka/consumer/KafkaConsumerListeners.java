package com.work.vladimirs.kafka.consumer;

import com.work.vladimirs.kafka.dto.JsonMessage;
import com.work.vladimirs.kafka.exception.OurSpecificException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerListeners {

    @KafkaListener(
            // Определяет группу консюмера
            id = "consumer-group-1",
            // Определяет топик откуда читаем
            topics = "${kafka.topics.test-topic}",
            // ВАЖНО: определяет фабрику, которую мы используем.
            // Иначе используется фабрика по умолчанию и многопоточность не работает
            containerFactory = "kafkaListenerContainerFactory")
    public void handle(@Payload JsonMessage message) {
        readMessage(message);
    }

    @KafkaListener(
            // Определяет группу консюмера
            id = "consumer-group-2",
            // Определяет топик откуда читаем
            topics = "${kafka.topics.test-topic}",
            // ВАЖНО: определяет фабрику, которую мы используем.
            // Иначе используется фабрика по умолчанию и многопоточность не работает
            containerFactory = "kafkaListenerContainerFactory")
    public void handle2(@Payload JsonMessage message) {
        readMessage(message);
    }

    private void readMessage(JsonMessage message) {
        long number = message.getNumber();
        String currentThreadName = Thread.currentThread().getName();
        log.info("Прочитано сообщение с номером: {} в потоке: {}", number, currentThreadName);
        if (number % 100 == 0) {
            log.info("Сообщение {} кратно 100", message);
            throw new OurSpecificException("Получено сообщение с номером кратным 100");
        }
    }
}
