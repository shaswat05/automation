package com.test.automation.clients.rest_api_clients;

import com.test.automation.exceptions.HTTPMethodImplNotFound;
import com.test.automation.pojo.rest_api_client.HTTPRequest;
import com.test.automation.pojo.rest_api_client.HTTPResponse;

public abstract class RestApiClient {

    protected HTTPRequest httpRequest;

    protected abstract HTTPResponse get();
    protected abstract HTTPResponse post();
    protected abstract HTTPResponse put();
    protected abstract HTTPResponse delete();

    public HTTPResponse execute() {
        return switch (this.httpRequest.getHTTPMethod()) {
            case GET -> get();
            case POST -> post();
            case PUT -> put();
            case DELETE -> delete();
            default -> throw new HTTPMethodImplNotFound(this.httpRequest.getHTTPMethod());
        };
    }
}
