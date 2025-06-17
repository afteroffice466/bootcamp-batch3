package selenium_page_factory.test_suite_cucumber.runner;

import org.testng.annotations.AfterSuite;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import selenium_page_factory.test_suite_cucumber.helper.GenerateReport;

@CucumberOptions(
    features = "src/test/resources/selenium_page_factory",
    glue = "selenium_page_factory.test_suite_cucumber.definitions",
    plugin = {"pretty", "json:target/cucumber.json"},
    monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @AfterSuite
    public void after_suite() {
        GenerateReport.generateReport();
    }
}
