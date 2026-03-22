package com.crm.productservice.dto;

import com.crm.common.enums.StatusProduct;


import java.time.LocalDateTime;

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer stock;
    private Long idUserCreate;
    private LocalDateTime createdAt;
    private Long idUserUpdate;
    private LocalDateTime updatedAt;
    private StatusProduct status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getIdUserCreate() {
        return idUserCreate;
    }

    public void setIdUserCreate(Long idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getIdUserUpdate() {
        return idUserUpdate;
    }

    public void setIdUserUpdate(Long idUserUpdate) {
        this.idUserUpdate = idUserUpdate;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public StatusProduct getStatus() {
        return status;
    }

    public void setStatus(StatusProduct status) {
        this.status = status;
    }
}
