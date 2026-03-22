package com.crm.productservice.mapper;

import com.crm.productservice.dto.*;
import com.crm.productservice.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequest dto, Long userId) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setStatus(dto.getStatus());
        product.setIdUserCreate(userId);
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setStatus(product.getStatus());
        dto.setIdUserCreate(product.getIdUserCreate());
        dto.setIdUserUpdate(product.getIdUserUpdate());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }
}
