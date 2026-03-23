package com.crm.productservice.entity;

import com.crm.common.enums.StatusProduct;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Float price;

    @Column(columnDefinition = "int default 0", nullable = false)
    private Integer stock;

    @Column(nullable = true)
    private Long idUserCreate;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private Long idUserUpdate;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private StatusProduct status = StatusProduct.DEACTIVATE;

    public Product() {}

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.stock == null) {
            this.stock = 0;
        }

        if (status == null) {
            status = StatusProduct.DEACTIVATE;
        }

        if (this.price == null) {
            this.price = Float.NaN;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();

    }

    public Long getId() {
        return id;
    }


    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getIdUserCreate() { return idUserCreate; }


    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;}
    public void setIdUserCreate(Long idUserCreate) { this.idUserCreate = idUserCreate;}


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

    public StatusProduct getStatus() {
        return status;
    }

    public void setStatus(StatusProduct status) {
        this.status = status;
    }


}
