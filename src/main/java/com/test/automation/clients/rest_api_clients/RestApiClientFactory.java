package com.test.automation.clients.rest_api_clients;

import com.test.automation.pojo.rest_api_client.HTTPRequest;

public class RestApiClientFactory {

    public static RestApiClient getRestApiClient(HTTPRequest httpRequest) {
        return new RestApiClientRestAssured(httpRequest);
    }

}
