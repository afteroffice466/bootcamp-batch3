package api_test_integrate_db;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demo.testng.program.memory_cache.EmployeeApi;
import com.demo.testng.program.pojo.User;

import io.restassured.RestAssured;

public class EmployeeLoginApiTest {
    @BeforeSuite
    public void setUp() {
        System.out.println("Setting up the test suite...");
        // Set up database then set data from database to cache
        try {
            User.applyDataFromDatabase();
        } catch (Exception e) {
            Assert.fail("Error setting up database: " + e.getMessage());
        }
    }

    @DataProvider(name = "validUserData")
    public Object[][] validUserData() {
        // Create a 2D array to hold the data
        Object[][] data = new Object[EmployeeApi.validUsers.size()][1];
        for (int i = 0; i < EmployeeApi.validUsers.size(); i++) {
            if (EmployeeApi.validUsers.get(i).getUserGroup().equals("VALID_USER")) {
                data[i][0] = EmployeeApi.validUsers.get(i);
            }
        }
        return data;
    }

    @DataProvider(name = "invalidUserData")
    public Object[][] invalidUserData() {
        // Create a 2D array to hold the data
        Object[][] data = new Object[EmployeeApi.invalidUsers.size()][1];
        for (int i = 0; i < EmployeeApi.invalidUsers.size(); i++) {
            if (EmployeeApi.invalidUsers.get(i).getUserGroup().equals("INVALID_USER")) {
                data[i][0] = EmployeeApi.invalidUsers.get(i);
            }
        }
        return data;
    }

    @Test(dataProvider = "validUserData")
    public void loginWithValidUserTest(User user) {
        System.out.println("Running login test...");
        System.out.println(user);
        RestAssured
                .given()
                .headers(new HashMap<String, String>())
                .contentType("application/json")
                .body(user)
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/login")
                .then()
                .log().all()
                .and()
                .statusCode(200)
                .and()
                .body("token", Matchers.notNullValue());
    }

    @Test(dataProvider = "invalidUserData")
    public void loginWithInvalidUserTest(User user) {
        System.out.println("Running login test...");
        System.out.println(user);
        RestAssured
                .given()
                .headers(new HashMap<String, String>())
                .contentType("application/json")
                .body(user)
                .when()
                .post("https://whitesmokehouse.com/webhook/employee/login")
                .then()
                .log().all()
                .and()
                .statusCode(200)
                .and()
                .body("result", Matchers.equalTo("nok"))
                .and()
                .body("reason", Matchers.equalTo("password not match"));
    }
}
