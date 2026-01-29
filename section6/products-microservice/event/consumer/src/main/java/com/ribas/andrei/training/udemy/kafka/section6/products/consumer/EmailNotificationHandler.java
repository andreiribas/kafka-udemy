package com.ribas.andrei.training.udemy.kafka.section6.products.consumer;


import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event.ProductCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationHandler {

    @KafkaListener(topics = "${spring.kafka.consumer.emailNotification.event.productCreatedEvent.topicName}")
    public void handle(ProductCreatedEvent productCreatedEvent) {
    }
}
