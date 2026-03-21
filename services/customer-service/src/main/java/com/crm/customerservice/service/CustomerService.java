package com.crm.customerservice.service;

import com.crm.customerservice.exception.DuplicateEmailException;
import com.crm.customerservice.exception.NotFoundException;
import com.crm.customerservice.entity.Customer;
import com.crm.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import com.crm.common.enums.LoggingCode;

import java.time.LocalDateTime;
import java.util.List;

import com.crm.customerservice.dto.*;
import com.crm.customerservice.mapper.CustomerMapper;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final LogProducer logProducer;

    public CustomerResponse create(CustomerRequest request, Long userId) {
        try
        {


            if (repository.existsByEmail(request.getEmail())) {
                throw new DuplicateEmailException();
            }

            Customer customer = CustomerMapper.toEntity(request, userId);
            Customer saved = repository.save(customer);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A new customer has been created");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            return CustomerMapper.toResponse(saved);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to create a new customer failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

        return new CustomerResponse();
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

        try
        {
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

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A customer with id " + id + " has been updated");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            return CustomerMapper.toResponse(saved);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to update a customer failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

        return new CustomerResponse();

    }

    public List<CustomerResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    public CustomerService(CustomerRepository repository, LogProducer logProducer) {
        this.repository = repository;
        this.logProducer = logProducer;
    }

    public CustomerResponse getById(Long id) {
        Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer with id " + id + " not found"));
        return CustomerMapper.toResponse(customer);
    }

    public void delete(Long id) {
        try
        {
            try {

                CustomerResponse customer = getById(id);
                logProducer.sendLog(LoggingCode.WARNING.name(), "A customer with id " + id + " and name: " + customer.getFirstName() +" " + customer.getLastName() +" email: " + customer.getEmail() + " has been deleted");

            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            if (!repository.existsById(id)) {
                throw new NotFoundException("Customer with id " + id + " not found");
            }
            repository.deleteById(id);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to delete a customer failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }
    }

}
