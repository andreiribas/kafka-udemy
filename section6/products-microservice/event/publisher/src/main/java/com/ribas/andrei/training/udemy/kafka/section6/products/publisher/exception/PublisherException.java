package com.ribas.andrei.training.udemy.kafka.section6.products.publisher.exception;

import lombok.Getter;

@Getter
public class PublisherException extends RuntimeException {

    private final String topicName;
    private final String messageId;

    public PublisherException(String message, String topicName, String messageId, Throwable cause) {
        super(message, cause);
        this.topicName = topicName;
        this.messageId = messageId;
    }
}
