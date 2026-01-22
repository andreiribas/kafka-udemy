package com.ribas.andrei.training.udemy.kafka.section6.products.publisher;

import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event.ProductCreatedEvent;
import com.ribas.andrei.training.udemy.kafka.section6.products.publisher.exception.PublisherException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.ribas.andrei.training.udemy.kafka.section6.products.event.common.config.KafkaConfig.PRODUCT_CREATED_EVENTS_TOPIC_NAME;

@Slf4j
@RequiredArgsConstructor
@Component
class ProductCreatedEventPublisherImpl implements ProductCreatedEventPublisher {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public CompletableFuture<SendResult<String, ProductCreatedEvent>> publishAsync(ProductCreatedEvent event) {
        return publishAsyncInternal(event, true);
    }

    @Override
    public void publishSync(ProductCreatedEvent event) {
        try {
            publishAsyncInternal(event, false).get(30, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException | RuntimeException e) {
            throw new PublisherException("Error while sending event %s with id %s to topic %s.".formatted(event.getClass().getSimpleName(), event.getId(), PRODUCT_CREATED_EVENTS_TOPIC_NAME), event.getId(), PRODUCT_CREATED_EVENTS_TOPIC_NAME, e);
        }
    }

    public CompletableFuture<SendResult<String, ProductCreatedEvent>> publishAsyncInternal(ProductCreatedEvent event, boolean async) {
        return this.kafkaTemplate.send(PRODUCT_CREATED_EVENTS_TOPIC_NAME, event.getId(), event)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        if(async) {
                            log.error("Error while sending event {} with id {} to topic {}.", event.getClass().getSimpleName(), event.getId(), PRODUCT_CREATED_EVENTS_TOPIC_NAME, exception);
                        }
                    } else {
                        log.debug("Event sent successfully: {} with id {} to topic {}: {}", event.getClass().getSimpleName(), event.getId(), PRODUCT_CREATED_EVENTS_TOPIC_NAME, result.getRecordMetadata());
                    }
                });
    }

}
