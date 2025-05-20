package e2e;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import com.demo.testng.program.model.response_model.AddEmployeeResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateEmployeeTest {
    @Test(dependsOnGroups = "assertEmployeeRegister")
    public void UpdateEmployee() throws Exception {
        System.out.println("UpdateEmployee running...");

        String randomString = RandomStringUtils.randomAlphabetic(10);

        StaticVar.employee.setEmail("e2etest-update-" + randomString + "@mail.com");
        StaticVar.employee.setPassword("password-update");
        StaticVar.employee.setFullName("E2E Test Update " + randomString);
        StaticVar.employee.setDepartment("Human Resource");
        StaticVar.employee.setTitle("HR");

        // String body = "{\r\n" + //
        // " \"email\": \"" + StaticVar.email + "\",\r\n" + //
        // " \"password\": \"" + StaticVar.password + "\",\r\n" + //
        // " \"full_name\": \"" + StaticVar.fullName + "\",\r\n" + //
        // " \"department\": \"" + StaticVar.department + "\",\r\n" + //
        // " \"title\": \"" + StaticVar.title + "\"\r\n" + //
        // "}";

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(StaticVar.employee);

        // Add employee data
        Response res = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + StaticVar.token)
                .body(body)
                .log()
                .all()
                .when()
                .put(StaticVar.BASE_URL + "/employee/update");

        System.out.println(res.asPrettyString());

        assert res.getStatusCode() == 200 : "Status code add employee must be 200";

        List<AddEmployeeResponse> addEmployeeResponse = objectMapper.readValue(res.body().asString(),
                new TypeReference<List<AddEmployeeResponse>>() {
                });

        assert addEmployeeResponse.size() > 0 : "Data is empty";
        assert addEmployeeResponse.get(0).getEmail().equals(StaticVar.employee.getEmail()) : "email not expected";
        assert addEmployeeResponse.get(0).getPasswordHash() != null : "password hash is null";

        // assert res.jsonPath().get("[0].email").toString().equals(StaticVar.email);
        // assert
        // res.jsonPath().get("[0].full_name").toString().equals(StaticVar.fullName);
        // assert
        // res.jsonPath().get("[0].department").toString().equals(StaticVar.department);
        // assert res.jsonPath().get("[0].title").toString().equals(StaticVar.title);
    }

    @Test(dependsOnMethods = "UpdateEmployee", groups = "assertEmployeeUpdate")
    public void searchEmployee() {
        RegisterEmployeeTest registerEmployeeTest = new RegisterEmployeeTest();
        registerEmployeeTest.searchEmployee();
    }

    @Test(dependsOnMethods = "UpdateEmployee", groups = "assertEmployeeUpdate")
    public void getAllEmployee() {
        RegisterEmployeeTest registerEmployeeTest = new RegisterEmployeeTest();
        registerEmployeeTest.getAllEmployee();
    }
}
