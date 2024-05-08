package com.test.automation.rest_api_clients.rest_assured;

import com.test.automation.rest_api_clients.CurlGenerator;
import com.test.automation.rest_api_clients.HTTPMethod;
import com.test.automation.rest_api_clients.RequestSpecification;
import com.test.automation.rest_api_clients.ResponseFormatter;
import io.restassured.response.Response;

public class HTTPMethodExecutor {

    public static Response get(RequestSpecification spec) {
        CurlGenerator.print(spec, HTTPMethod.GET);
        Response response =  spec.toRestAssured().get();
        ResponseFormatter.print(response);
        return response;
    }

    public static Response post(RequestSpecification spec) {
        CurlGenerator.print(spec, HTTPMethod.POST);
        Response response =  spec.toRestAssured().post();
        ResponseFormatter.print(response);
        return response;
    }

    public static Response put(RequestSpecification spec) {
        CurlGenerator.print(spec, HTTPMethod.PUT);
        Response response =  spec.toRestAssured().put();
        ResponseFormatter.print(response);
        return response;
    }

    public static Response delete(RequestSpecification spec) {
        CurlGenerator.print(spec, HTTPMethod.DELETE);
        Response response =  spec.toRestAssured().delete();
        ResponseFormatter.print(response);
        return response;
    }

}
