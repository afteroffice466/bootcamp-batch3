package restassured;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class NewEmployeeTest {
    private final String EMAIL = "test10@test.com";
    private final String PASSWORD = "test4";
    private String token;

    @Test
    public void addEmployeeTest() {
        // First running test because not depends on other test
        System.out.println("addEmployeeTest starting....");
        String body = "{\r\n" + //
                "    \"email\": \"" + EMAIL + "\",\r\n" + //
                "    \"password\": \"" + PASSWORD + "\",\r\n" + //
                "    \"full_name\": \"test name\",\r\n" + //
                "    \"department\": \"IT\",\r\n" + //
                "    \"title\": \"QA\"\r\n" + //
                "}";

        // Add employee data
        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/add");

        System.out.println(res.prettyPrint());

        // Save to global variable
        String email = res.jsonPath().getString("[0].email");
        String password = res.jsonPath().getString("[0].password_hash");

        // Assert
        Assert.assertEquals(email, EMAIL);
        Assert.assertNotNull(password);
    }

    @Test(dependsOnMethods = {"addEmployeeTest"})
    public void loginEmployeeTest() {
        // This test must running after add employee test
        System.out.println("loginEmployeeTest starting....");
        String body = "{\r\n" + //
                "    \"email\": \"" + EMAIL + "\",\r\n" + //
                "    \"password\": \"" + PASSWORD + "\"\r\n" + //
                "}";
        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/login");

        token = res.jsonPath().getString("[0].token");
        Assert.assertNotNull(token);
        System.out.println(token);
    }

    @Test(dependsOnMethods = {"loginEmployeeTest"})
    public void deleteEmployeeTest() {
        // This test must running after login employee test
        System.out.println("deleteEmployeeTest starting....");
        RestAssured
                .given()
                .header(new Header("Authorization", "Bearer " + token))
                .log()
                .all()
                .when()
                .delete("https://whitesmokehouse.com/webhook/employee/delete")
                .then()
                .statusCode(200)
                .body("[0].success", Matchers.equalTo(true));
    }
}
