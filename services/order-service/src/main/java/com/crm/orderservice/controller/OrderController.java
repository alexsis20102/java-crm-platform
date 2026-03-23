package com.crm.orderservice.controller;
import com.crm.orderservice.dto.OrderRequest;
import com.crm.orderservice.dto.OrderResponse;
import com.crm.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/create-order")
    public OrderResponse create(@RequestHeader("X-User-Id") Long userId, @Valid @RequestBody OrderRequest request) {
        return service.create(request, userId);
    }

    @GetMapping("/get-order/{id}")
    public OrderResponse getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/get-all-orders")
    public List<OrderResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/cancel-order/{id}")
    public void cancel(@RequestHeader("X-User-Id") Long userId, @PathVariable("id") Long id) {
        service.cancel(id, userId);
    }
}
