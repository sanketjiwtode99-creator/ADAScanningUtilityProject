package core;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiClient extends BaseTest{

    public static Response getRequest(String endpoint)
    {
       return RestAssured.given().when().get(endpoint);
    }

    public static Response getRequest(String endpoint, Map<String, String> params)
    {
        return RestAssured.given().queryParams(params).when().get(endpoint);
    }

    public static Response postRequest(String endpoint, Object body)
    {
        return RestAssured.given().headers("Content-Type", "application/json")
                .body(body).when().post(endpoint);
    }
    public static Response putRequest(String endpoint, Object body)
    {
        return RestAssured.given().headers("Content-Type", "application/json")
                .body(body).when().put(endpoint);
    }

    public static void createOrderPayload()
    {

    }

    /*
    {
   "name": "Apple MacBook Pro 16",
   "data": {
      "year": 2019,
      "price": 1849.99,
      "CPU model": "Intel Core i9",
      "Hard disk size": "1 TB"
   }
}
based on above request body payload method is created.
    */

    public static Map<String, Object> createProductPayload(String name, int year, double price, String cpuModel, String hardDiskSize) {
        Map<String, Object> body = new HashMap<>(); // 1st hashmap for array inside body
        body.put("name", name);

        Map<String, Object> data = new HashMap<>(); // 2nd hashmap for nested array inside data
        data.put("year", year);
        data.put("price", price);
        data.put("CPU model", cpuModel);
        data.put("Hard disk size", hardDiskSize);

        //putting data in body
        body.put("data", data);

        return body;
    }


    @Test
    public static void verifyGetRequestResponse()
    {
        Response Response = getRequest("/objects");
        Assert.assertEquals(Response.statusCode(), 200);
        int responseSize = Response.jsonPath().getList("$").size();

        Assert.assertTrue(responseSize > 5);

        boolean result = Response.jsonPath().getString("[0].name").contains("google");

        Assert.assertTrue(result);
    }

    @Test
    public void verifyPost()
    {

        Response response = postRequest("/objects", createProductPayload("sanke6t", 2012, 3.45, "CPUModel", "20GB"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        double price = response.jsonPath().getDouble("data.price");
        System.out.println(price);
        Assert.assertTrue(price > 0);
    }

}
