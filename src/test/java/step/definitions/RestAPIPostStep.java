package step.definitions;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.net.URI;
import java.net.URISyntaxException;

public class RestAPIPostStep {

    private final String BASE_URL = "";
    private Response response;
    private Scenario scenario;
    private String statusCode;

    @Before
    public void before(Scenario scenario)
    {
        this.scenario = scenario;
    }

    @Given("Get call to {string}")
    public void getCallToUrl(String url) throws URISyntaxException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.when().get(new URI(url));
    }

    @Then("Response is {string}")
    public void responseStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
        int actualResponseCode = response.getStatusCode();
        Assert.assertEquals(statusCode, actualResponseCode);
    }





}
