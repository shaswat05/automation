package com.test.automation.utils;

import com.test.automation.exceptions.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    private static final Map<String, Properties> propsMap = new HashMap<>();

    /**
     * Gets a Properties object that contains the keys and values defined
     * in the file src/main/resources/{{fileName}}
     * And stores the Properties object in propsMap.
     *
     * @return a {@link java.util.Properties} object
     */
    public static Properties getProperties(String fileName) {
        if (propsMap.containsKey(fileName)) return propsMap.get(fileName);
        Properties props = null;
        //try to load the file {{fileName}}
        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            props = new Properties();
            if (input == null) throw new FileNotFoundException("File '" + fileName + "' not found.");
            //load a properties file from class path, inside static method
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        propsMap.put(fileName, props);
        return props;
    }

}
