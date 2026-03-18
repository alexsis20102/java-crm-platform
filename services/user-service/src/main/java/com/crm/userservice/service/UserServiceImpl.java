package com.crm.userservice.service;

import com.crm.userservice.dto.CreateUserRequest;
import com.crm.userservice.dto.UserDto;
import com.crm.userservice.entity.User;
import com.crm.userservice.exception.DuplicateEmailException;
import com.crm.userservice.mapper.UserMapper;
import com.crm.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

import com.crm.userservice.exception.NoUserException;

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
                .map(UserMapper::toDto)   // используем маппер
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(CreateUserRequest request) {

        if (repository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException();
        }

        User user = UserMapper.toEntity(request);


        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = repository.save(user);

        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(NoUserException::new);

        return UserMapper.toDtoWithPassword(user);
    }
}
