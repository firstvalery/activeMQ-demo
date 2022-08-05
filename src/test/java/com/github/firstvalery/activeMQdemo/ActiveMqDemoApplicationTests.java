package com.github.firstvalery.activeMQdemo;

import com.github.firstvalery.activeMQdemo.config.ApplicationConfiguration;
import com.github.firstvalery.activeMQdemo.service.Handler;
import com.github.firstvalery.activeMQdemo.service.Receiver;
import com.github.firstvalery.activeMQdemo.service.Sender;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.jms.JMSException;
import javax.jms.Message;

@SpringBootTest
@Slf4j
@Testcontainers
class ActiveMqDemoApplicationTests {
    @Container
    static MqTestContainer<?> activeMq = new MqTestContainer<>(DockerImageName.parse("rmohr/activemq:latest"))
            .withExposedPorts(61616);

    @TestConfiguration
    static class ActiveMqDemoApplicationTestsConfig {
        @Bean
        Handler handler() {
            TestHandler testHandler = new TestHandler();
            testHandler.setConsumer(mes -> {
            });
            return testHandler;
        }
    }

    @Autowired
    private Sender sender;


    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ApplicationConfiguration applicationConfiguration;

    @Test
    public void testReceive() throws Exception {

        String content = "Hello Spring JMS ActiveMQ!";
        sender.send(content);
        Message receive = jmsTemplate.receive(applicationConfiguration.getDestinationName());
        Assertions.assertThat(receive)
                .asInstanceOf(new InstanceOfAssertFactory<>(ActiveMQTextMessage.class, Assertions::assertThat))
                .isNotNull()
                .extracting(activeMQTextMessage -> {
                    try {
                        return activeMQTextMessage.getText();
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                }).isEqualTo(content);
    }


}
