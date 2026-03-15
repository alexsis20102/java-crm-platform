package com.crm.userservice.service;


import com.crm.userservice.dto.UserDto;
import com.crm.userservice.dto.CreateUserRequest;


import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto createUser(CreateUserRequest request);

    UserDto getByEmail(String email);
}
