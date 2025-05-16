package restassured;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class EmployeeTest {
    public String token;

    public final String EXPECT_EMAIL = "test2@test.com";
    public final String EXPECT_NAME = "fifi noela";

    @BeforeSuite
    public void login() {
        // Implement body as string
        String body = "{\r\n" + //
                "    \"email\": \"" + EXPECT_EMAIL + "\",\r\n" + //
                "    \"password\": \"test\"\r\n" + //
                "}";

        // Hit api with rest assured and save response to variable res
        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log()
                .all()
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/login");

        // save token to global variable token
        token = res.jsonPath().getString("[0].token");

        // assert token
        Assert.assertNotNull(token);
        System.out.println(token);
    }

    @Test
    public void getCurrentEmployeeTest() {
        // hit endpoint with token with get method
        RestAssured
                .given()
                .header(new Header("Authorization", "Bearer " + token))
                .log()
                .all()
                .when()
                .get("https://whitesmokehouse.com/webhook/employee/get")
                .then()
                .statusCode(200)
                .body("[0].email", Matchers.containsString(EXPECT_EMAIL))
                .and()
                .body("[0].full_name", Matchers.containsString(EXPECT_NAME));
    }
}
