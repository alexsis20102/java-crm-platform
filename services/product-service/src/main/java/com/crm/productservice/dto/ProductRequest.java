package com.crm.productservice.dto;

import com.crm.common.enums.StatusProduct;
import com.crm.productservice.util.FlexibleFloatDeserializer;
import com.crm.productservice.util.FlexibleIntegerDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;

public class ProductRequest {

    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 50)
    private String description;

    @Min(0)
    @Max(100000)
    @JsonDeserialize(using = FlexibleIntegerDeserializer.class)
    private Integer stock;

    @Min(0)
    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    private Float price;

    private StatusProduct status;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public StatusProduct getStatus() {
        return status;
    }

    public void setStatus(StatusProduct status) {
        this.status = status;
    }
}
