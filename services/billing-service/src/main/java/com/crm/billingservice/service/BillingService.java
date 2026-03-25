package com.crm.billingservice.service;
import com.crm.billingservice.dto.InvoiceResponse;
import com.crm.billingservice.entity.Invoice;
import com.crm.billingservice.exception.NotFoundException;
import com.crm.billingservice.mapper.InvoiceMapper;
import com.crm.billingservice.repository.InvoiceRepository;
import com.crm.common.dto.OrderCreatedEvent;
import com.crm.common.enums.InvoiceStatus;
import com.crm.common.enums.LoggingCode;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingService {

    private final InvoiceRepository repository;
    private final LogProducer logProducer;

    public BillingService(InvoiceRepository repository, LogProducer logProducer) {
        this.repository = repository;
        this.logProducer = logProducer;
    }

    public List<InvoiceResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(InvoiceMapper::toResponse)
                .toList();
    }

    public InvoiceResponse getById(Long id) {
        Invoice invoice = repository.findById(id).orElseThrow(() -> new NotFoundException("Invoice with id " + id + " not found"));
        return InvoiceMapper.toResponse(invoice);
    }

    public void createInvoice(OrderCreatedEvent event) {
        try {
            Invoice invoice = InvoiceMapper.toEntity(event);
            repository.save(invoice);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A new invoice has been created");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "Failed to create invoice.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

    }

    public void cancel(Long id, Long userId) {

        try {
            Invoice invoice = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Invoice whit id " + id + " not found"));

            invoice.setStatus(InvoiceStatus.CANCELLED);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "Modification of invoice with ID "+id+" by user with ID "+userId+" setting invoice status: "+InvoiceStatus.CANCELLED.name());
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            repository.save(invoice);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "Changing the invoice status failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }
    }


    @KafkaListener(topics = "order-created", groupId = "billing-group")
    public void handleOrderCreated(OrderCreatedEvent event) {

        System.out.println("Order creation event detected");

        this.createInvoice(event);
    }

}
