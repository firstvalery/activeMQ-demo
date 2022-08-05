package com.github.firstvalery.activeMQdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;

@Slf4j
public class Receiver {
    private final Handler handler;

    public Receiver(Handler handler) {
        this.handler = handler;
    }


    @JmsListener(destination = "${activemq.destination-name}")
    public void receive(Message<?> message) {
        log.info("received message='{}'", message);
        handler.handle(message);
    }
}