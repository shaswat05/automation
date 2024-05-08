package com.test.automation.rest_api_clients;

import com.test.automation.rest_api_clients.BaseUriConstructor;
import com.test.automation.rest_api_clients.HTTPHeaders;
import com.test.automation.rest_api_clients.rest_assured.RequestSpecificationConverter;
import io.restassured.RestAssured;
import lombok.*;

import java.text.MessageFormat;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestSpecification {

    private String baseUrl;
    private String apiPath;
    private String requestBody;
    private HTTPHeaders headers;
    private HTTPPathParams pathParams;
    private HTTPQueryParams queryParams;

    public String getUrl() {
        String url = BaseUriConstructor.getBaseUri(baseUrl, apiPath);
        if (pathParams != null && !pathParams.isEmpty()) {
            for (Map.Entry<String, Object> entry : pathParams.entrySet())
                url = url.replace("{" + entry.getKey() + "}", entry.getValue().toString());
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            StringBuilder tempUrl = new StringBuilder();
            tempUrl.append(url);
            boolean isFirst = true;
            for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
                tempUrl.append(isFirst ? "?" : "&");
                isFirst = false;
                tempUrl.append(MessageFormat.format("{0}={1}", entry.getKey(), entry.getValue()));
            }
            url = tempUrl.toString();
        }

        return url;
    }

    public io.restassured.specification.RequestSpecification toRestAssured() {
        return RequestSpecificationConverter.toRestAssured(this);
    }

}
