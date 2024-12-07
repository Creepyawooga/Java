package org.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Конструктор для инъекции KafkaTemplate
    public OrderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; // Инициализация обязательного поля
    }

    @PostMapping
    public String createOrder(@RequestBody String order) {
        kafkaTemplate.send("order-topic", order);
        return "Order sent to Kafka: " + order;
    }
}
