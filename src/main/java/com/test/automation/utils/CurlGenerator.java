package com.test.automation.utils;


import com.test.automation.pojo.rest_api_client.HTTPRequest;

import java.text.MessageFormat;
import java.util.Map;

public class CurlGenerator {

    public static String getCurl(HTTPRequest httpRequest, boolean isMultiline) {
        StringBuilder builder = new StringBuilder();
        builder.append(MessageFormat.format("curl -L -X {0} ''{1}''", httpRequest.getHTTPMethod(), httpRequest.getCompleteUrl()));
        if (httpRequest.getHeaders() != null) {
            for (Map.Entry<String, String> header : httpRequest.getHeaders().entrySet()) {
                builder.append(' ');
                if (isMultiline) builder.append("\\\n");
                builder.append(MessageFormat.format("-H ''{0}: {1}''", header.getKey(), header.getValue()));
            }
        }
        if (httpRequest.getBody() != null) {
            builder.append(' ');
            if (isMultiline) builder.append("\\\n");
            builder.append(MessageFormat.format("-d ''{0}''", httpRequest.getBody()));
        }
        return builder.toString();
    }
}
