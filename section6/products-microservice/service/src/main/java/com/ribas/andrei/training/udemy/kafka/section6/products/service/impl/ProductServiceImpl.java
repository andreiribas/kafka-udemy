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

        productCreatedEventPublisher.publishSync(productCreatedEvent);

        return productId;
    }
}
