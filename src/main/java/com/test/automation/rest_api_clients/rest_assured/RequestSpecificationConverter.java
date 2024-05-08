package com.test.automation.rest_api_clients.rest_assured;

import com.test.automation.rest_api_clients.RequestSpecification;
import io.restassured.RestAssured;

public class RequestSpecificationConverter {

    public static io.restassured.specification.RequestSpecification toRestAssured(RequestSpecification givenSpec) {
        io.restassured.specification.RequestSpecification spec = RestAssured.given();
        spec.baseUri(givenSpec.getUrl());
        if (givenSpec.getHeaders() != null) spec.headers(givenSpec.getHeaders());
        if (givenSpec.getPathParams() != null) spec.params(givenSpec.getPathParams());
        if (givenSpec.getQueryParams() != null) spec.queryParams(givenSpec.getQueryParams());
        if (givenSpec.getRequestBody() != null) spec.body(givenSpec.getRequestBody());
        return spec;
    }

}
