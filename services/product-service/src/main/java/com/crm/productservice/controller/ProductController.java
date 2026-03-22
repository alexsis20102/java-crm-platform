package com.crm.productservice.controller;
import com.crm.productservice.dto.*;
import com.crm.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/new-product")
    public ProductResponse create(@RequestHeader("X-User-Id") Long userId, @Valid @RequestBody ProductRequest request) {

        return service.create(request, userId);

    }

    @PutMapping("/update-product/{id}")
    public ProductResponse update(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductUpdateRequest request) {

        return service.update(id, request, userId);
    }

    @GetMapping("/get-all-products")
    public List<ProductResponse> getAll(@RequestHeader("X-User-Id") String userId) {

        return service.getAll();
    }

    @GetMapping("/get-product/{id}")
    public ProductResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
