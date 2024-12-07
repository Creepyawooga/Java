package org.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;  // зависимость от KafkaTemplate
    private static final String TOPIC = "order-topic";

    // Конструктор с инжекцией зависимости KafkaTemplate
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;  // инициализация kafkaTemplate через конструктор
    }

    // Метод для отправки сообщений в Kafka
    public void sendMessage(String key, String message) {
        kafkaTemplate.send(TOPIC, key, message);  // отправка сообщения в Kafka
        System.out.println("Message sent: " + message);  // вывод в консоль
    }
}
