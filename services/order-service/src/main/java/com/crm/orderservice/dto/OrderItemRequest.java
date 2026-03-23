package com.crm.orderservice.dto;

import com.crm.orderservice.util.FlexibleIntegerDeserializer;
import com.crm.orderservice.util.FlexibleLongDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {

    @NotNull
    @JsonDeserialize(using = FlexibleLongDeserializer.class)
    private Long productId;

    @NotNull
    @Min(1)
    @JsonDeserialize(using = FlexibleIntegerDeserializer.class)
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
