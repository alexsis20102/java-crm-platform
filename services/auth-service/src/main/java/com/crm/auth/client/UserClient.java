package com.crm.auth.client;

import com.crm.auth.config.FeignTracingConfig;
import com.crm.auth.dto.CreateUserRequest;
import com.crm.auth.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service",
        configuration = FeignTracingConfig.class)
public interface UserClient {

    @GetMapping("/users/email/{email}")
    UserDto getUserByEmail(@PathVariable("email") String email);

    @PostMapping("/users/create-user")
    void createUser(@RequestBody CreateUserRequest request);

}
