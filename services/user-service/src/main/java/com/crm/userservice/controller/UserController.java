package com.crm.userservice.controller;

import com.crm.userservice.dto.CreateUserRequest;
import com.crm.userservice.dto.UserDto;
import com.crm.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/email/{email}")
    public UserDto getByEmail(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }

}