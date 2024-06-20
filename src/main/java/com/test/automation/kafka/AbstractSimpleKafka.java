package com.test.automation.kafka;

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

    public abstract void shutdown() throws Exception;

    public abstract void runAlways(String topicName, KafkaMessageHandler callback) throws Exception;
}
