package com.test.automation.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.automation.rest_api_clients.*;
import com.test.automation.rest_api_clients.rest_assured.*;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.test.automation.helpers.Constants.*;

public class ReqResHelpers {

    private final ObjectMapper mapper = new ObjectMapper();

    public Response getListOfUsers(int page) {
        HTTPQueryParams queryParams = new HTTPQueryParams();
        queryParams.put("page", page);
        RequestSpecification spec = RequestSpecificationConstructor.constructRequestSpecification(HOST, LIST_USERS_API, queryParams);
        return HTTPMethodExecutor.get(spec);
    }

    public Response getSingleUser(int id) {
        HTTPPathParams pathParams = new HTTPPathParams();
        pathParams.put("id", id);
        RequestSpecification spec = RequestSpecificationConstructor.constructRequestSpecification(HOST, SINGLE_USER_API, pathParams);
        return HTTPMethodExecutor.get(spec);
    }

    public Response createUser(String name, String job) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("job", job);
        String requestBodyAsString;
        try {
            requestBodyAsString = mapper.writeValueAsString(bodyMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        RequestSpecification spec = RequestSpecificationConstructor.constructRequestSpecification(HOST, CREATE_USER_API, requestBodyAsString);
        return HTTPMethodExecutor.post(spec);
    }

    public Response updateUser(int id, String name, String job) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("job", job);
        String requestBodyAsString;
        try {
            requestBodyAsString = mapper.writeValueAsString(bodyMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        HTTPPathParams pathParams = new HTTPPathParams();
        pathParams.put("id", id);
        RequestSpecification spec = RequestSpecificationConstructor.constructRequestSpecification(HOST, UPDATE_USER_API, requestBodyAsString, new HTTPHeaders(), pathParams);
        return HTTPMethodExecutor.put(spec);
    }

    public Response deleteUser(int id) {
        HTTPPathParams pathParams = new HTTPPathParams();
        pathParams.put("id", id);
        RequestSpecification spec = RequestSpecificationConstructor.constructRequestSpecification(HOST, DELETE_USER_API, pathParams);
        return HTTPMethodExecutor.delete(spec);
    }

}
