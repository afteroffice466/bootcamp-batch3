package stepdefenitions;

import java.net.URL;

import org.testng.Assert;

import com.demo.model.ResponseEmployee;

import apiengine.Endpoints;
import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class EmployeeDefenitions extends Endpoints {
    public static String baseUrl;
    public static Response response;
    public static String token;
    private String createdUserId;
    private final TestContext context;

    // PicoContainer will inject the same TestContext instance into all step classes that declare it
    public EmployeeDefenitions(TestContext context) {
        this.context = context;
    }

    @Given("The base url in this feature is {string}")
    public void set_base_url(String baseUrl) {
        EmployeeDefenitions.baseUrl = baseUrl;
    }

    @When("Send employee to register with body:")
    public void send_request_http(String body) {
        response = registerEmployee(body);
        context.setResponse(response);
        if (response.statusCode() == 200) {
            createdUserId = response.jsonPath().getString("[0].id");
        } 
    }



    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert context.getResponse().statusCode() == statusCode : "Error, due to actual status code is " + context.getResponse().statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        context.getResponse().then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        context.set(token, context.getResponse().jsonPath().getString("[0].token"));
    }

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert context.get(token, String.class) != null : "Token null";
    }

    @And("Full name in the response must be {string}")
    public void assert_full_name(String fullName) throws Exception {
        ResponseEmployee[] resAdd = context.getResponse().as(ResponseEmployee[].class);
        Assert.assertEquals(resAdd[0].fullName, fullName,
                "Expected full name " + fullName + " but got " + resAdd[0].fullName);
    }
    
    @And("Department in the response must be {string}")
    public void assert_department(String department) throws Exception {
        ResponseEmployee[] resAdd = context.getResponse().as(ResponseEmployee[].class);
        Assert.assertEquals(resAdd[0].department, department,
                "Expected department " + department + " but got " + resAdd[0].department);
    }

    @And("Title in the response must be {string}")
    public void assert_title(String title) throws Exception {
       ResponseEmployee[] resAdd = context.getResponse().as(ResponseEmployee[].class);
        Assert.assertEquals(resAdd[0].title, title,
                "Expected title " + title + " but got " + resAdd[0].title);
    }

    @When("Send the employee to login with body:")
    public void Send_the_employee_to_login_with_body(String body) {
        // Write code here that turns the phrase above into concrete actions
        response = loginEmployee(body);
        context.setResponse(response);
    }

    @After
    public void tearDown() {
        // Clean up after tests
        if (createdUserId != null) {
            // Delete the created user if exists
            response = deletedEmployee(EmployeeDefenitions.token);
            System.out.println("Deleted User Response: " + response.asPrettyString());
        }
    }
}