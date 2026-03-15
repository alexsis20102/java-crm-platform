package com.crm.userservice.service;

import com.crm.userservice.dto.CreateUserRequest;
import com.crm.userservice.dto.UserDto;
import com.crm.userservice.entity.User;
import com.crm.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
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
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User savedUser = repository.save(user);

        return new UserDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole());
    }
}
