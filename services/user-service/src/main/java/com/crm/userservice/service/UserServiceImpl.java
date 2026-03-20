package com.crm.userservice.service;

import com.crm.userservice.dto.CreateUserRequest;
import com.crm.userservice.dto.UserDto;
import com.crm.userservice.entity.User;
import com.crm.common.enums.LoggingCode;
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
    private final LogProducer logProducer;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, LogProducer logProducer) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.logProducer = logProducer;
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
        try {

            try {
                logProducer.sendLog(LoggingCode.INFO.name(), "A new user has been created");
            } catch (Exception e) {
                System.err.println("Error processing log event: " + e.getMessage());
            }

            User user = UserMapper.toEntity(request);


            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = repository.save(user);

            return UserMapper.toDto(savedUser);
        }
        catch (Exception e) {

            try {

                logProducer.sendLog(LoggingCode.ERROR.name(), "An attempt to create a new user failed.");

            } catch (Exception e1) {
                System.err.println("Error processing log event: " + e1.getMessage());
            }

            System.err.println("Error processing log event: " + e.getMessage());
        }

        return UserMapper.toDto(new User());
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(NoUserException::new);

        return UserMapper.toDtoWithPassword(user);
    }
}
