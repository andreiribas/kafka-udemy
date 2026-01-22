package com.ribas.andrei.training.udemy.kafka.section6.products.web;

import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.config.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(KafkaConfig.class)
@SpringBootApplication
public class ProductsMicroserviceApplication {

    static void main(String[] args) {
        SpringApplication.run(ProductsMicroserviceApplication.class, args);
    }

}
