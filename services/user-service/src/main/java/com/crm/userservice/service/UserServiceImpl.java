package com.crm.userservice.service;

import com.crm.userservice.dto.CreateUserRequest;
import com.crm.userservice.dto.UserDto;
import com.crm.userservice.entity.User;
import com.crm.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() {

        return repository.findAll()
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getEmail(),
                        user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = repository.save(user);

        return new UserDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole());
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDto(user.getId(), user.getEmail(), user.getRole());
    }
}
