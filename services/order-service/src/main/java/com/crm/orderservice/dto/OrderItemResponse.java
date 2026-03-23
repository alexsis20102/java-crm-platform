package com.crm.orderservice.dto;

import com.crm.orderservice.util.FlexibleFloatDeserializer;
import com.crm.orderservice.util.FlexibleIntegerDeserializer;
import com.crm.orderservice.util.FlexibleLongDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OrderItemResponse {
    @JsonDeserialize(using = FlexibleLongDeserializer.class)
    private Long productId;
    private String productName;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    private Float price;

    @JsonDeserialize(using = FlexibleIntegerDeserializer.class)
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
