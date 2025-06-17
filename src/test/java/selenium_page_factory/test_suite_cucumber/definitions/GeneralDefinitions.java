package selenium_page_factory.test_suite_cucumber.definitions;

import java.time.Duration;

import io.cucumber.java.en.Then;

public class GeneralDefinitions {
    @Then("Delay for {int} seconds")
    public void doCheckoutFlow(int sec) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(sec));
    }
}
