package com.ribas.andrei.training.udemy.kafka.section6.products.publisher;

import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event.ProductCreatedEvent;
import com.ribas.andrei.training.udemy.kafka.section6.products.publisher.exception.PublisherException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RequiredArgsConstructor
@Component
class ProductCreatedEventPublisherImpl implements ProductCreatedEventPublisher {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    @Value("${spring.kafka.producer.event.productCreatedEvent.topicName}")
    private String topicName;

    public CompletableFuture<SendResult<String, ProductCreatedEvent>> publishAsync(ProductCreatedEvent event) {
        return publishAsyncInternal(event, true);
    }

    @Override
    public SendResult<String, ProductCreatedEvent> publishSync(ProductCreatedEvent event) {
        try {
            return publishAsyncInternal(event, false).get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException | RuntimeException e) {
            throw new PublisherException("Error while sending event %s with id %s to topic %s.".formatted(event.getClass().getSimpleName(), event.getId(), topicName), event.getId(), topicName, e);
        }
    }

    public CompletableFuture<SendResult<String, ProductCreatedEvent>> publishAsyncInternal(ProductCreatedEvent event, boolean async) {
        return this.kafkaTemplate.send(topicName, event.getId(), event)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        if(async) {
                            log.error("Error while sending event {} with id {} to topic {}.", event.getClass().getSimpleName(), event.getId(), topicName, exception);
                        }
                    } else {
                        log.debug("Event sent successfully: {} with id {} to topic {}: {}", event.getClass().getSimpleName(), event.getId(), topicName, result.getRecordMetadata());
                    }
                });
    }

}
