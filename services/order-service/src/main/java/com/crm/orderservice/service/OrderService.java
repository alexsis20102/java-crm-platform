package com.crm.orderservice.service;
import com.crm.common.enums.LoggingCode;
import com.crm.common.dto.OrderCreatedEvent;
import com.crm.orderservice.client.ProductClient;
import com.crm.orderservice.client.ProductResponse;
import com.crm.orderservice.dto.OrderRequest;
import com.crm.orderservice.dto.OrderResponse;
import com.crm.orderservice.entity.Order;
import com.crm.orderservice.entity.OrderItem;
import com.crm.orderservice.exception.NotFoundException;
import com.crm.orderservice.exception.ProductNotFoundException;
import com.crm.orderservice.mapper.OrderMapper;
import com.crm.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import com.crm.common.enums.OrderStatus;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final ProductClient productClient;
    private final LogProducer logProducer;
    private final OrderEventProducer orderEventProducer;

    public OrderService(OrderRepository repository, ProductClient productClient, LogProducer logProducer, OrderEventProducer orderEventProducer) {
        this.repository = repository;
        this.productClient = productClient;
        this.logProducer = logProducer;
        this.orderEventProducer = orderEventProducer;
    }

    public OrderResponse create(OrderRequest request, Long userId) {

        try {
            Order order = OrderMapper.toEntity(request);

            order.setCreatedBy(userId);

            float total = 0;

            for (OrderItem item : order.getItems()) {

                ProductResponse product = productClient.getProduct(item.getProductId());

                if (product == null) {
                    throw new ProductNotFoundException("Product not found: " + item.getProductId());
                }

                item.setProductName(product.getName());
                item.setPrice(product.getPrice());

                total += product.getPrice() * item.getQuantity();
            }

            order.setTotalAmount(total);

            Order saved = repository.save(order);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A new order has been created");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            try {
                OrderCreatedEvent event = new OrderCreatedEvent();
                event.setOrderId(saved.getId());
                event.setCustomerId(saved.getCustomerId());
                event.setTotalAmount(saved.getTotalAmount());
                event.setCreatedId(saved.getCreatedBy());

                orderEventProducer.sendOrderCreated(event);
            }catch (Exception e) {
                System.err.println("Kafka event processing error: " + e.getMessage());
            }

            return OrderMapper.toResponse(saved);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to create a new order failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

        return new OrderResponse();
    }

    public OrderResponse getById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order whit id " + id + " not found"));

        return OrderMapper.toResponse(order);
    }

    public List<OrderResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    public void cancel(Long id, Long userId) {

        try {
            Order order = repository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Order whit id " + id + " not found"));

            order.setStatus(OrderStatus.CANCELLED);

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "Modification of order with ID "+id+" by user with ID "+userId+" setting order status: "+OrderStatus.CANCELLED.name());
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            repository.save(order);
        }
        catch (Exception e){

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "Changing the order status failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }
    }
}
