package com.crm.customerservice.controller;

import com.crm.customerservice.dto.CustomerRequest;
import com.crm.customerservice.dto.CustomerResponse;
import com.crm.customerservice.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/new-customer")
    public CustomerResponse create(@RequestHeader("X-User-Id") Long userId, @Valid @RequestBody CustomerRequest request) {
        return service.create(request, userId);
    }

    @GetMapping("/get-all-customers")
    public List<CustomerResponse> getAll(@RequestHeader("X-User-Id") String userId) {
        System.out.println("USER ID = " + userId);
        return service.getAll();
    }

    @GetMapping("/get-customer/{id}")
    public CustomerResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
