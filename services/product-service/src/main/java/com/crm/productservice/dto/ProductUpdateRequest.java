package com.crm.productservice.dto;

import com.crm.common.enums.StatusProduct;
import com.crm.productservice.util.FlexibleFloatDeserializer;
import com.crm.productservice.util.FlexibleIntegerDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class ProductUpdateRequest {
    private String name;
    private String description;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    private Float price;
    @JsonDeserialize(using = FlexibleIntegerDeserializer.class)
    private Integer stock;
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
