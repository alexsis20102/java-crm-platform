package com.crm.billingservice.service;
import com.crm.common.dto.InvoiceCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BillingEventProducer {
    private final KafkaTemplate<String, InvoiceCreatedEvent> kafkaTemplate;

    public BillingEventProducer(KafkaTemplate<String, InvoiceCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInvoiceCreated(InvoiceCreatedEvent event) {
        kafkaTemplate.send("invoice-created", event);
    }
}
