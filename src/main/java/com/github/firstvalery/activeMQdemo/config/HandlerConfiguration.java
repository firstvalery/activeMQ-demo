package com.github.firstvalery.activeMQdemo.config;

import com.github.firstvalery.activeMQdemo.service.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@Profile("!test")
public class HandlerConfiguration {
    @Bean
    public Handler handler() {
        return (message) -> {
            log.info("this is a simple handler realisation");
        };
    }
}
