package com.github.firstvalery.activeMQdemo.config;

import com.github.firstvalery.activeMQdemo.service.Handler;
import com.github.firstvalery.activeMQdemo.service.Receiver;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class ReceiverConfig {


    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory(ApplicationConfiguration applicationConfiguration) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(applicationConfiguration.getBrokerUrl());

        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ApplicationConfiguration applicationConfiguration) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(receiverActiveMQConnectionFactory(applicationConfiguration));

        return factory;
    }

    @Bean
    @Profile("!test")
    public Receiver receiver(Handler handler) {
        return new Receiver(handler);
    }
}