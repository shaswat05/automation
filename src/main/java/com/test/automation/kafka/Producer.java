package com.test.automation.kafka;

import com.test.automation.utils.PropertiesLoader;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type SimpleProducer is a wrapper class for {@link org.apache.kafka.clients.producer.KafkaProducer}.
 * The object publishes methods that send messages that have random string
 * content onto the Kafka broker defined in {@link /src/resources/kafka.properties}
 */
public class Producer extends AbstractSimpleKafka {

    private KafkaProducer<String, String> kafkaProducer;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    @Getter
    @Setter
    private String topicName;

    /**
     * Instantiates a new Abstract class, SimpleKafka.
     * <p>
     * This abstract class's constructor provides graceful
     * shutdown behavior for Kafka producers and consumers
     *
     * @throws Exception the exception
     */
    public Producer() {
    }

    @Override
    public void shutdown() throws Exception {
        closed.set(true);
        getKafkaProducer().close();
    }

    /**
     * The runAlways method sends a message to a topic.
     *
     * @param topicName the name of topic to access
     * @param callback  the callback function that processes messages retrieved
     *                  from Kafka
     * @throws Exception the Exception that will get thrown upon an error
     */
    @Override
    public void runAlways(String topicName, KafkaMessageHandler callback) throws Exception {
        while (true) {
            String key = UUID.randomUUID().toString();
            String message = UUID.randomUUID().toString();
            send(topicName, key, message);
            Thread.sleep(100);
        }
    }

    /**
     * This method sends a limited number of messages
     * with random string data to the Kafka broker.
     * <p>
     * This method is provided for testing purposes.
     *
     * @param topicName        the name of the topic to where messages
     *                         will be sent
     * @param numberOfMessages the number of messages to send
     * @throws Exception the exception that gets raised upon error
     */
    public void run(String topic, int numberOfMessages) throws Exception {
        int i = 0;
        while (i <= numberOfMessages) {
            String key = UUID.randomUUID().toString();
            String message = "Kafka message";
            this.send(topic, key, message);
            i++;
            Thread.sleep(100);
        }
        this.shutdown();
    }

    /**
     * Does the work of sending a message to
     * a Kafka broker. The method uses the name of
     * the topic that was declared in this class's
     * constructor.
     *
     * @param topicName the name of the topic to where the message                   will be sent
     * @param key       the key value for the message
     * @param message   the content of the message
     * @throws Exception the exception that gets thrown upon error
     */
    protected void send(String topicName, String key, String message) throws Exception {

        String source = Producer.class.getName();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, message);
        getKafkaProducer().send(producerRecord);
    }

    private KafkaProducer<String, String> getKafkaProducer() throws Exception {
        if (this.kafkaProducer == null) {
            Properties props = PropertiesLoader.getProperties("kafka.properties");
            this.kafkaProducer = new KafkaProducer<>(props);
        }
        return this.kafkaProducer;
    }

}
