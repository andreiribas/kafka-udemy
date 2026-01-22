package com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    private String id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
