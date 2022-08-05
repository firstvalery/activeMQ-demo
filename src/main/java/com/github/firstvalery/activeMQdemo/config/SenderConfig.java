package com.github.firstvalery.activeMQdemo.config;

import com.github.firstvalery.activeMQdemo.service.Sender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {
    @Bean
    public ActiveMQConnectionFactory senderActiveMQConnectionFactory(ApplicationConfiguration applicationConfiguration) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(applicationConfiguration.getBrokerUrl());

        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(ApplicationConfiguration applicationConfiguration) {
        return new CachingConnectionFactory(senderActiveMQConnectionFactory(applicationConfiguration));
    }

    @Bean
    public JmsTemplate jmsTemplate(ApplicationConfiguration applicationConfiguration) {
        return new JmsTemplate(cachingConnectionFactory(applicationConfiguration));
    }

    @Bean
    public Sender sender(JmsTemplate jmsTemplate, ApplicationConfiguration applicationConfiguration) {
        return new Sender(jmsTemplate, applicationConfiguration);
    }
}