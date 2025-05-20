package e2e;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteEmployeeTest {
    @Test(dependsOnGroups = "assertEmployeeUpdate")
    public void DeleteEmployee() {
        // This test must running after login employee test
        System.out.println("deleteEmployeeTest starting....");
        Response res = RestAssured
                .given()
                .header(new Header("Authorization", "Bearer " + StaticVar.token))
                .log()
                .all()
                .when()
                .delete(StaticVar.BASE_URL + "/employee/delete");

        assert res.getStatusCode() == 200 : "Status code add employee must be 200";
        assert res.jsonPath().getBoolean("[0].success") == true : "Delete not success";
    }

    @Test(dependsOnMethods = "DeleteEmployee")
    public void searchEmployee() {
        // This test must running after add employee test
        System.out.println("searchEmployee starting....");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .log()
                .all()
                .when()
                .get(StaticVar.BASE_URL + "/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/"
                        + StaticVar.employee.getFullName());

        System.out.println(res.asPrettyString());

        assert res.getStatusCode() == 200 : "Status code search employee must be 200";
        assert res.jsonPath().getString("[0].query").equals(StaticVar.employee.getFullName()) : "Query must be same as fullname";
        assert !res.jsonPath().getString("[0].result.full_name").contains(StaticVar.employee.getFullName())
                : "Fullname not expected, must not contains " + StaticVar.employee.getFullName();
    }

    @Test(dependsOnMethods = "DeleteEmployee")
    public void getAllEmployee() {
        // This test must running after add employee test
        System.out.println("getAllEmployee starting....");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .log()
                .all()
                .when()
                .get(StaticVar.BASE_URL + "/employee/get_all");

        System.out.println(res.asPrettyString());

        assert res.getStatusCode() == 200 : "Status code get all employee must be 200";

        int i = 0;
        boolean dataIsFound = false;
        while (true) {
            String fullName = res.jsonPath().getString("[" + i + "].full_name");
            if (fullName == null) {
                break;
            }
            if (fullName.equals(StaticVar.employee.getFullName())) {
                dataIsFound = true;
            }
            i++;
        }
        assert !dataIsFound : "Data found in system";
    }
}
