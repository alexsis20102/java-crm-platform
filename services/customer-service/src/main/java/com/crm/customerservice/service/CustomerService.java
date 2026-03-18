package com.crm.customerservice.service;

import com.crm.customerservice.exception.DuplicateEmailException;
import com.crm.customerservice.exception.NotFoundException;
import com.crm.customerservice.entity.Customer;
import com.crm.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.crm.customerservice.dto.*;
import com.crm.customerservice.mapper.CustomerMapper;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerResponse create(CustomerRequest request, Long userId) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        Customer customer = CustomerMapper.toEntity(request, userId);
        Customer saved = repository.save(customer);
        return CustomerMapper.toResponse(saved);
    }

    public CustomerResponse update(Long id, CustomerUpdateRequest request, Long userId) {

        System.out.println("===== UPDATE DEBUG =====");
        System.out.println("ID: " + id);
        System.out.println("UserId: " + userId);

        if (request != null) {
            System.out.println("FirstName: " + request.getFirstName());
            System.out.println("LastName: " + request.getLastName());
            System.out.println("Email: " + request.getEmail());
            System.out.println("Phone: " + request.getPhone());
        } else {
            System.out.println("Request is NULL");
        }

        System.out.println("========================");

        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer with id " + id + " not found"));

        boolean changed = false;

        if (request.getFirstName() != null &&
                !request.getFirstName().equals(customer.getFirstName())) {
            customer.setFirstName(request.getFirstName());
            changed = true;
        }

        if (request.getLastName() != null &&
                !request.getLastName().equals(customer.getLastName())) {
            customer.setLastName(request.getLastName());
            changed = true;
        }

        if (request.getEmail() != null &&
                !request.getEmail().equals(customer.getEmail())) {

            if (repository.existsByEmail(request.getEmail())) {
                throw new DuplicateEmailException();
            }

            customer.setEmail(request.getEmail());
            changed = true;
        }

        if (request.getPhone() != null &&
                !request.getPhone().equals(customer.getPhone())) {
            customer.setPhone(request.getPhone());
            changed = true;
        }

        if (changed) {
            customer.setIdUserUpdate(userId);
            customer.setUpdatedAt(LocalDateTime.now());
        }

        Customer saved = repository.save(customer);

        return CustomerMapper.toResponse(saved);

        //return new CustomerResponse();
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
