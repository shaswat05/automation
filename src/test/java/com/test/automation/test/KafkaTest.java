package com.test.automation.test;

import com.test.automation.kafka.KafkaMessageHandlerImpl;
import com.test.automation.kafka.Consumer;
import com.test.automation.kafka.Producer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.testng.annotations.Test;

public class KafkaTest {

    private final Producer producer = new Producer();
    private final Consumer consumer = new Consumer();

    private final String KAFKA_TOPIC = "kafka-topic";
    private final int RECORD_COUNT = 5;

    @Test
    public void testKafka() throws Exception {
        producer.run(KAFKA_TOPIC, 5);
        producer.shutdown();
        ConsumerRecords<String, String> records = consumer.run(KAFKA_TOPIC, new KafkaMessageHandlerImpl(), 5);
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.key() + ": " + record.value());
        }
    }
}
