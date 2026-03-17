package com.crm.userservice.mapper;

import com.crm.userservice.dto.CreateUserRequest;
import com.crm.userservice.dto.UserDto;
import com.crm.userservice.entity.User;

public class UserMapper {
    // Entity → DTO
    public static UserDto toDto(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }

    // Entity → DTO
    public static UserDto toDtoWithPassword(User user) {
        if (user == null) return null;
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    // DTO → Entity
    public static User toEntity(CreateUserRequest request) {
        if (request == null) return null;
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // пароль шифруем в сервисе
        user.setRole(request.getRole());
        return user;
    }
}
