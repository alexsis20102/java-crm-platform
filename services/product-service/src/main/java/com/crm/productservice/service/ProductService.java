package com.crm.productservice.service;

import com.crm.productservice.entity.Product;
import com.crm.productservice.exception.NotFoundException;
import com.crm.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.crm.common.enums.LoggingCode;

import java.time.LocalDateTime;
import java.util.List;

import com.crm.productservice.dto.*;
import com.crm.productservice.mapper.ProductMapper;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final LogProducer logProducer;

    public ProductService(ProductRepository repository, LogProducer logProducer) {
        this.repository = repository;
        this.logProducer = logProducer;
    }

    public List<ProductResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    public ProductResponse getById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));
        return ProductMapper.toResponse(product);
    }

    public void delete(Long id) {
        try
        {
            try {

                ProductResponse product = getById(id);
                logProducer.sendLog(LoggingCode.WARNING.name(), "A product with id " + id + " and name: " + product.getName() +" has been deleted");

            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            if (!repository.existsById(id)) {
                throw new NotFoundException("Product with id " + id + " not found");
            }
            repository.deleteById(id);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to delete a product failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }
    }

    public ProductResponse create(ProductRequest request, Long userId) {
        try
        {

            Product product = ProductMapper.toEntity(request, userId);
            Product saved = repository.save(product);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A new product has been created");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            return ProductMapper.toResponse(saved);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to create a new product failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

        return new ProductResponse();
    }


    public ProductResponse update(Long id, ProductUpdateRequest request, Long userId) {

        try
        {
            Product product = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));

            boolean changed = false;

            if (request.getName() != null &&
                    !request.getName().equals(product.getName())) {
                product.setName(request.getName());
                changed = true;
            }

            if (request.getDescription() != null &&
                    !request.getDescription().equals(product.getDescription())) {
                product.setDescription(request.getDescription());
                changed = true;
            }

            if (request.getPrice() != null &&
                    !request.getPrice().equals(product.getPrice())) {

                product.setPrice(request.getPrice());
                changed = true;
            }

            if (request.getStock() != null &&
                    !request.getStock().equals(product.getStock())) {
                product.setStock(request.getStock());
                changed = true;
            }

            if (request.getStatus() != null &&
                    !request.getStatus().equals(product.getStatus())) {
                product.setStatus(request.getStatus());
                changed = true;
            }

            if (changed) {
                product.setIdUserUpdate(userId);
                product.setUpdatedAt(LocalDateTime.now());
            }

            Product saved = repository.save(product);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A product with id " + id + " has been updated");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            return ProductMapper.toResponse(saved);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to update a product failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

        return new ProductResponse();

    }

}
