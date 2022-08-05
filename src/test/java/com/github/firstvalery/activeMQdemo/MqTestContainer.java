package com.github.firstvalery.activeMQdemo;

import lombok.NonNull;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class MqTestContainer<SELF extends MqTestContainer<SELF>> extends GenericContainer<SELF> {
    public static final String IMAGE = "activemq";
    public static final String DEFAULT_TAG = "latest";
    private static final DockerImageName DEFAULT_IMAGE_NAME = DockerImageName.parse(IMAGE);
    public static final Integer LISTENER_PORT = 61616;

    public MqTestContainer(@NonNull DockerImageName dockerImageName) {
        super(dockerImageName);
        this.addExposedPort(LISTENER_PORT);
    }

    @Deprecated
    public MqTestContainer() {
        this(DEFAULT_IMAGE_NAME.withTag(DEFAULT_TAG));
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("activemq.broker-url", "tcp://" + getHost() + ":" + getFirstMappedPort());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }

}
