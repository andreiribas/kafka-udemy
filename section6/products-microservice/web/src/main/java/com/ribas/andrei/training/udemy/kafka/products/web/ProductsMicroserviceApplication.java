package com.ribas.andrei.training.udemy.kafka.products.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.ribas.andrei.training.udemy.kafka.products")
public class ProductsMicroserviceApplication {

    static void main(String[] args) {
        SpringApplication.run(ProductsMicroserviceApplication.class, args);
    }

}
