package com.test.automation.rest_api_clients;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Map;

public class CurlGenerator {

    final static boolean isMultiline = true;
    static final Logger logger = LoggerFactory.getLogger(CurlGenerator.class.getSimpleName());

    public static String print(RequestSpecification spec, HTTPMethod method) {
        String url = spec.getUrl();
        StringBuilder builder = new StringBuilder();
        builder.append(MessageFormat.format("curl -L -X {0} ''{1}'' ", method.toString(), url));

        boolean isFirst = true;
        if (spec.getHeaders() != null && !spec.getHeaders().isEmpty()) {
            builder.append(isMultiline ? "\\\n" : "");
            for (Map.Entry<String, ?> entry : spec.getHeaders().entrySet()) {
                if (!isFirst) builder.append(isMultiline ? " \\\n" : " ");
                isFirst = false;
                builder.append(MessageFormat.format("-H ''{0}: {1}''", entry.getKey(), entry.getValue()));
            }
        }
        if (spec.getRequestBody() != null) {
            builder.append(isMultiline ? " \\\n" : " ");
            builder.append(MessageFormat.format("-d ''{0}''", spec.getRequestBody()));
        }

        logger.info(builder.toString());
        return builder.toString();
    }
}
