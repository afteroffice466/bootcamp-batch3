package cucumber.runners;

import org.testng.annotations.AfterSuite;

import cucumber.helper.GenerateReport;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources",
    glue = "cucumber.definitions",
    plugin = {"pretty", "json:target/cucumber.json"},
    monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
        System.out.println("adjkhiuqehfblqefbeqkfb");
    }
}
