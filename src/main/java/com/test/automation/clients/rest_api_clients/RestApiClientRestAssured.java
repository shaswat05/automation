package com.test.automation.clients.rest_api_clients;

import com.test.automation.pojo.rest_api_client.HTTPRequest;
import com.test.automation.pojo.rest_api_client.HTTPResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class RestApiClientRestAssured extends RestApiClient {

    RestApiClientRestAssured(HTTPRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    @Override
    protected HTTPResponse get() {
        RequestSpecification specs = toRequestSpecification();
        return toHTTPResponse(specs.get());
    }

    @Override
    protected HTTPResponse post() {
        RequestSpecification specs = toRequestSpecification();
        return toHTTPResponse(specs.post());
    }

    @Override
    protected HTTPResponse put() {
        RequestSpecification specs = toRequestSpecification();
        return toHTTPResponse(specs.put());
    }

    @Override
    protected HTTPResponse delete() {
        RequestSpecification specs = toRequestSpecification();
        return toHTTPResponse(specs.delete());
    }

    private RequestSpecification toRequestSpecification() {
        RequestSpecification specs = RestAssured.given();
        specs.baseUri(httpRequest.getCompleteUrl());
        if (httpRequest.getHeaders() != null)
            specs.headers(httpRequest.getHeaders());
        if (httpRequest.getBody() != null)
            specs.body(httpRequest.getBody());
        return specs;
    }

    private HTTPResponse toHTTPResponse(Response response) {
        HTTPResponse.HTTPResponseBuilder builder = HTTPResponse.builder();
        builder.statusCode(response.getStatusCode());
        builder.bodyAsString(response.print());
        HTTPResponse httpResponse = builder.build();
        return httpResponse;
    }
}
