package com.crm.auth.controller;

import com.crm.auth.dto.LoginRequest;
import com.crm.auth.dto.RegisterRequest;
import com.crm.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}
