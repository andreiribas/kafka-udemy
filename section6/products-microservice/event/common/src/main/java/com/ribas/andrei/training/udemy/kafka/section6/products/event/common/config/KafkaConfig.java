package com.ribas.andrei.training.udemy.kafka.section6.products.event.common.config;

import com.ribas.andrei.training.udemy.kafka.section6.products.event.common.event.ProductCreatedEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@Configuration
public class KafkaConfig {

    public static final String PRODUCT_CREATED_EVENTS_TOPIC_NAME = "product-created-events-topic";

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.properties.delivery.timeout.ms}")
    private String deliveryTimeout;

    @Value("${spring.kafka.producer.properties.linger.ms}")
    private String linger;

    @Value("${spring.kafka.producer.properties.request.timeout.ms}")
    private String requestTimeout;

    @Value("${spring.kafka.producer.properties.enable.idempotence}")
    private String enableIdempotence;

    Map<String, Object> producerConfigs() {
        return Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers, //
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer, //
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer, //
                ProducerConfig.ACKS_CONFIG, acks, //
                ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeout, //
                ProducerConfig.LINGER_MS_CONFIG, linger, //
                ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout, //
                ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, enableIdempotence
        );
    }

    @Bean
    ProducerFactory<String, ProductCreatedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

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
