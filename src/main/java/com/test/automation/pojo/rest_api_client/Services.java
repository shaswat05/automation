package com.test.automation.pojo.rest_api_client;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Services implements IService {

    REQ_RES_SERVICE("https://reqres.in");

    private final String baseUrl;

    @Override
    public String getBaseUrl() {
        return this.baseUrl;
    }

}
