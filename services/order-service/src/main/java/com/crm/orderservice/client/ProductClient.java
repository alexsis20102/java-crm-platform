package com.crm.orderservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/get-product/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

}
