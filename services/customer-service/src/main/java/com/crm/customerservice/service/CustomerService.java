package com.crm.customerservice.service;

import com.crm.customerservice.exception.NotFoundException;
import com.crm.customerservice.entity.Customer;
import com.crm.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import com.crm.customerservice.dto.*;
import com.crm.customerservice.mapper.CustomerMapper;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponse create(CustomerRequest request, Long userId) {
        Customer customer = CustomerMapper.toEntity(request, userId);
        Customer saved = repository.save(customer);
        return CustomerMapper.toResponse(saved);
    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }


    public CustomerResponse getById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id " + id + " not found"));
        return CustomerMapper.toResponse(customer);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Customer with id " + id + " not found");
        }
        repository.deleteById(id);
    }

}
