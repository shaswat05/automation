package com.test.automation.pojo.rest_api_client;

import com.test.automation.utils.CurlGenerator;
import com.test.automation.utils.JsonUtils;
import lombok.Builder;
import lombok.Getter;

import java.text.MessageFormat;
import java.util.Map;

@Getter
@Builder
public class HTTPRequest {

    IServiceApi serviceApi;
    String body;
    HTTPHeaders headers;
    HTTPPathParams pathParams;
    HTTPQueryParams queryParams;

    public HTTPMethod getHTTPMethod() {
        return this.serviceApi.getHTTPMethod();
    }

    public String getCompleteUrl() {
        String url = this.serviceApi.getUrl();
        if (pathParams != null && !pathParams.isEmpty()) {
            for (Map.Entry<String, String> pathParam : pathParams.entrySet()) {
                url = url.replace("{" + pathParam.getKey() + "}", pathParam.getValue());
            }
        }
        if (queryParams != null && !queryParams.isEmpty()) {
            StringBuilder builder = new StringBuilder(url);
            boolean isFirstElement = true;
            for (Map.Entry<String, String> queryParam : queryParams.entrySet()) {
                if (isFirstElement) {
                    builder.append('?');
                    isFirstElement = false;
                } else builder.append('&');
                builder.append(MessageFormat.format("{0}={1}", queryParam.getKey(), queryParam.getValue()));
            }
            return builder.toString();
        }
        return url;
    }

    public String getCurl(boolean isMultiline) {
        return CurlGenerator.getCurl(this, isMultiline);
    }

}
