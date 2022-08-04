package com.github.firstvalery.activeMQdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
public class Sender {

    private final JmsTemplate jmsTemplate;

    public Sender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String message) {
        log.info("sending message='{}'", message);
        jmsTemplate.convertAndSend("helloworld.q", message);
    }
}