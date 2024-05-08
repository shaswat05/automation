package com.test.automation.rest_api_clients;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseFormatter {

    static Logger logger = LoggerFactory.getLogger(ResponseFormatter.class.getSimpleName());
    public static String print(Response response) {
        String resAsStr = response.getStatusCode() + ": " + response.body().asString();
        logger.info(resAsStr);
        return resAsStr;
    }
}
