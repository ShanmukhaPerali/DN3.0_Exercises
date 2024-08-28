package com.example.bookstore;

import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "online-bookstore");
    }

    @Bean
    public CommandLineRunner demo(MeterRegistry meterRegistry) {
        return args -> {
            meterRegistry.counter("books.added").increment();
        };
    }
}