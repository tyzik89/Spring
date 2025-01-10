package com.work.vladimirs.kafka.producer;

import com.work.vladimirs.kafka.dto.JsonMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    private static final int MESSAGE_LIMIT = 10_000;

    @Value("${kafka.topics.test-topic}")
    private String topic;

    private int messageNumber = 0;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessages() {
        while (messageNumber != MESSAGE_LIMIT) {
            messageNumber++;
            JsonMessage jsonMessage = JsonMessage.builder()
                    .number(messageNumber)
                    .message("Message number " + messageNumber)
                    .build();

            String genNum = String.valueOf(ThreadLocalRandom.current().nextLong());
            kafkaTemplate.send(topic, genNum, jsonMessage);
            log.info("Отправлено сообщение с genNum: {} и номером {}", genNum, messageNumber);
        }
    }
}
