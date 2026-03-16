package com.crm.auth.client;

import com.crm.auth.dto.RegisterRequest;
import com.crm.auth.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public UserDto getUserByEmail(String email) {

        return webClient.get()
                .uri("lb://user-service/users/email/{email}", email)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public void createUser(String email, String password) {

        webClient.post()
                .uri("lb://user-service/users")
                .bodyValue(new RegisterRequest(email, password))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
