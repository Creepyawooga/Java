package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class KafkaOrderStatusListener {

    private static final Logger log = LoggerFactory.getLogger(KafkaOrderStatusListener.class);
    private final KafkaTemplate<String, String> kafkaTemplate; // Обязательное поле
    private static final String OUTPUT_TOPIC = "order-status-topic";

    // Явный конструктор для инъекции зависимости
    public KafkaOrderStatusListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic", groupId = "group_id")
    public void processOrder(String message) {
        log.info("Received order: {}", message);

        // Генерация нового события
        String statusMessage = "Status: CREATED, Date: " + Instant.now();
        kafkaTemplate.send(OUTPUT_TOPIC, statusMessage);
        log.info("Sent status to Kafka: {}", statusMessage);
    }
}

