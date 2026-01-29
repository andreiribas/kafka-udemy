package com.ribas.andrei.training.udemy.kafka.products.consumer.handler;


import com.ribas.andrei.training.udemy.kafka.products.event.common.event.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@KafkaListener(topics = "${spring.kafka.consumer.emailNotification.event.productCreatedEvent.topicName}")
@Component
public class EmailNotificationHandler {

    @KafkaHandler
    public void handle(ProductCreatedEvent productCreatedEvent) {
        log.info("Received event: {}", productCreatedEvent.getTitle());
    }
}
