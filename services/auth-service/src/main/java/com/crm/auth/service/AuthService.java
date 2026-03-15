package com.crm.auth.service;

import com.crm.auth.client.UserClient;
import com.crm.auth.dto.LoginRequest;
import com.crm.auth.dto.RegisterRequest;

import com.crm.auth.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.crm.auth.security.JwtService;



@Service
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserClient userClient,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userClient = userClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {
        userClient.createUser(
                request.getEmail(),
                request.getPassword()
        );
    }

    public String login(LoginRequest request) {

        UserDto user = userClient.getUserByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user.getEmail());
    }

}
