package com.test.automation.rest_api_clients;

public class BaseUriConstructor {

    public static String getBaseUri(String host, String apiPath) {
        if (host.charAt(host.length() - 1) == '/')
            host = host.substring(0, host.length() - 1);
        if (apiPath.charAt(0) != '/')
            apiPath = '/' + apiPath;
        return host + apiPath;
    }

}
