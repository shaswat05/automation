package com.test.automation.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/**
 * An implementation of the interface {@link com.demo.kafka.KafkaMessageHandler}.
 * <p>
 * The class implements the processMessage() method. Typically, this class is used
 * to supply callback behavior for this project's producers and consumers.
 */
public class KafkaMessageHandlerImpl implements KafkaMessageHandler {

    static Logger log = Logger.getLogger(KafkaMessageHandlerImpl.class.getName());

    /**
     * The method that defines the message processing behavior
     *
     * @param topicName
     * @param message   The message that was consumed
     * @throws Exception Thrown if an exception occurs
     */

    @Override
    public void processMessage(String topicName, ConsumerRecord<String, String> message) throws Exception {
        String position = message.partition() + "-" + message.offset();
        String source = KafkaMessageHandlerImpl.class.getName();
        System.out.println("Shaswat Message: " + message.value());
        JSONObject obj = MessageHelper.getMessageLogEntryJSON(source, topicName, message.key(), message.value());
        log.info(obj.toJSONString());
    }
}
