package com.crm.productservice.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Products Service is working";
    }
}
