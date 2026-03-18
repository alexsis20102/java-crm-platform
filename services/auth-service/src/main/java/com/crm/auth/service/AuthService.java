package com.crm.auth.service;

import com.crm.auth.client.UserClient;
import com.crm.auth.dto.LoginRequest;
import com.crm.auth.dto.RegisterRequest;

import com.crm.auth.dto.UserDto;
import com.crm.auth.exception.UserNotFound;
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

        System.out.println("RAW password: " + request.getPassword());
        System.out.println("HASH from DB: " + user.getPassword());
        System.out.println("E-mail from DB: " + user.getEmail());
        System.out.println("Id from DB: " + user.getId());
        System.out.println("Role from DB: " + user.getRole());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserNotFound();
        }

        return jwtService.generateToken(user.getId(), user.getEmail(), user.getRole());
    }

}
