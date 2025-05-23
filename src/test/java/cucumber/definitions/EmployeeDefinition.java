package cucumber.definitions;
import java.util.List;

import com.demo.testng.program.model.response_model.AddEmployeeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class EmployeeDefinition {
    public static String baseUrl;
    public static Response response;
    public static String token;

    @Given("The base url in this feature is {string}")
    public void set_base_url(String baseUrl) {
        EmployeeDefinition.baseUrl = baseUrl;
    }

    @When("Send a http {string} request to {string} with body:")
    public void send_request_http(String method, String url, String body) {
        // Add employee data
        response = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", EmployeeDefinition.token != null ? "Bearer " + EmployeeDefinition.token : "")
                .body(body)
                .when()
                .request(method, EmployeeDefinition.baseUrl + url);
    }

    @Then("The response status must be {int}")
    public void send_request_http(int statusCode) {
        assert response.statusCode() == statusCode : "Error, due to actual status code is " + response.statusCode();
    }

    @And("The response schema should be match with schema {string}")
    public void schema_validation(String schemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }

    @And("Save the token from the response to local storage")
    public void save_the_token() {
        EmployeeDefinition.token = response.jsonPath().getString("[0].token");
    }

    @Given("Make sure token in local storage not empty")
    public void assert_token_in_variable() {
        assert EmployeeDefinition.token != null : "Token null";
    }

    @And("Full name in the response must be {string}")
    public void assert_full_name(String fullName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddEmployeeResponse> addEmployeeResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddEmployeeResponse>>() {});
        assert addEmployeeResponse.get(0).getFullName().equals(fullName) : "fullname not expected";
    }
    
    @And("Department in the response must be {string}")
    public void assert_department(String department) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddEmployeeResponse> addEmployeeResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddEmployeeResponse>>() {});
        assert addEmployeeResponse.get(0).getDepartment().equals(department) : "fullname not expected";
    }

    @And("Title in the response must be {string}")
    public void assert_title(String title) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AddEmployeeResponse> addEmployeeResponse = objectMapper.readValue(response.body().asString(), new TypeReference<List<AddEmployeeResponse>>() {});
        assert addEmployeeResponse.get(0).getTitle().equals(title) : "fullname not expected";
    }
}
