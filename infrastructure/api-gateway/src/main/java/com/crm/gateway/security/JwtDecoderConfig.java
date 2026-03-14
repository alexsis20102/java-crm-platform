package com.crm.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

@Configuration
public class JwtDecoderConfig {

    private static final String SECRET = "my_super_secret_key_my_super_secret_key";

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {

        SecretKey key = new SecretKeySpec(
                SECRET.getBytes(),
                "HmacSHA256"
        );

        return NimbusReactiveJwtDecoder
                .withSecretKey(key)
                .build();
    }

}
