package e2e;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateEmployeeTest {
    @Test(dependsOnGroups = "assertEmployeeRegister")
    public void UpdateEmployee() {
        System.out.println("UpdateEmployee running...");

        String randomString = RandomStringUtils.randomAlphabetic(10);

        StaticVar.email = "e2etest-update-" + randomString + "@mail.com";
        StaticVar.password = "password-update";
        StaticVar.fullName = "E2E Test Update " + randomString;
        StaticVar.department = "Human Resource";
        StaticVar.title = "HR";

        String body = "{\r\n" + //
                "    \"email\": \"" + StaticVar.email + "\",\r\n" + //
                "    \"password\": \"" + StaticVar.password + "\",\r\n" + //
                "    \"full_name\": \"" + StaticVar.fullName + "\",\r\n" + //
                "    \"department\": \"" + StaticVar.department + "\",\r\n" + //
                "    \"title\": \"" + StaticVar.title + "\"\r\n" + //
                "}";

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
        assert res.jsonPath().get("[0].email").toString().equals(StaticVar.email);
        assert res.jsonPath().get("[0].full_name").toString().equals(StaticVar.fullName);
        assert res.jsonPath().get("[0].department").toString().equals(StaticVar.department);
        assert res.jsonPath().get("[0].title").toString().equals(StaticVar.title);
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
