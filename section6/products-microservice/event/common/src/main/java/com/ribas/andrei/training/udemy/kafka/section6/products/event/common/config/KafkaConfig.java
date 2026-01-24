package com.ribas.andrei.training.udemy.kafka.section6.products.event.common.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String PRODUCT_CREATED_EVENTS_TOPIC_NAME = "product-created-events-topic";

    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name(PRODUCT_CREATED_EVENTS_TOPIC_NAME)
                .partitions(3)
                .replicas(3)
                // min number of replicas that need to acknowledge the write operation (publish of an event)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
