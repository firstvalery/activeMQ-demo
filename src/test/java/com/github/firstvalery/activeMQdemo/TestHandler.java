package com.github.firstvalery.activeMQdemo;

import com.github.firstvalery.activeMQdemo.service.Handler;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
public class TestHandler implements Handler {
    @Setter
    private Consumer<Message<?>> consumer;

    @Override
    public void handle(Message<?> message) {
        log.info("This is testHandler");
        consumer.accept(message);
    }
}
