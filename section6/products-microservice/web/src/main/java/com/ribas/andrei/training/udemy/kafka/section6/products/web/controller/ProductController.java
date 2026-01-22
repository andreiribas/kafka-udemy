package com.ribas.andrei.training.udemy.kafka.section6.products.web.controller;

import com.ribas.andrei.training.udemy.kafka.section6.products.domain.Product;
import com.ribas.andrei.training.udemy.kafka.section6.products.service.ProductService;
import com.ribas.andrei.training.udemy.kafka.section6.products.web.dto.CreateProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/api/products
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        var product = new Product(createProductDTO.title(), createProductDTO.price(), createProductDTO.quantity());
        var productId = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
