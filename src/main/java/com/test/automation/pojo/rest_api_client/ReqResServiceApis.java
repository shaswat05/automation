package com.test.automation.pojo.rest_api_client;

import lombok.AllArgsConstructor;

import static com.test.automation.pojo.rest_api_client.Services.REQ_RES_SERVICE;

@AllArgsConstructor
public enum ReqResServiceApis implements IServiceApi {
    LIST_USERS(HTTPMethod.GET, "/api/users"),
    SINGLE_USER(HTTPMethod.GET, "/api/users/{id}"),
    SINGLE_USER_NOT_FOUND(HTTPMethod.GET, "/api/users/{id}"),
    CREATE_USER(HTTPMethod.POST, "/api/users"),
    UPDATE_USER(HTTPMethod.PUT, "/api/users/{id}"),
    DELETE_USER(HTTPMethod.DELETE, "/api/users/{id}");

    private final HTTPMethod httpMethod;
    private final String apiPath;

    @Override
    public IService getService() {
        return REQ_RES_SERVICE;
    }

    @Override
    public HTTPMethod getHTTPMethod() {
        return this.httpMethod;
    }

    @Override
    public String getApiPath() {
        return this.apiPath;
    }
}
