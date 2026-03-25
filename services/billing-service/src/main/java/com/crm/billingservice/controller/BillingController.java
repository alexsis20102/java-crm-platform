package com.crm.billingservice.controller;

import com.crm.billingservice.dto.InvoiceResponse;
import com.crm.billingservice.service.BillingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }


    @GetMapping("/get-invoice/{id}")
    public InvoiceResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/get-all-invoices")
    public List<InvoiceResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/cancel-invoice/{id}")
    public void cancel(@RequestHeader("X-User-Id") Long userId, @PathVariable("id") Long id) {
        service.cancel(id, userId);
    }
}
