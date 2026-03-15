package com.crm.auth.client;

import com.crm.auth.dto.RegisterRequest;
import com.crm.auth.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserClient {

    private final WebClient webClient;

    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public UserDto getUserByEmail(String email) {

        return webClient.get()
                .uri("http://user-service/users/email/{email}", email)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public void createUser(String email, String password) {

        webClient.post()
                .uri("http://user-service/users")
                .bodyValue(new RegisterRequest(email, password))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
