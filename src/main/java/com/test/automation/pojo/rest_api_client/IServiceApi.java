package com.test.automation.pojo.rest_api_client;

public interface IServiceApi {

    IService getService();
    HTTPMethod getHTTPMethod();
    String getApiPath();

    default String getUrl() {
        return getService().getBaseUrl() + getApiPath();
    }
}
