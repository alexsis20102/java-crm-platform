package com.crm.orderservice.mapper;
import com.crm.orderservice.dto.*;
import com.crm.orderservice.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static Order toEntity(OrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());

        List<OrderItem> items = request.getItems().stream()
                .map(itemReq -> {
                    OrderItem item = new OrderItem();
                    item.setProductId(itemReq.getProductId());
                    item.setQuantity(itemReq.getQuantity());
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(items);

        return order;
    }

    public static OrderResponse toResponse(Order order) {

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomerId());
        response.setStatus(order.getStatus());
        response.setCreatedBy(order.getCreatedBy());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> {
                    OrderItemResponse dto = new OrderItemResponse();
                    dto.setProductId(item.getProductId());
                    dto.setProductName(item.getProductName());
                    dto.setPrice(item.getPrice());
                    dto.setQuantity(item.getQuantity());
                    return dto;
                })
                .collect(Collectors.toList());

        response.setItems(items);

        return response;
    }
}
