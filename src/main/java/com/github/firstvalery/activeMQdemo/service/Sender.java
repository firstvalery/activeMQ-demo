package com.github.firstvalery.activeMQdemo.service;

import com.github.firstvalery.activeMQdemo.config.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
public class Sender {

    private final JmsTemplate jmsTemplate;
    private final ApplicationConfiguration applicationConfiguration;

    public Sender(JmsTemplate jmsTemplate, ApplicationConfiguration applicationConfiguration) {
        this.jmsTemplate = jmsTemplate;
        this.applicationConfiguration = applicationConfiguration;
    }

    public void send(String message) {
        log.info("sending message='{}'", message);
        jmsTemplate.convertAndSend(applicationConfiguration.getDestinationName(), message);
    }
}