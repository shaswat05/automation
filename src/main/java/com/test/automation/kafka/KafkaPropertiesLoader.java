package com.test.automation.kafka;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type KafkaPropertiesLoader is a class that represents the value stored
 * in the properties file named kafka.properties.
 */
public class KafkaPropertiesLoader {


    /**
     * Gets a Properties object that contains the keys and values defined
     * in the file src/main/resources/kafka.properties
     *
     * @return a {@link java.util.Properties} object
     * @throws Exception Thrown if the file kafka.properties is not available
     *                   in the directory src/main/resources
     *
     *  This method is deprecated.
     *  Please use {@link com.test.automation.utils.PropertiesLoader}
     */
    @Deprecated
    public static Properties getProperties() throws Exception {
        Properties props = null;
        //try to load the file kafka.properties
        try (InputStream input = Producer.class.getClassLoader().getResourceAsStream("kafka.properties")) {
            props = new Properties();
            if (input == null) {
                throw new Exception("File 'kafka.properties' not found.");
            }
            //load a properties file from class path, inside static method
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
