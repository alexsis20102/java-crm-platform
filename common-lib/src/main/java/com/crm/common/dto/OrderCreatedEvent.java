package com.crm.common.dto;

public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private Long createdId;
    private Float totalAmount;
    private String serviceName;

    public OrderCreatedEvent() {

    }
    public OrderCreatedEvent(Long orderId, Long customerId, Long createdId, Float totalAmount, String serviceName) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.createdId = createdId;
        this.totalAmount = totalAmount;
        this.serviceName = serviceName;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCreatedId() {
        return createdId;
    }

    public void setCreatedId(Long createdId) {
        this.createdId = createdId;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
