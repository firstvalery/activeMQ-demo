package com.github.firstvalery.activeMQdemo.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "activemq")
@Configuration
@NoArgsConstructor
@Setter
@Getter
public class ApplicationConfiguration {
    private String destinationName;
    private String brokerUrl;
}
