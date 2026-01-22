package com.ribas.andrei.training.udemy.kafka.section6.products.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String title;
    private BigDecimal price;
    private Integer quantity;

}
