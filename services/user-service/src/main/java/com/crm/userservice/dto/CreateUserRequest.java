package com.crm.userservice.dto;

import com.crm.userservice.enums.Role;

public class CreateUserRequest {
    private String email;
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
