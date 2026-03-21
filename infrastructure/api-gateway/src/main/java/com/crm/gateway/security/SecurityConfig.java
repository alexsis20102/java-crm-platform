package com.crm.gateway.security;


import com.crm.common.enums.ErrorCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;
import com.crm.common.exception.ErrorResponse;


@Configuration
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    public SecurityConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((exchange, ex) -> {

                            var response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.UNAUTHORIZED);
                            response.getHeaders().add("Content-Type", "application/json");

                            ErrorResponse error = new ErrorResponse(
                                    "Authentication required",
                                    401,
                                    "api-gateway",
                                    ErrorCode.AUTHENTICATION_REQUIRED.name()
                            );

                            try {
                                byte[] bytes = objectMapper.writeValueAsBytes(error);
                                var buffer = response.bufferFactory().wrap(bytes);
                                return response.writeWith(Mono.just(buffer));
                            } catch (Exception e) {
                                return Mono.error(e);
                            }
                        })

                        .accessDeniedHandler((exchange, ex) -> {

                            var response = exchange.getResponse();
                            response.setStatusCode(HttpStatus.FORBIDDEN);
                            response.getHeaders().add("Content-Type", "application/json");

                            ErrorResponse error = new ErrorResponse(
                                    "Insufficient rights to perform this operation",
                                    403,
                                    "api-gateway",
                                    ErrorCode.ACCESS_DENIED.name()
                            );

                            try {
                                byte[] bytes = objectMapper.writeValueAsBytes(error);
                                var buffer = response.bufferFactory().wrap(bytes);
                                return response.writeWith(Mono.just(buffer));
                            } catch (Exception e) {
                                return Mono.error(e);
                            }
                        })
                )

                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/auth/**", "/test").permitAll()
                        .pathMatchers("/products/test").permitAll()
                        .pathMatchers("/logging/**").permitAll()
                        .pathMatchers("/users/email/{email}").permitAll()
                        .pathMatchers("/users").permitAll()

                        .pathMatchers("/users/get-all-users")
                        .hasAnyRole("ADMIN", "MANAGER")

                        .pathMatchers("/customers/delete/{id}")
                        .hasAnyRole("ADMIN")

                        .pathMatchers("/logs/trace/{traceId}", "/logs/service/{serviceName}", "/logs/get-all-logs")
                        .hasAnyRole("ADMIN")

                        .pathMatchers("/customers/new-customer", "/customers/get-customer/{id}")
                        .hasAnyRole("ADMIN", "MANAGER", "USER")

                        .pathMatchers("/customers/update-customer/{id}")
                        .hasAnyRole("ADMIN", "MANAGER")

                        .pathMatchers("/customers/get-all-customers")
                        .hasAnyRole("ADMIN", "MANAGER")

                        .anyExchange().authenticated()

                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(new JwtAuthConverter()))
                )
                .build();
    }
}
