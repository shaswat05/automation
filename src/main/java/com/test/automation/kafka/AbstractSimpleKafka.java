package com.test.automation.kafka;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AbstractSimpleKafka {

    public AbstractSimpleKafka() {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private final Logger log = LogManager.getLogger(AbstractSimpleKafka.class.getName());

    public abstract void shutdown() throws Exception;

    public abstract void runAlways(String topicName, KafkaMessageHandler callback) throws Exception;
}
