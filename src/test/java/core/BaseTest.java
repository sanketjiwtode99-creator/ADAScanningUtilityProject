package core;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev";

//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//
//        RestAssured.requestSpecification = RestAssured.given()
//                .header("Authorization", EnvironmentManager.getAuthToken());
//
//        System.out.println("Base URI set: " + EnvironmentManager.getBaseUrl());
    }
}
