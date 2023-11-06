package org.pancakeapple.config;

import org.pancakeapple.session.SseSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SseConfig {
    @Bean
    public SseSession sseSession() {
        return new SseSession();
    }
}
