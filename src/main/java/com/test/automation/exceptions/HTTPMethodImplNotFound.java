package com.test.automation.exceptions;

import com.test.automation.pojo.rest_api_client.HTTPMethod;

public class HTTPMethodImplNotFound extends RuntimeException {
    public HTTPMethodImplNotFound(HTTPMethod httpMethod) {
        super("Rest api client implementation not found for HTTPMethod " + httpMethod);
    }
}
