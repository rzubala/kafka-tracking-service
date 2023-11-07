package dev.lydtech.tracking.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class TestConfig {
    @Bean
    public KafkaTestListener testListener() {
        return new KafkaTestListener();
    }
}
