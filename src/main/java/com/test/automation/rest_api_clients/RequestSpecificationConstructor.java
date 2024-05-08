package com.test.automation.rest_api_clients;

public class RequestSpecificationConstructor {

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, String body, HTTPHeaders headers, HTTPQueryParams queryParams, HTTPPathParams pathParams) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath, body, headers, pathParams);
        spec.setQueryParams(queryParams);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, HTTPQueryParams queryParams) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath);
        spec.setQueryParams(queryParams);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, HTTPPathParams pathParams) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath);
        spec.setPathParams(pathParams);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, String body, HTTPHeaders headers, HTTPQueryParams queryParams) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath, body, headers);
        spec.setQueryParams(queryParams);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, String body, HTTPHeaders headers, HTTPPathParams pathParams) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath, body, headers);
        spec.setPathParams(pathParams);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, String body, HTTPHeaders headers) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath, body);
        spec.setHeaders(headers);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath, String body) {
        RequestSpecification spec = constructRequestSpecification(host, apiPath);
        spec.setRequestBody(body);
        return spec;
    }

    public static RequestSpecification constructRequestSpecification(String host, String apiPath) {
        return RequestSpecification.builder().baseUrl(host).apiPath(apiPath).build();
    }


}
