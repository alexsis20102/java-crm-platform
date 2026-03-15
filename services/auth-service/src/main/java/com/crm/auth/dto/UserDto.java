package com.crm.auth.dto;

public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String role;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}
