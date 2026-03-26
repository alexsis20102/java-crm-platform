package com.crm.orderservice.client;
import com.crm.orderservice.config.FeignTracingConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service",
        configuration = FeignTracingConfig.class)
public interface ProductClient {

    @GetMapping("/products/get-product/{id}")
    ProductResponse getProduct(@PathVariable("id") Long id);

}
