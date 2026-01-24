package com.ribas.andrei.training.udemy.kafka.section6.products.service.impl;

import com.ribas.andrei.training.udemy.kafka.section6.products.domain.Product;
import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event.ProductCreatedEvent;
import com.ribas.andrei.training.udemy.kafka.section6.products.publisher.ProductCreatedEventPublisher;
import com.ribas.andrei.training.udemy.kafka.section6.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductCreatedEventPublisher productCreatedEventPublisher;

    @Override
    public String createProduct(Product product) {
        String productId = UUID.randomUUID().toString();

        // TODO: persist product in the db

        var productCreatedEvent = new ProductCreatedEvent(productId, product.getTitle(), product.getPrice(), product.getQuantity());

        log.info("Before publishing event for productId: {}", productId);
        // var result = productCreatedEventPublisher.publishSync(productCreatedEvent);
        var result = productCreatedEventPublisher.publishAsync(productCreatedEvent);

        //log.info("After publishing event for productId: {}, topic: {}, partition: {}, offset: {}.", productId, result.getRecordMetadata().topic(), result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        return productId;
    }
}
