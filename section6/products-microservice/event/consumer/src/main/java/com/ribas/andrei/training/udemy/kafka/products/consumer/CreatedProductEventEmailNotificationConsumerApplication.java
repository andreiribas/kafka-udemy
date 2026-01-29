package com.ribas.andrei.training.udemy.kafka.products.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.ribas.andrei.training.udemy.kafka.products.consumer")
public class CreatedProductEventEmailNotificationConsumerApplication {

    static void main(String[] args) {
        SpringApplication.run(CreatedProductEventEmailNotificationConsumerApplication.class, args);
    }

}
