package com.crm.gateway.filter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class UserIdForwardFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {

                    Authentication authentication = securityContext.getAuthentication();

                    if (authentication != null && authentication.isAuthenticated()) {

                        Object principal = authentication.getPrincipal();

                        if (principal instanceof Jwt jwt) {

                            String userId = jwt.getSubject();

                            ServerHttpRequest request = exchange.getRequest()
                                    .mutate()
                                    .header("X-User-Id", userId)
                                    .build();

                            return exchange.mutate().request(request).build();
                        }
                    }

                    return exchange;
                })
                .defaultIfEmpty(exchange)
                .flatMap(chain::filter);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
