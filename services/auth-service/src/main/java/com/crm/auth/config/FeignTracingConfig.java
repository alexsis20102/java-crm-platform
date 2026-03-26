package com.crm.auth.config;

import feign.RequestInterceptor;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTracingConfig {

    @Bean
    public RequestInterceptor tracingFeignInterceptor(Tracer tracer) {
        return requestTemplate -> {

            if (tracer.currentSpan() == null) {
                return;
            }

            requestTemplate.header("x-b3-traceid",
                    tracer.currentSpan().context().traceId());

            requestTemplate.header("x-b3-spanid",
                    tracer.currentSpan().context().spanId());
        };
    }
}
