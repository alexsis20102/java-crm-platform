package com.crm.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.crm.userservice.enums.Role;

public class CreateUserRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private Role role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
