package com.ribas.andrei.training.udemy.kafka.products.web.dto;

import java.math.BigDecimal;

public record CreateProductDTO(String title, BigDecimal price, Integer quantity) {
}
