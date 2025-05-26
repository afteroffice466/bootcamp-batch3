package apiengine;


import com.demo.constants.Constants;

import helper.ConfigManager;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.an.E;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {

    private RequestSpecification requestSpecification;

    public Endpoints() {
        String baseUrl = ConfigManager.getBaseUrl();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType("application/json")
                .build();
    }

    public RequestSpecification getRequestSpec() {
        return requestSpecification;
    }


    public Response registerEmployee(String bodyRegister){
        // Send POST request to employee endpoint
        Response responseCreateEmployee = RestAssured.given()
                .header("Content-Type", "application/json")
                .spec(requestSpecification)
                .body(bodyRegister)
                .log().all()
                .when()
                .post("/webhook/employee/add");
        return responseCreateEmployee;
    }

    public Response deletedEmployee(String token){
        // Create Delete Employee request
        // Send DELETE request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .spec(requestSpecification)
                .when()
                .delete("/webhook/employee/delete");
        return response;
    }

    public Response updateEmployee(String token, String bodyUpdate){
        // Send PUT request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(bodyUpdate)
                .log().all()
                .spec(requestSpecification)
                .when()
                .put("/webhook/employee/update");
        return response;
    }

    public Response getEmployee(String token){
        // Create Get Employee request
        // Send GET request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .log().all()
                .spec(requestSpecification)
                .when()
                .get("/webhook/employee/get");
        return response;
    }

    public Response getAllEmployee(){
        // Create Get All Employee request
        // Send GET request to employee endpoint
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .spec(requestSpecification)
                .when()
                .get("/webhook/employee/get_all");
        return response;
    }

    public Response loginEmployee(String bodyLogin){
        // Send POST request to employee endpoint
        Response responseLogin = RestAssured.given()
                .header("Content-Type", "application/json")
                .spec(requestSpecification)
                .body(bodyLogin)
                .log().all()
                .when()
                .post("/webhook/employee/login");
        return responseLogin;
    }
}



