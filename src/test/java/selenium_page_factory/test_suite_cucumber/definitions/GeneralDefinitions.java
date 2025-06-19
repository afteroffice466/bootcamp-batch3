package selenium_page_factory.test_suite_cucumber.definitions;

import java.time.Duration;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class GeneralDefinitions {
    @Given("Open {string}")
    public void openUrl(String url) {
        Hook.webDriver.get(url);
        Hook.webDriver.manage().window().fullscreen();
        Hook.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("Delay for {int} seconds")
    public void delay(int sec) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(sec));
    }
}
