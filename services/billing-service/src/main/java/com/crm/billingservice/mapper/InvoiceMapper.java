package com.crm.billingservice.mapper;

import com.crm.billingservice.dto.InvoiceResponse;
import com.crm.billingservice.entity.Invoice;
import com.crm.common.dto.OrderCreatedEvent;

public class InvoiceMapper {

    public static Invoice toEntity(OrderCreatedEvent event) {
        Invoice invoice = new Invoice();

        invoice.setOrderId(event.getOrderId());
        invoice.setCustomerId(event.getCustomerId());
        invoice.setAmount(event.getTotalAmount());

        return invoice;
    }

    public static InvoiceResponse toResponse(Invoice invoice) {

        InvoiceResponse response = new InvoiceResponse();

        response.setId(invoice.getId());
        response.setOrderId(invoice.getOrderId());
        response.setCustomerId(invoice.getCustomerId());
        response.setAmount(invoice.getAmount());
        response.setStatus(invoice.getStatus());
        response.setCreatedAt(invoice.getCreatedAt());

        return response;
    }

}
