package com.biologix.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import reactor.core.publisher.Sinks;

@Configuration
public class StreamConfig {

    // Create a Sinks.Many that acts as a bridge for pushing messages to subscribers
    @Bean
    public Sinks.Many<String> eventSink() {
        return Sinks.many().multicast().onBackpressureBuffer();
    }
}