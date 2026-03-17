package com.crm.customerservice.mapper;

import com.crm.customerservice.dto.*;
import com.crm.customerservice.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequest dto, Long userId) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setIdUserCreate(userId);
        return customer;
    }

    public static CustomerResponse toResponse(Customer customer) {
        CustomerResponse dto = new CustomerResponse();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setIdUserCreate(customer.getIdUserCreate());
        dto.setCreatedAt(customer.getCreatedAt());
        return dto;
    }

}
