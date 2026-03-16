package com.crm.userservice.dto;
import com.crm.userservice.enums.Role;

public class UserDto {
    private Long id;
    private String email;
    private Role role;
    private String password;

    public UserDto(Long id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public UserDto(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
