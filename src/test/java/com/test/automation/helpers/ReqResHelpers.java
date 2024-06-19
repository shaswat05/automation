package com.test.automation.helpers;

import com.test.automation.clients.rest_api_clients.RestApiClientFactory;
import com.test.automation.pojo.rest_api_client.*;
import com.test.automation.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class ReqResHelpers {

    public HTTPResponse getListOfUsers(Integer page) {
        HTTPQueryParams queryParams = new HTTPQueryParams();
        queryParams.put("page", page.toString());
        HTTPRequest.HTTPRequestBuilder builder = HTTPRequest.builder();
        builder.queryParams(queryParams);
        builder.serviceApi(ReqResServiceApis.LIST_USERS);
        return RestApiClientFactory.getRestApiClient(builder.build()).execute();
    }

    public HTTPResponse getSingleUser(Integer id) {
        HTTPPathParams pathParams = new HTTPPathParams();
        pathParams.put("id", id.toString());
        HTTPRequest.HTTPRequestBuilder builder = HTTPRequest.builder();
        builder.pathParams(pathParams);
        builder.serviceApi(ReqResServiceApis.SINGLE_USER);
        return RestApiClientFactory.getRestApiClient(builder.build()).execute();
    }

    public HTTPResponse createUser(String name, String job) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("job", job);
        HTTPRequest.HTTPRequestBuilder builder = HTTPRequest.builder();
        builder.body(JsonUtils.mapToJson(bodyMap));
        builder.serviceApi(ReqResServiceApis.CREATE_USER);
        return RestApiClientFactory.getRestApiClient(builder.build()).execute();
    }

    public HTTPResponse updateUser(Integer id, String name, String job) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("job", job);
        HTTPPathParams pathParams = new HTTPPathParams();
        pathParams.put("id", id.toString());
        HTTPRequest.HTTPRequestBuilder builder = HTTPRequest.builder();
        builder.body(JsonUtils.mapToJson(bodyMap));
        builder.serviceApi(ReqResServiceApis.UPDATE_USER);
        builder.pathParams(pathParams);
        return RestApiClientFactory.getRestApiClient(builder.build()).execute();
    }

    public HTTPResponse deleteUser(Integer id) {
        HTTPPathParams pathParams = new HTTPPathParams();
        pathParams.put("id", id.toString());
        HTTPRequest.HTTPRequestBuilder builder = HTTPRequest.builder();
        builder.serviceApi(ReqResServiceApis.DELETE_USER);
        builder.pathParams(pathParams);
        return RestApiClientFactory.getRestApiClient(builder.build()).execute();
    }

}
