package com.crm.common.dto;

import java.time.LocalDateTime;

public class InvoiceCreatedEvent {
    private Long invoiceId;
    private Long orderId;
    private Long customerId;
    private Float amount;
    private String email;

    private LocalDateTime createdAt;

    public InvoiceCreatedEvent() {}

    public InvoiceCreatedEvent(Long invoiceId,
                               Long orderId,
                               Long customerId,
                               Float amount,
                               String email,
                               LocalDateTime createdAt) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Float getAmount() {
        return amount;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
