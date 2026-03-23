package com.crm.orderservice.service;

import com.crm.common.dto.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer {
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Value("${spring.application.name}")
    private String serviceName;

    public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreated(OrderCreatedEvent event) {

        event.setServiceName(serviceName);

        kafkaTemplate.send("order-created", event);
    }
}
