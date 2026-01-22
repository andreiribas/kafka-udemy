package com.ribas.andrei.training.udemy.kafka.section6.products.web.controller;

import com.ribas.andrei.training.udemy.kafka.section6.products.web.dto.CreateProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }

}
