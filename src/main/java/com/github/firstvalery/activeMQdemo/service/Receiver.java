package com.github.firstvalery.activeMQdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Receiver {

    private final CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "helloworld.q")
    public void receive(String message) {
        log.info("received message='{}'", message);
        latch.countDown();
    }
}