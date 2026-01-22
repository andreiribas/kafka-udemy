package com.ribas.andrei.training.udemy.kafka.section6.products.web.dto;

import java.math.BigDecimal;

public record CreateProductDTO(String title, BigDecimal price, Integer quantity) {
}
