package com.test.automation.test;

import com.test.automation.helpers.ReqResHelpers;
import com.test.automation.pojo.rest_api_client.HTTPResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredTest {

    final ReqResHelpers helpers = new ReqResHelpers();

    @Test
    public void listUsersTest() {
        int page = 2;
        HTTPResponse response = helpers.getListOfUsers(page);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch.");
    }

    @Test
    public void getSingleUserTest() {
        int id = 2;
        HTTPResponse response = helpers.getSingleUser(id);
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch.");
    }

    @Test
    public void createUserTest() {
        HTTPResponse response = helpers.createUser("Test User", "Rest Assured");
        Assert.assertEquals(response.getStatusCode(), 201, "Status code mismatch.");
    }

    @Test
    public void updateUserTest() {
        HTTPResponse response = helpers.updateUser(2, "Test User 2", "Rest Assured 2");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch.");
    }

    @Test
    public void deleteUserTest() {
        int id = 2;
        HTTPResponse response = helpers.deleteUser(id);
        Assert.assertEquals(response.getStatusCode(), 204, "Status code mismatch.");
    }

}
