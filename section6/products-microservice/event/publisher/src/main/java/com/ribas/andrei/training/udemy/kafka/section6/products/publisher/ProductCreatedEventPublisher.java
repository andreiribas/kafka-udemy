package com.ribas.andrei.training.udemy.kafka.section6.products.publisher;

import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event.ProductCreatedEvent;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface ProductCreatedEventPublisher {

    CompletableFuture<SendResult<String, ProductCreatedEvent>> publishAsync(ProductCreatedEvent event);

    SendResult<String, ProductCreatedEvent> publishSync(ProductCreatedEvent event);
}
