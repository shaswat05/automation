package com.test.automation.pojo.rest_api_client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.automation.utils.JsonUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HTTPResponse {

    @JsonProperty("status_code")
    int statusCode;
    @JsonProperty("body")
    String bodyAsString;

    public <T> T path(String path) {
        return JsonUtils.path(this.bodyAsString, path);
    }

    public <T> T getBody(Class<T> clazz) {
        return JsonUtils.jsonToPojo(this.bodyAsString, clazz);
    }

    @Override
    public String toString() {
        return JsonUtils.pojoToJsonString(this);
    }
}
