package com.github.firstvalery.activeMQdemo.service;


import org.springframework.messaging.Message;

@FunctionalInterface
public interface Handler {
    void handle(Message<?> message);
}
